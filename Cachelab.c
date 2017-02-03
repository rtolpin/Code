/* 
 * cachelab.c - A cache simulator that can replay traces of memory accesses
 *     and output statistics such as number of hits, misses, and
 *     evictions.  The replacement policy is LRU.
 *
 * Implementation and assumptions:
 *  1. Each load/store can cause at most one cache miss. (I examined the trace,
 *  the largest request I saw was for 8 bytes).
 *  2. Instruction loads (I) are ignored.
 *  3. data modify (M) is treated as a load followed by a store to the same
 *  address. Hence, an M operation can result in two cache hits, or a miss and a
 *  hit plus a possible eviction. 
 */
#include <getopt.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <assert.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <errno.h>
#include <stdbool.h>

//#define DEBUG_ON 
#define ADDRESS_LENGTH 64

/* Type: Memory address */
typedef unsigned long long int mem_addr_t;


/* 
 * printSummary - provides standard way for cache
 * simulator * to display its final hit and miss statistics
 */ 
void printSummary(int hits,  /* number of  hits */
				  int misses, /* number of misses */
				  int evictions); /* number of evictions */

/*
 * replayTrace - replays the given trace file against the cache 
 */
void replayTrace(char* trace_fn);

/* 
 * accessData - Access data at memory address addr. 
 * This is the one that you need to implement.
 */
void accessData(mem_addr_t addr);

/*
 * printUsage - Print usage info
 */
void printUsage(char* argv[]);


int s = 0; /* set index bits */
int b = 0; /* block offset bits */
int E = 0; /* associativity */
char* trace_file = NULL;

/* Counters used to record cache statistics */
int miss_count = 0;     /* cache miss */
int hit_count = 0;      /* cache hit */
int eviction_count = 0; /* A block is evicted from the cache */

typedef unsigned char byte_t;
typedef struct CacheBlock
{
	unsigned long long tag;
	bool validBit;
	unsigned long requestID;
	byte_t* data;
} CacheBlock;

typedef struct CacheSet
{
	unsigned long totNumBlocks;
	unsigned long occNumBlocks;
	CacheBlock* blocks;
} CacheSet;

typedef struct CacheByteID
{
	unsigned long long tag;
	unsigned long setID;
	unsigned long byteOffset;
} CacheByteID;

CacheSet* cache = NULL;
unsigned long request_id = 0U;


void setCacheBlock(CacheBlock* block_addr)
{
	int numBytes = (1 << b);
	block_addr->data = (byte_t*)malloc(numBytes * sizeof(byte_t));
	block_addr->tag = 0L;
	block_addr->validBit = false;
	block_addr->requestID = 0;
}

void freeCacheBlock(CacheBlock* block_addr)
{
	if (block_addr->data)
		free(block_addr->data);
	block_addr->tag = 0L;
	block_addr->validBit = false;
	block_addr->requestID = 0;
}

void setCacheSet(CacheSet* set_addr)
{
	set_addr->totNumBlocks = E;
	set_addr->occNumBlocks = 0;
	set_addr->blocks = (CacheBlock*)malloc(E * sizeof(CacheBlock));
	for (int i = 0; i < E; ++i)
		setCacheBlock(&set_addr->blocks[i]);
}

void freeCacheSet(CacheSet* set_addr)
{
	set_addr->totNumBlocks = 0;
	set_addr->occNumBlocks = 0;
	for (int i = 0; i < E; ++i)
		freeCacheBlock(&set_addr->blocks[i]);
	free(set_addr->blocks);
}


void destroyCache()
{
	unsigned long numSets = (1 << s);
	if (cache) {
		for (unsigned i = 0U; i < numSets; ++i)
			freeCacheSet(&cache[i]);
		free(cache);
		cache = NULL;
	}
}

void createCache()
{
	unsigned long numSets = (1 << s);
	cache = (CacheSet*)malloc(numSets * sizeof(CacheSet));
	for (unsigned i = 0U; i < numSets; ++i)
		setCacheSet(&cache[i]);
}

void parseAddress(mem_addr_t addr, CacheByteID* cache_id)
{
	int i;
	mem_addr_t mask_s = 0;
	//mem_addr_t mask_t = 0;
	mem_addr_t mask_b = 0;
	//int t = ADDRESS_LENGTH - (b+s);	

	for(i = 0; i < s; ++i)
		mask_s |= (1 << (b+i));
	
	cache_id->setID= ((addr & mask_s) >> b);

	for(i = 0; i < b; ++i)
		mask_b |= (1 << i);

	cache_id->byteOffset = (addr & mask_b);

	//for(i = 0; i < t; ++i)
	//	mask_t |= (1 <<(b+s+i));

	cache_id->tag = addr >> (b+s);
	
}

byte_t* readByte(const CacheByteID* cache_id)
{
	CacheSet* set_p = &cache[cache_id->setID];
	unsigned long i;

	for(i = 0; i < set_p->totNumBlocks; ++i){
		if(set_p->blocks[i].validBit && set_p->blocks[i].tag ==cache_id->tag){
			set_p->blocks[i].requestID = ++request_id;
			return &set_p->blocks[i].data[cache_id->byteOffset];
		}
	}

	return NULL;

}


void addNewTag(const CacheByteID* cache_id)
{
	CacheSet* set_p = &cache[cache_id->setID];
	unsigned long i;
	unsigned long min_rID = ULONG_MAX;
	unsigned long min_rID_block;

	for(i = 0; i < set_p->totNumBlocks; ++i){
		if(!set_p->blocks[i].validBit){
			set_p->blocks[i].tag = cache_id->tag;
			set_p->blocks[i].validBit = true;
			rerfreshCacheData(cache_id, &set_p->blocks[i]);
			return;
		}
		else if(set_p->blocks[i].requestID < min_rID){
			min_rID = set_p->blocks[i].requestID;
			min_rID_block = i;	
		}
	}
	set_p->blocks[min_rID_block].tag = cache_id->tag;
	rerfreshCacheData(cache_id, &set_p->blocks[min_rID_block]);
	++eviction_count;
}



/* 
 * accessData - Access data at memory address addr.
 *   If it is already in cache, increast hit_count
 *   If it is not in cache, bring it in cache, increase miss count.
 *   Also increase eviction_count if a line is evicted.
 * 
 *   If you need to evict a line (i.e. block) apply least recently used 
 *   replacement policy.
 */
void accessData(mem_addr_t addr)
{
	CacheByteID cache_id;
	parseAddress(addr, &cache_id);
	byte_t* byteData = readByte(&cache_id);
	if (byteData != NULL) {
		++hit_count;
	}
	else {
		++miss_count;
		addNewTag(&cache_id);
		byteData = readByte(&cache_id);
	}
  
}







/*
 * main - Main routine 
 */
int main(int argc, char* argv[])
{
    char c;

    /* Do NOT modify anything from this point till the following comment */
    while( (c=getopt(argc,argv,"s:E:b:t:vh")) != -1){
        switch(c){
        case 's':
            s = atoi(optarg);
            break;
        case 'E':
            E = atoi(optarg);
            break;
        case 'b':
            b = atoi(optarg);
            break;
        case 't':
            trace_file = optarg;
            break;
        case 'h':
            printUsage(argv);
            exit(0);
        default:
            printUsage(argv);
            exit(1);
        }
    }

    /* Make sure that all required command line args were specified */
    if (s == 0 || E == 0 || b == 0 || trace_file == NULL) {
        printf("%s: Missing required command line argument\n", argv[0]);
        printUsage(argv);
        exit(1);
    }
    /* From here you can make any modification, 
     * except removing the call to replayTrace */
    
	createCache();
    
    /* Do not remove this line as it is the one calls your cache access function */
    replayTrace(trace_file);
    
  
    /* Do not modify anything from here till end of main() function */
    printSummary(hit_count, miss_count, eviction_count);
	destroyCache();
    return 0;
}


/****** Do NOT modify anything below this point ******/

/* 
 * printSummary - Summarize the cache simulation statistics. Student cache simulators
 *                must call this function in order to be properly autograded. 
 */
void printSummary(int hits, int misses, int evictions)
{
    printf("hits:%d misses:%d evictions:%d\n", hits, misses, evictions);
    
}

/*
 * printUsage - Print usage info
 */
void printUsage(char* argv[])
{
    printf("Usage: %s [-hv] -s <num> -E <num> -b <num> -t <file>\n", argv[0]);
    printf("Options:\n");
    printf("  -h         Print this help message.\n");
    printf("  -s <num>   Number of set index bits.\n");
    printf("  -E <num>   Number of blocks per set (i.e. associativity).\n");
    printf("  -b <num>   Number of block offset bits.\n");
    printf("  -t <file>  Trace file.\n");
    printf("\nExamples:\n");
    printf("  linux>  %s -s 4 -E 1 -b 4 -t ls.trace\n", argv[0]);
    exit(0);
}

/*
 * replayTrace - replays the given trace file against the cache 
 */
void replayTrace(char* trace_fn)
{
    char buf[1000];
    mem_addr_t addr=0;
    unsigned int len=0;
    FILE* trace_fp = fopen(trace_fn, "r");

    if(!trace_fp){
        fprintf(stderr, "%s: %s\n", trace_fn, strerror(errno));
        exit(1);
    }

    while( fgets(buf, 1000, trace_fp) != NULL) {
        if(buf[1]=='S' || buf[1]=='L' || buf[1]=='M') {
            sscanf(buf+3, "%llx,%u", &addr, &len);

            accessData(addr);

            /* If the instruction is R/W then access again */
            if(buf[1]=='M')
                accessData(addr);
            
        }
    }

    fclose(trace_fp);
}
