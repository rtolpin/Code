
import java.util.ArrayList;
import java.io.IOException;

public class CommunityMain {

	public static void main(String[] args) {
		String inpFile = args[0];
		String queryFile = args[1];
		String outputFile = "communityOutput.txt";
		Community comm = new Community();
		try {
			ArrayList<Community.InputStruct> inputs = comm.readInputs(inpFile);
			comm.buildCommunity(inputs);
			comm.processQueries(queryFile, outputFile);
		} catch (IOException exc) {
			System.err.println("Input Error" + exc);
			return;
		} catch (Exception exc) {
			System.err.println("Runtime Error" + exc);
			return;
		}		
	}

}