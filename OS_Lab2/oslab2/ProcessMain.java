package oslab2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.io.IOException;

public class ProcessMain {
	ArrayList<Specs> readInputs(BufferedReader br) throws IOException{
		String line;
		ArrayList<Specs> specsList = new ArrayList<Specs>();
		int A,B,C,IO;
		while((line = br.readLine()) != null){
			line = line.trim();
			if(line.equals("")){
				continue;
			}
			String[] tokens = line.split(" +");
			int numProcessSpecs = Integer.parseInt(tokens[0]);
			if(tokens.length != (numProcessSpecs * 4 + 1)){
				throw new IOException("Invalid line:\n" + line);
			}
			for (int i = 1; i < tokens.length;) {
				A = Integer.parseInt(tokens[i]); 
				++i;
				B = Integer.parseInt(tokens[i]); 
				++i;
				C = Integer.parseInt(tokens[i]); 
				++i;
				IO = Integer.parseInt(tokens[i]); 
				++i;
				specsList.add(new Specs(A, B, C, IO));
			}
		}
		Integer[] spIndex = new Integer[specsList.size()];
		for (int i = 0; i < spIndex.length; ++i){
			spIndex[i] = i;
		}
		class sortRankIndex implements Comparator<Integer>{
			public int compare(Integer ai, Integer bi) {
				Specs a = specsList.get(ai);
				Specs b = specsList.get(bi);
				if (a.A == b.A){
					return ai - bi;
				}
				else if (a.A < b.A){
					return -1;
				}
				else{
					return 1;
				}
			}
		}
		//sortRankIndex sortR = new sortRankIndex();
		Arrays.sort(spIndex, new sortRankIndex());
		ArrayList<Specs> sorted_specsList = new ArrayList<Specs>();
		for (int i = 0; i < spIndex.length; ++i)
			sorted_specsList.add(specsList.get(spIndex[i]));
		return sorted_specsList;
	}
	
	public static void main(String[] args) throws Exception {
		Boolean verbose = false;
		String inputFile = null;
		for (int i = 0; i < args.length; ++i) {
			if (args[i].equals("--verbose"))
				verbose = true;
			else
				inputFile = new String(args[i]);
		}
		ProcessMain jmain = new ProcessMain();
		ArrayList<Specs> processSpecsList = null;
		// Read all job specs
		try (BufferedReader br = new BufferedReader (new FileReader(inputFile))) {
			processSpecsList = jmain.readInputs(br);
		} catch (IOException exc) {
			System.err.println("Input Error" + exc);
			return;
		}
		ProcessScheduler.Method[] allMethods = {ProcessScheduler.Method.FCFS, ProcessScheduler.Method.RR,
				ProcessScheduler.Method.LCFS, ProcessScheduler.Method.SRTN};
		String[] allMethodNames = {"FCFS", "RR", "LCFS", "SRTN"};
		// Run job scheduler for each of four scheduling methods
		for (int i = 1; i < 2; ++i) {
			ProcessScheduler.Method method = allMethods[i];
			// Create new scheduler
			ProcessScheduler js = new ProcessScheduler(method, processSpecsList);
			System.out.println("\n");
			// Run scheduler
			js.run(verbose);
			// Print results
			System.out.println("\nMethod Used: " + allMethodNames[i]);
			if (method == ProcessScheduler.Method.RR)
				System.out.println("RR quantum: " + Integer.toString(ProcessScheduler.RRCycle));
			System.out.println("Number of Processes: " + Integer.toString(processSpecsList.size()));
			js.processQueue.printProcesses();
			js.processQueue.printSummary();
		}
	}

}
