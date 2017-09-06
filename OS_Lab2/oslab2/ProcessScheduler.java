package oslab2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class ProcessScheduler {
	enum Method{FCFS, RR, LCFS, SRTN};
	
	Method method;
	ProcessQueue processQueue;
	Process runningProcess;
	static final int RRCycle = 2;
	static final String RandNumInputFile = "randomInts.txt";
	static ArrayList<Integer> RandNumList = new ArrayList<Integer>();
	private int randNumCounter;
	
	ProcessScheduler(Method method, ArrayList<Specs> specsList) throws FileNotFoundException{
		this.method = method;
		processQueue = new ProcessQueue(specsList);
		randNumCounter = 0;
		if(RandNumList.size() == 0)
			readRandNumFile(RandNumInputFile);
		runningProcess = null;
	}
	
	static void readRandNumFile(String RandNumInputFile) throws FileNotFoundException{
		int num;
		File f = new File(RandNumInputFile);
		Scanner input = new Scanner(f);
		String line;
		while(input.hasNextLine()){
			line = input.nextLine();
			line = line.trim();
			if(line.equals(""))
				continue;
			num = Integer.parseInt(line);
			RandNumList.add(num);
		}
	}
	
	int getBurst(int maxNum){
		int X = RandNumList.get(randNumCounter);
		randNumCounter = (randNumCounter+1) % RandNumList.size();
		return 1 + X % maxNum;
	}
	
	int getCPUBurst(){
		int burst = getBurst(runningProcess.specs.B);
		return burst;
	}
	
	int getWaitBurst(int clock){
		if(runningProcess.aboutToTerminate(clock))
			return 0;
		else{
			return getBurst(runningProcess.specs.IO);
		}
	}
	
	Process findNextProcess(int clock){
		Process nextRunProcess = null;
		if(method.equals(Method.SRTN)){
			nextRunProcess = processQueue.findShortestRem(clock);
		}
		else if(method.equals(Method.RR)){
			nextRunProcess = processQueue.findFirst(clock);
		}
		else if(method.equals(Method.FCFS)){
			nextRunProcess = processQueue.findFirst(clock);
		}
		else if(method.equals(Method.LCFS)){
			nextRunProcess = processQueue.findLast(clock);
		}
		return nextRunProcess;
	}
	
	int processEvents(int firstEventIndex) throws Exception{
		int clock = processQueue.events.get(firstEventIndex).clock;
		Boolean mightPreempt = false;
		int i;
		
		for(i = firstEventIndex; i < processQueue.events.size(); ++i){
			ProcessQueue.Event event = processQueue.events.get(i);
			if(event.clock > clock){
				break;
			}
			if (event.type.equals(ProcessQueue.Event.Type.WAIT)) {
				if (event.procID != runningProcess.id)
					throw new Exception("Error: event process ID " + event.procID + "is not equal to running Process ID " + runningProcess.id);
				int sched_end = processQueue.wait(event.procID, event.clock, getWaitBurst(event.clock));
				if (sched_end > clock) {
					event = new ProcessQueue.Event(ProcessQueue.Event.Type.READY, runningProcess.id, sched_end);
					processQueue.addEvent(event);
				}
				runningProcess = null;
			}
			// If ready event is encountered, put blocked job into ready state
			else if (event.type.equals(ProcessQueue.Event.Type.READY)) {
				if (runningProcess != null && method == Method.RR && runningProcess.id == event.procID) {
					processQueue.wait(runningProcess.id, clock, 0);
					runningProcess = null;
				}
				else	
					processQueue.ready(event.procID, event.clock);
			}
			else{
				throw new Exception("Error: could not process event " + event + "event, type=" + event.type);
			}
		}
		int lastEventIndex = i;
		
		if(method == Method.SRTN){
			mightPreempt = true;
		}
		
		Process nextRunProcess = null;
		if (runningProcess == null || mightPreempt)
			nextRunProcess = findNextProcess(clock);

		// check if process needs to be preempted
		if (mightPreempt && runningProcess != null && nextRunProcess.id != runningProcess.id) {
			// If preempted, remove scheduled wait (block) event for this process
			ProcessQueue.Event event = null;
			for (int j = lastEventIndex; j < processQueue.events.size(); ++j) {
				event = processQueue.events.get(j);
				if (event.procID == runningProcess.id)
					break;
			}
			if (event == null || event.type != ProcessQueue.Event.Type.WAIT)
				throw new Exception("Error: ");
			processQueue.events.remove(event);
			// Pause this process and put it into ready state
			processQueue.wait(runningProcess.id, clock, 0);
			runningProcess = null;
		}

		// Check if job needs to be run
		if (nextRunProcess != null && (runningProcess == null || runningProcess.id != nextRunProcess.id)) {
			runningProcess = nextRunProcess;
			int sched_end;
			int readyTimer = method == Method.RR ? RRCycle : 0;
			// Run or unpause a job
			if (runningProcess.paused) {
				sched_end = runningProcess.unpause(clock, readyTimer);
				if (method != Method.RR)
					processQueue.addEvent(new ProcessQueue.Event(ProcessQueue.Event.Type.WAIT, runningProcess.id, sched_end));
				else {
					int cpuBurst = runningProcess.burst;
					if (cpuBurst <= readyTimer)
						processQueue.addEvent(new ProcessQueue.Event(ProcessQueue.Event.Type.WAIT, runningProcess.id, sched_end));
					else
						processQueue.addEvent(new ProcessQueue.Event(ProcessQueue.Event.Type.READY, runningProcess.id, sched_end));
				}
			}
			else {
				int cpuBurst = getCPUBurst();
				sched_end = processQueue.run(nextRunProcess.id, clock, cpuBurst, readyTimer);
				if (method == Method.RR && cpuBurst > readyTimer)
					processQueue.addEvent(new ProcessQueue.Event(ProcessQueue.Event.Type.READY, runningProcess.id, sched_end));
				else
					processQueue.addEvent(new ProcessQueue.Event(ProcessQueue.Event.Type.WAIT, runningProcess.id, sched_end));
			}
		}

		return lastEventIndex;
	}
	
	void run(Boolean verbose) throws Exception{
		for (int i = 0, clock, prev_clock = 0; i < processQueue.events.size(); ) {
			if (verbose) {
				clock = processQueue.events.get(i).clock;
				for (; prev_clock <= clock; ++prev_clock){
					processQueue.printStateBefore(prev_clock);
				}
			}
			// Process batch of events at a time
			i = processEvents(i);
		}
		if (!processQueue.allDone()){
			throw new Exception("Error: not all processes in Process Queue were processed");
		}
	}
	
	
	
}
