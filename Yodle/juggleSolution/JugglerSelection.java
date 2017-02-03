package juggleSolution;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class JugglerSelection {
	ArrayList<Juggler> allJugglers = new ArrayList<Juggler>();
	ArrayList<Circuit> allCircuits = new ArrayList<Circuit>();
	
	public JugglerSelection(){ }
	
	public static void main(String[] args) throws Exception{
		
		File f = new File(args[0]); //jugglefest.txt
		Scanner input = new Scanner(f);
		JugglerSelection s = new JugglerSelection();
		
		s.populateCircuitJugglers(input);
		
		s.doAllSelections(s.allCircuits, s.allJugglers);
		
		boolean y = s.checkSelections(s.allCircuits, s.allJugglers);
		
		System.out.println("\n" + y);
		
		if(y == false){
			throw new Exception();
		}
		
		PrintWriter output = null;
		File f1 = new File("JugglerCircuitResultsTest.out");
		
		try{
			output = new PrintWriter(f1);
		}
		catch(FileNotFoundException e){
			System.out.println("Error: cannot create file");
			System.exit(1);
		}
		
		int jId = -1;
		int cId = -1;
		Juggler jug;
		Circuit circ;
		int circScore;
		for(Circuit c : s.allCircuits){
			output.printf("%2s%s", c.getLabel(), ": ");
			for(String j : c.getSelectedJugglers()){
				jId = s.findJugglerIdByLabel(j, s.allJugglers);
				output.printf("%2s%s", j, " ");
				jug = s.allJugglers.get(jId);
				for(String p : jug.getCircuitPreferences()){
					cId = s.findCircuitIdByLabel(p, s.allCircuits);
					circ = s.allCircuits.get(cId);
					circScore = circ.getJugglerInfoByID(jId);
					if(jug.getCircuitPreferences().indexOf(p) == jug.getCircuitPreferences().size()-1 && c.getSelectedJugglers().indexOf(j) != c.getSelectedJugglers().size()-1){
						output.printf("%2s:%2d%s", p, circScore, ", ");
					}
					else{
						output.printf("%2s:%2d%s", p, circScore, " ");
					}
				}
			}
			output.println();
		}
		
		output.close();
		
	}
	
	public void populateCircuitJugglers(Scanner input){
		String label;
		String line;
		String[] lineArray;
		ArrayList<Integer> hepScores; 
		ArrayList<String> circuitPreferences;
		
		while(input.hasNextLine()){
			
			line = input.nextLine();
			
			line = line.trim();
			
			if(line.isEmpty())
				continue;
			
			hepScores = new ArrayList<Integer>(3);
				
			lineArray = line.split(" |:|\\,");
				
			/*for(String s : lineArray){
				System.out.print(s + " ");
			}*/
				
			label = lineArray[1];
				
			assert(lineArray[2].equals("H"));
			assert(lineArray[4].equals("E"));
			assert(lineArray[6].equals("P"));
				
			hepScores.add(Integer.parseInt(lineArray[3]));
			hepScores.add(Integer.parseInt(lineArray[5]));
			hepScores.add(Integer.parseInt(lineArray[7]));
			
			if(line.charAt(0) == 'C'){
					Circuit c = new Circuit(label, hepScores);
					this.allCircuits.add(c);
			}
		
			else if(line.charAt(0) == 'J'){
				
				circuitPreferences = new ArrayList<String>();
				
				for(int i = 8; i < lineArray.length; i++){
					assert(lineArray[i].charAt(0) == 'C');
					circuitPreferences.add(lineArray[i]);
				}
				
				Juggler j = new Juggler(label, hepScores, circuitPreferences);
				
				this.allJugglers.add(j);
			}
		}
		
		input.close();
	}
	
	
	
	public int nextRoundSelectionAllCircuits(ArrayList<Circuit> allCircuits, ArrayList <Juggler> allJugglers,
			int preferenceLevel)
		{
			int selectedJugglers = 0;
			boolean allDone = true;
			int requiredNumJugglers = allJugglers.size() / allCircuits.size();
			for (Circuit c : allCircuits)
			{
				if (c.getSelectedJugglers().size() < requiredNumJugglers)
				{
					selectedJugglers += c.nextRoundSelection(allJugglers, preferenceLevel, requiredNumJugglers);
					allDone = false;
				}
			}
			return allDone ? -1 : selectedJugglers;
		}
	
	public void doAllSelections(ArrayList<Circuit> allCircuits, ArrayList<Juggler> allJugglers)
	{
		int status = 0;
		int preferenceLevel = 0;
		for (int i = 0; i < allCircuits.size(); i++)
			allCircuits.get(i).createJugglersRanking(allJugglers);
		while (status > -1)
		{
			status = nextRoundSelectionAllCircuits(allCircuits, allJugglers, preferenceLevel);
			if (status == 0)
				++preferenceLevel;
		}
	}
	
	public boolean checkSelections(ArrayList<Circuit> allCircuits, ArrayList<Juggler> allJugglers)
	{
		for (int i = 0; i < allJugglers.size(); ++i){
			Juggler juggler = allJugglers.get(i);
			int orderPref = juggler.orderPreference(juggler.getSelectedCircuit());
			if (orderPref > 0){
				ArrayList<String> circuitPreferences = juggler.getCircuitPreferences();
				for (int j = 0; j < orderPref; j++ ){
					
					int cirId = findCircuitIdByLabel(circuitPreferences.get(j), allCircuits);
					if (!allCircuits.get(cirId).leMinRankScore(i))
						return false;
				}
			}
		}
		return true;
	}
	
	public int findCircuitIdByLabel(String label, ArrayList<Circuit> allCircuits)
	{
		int result = -1;
		for (int i = 0; i < allCircuits.size(); i++){
			if (allCircuits.get(i).getLabel().equals(label)){
				result = i;
				break;
			}
		}
			
		assert(result != -1);
		return result;
	}
	
	public int findJugglerIdByLabel(String label, ArrayList<Juggler> allJugglers)
	{
		int result = -1;
		for(int i = 0; i < allJugglers.size(); i++){
			if(allJugglers.get(i).getLabel().equals(label)){
				result = i;
				break;
			}
		}
		
		assert(result != -1);
		
		return result;
	}
	
	
}
