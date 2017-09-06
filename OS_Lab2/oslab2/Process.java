package oslab2;

import java.util.ArrayList;

public class Process {
	enum Status{READY, RUN, WAIT, TERMINATE};
	int id;
	Status status;
	Specs specs;
	int remainingCPU;
	int turnaroundTime;
	int blockedTime;
	int waitTime;
	int burst;
	int cycleStart;
	Boolean paused;	
	//private READY = "";
	
	String toString(Status type) throws Exception{
		String res = null;
		switch(type){
		case READY:
			res = "ready";
			break;
		case RUN:
			res = "running";
			break;
		case WAIT:
			res = "blocked";
			break;
		case TERMINATE:
			res = "terminated";
			break;
		default:
			throw new Exception("Error: could not parse status of process: " + type);
		}
		return res;
	}
	
	public Process (int id, Specs specs){
		this.id = id;
		this.specs = specs;
		remainingCPU = specs.C;
		turnaroundTime = 0;
		blockedTime = 0;
		waitTime = 0;
		burst = 0;
		status = Status.WAIT;
		cycleStart = specs.A;
		paused = false;
	}
	
	Boolean aboutToTerminate (int clock){
		int cpuBurst = clock - cycleStart;
		return remainingCPU - cpuBurst <= 0;
	}
	
	int run(int clock, int cpuBurst, int readyTimer) throws Exception{
		if(status.equals(Status.READY) && !paused){
			waitTime += clock - cycleStart;
		}
		else{
			throw new Exception("Error: not in Ready state, status=" + status);
		}
		cycleStart = clock;
		status = Status.RUN;
		turnaroundTime = clock - specs.A;
		burst = cpuBurst;
		int act_burst = burst;
		if (readyTimer > 0)
			act_burst = Math.min(readyTimer, burst);
		return clock + Math.min(act_burst, remainingCPU);
	}
	
	int unpause(int clock, int readyTimer) throws Exception{
		if(status.equals(Status.READY) && paused)
			waitTime += clock - cycleStart;
		else{
			throw new Exception("Error: not in Ready state, status=" + status);
		}
		paused = false;
		cycleStart = clock;
		status = Status.RUN;
		turnaroundTime = clock - specs.A;
		int act_burst = burst;
		if (readyTimer > 0)
			act_burst = Math.min(readyTimer, burst);
		return clock + Math.min(act_burst, remainingCPU);
	}
	
	int wait(int clock, int waitBurst) throws Exception{
		if(!status.equals(Status.RUN)){
			throw new Exception("Error: not in Ready state, status=" + status);
		}
		int cpuBurst = clock - cycleStart;
		remainingCPU = Math.max(remainingCPU - cpuBurst, 0);
		if(remainingCPU == 0){
			status = Status.TERMINATE;
			waitBurst = 0;
		}
		else if(waitBurst > 0){
			status = Status.WAIT;
			burst = waitBurst;
		}
		else if(waitBurst == 0){
			burst -= clock - cycleStart;
			status = Status.READY;
			paused = true;
		}
		cycleStart = clock;
		turnaroundTime = clock - specs.A;
		return clock + waitBurst;
	}
	
	void ready(int clock) throws Exception{
		if(status.equals(Status.WAIT)){
			blockedTime += clock - cycleStart;
		}
		else{
			throw new Exception("Error: not in Waiting state, status=" + status);
		}
		cycleStart = clock;
		status = Status.READY;
		turnaroundTime = clock - specs.A;
		burst = 0;
	}
	
	int remainingBurst(int clock){
		int remBurst = 0;
		if(status.equals(Status.WAIT) || status.equals(Status.RUN)){
			remBurst = burst - clock + 1 + cycleStart;
		}
		else if(paused){
			remBurst = burst;
		}
		return remBurst;
	}
	
	int finishTime() throws Exception{
		if(!status.equals(Status.TERMINATE)){
			throw new Exception("Error: not in Terminate state, status=" + status);
		}
		return specs.A + turnaroundTime;
	}
	
	void print(){
		int[] numArr = {specs.A, specs.B, specs.C, specs.IO};
		
		System.out.println("\nProcess " + Integer.toString(id) + ":");
		String line = "(A,B,C,IO) = (";
		for(int i = 0; i < numArr.length; ++i){
			line += Integer.toString(numArr[i]) + (i < numArr.length - 1 ? " ":")");
		}
		System.out.println(line);
		System.out.println("\tFinishing time: " + Integer.toString(specs.A + turnaroundTime));
		System.out.println("\tTurnaround time: " + Integer.toString(turnaroundTime));
		System.out.println("\tI/O time: " + Integer.toString(blockedTime));
		System.out.println("\tWaiting time: " + Integer.toString(waitTime));
	}
	
	
	
}


