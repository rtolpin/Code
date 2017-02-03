package juggleSolution;

import java.util.ArrayList;

public class Juggler extends JCBase {
	private String selectedCircuit = "";
	private ArrayList<String> circuitPreferences = new ArrayList<String>();
	
	public Juggler(String label, ArrayList<Integer> hepScores, ArrayList<String> circuitPreferences){
		super(label, hepScores);
		this.circuitPreferences = circuitPreferences;
	}
	
	public String getSelectedCircuit(){
		return this.selectedCircuit;
	}
	
	public ArrayList<String> getCircuitPreferences(){
		return this.circuitPreferences;
	}
	
	public int orderPreference(String circuitLabel){
		return circuitPreferences.indexOf(circuitLabel);
	}
	
	public boolean notSelected(){
		return selectedCircuit.isEmpty();
	}
	
	public void setSelectedCircuit(String selCircuit){
		selectedCircuit = selCircuit;
	}
	
	
}
