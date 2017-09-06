package oslab1;
/**
 * 
 */

/**
 * @author rt1384
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;



public class Linker {
	ArrayList<ObjModule> objModules;
	Map<ObjModule, Integer>baseAddresses;
	Map<String, Integer>symValues;
	
	
	public Linker(){
		objModules = new ArrayList<ObjModule>();  //length has same thing
		baseAddresses = new Hashtable<ObjModule, Integer>();
		symValues = new Hashtable<String, Integer>();
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		File f = new File(args[0]);
		//File f = new File("oslab1.txt");
		
		Linker linker = new Linker();
		
		
		linker.readInputFile(f);
		
		linker.firstPass();
		
		linker.secondPass();
		
			
	}
	
	void readInputFile(File f) throws FileNotFoundException{
	
		Scanner input = new Scanner(f);
		
		
		int numObjectModules = 0;
		try{
			numObjectModules = Integer.parseInt(input.next());
			input.nextLine();
		}catch(Exception e){
			System.out.println("Error: first input must be of type int: Count of Object Modules.");
		}
		
		
		for(int objID = 0; input.hasNextLine(); ++objID){ //Boolean
			Map<String, Integer> defnList = new Hashtable<String, Integer>();
			Map<Integer, String> useList = new Hashtable<Integer, String>();
			ArrayList<ObjModule.ProgCommand> progCommands = new ArrayList<ObjModule.ProgCommand>();
			int defnListLength;
			int useListLength;
			int progAddrLength;
			String line = "";
			String[] lineArray;
			
			ArrayList<Integer> usedAddresses = new ArrayList<Integer>();
			
			line = input.nextLine();
			line = line.trim();
			if(!line.equals("0")){
				lineArray = line.split(" ");
				defnListLength = Integer.parseInt(lineArray[0]);
				if(defnListLength != 0){
					for(int i = 1; i < (defnListLength*2); i+=2){
						String sym = lineArray[i];
						if(defnList.get(sym) != null){
							System.out.println("Error: Symbol " + sym + " cannot be multiply defined");
						}
						else{
							defnList.put(sym, Integer.parseInt(lineArray[i+1]));
						}
					}
				 }
			}
			
			line = input.nextLine();
			line = line.trim();
			if(!line.equals("0")){
				lineArray = line.split(" ");
				useListLength = Integer.parseInt(lineArray[0]);
				if(useListLength != 0){
					String value = lineArray[1];
					int usedAddr = Integer.MIN_VALUE;
					for(int i = 2; i < lineArray.length-1; ++i){
						usedAddr = Integer.parseInt(lineArray[i]);
						if(usedAddr == -1){ 
							value = lineArray[i+1];
							++i;
							continue; 
						}
						if (useList.get(usedAddr) != null){
							System.out.println("Error: Use list in object module " + objID + 
									" contains duplicates");
						}
						else{
							useList.put(usedAddr, value);
						}
							
					}
				}
			}
			
			line = input.nextLine();
			line = line.trim();
			if(!line.equals("0")){
				lineArray = line.split(" ");
				progAddrLength = Integer.parseInt(lineArray[0]);
				
				ObjModule o = new ObjModule();
				
				/*for(int i = 0; i < lineArray.length; ++i){
					if(lineArray[i] == " "){ }
				}*/
				//(progAddrLength * 2)
				//i+=2
				for(int i = 1; i < lineArray.length; i++){
					if(lineArray[i] == " "){ continue; }
					ObjModule.ProgCommand p = o.new ProgCommand();
					p.typeOfAddr = lineArray[i];
					p.addr = Integer.parseInt(lineArray[i+1]);
					progCommands.add(p);
				}
			 }	

			ObjModule objMod = new ObjModule(defnList, useList, progCommands);
			objModules.add(objMod);
			if(objModules.size() == numObjectModules)
				break;
		}
		
		if(objModules.size() != numObjectModules)
			System.out.println("Error: Number of Object Modules is less than specified");
			
		input.close();	
		
		int index = -1;
		
		for(int i = 0; i < objModules.size(); ++i){
			Map<String, Integer> currDefnList = objModules.get(i).defnList;
			Iterator<String> it = currDefnList.keySet().iterator();
			index = i;
			while(it.hasNext()){
				String currSym = it.next();
				for(int j = 0; j < objModules.size(); ++j){
					if(j == index){ continue; }
					ObjModule objMod2 = objModules.get(j);
					if(objMod2.defnList.isEmpty()){ continue; }
					if(objMod2.defnList.containsKey(currSym)){
						System.out.println("Error: Symbol " + currSym + " is multiply defined");
						objMod2.defnList.remove(currSym);
					}
				}
			}
		}
		
		for(int i = 0; i < objModules.size(); ++i){
			Integer usedAddr;
			String sym;
			Map<Integer, String> currUseList = objModules.get(i).useList;
			Iterator<Integer> itUL = currUseList.keySet().iterator();
			while(itUL.hasNext()){
				usedAddr = itUL.next();
				sym = currUseList.get(usedAddr);
				boolean isDefined = false; 
				for(int j = 0; j < objModules.size(); ++j){
						if(objModules.get(j).isDefinedSym(sym)){
							isDefined = true;
							break;
						} 
				}
				if(!isDefined){
					System.out.println("Error: Symbol " + sym + " is Used but not Defined");
					objModules.get(i).defnList.put(sym, 0);
				}
			}
		}
		
		for(int i = 0; i < objModules.size(); ++i){
			String sym;
			Map<String, Integer> currDefnList = objModules.get(i).defnList;
			Iterator<String> itDL = currDefnList.keySet().iterator();
			while(itDL.hasNext()){
				sym = itDL.next();
				if(!objModules.get(i).isUsedSym(sym)){
					System.out.println("Warning: Symbol " + sym + " is Defined but not Used");
				}
			}
		}
		
		for(int i = 0; i < objModules.size(); ++i){
			String sym;
			int defAddr;
			Map<String, Integer> currDefnList = objModules.get(i).defnList;
			Iterator<String> itDL = currDefnList.keySet().iterator();
			while(itDL.hasNext()){
				sym = itDL.next();
				defAddr = objModules.get(i).defnList.get(sym);
				if(defAddr > objModules.get(i).progCommands.size()){
					System.out.println("Error: Address " + defAddr + " appearing in Definition List cannot exceed size of Object Module");
					objModules.get(i).defnList.put(sym, 0);
				}
			}
		}	
		
		for(int i = 0; i < objModules.size(); ++i){
			int usedAddr;
			Map<Integer, String> currUseList = objModules.get(i).useList;
			Iterator<Integer> itUL = currUseList.keySet().iterator();
			while(itUL.hasNext()){
				 usedAddr = itUL.next();
				 if(usedAddr > objModules.get(i).progCommands.size()){
					 System.out.println("Error: Address " + usedAddr + " appearing in Use List cannot exceed size of Object Module");
					 objModules.get(i).useList.remove(usedAddr);
				 }
			}			
		}
		
		
	}

	
	
	void firstPass(){
		Set<String> symbols;
		String sym;
		int currValue;
		int maxAddrMachine = 0;
		
		for(ObjModule objMod : objModules){
			maxAddrMachine += objMod.progCommands.size();
		}
		
		baseAddresses.put(objModules.get(0), 0);
		
		for(int i = 1; i < objModules.size(); ++i){
			int progCommandsLen = objModules.get(i-1).progCommands.size();
			int prevBaseAddr = baseAddresses.get(objModules.get(i-1));
			baseAddresses.put(objModules.get(i), progCommandsLen + prevBaseAddr);
		}
		
		for(int i = 0; i < objModules.size(); ++i){
			ObjModule objMod = objModules.get(i);
			int baseAddr = baseAddresses.get(objMod);
			//for(int j = 0; j < objMod.defnList.size(); ++j){
			symbols = objMod.defnList.keySet();
			Iterator<String> it = symbols.iterator();
			while(it.hasNext()){
				sym = it.next();
				if(symValues.get(sym) != null){
					System.out.println("Symbol " + sym + " has mutiple definitions");
				}
				else{
					currValue = objMod.defnList.get(sym) + baseAddr;
					if(currValue > maxAddrMachine){
						System.out.println("Error: Absolute address " + currValue + " cannot exceed size of the Machine");
						currValue = 0;
					}
					symValues.put(sym, currValue);
				}
			}	
		}
		
	}
	
	void secondPass(){
		
		System.out.println("Symbol Table:");
		Iterator<String> it = symValues.keySet().iterator();
		while(it.hasNext()){
			String currSym = it.next(); 
			System.out.println(currSym + "=" + symValues.get(currSym));
		}
		
		//Map index to z
		//Map z to the address
		System.out.println("Memory Map:");
		for(ObjModule objMod : objModules){
			System.out.println("+" + baseAddresses.get(objMod));
			for(int i = 0; i < objMod.progCommands.size(); ++i){
				System.out.print(i + ":");
				String defSymb = objMod.getDefSymbol(i); //passes i as parameter
				if (defSymb != null){
					System.out.print(" " + defSymb + "		");
				}
				else{
					System.out.print("		");
				}
				ObjModule.ProgCommand prC = objMod.progCommands.get(i);
				System.out.print(prC.typeOfAddr + " " + prC.addr);
				if(prC.typeOfAddr.equals("R")){
					//0: R 1004 1004+0 = 1004
					int baseAddr = baseAddresses.get(objMod);
					System.out.println("\t\t" +  prC.addr + " + " + baseAddr
							+ " = " + (prC.addr + baseAddr));
				}
				else if (prC.typeOfAddr.equals("E")) {
					String symbol = objMod.useList.get(i);
					String symAddr = Integer.toString(symValues.get(symbol));
					// 2: xy: E 2000 ->z 2015
					String addr = Integer.toString(prC.addr);
					addr = addr.substring(0, addr.length() - symAddr.length());
					System.out.println(" ->" + symbol + "\t\t" + (addr + symAddr));
				}
				else if (prC.typeOfAddr.equals("A")){
					System.out.print("\t\t" + prC.addr + "\n");
				}
				else if (prC.typeOfAddr.equals("I")){
					System.out.print("\t\t" + prC.addr + "\n");
				}
				else{
					//ERROR
					if(prC.typeOfAddr != null)
						System.out.println("Error: Invalid Address Symbol " + prC.typeOfAddr);
				}
			}
		}
	}
	

}
