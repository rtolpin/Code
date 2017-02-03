package juggleSolution;
import java.util.ArrayList;

public class JCBase {
	private String label;
	private ArrayList<Integer> hepScores = new ArrayList<Integer>();
	
	public JCBase(){ }
	
	public JCBase(String label, ArrayList<Integer> hepScores){
		this.label = label;
		this.hepScores = hepScores;
	}
	
	public String getLabel(){
		return this.label;
	}
	
	public ArrayList<Integer> getHepScores(){
		return this.hepScores;
	}
	
	
	
}
