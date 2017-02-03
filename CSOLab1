#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>


typedef struct _element
       { int x; 
	 struct _element * next;} element;

void insertion_sort(int *, int);
void find_prime(int, int);
void flipping(unsigned int);
void file_histogram(char *);
void file2upper(char *);
void file_encrypt(char *);


int main(int argc, char * argv[])
{
  int option = 0;
  int i, j;
  int * list;
  
  if(argc < 2 )
  {
     fprintf(stderr,"Usage: lab1 num [input]\n");
     fprintf(stderr,"num: 1, 2, 3, 4, 5, or 6\n");
     exit(1);
  }
  
  option = atoi(argv[1]);
  
  switch(option)
  {
    case 1: if(argc != 3)
	    {
	      fprintf(stderr,"Usage: lab1 1 num\n");
	      fprintf(stderr,"num: number of elements of the array to be sorted\n");
	      exit(1);
	    }
	    j = atoi(argv[2]);
	    list = (int *)malloc(j*sizeof(int));
	    if(!list)
	    {
	      fprintf(stderr,"Cannot allocate list in option 1\n");
	      exit(1);
	    }
	    
	    /* Gnerate random numbers in the range [0, 1000) */
	    for(i = 0; i < j; i++)
	      list[i] = rand()% 1000;
	    
	    /* Print unsorted array */
	    printf("The unsorted array:\n");
	    for(i = 0; i < j; i++) 
	      printf("%d ", list[i]);
	    printf("\n");
	    
	    insertion_sort(list, j);
	    
	    break;
	    
	    
    case 2: if(argc != 4)
	    {
	      fprintf(stderr,"Usage: lab1 2 x y\n");
	      fprintf(stderr,"x and y: positive integers and x < y\n");
	      exit(1);
	    }
	    i = atoi(argv[2]);
	    j = atoi(argv[3]);
	    
	    find_prime(i, j);
	    
	    break;

	    
    case 3: if(argc != 3)
	    {
	      fprintf(stderr,"Usage: lab1 3 num\n");
	      fprintf(stderr,"num: unsigned intger number\n");
	      exit(1);
	    }
	    
	    flipping( atof(argv[2]));
	    
	    break; 
	    
	    
    case 4: if(argc != 3)
	    {
	      fprintf(stderr,"Usage: lab1 4 filename\n");
	      exit(1);
	    }
	    
	    file_histogram(argv[2]);
	    
	    break;

	    
    case 5: if(argc != 3)
	    {
	      fprintf(stderr,"Usage: lab1 5 filename\n");
	      exit(1);
	    } 
	    
	    file2upper(argv[2]);
	    
	    break;

	    
    case 6: if(argc != 3)
	    {
	      fprintf(stderr,"Usage: lab1 46filename\n");
	      exit(1);
	    }
	    
	    file_encrypt(argv[2]);
	    
	    break;	      
	    
    default: fprintf(stderr, "You entered an invalid option!\n");
	     exit(1);
  }
  
  return 0;
}


void insertion_sort(int *list, int num)
{
  int i, j;

  for(i = 0; i < num; ++i){
	j = i;
	while(j > 0 && list[j-1] > list[j]){
		int temp = list[j-1];
		list[j-1] = list[j];
		list[j] = temp;
		--j;
	}
  }

  for(i = 0; i < num; ++i){
	if(i < num-1){
	   printf("%d, ", list[i]);
	}
	else{
	   printf("%d\n", list[i]);
	}
   }
}
	
  
  
  
}


void find_prime(int x, int y)
{
  int i,j;
  bool isPrime;
  
  if(x >= y){
    return 1;
  }

  for(i = x; i < y; ++i){
	j = 2;
	isPrime = true;
	while(j<i){
	  if(i % j == 0){
	     isPrime = false;
	     break;
  	  }
	  ++j;
	}
	if(isPrime)
	  printf("%d ", i);
  }
  printf("\n");
}


void flipping(unsigned int num)
{
   int reverseNum;

   while(num != 0){
	reverseNum *= 10;
	reverseNum += num%10
	num = num/10;
   }

   printf("%d", reverseNum);

}


void file_histogram(char *filename)
{
  FILE *fp;
  char fileString[1024];
  fp = fopen(filename, "r");
  
  if(!fp)
    printf("Could not open file %s\n", filename);

  if(fscanf(fp, "%s", fileString) != 1)
    printf("Could not read file %s: should contain a string\n", filename);

  int counter['z'-'a'+1];
  memset(counter, 0, sizeof(counter));
  const char* str = fileString;
  const char* p;
  for(p = str; *p != '\0'; ++p){
	if(*p >= 'a' && *p <= 'z')
	  counter[*p-'a'] += 1;
  }
  int i;
  for(i = 0; i < sizeof(counter)/sizeof(*counter); ++i){
    if(counter[i] > 0)
      printf("%c: %d\n", (char)('a'+i), counter[i]);
  }
  
}


void file2upper(char *filename)
{
  FILE *fp;
  char fileString[1024];
  int dist = 'A' - 'a';
  
  fp = fopen(filename, "r");

  if(!fp)
    printf("Could not open file %s\n", filename);

  if(fscanf(fp, "%s", fileString) != 1)
    printf("Could not read file %s: should contain a string\n", filename);

  char* upp_str = malloc(strlen(fileString)+1);
  int i;
  for(i = 0; i < strlen(fileString); ++i){
    upp_str[i] = (char)(fileString[i] + dist);
  }
  upp_str[i] = '\0';
  printf("%s%s\n", filename, upp_str);
  
}


void file_encrypt(char * filename)
{
  FILE *fp;
  char fileString[1024];

  fp = fopen(filename, "r");

  if(!fp)
    printf("Could not open file %s\n", filename);

  if(fscanf(fp, "%s", fileString) != 1)
    printf("Could not read file %s: should contain a string\n", filename);

  for(int i = 0; i < strlen(fileString); ++i){
      int j = fileString[i] - 'a';
      j = j - 3;
      if(j < 0)
        j += 26;
      fileString[i] = (char) ('a'+j);
  }

  printf("%s\n", fileString);
   
}
