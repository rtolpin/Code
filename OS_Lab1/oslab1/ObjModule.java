package oslab1;

import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;

public class ObjModule {
	Map<String, Integer> defnList;
	Map<Integer, String> useList;
	ArrayList<ProgCommand> progCommands;

	public class ProgCommand{
		String typeOfAddr;
		int addr;
		ProgCommand(){
			typeOfAddr = "";
			addr = Integer.MIN_VALUE;
		}
	}
	
	String getDefSymbol(int i) {
		Iterator<String> it = defnList.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if (defnList.get(key) == i)
				return key;
		}
		return null;
	}
	
	Boolean isDefinedSym(String sym){
		Iterator<String> it = defnList.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(key.equals(sym)){
				return true;
			}
		}
		return false;
	}
	
	Boolean isUsedSym(String sym){
		Iterator<Integer> it = useList.keySet().iterator();
		while(it.hasNext()){
			Integer key = it.next();
			if(useList.get(key).equals(sym)){
				return true;
			}
		}
		return false;
	}
	
	public ObjModule(){ }
	
	public ObjModule(Map<String, Integer> defnList, Map<Integer, String> useList, ArrayList<ProgCommand> progAddresses){
		this.defnList = defnList;
		this.useList = useList;
		this.progCommands = progAddresses;
	}
	
	
}
