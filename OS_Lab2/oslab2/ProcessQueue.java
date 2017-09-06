package oslab2;

import java.util.ArrayList;

import oslab2.Process.Status;

public class ProcessQueue {
	private int latestID;
	private int rrID;
	ArrayList<Process> processes;
	ArrayList<Event> events;
	
	
	static class Event{
		enum Type{READY, WAIT};
		
		Type type;
		int procID;
		int clock;
		
		Event(Type type, int procID, int clock){
			this.type = type;
			this.procID = procID;
			this.clock = clock;
		}
	}
	
	
	ProcessQueue(ArrayList<Specs> specsList) {
		processes = new ArrayList<Process>();
		latestID = -1;
		rrID = -1;
		events = new ArrayList<Event>();
		for(Specs specs : specsList){
			add(specs);
		}
	}
	
	void add(Specs specs){
		latestID++;
		Process process = new Process(latestID, specs);
		processes.add(process);
		Event event = new Event(Event.Type.READY, latestID, specs.A);
		addEvent(event);
	}
	
	void addEvent(Event event){
		boolean added = false;
		for(int i = 0; i < events.size(); ++i){
			if(!added){
				if(event.clock < events.get(i).clock){
					events.add(i, event);
					added = true;
				}
			}
		}
		if(!added){
			events.add(event);
		}
	}
	
	int run(int procID, int clock, int cpuBurst, int readyTimer) throws Exception{
		Process process = findByID(procID);
		if(process == null)
			throw new Exception("Error: process is undefined");
		int schedEnd = process.run(clock, cpuBurst, readyTimer);
		return schedEnd;
	}
	
	int wait(int procID, int clock, int waitBurst) throws Exception{
		Process process = findByID(procID);
		if(process == null)
			throw new Exception("Error: process is undefined");
		int schedEnd = process.wait(clock, waitBurst);
		return schedEnd;
	}
	
	void ready(int procID, int clock) throws Exception{
		Process process = findByID(procID);
		if(process == null)
			throw new Exception("Error: process is undefined");
		process.ready(clock);
	}
	
	Process findByID(int id){
		for(Process process: processes){
			if(process.id == id){
				return process;
			}
		}
		return null;
	}
	
	Process findFirst(int clock){
		Process firstProcess = null;
		for(Process process : processes){
			if(process.specs.A <= clock && process.status.equals(Process.Status.READY) 
					&& (firstProcess == null || process.cycleStart < firstProcess.cycleStart)){
				firstProcess = process;
			}
		}
		return firstProcess;
	}
	
	Process findLast(int clock){
		Process lastProcess = null;
		for(Process process : processes){
			if(process.specs.A <= clock && process.status.equals(Process.Status.READY) && (lastProcess == null || process.cycleStart > lastProcess.cycleStart)){
				lastProcess = process;
			}
		}
		return lastProcess;
	}
	
	Process findShortestRem(int clock){
		Process srProcess = null;
		int minRemCPU = 1000000000;
		int remCPU;
		for(Process process : processes){
			if(process.specs.A <= clock){
				if(process.status.equals(Process.Status.RUN)){
					remCPU = process.remainingCPU - clock + process.cycleStart + 1;
				}
				else if(process.status.equals(Process.Status.READY)){
					remCPU = process.remainingCPU;
				}
				else{
					remCPU = 1000000001;
				}
				if(remCPU < minRemCPU){
					srProcess = process;
					minRemCPU = remCPU;
				}
			}
		}
		return srProcess;
	}
	
	Process findNextRR (int clock){
		for(int i = 0; i < processes.size(); ++i){
			rrID = (rrID + 1) % processes.size();
			Process process = processes.get(rrID);
			if(process.specs.A <= clock && process.status.equals(Process.Status.READY)){
				return process;
			}
		}
		return null;
	}
	
	Boolean allDone(){
		for(Process process : processes){
			if(!process.status.equals(Process.Status.TERMINATE)){
				return false;
			}
		}
		return true;
	}
	
	void printProcesses(){
		for(Process process : processes){
			process.print();
		}
	}
	
	
	void printStateBefore(int clock) throws Exception{
		String line = new String("Before cycle " + Integer.toString(clock) + ": \t");
		for (Process process : processes) {
			if (clock > process.specs.A) {
				line += process.toString(process.status) + " " + Integer.toString(process.remainingBurst(clock)) + "\t";
				if (process.status.equals(Process.Status.READY))
					line += "\t";
			}
			else{
				line += "unstarted 0\t";
			}
		}
		System.out.println(line);
	}

	// Print run summary
	void printSummary() throws Exception {
		int finishTime = 0, totCPUTime = 0, totIOTime = 0, totTurnaroundTime = 0, totalWaitTime = 0;
		for (Process process : processes) {
			finishTime = Math.max(finishTime, process.finishTime());
			totCPUTime += process.specs.C;
			totIOTime += process.blockedTime;
			totTurnaroundTime += process.turnaroundTime;
			totalWaitTime += process.waitTime;
		}
		System.out.println("\nSummary Data:");
		System.out.println("\tFinishing time: " + Integer.toString(finishTime));
		System.out.println("\tCPU Utilization: " + Double.toString(totCPUTime*1./finishTime));
		System.out.println("\tI/O Utilization: " + Double.toString(totIOTime*1./finishTime));
		System.out.println("\tThroughput: " + Double.toString(processes.size() * 100./finishTime) +
				" processes per hundred cycles");
		System.out.println("\tAverage turnaround time: " + Double.toString(totTurnaroundTime*1./processes.size()));
		System.out.println("\tAverage waiting time: " + Double.toString(totalWaitTime*1./processes.size()));
	}
	
	
	
	
	
	
	
	
	
	
}
