package juggleSolution;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class Circuit extends JCBase implements Comparator<int[]> {
	final private int JugID = 0;
	final private int JugScore = 1;
	final private int JugPref = 2;
	final private int JugNumRankings = 3;
	private int[] jugglerInfo = new int[JugNumRankings];
	private int minRankScore;
	private ArrayList<String> selectedJugglers = new ArrayList<String>();	
	private ArrayList <int[]> jugglersRanked = new ArrayList<int[]>();
	
	public Circuit(String label, ArrayList<Integer> hepScores){
		super(label, hepScores);
		minRankScore = 0;
	}
	
	public int rankScore (Juggler jug){
		ArrayList<Integer> hepC = getHepScores();
		ArrayList<Integer> hepJ = jug.getHepScores();
		
		int sum = 0;
		for(int i = 0; i < hepC.size(); i++){
			sum += hepC.get(i) * hepJ.get(i);
		}
		
		return sum;
	}
	
	public ArrayList<String> getSelectedJugglers(){
		return this.selectedJugglers;
	}
	
	@Override
	public int compare(int[] a, int[] b){
		if (a[JugScore] < b[JugScore] || a[JugScore] == b[JugScore] && a[JugPref] > b[JugPref]){
			return 1;
		}else if(a[JugScore] > b[JugScore] || a[JugScore] == b[JugScore] && a[JugPref] < b[JugPref]){
			return -1;
		}
		return 0;
	}
	
	public void createJugglersRanking (ArrayList<Juggler> allJugglers){
		jugglersRanked = new ArrayList<int[]>(allJugglers.size());
		int i = 0;
		for (Juggler jug : allJugglers){
			jugglerInfo[JugID] = i;
			jugglerInfo[JugScore] = rankScore(jug);
			jugglerInfo[JugPref] = jug.orderPreference(getLabel());
			jugglersRanked.add(jugglerInfo.clone());
			i++;
		}
		Collections.sort(jugglersRanked, this);
	}
	
	public int getJugglerInfoByID(int id){
		int[] rank;
		for(int i = 0; i< jugglersRanked.size(); i++){
			rank = jugglersRanked.get(i);
			if(rank[JugID] == id){
				return rank[JugScore];
			}
		}
		return -1;
	}
	
	public int nextRoundSelection(ArrayList<Juggler> allJugglers, int preferenceLevel, int requiredNumJugglers){
		int selectedNumJugglers = 0, counter = 0;
		if (requiredNumJugglers > selectedJugglers.size())
		{
			int maxAddJugglers = requiredNumJugglers - selectedJugglers.size();
			for (int i = 0; i < jugglersRanked.size(); i++){
				
				int[] jugRank = jugglersRanked.get(i);
				
				Juggler juggler = allJugglers.get(jugRank[JugID]);
				if (juggler.notSelected()){
					if (jugRank[JugPref] <= preferenceLevel){
						if(selectedJugglers.size() == 0 || jugRank[JugScore] < minRankScore){
							minRankScore = jugRank[JugScore];
						}
						juggler.setSelectedCircuit(getLabel());
						selectedJugglers.add(juggler.getLabel());
						selectedNumJugglers++;
					}
					counter++;
				}
				if (counter == maxAddJugglers || selectedJugglers.size() == requiredNumJugglers)
					break;
			}
		}
		return selectedNumJugglers;
	}
	
	public boolean leMinRankScore(int jugglerId)
	{
		boolean result = false, found = false;
		for (int i = 0; i < jugglersRanked.size() && !found; i++){
			int[] rank = jugglersRanked.get(i);
			if (rank[JugID] == jugglerId){
				result = rank[JugScore] <= minRankScore;
				found = true;
			}
		}
		assert(found);
		return result;
	}
	
}

