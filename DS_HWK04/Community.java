
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Community {
	
	public class InputStruct {
		String firstName;
		String lastName;
		int SSN; 
		int fatherSSN; 
		int motherSSN; 
		int[] friendSSNs;
		boolean personCreated;
		
		InputStruct(int ssn) {
			SSN = ssn;
			firstName = "Unknown";
			lastName = "Unknown";
			fatherSSN = motherSSN = 0;
			personCreated = false;
		}
		InputStruct() {
			SSN = 0;
			firstName = "Unknown";
			lastName = "Unknown";
			fatherSSN = motherSSN = 0;
			personCreated = false;
		}
	}//end of InputStructure class
	
	private class MostStruct {
		Person mostPerson;
		int maxMutualFriendsNum;
		MostStruct() {
			mostPerson = null;
			maxMutualFriendsNum = 0;
		}
	}//contains Person object with most mutual friends & num of mutual friends
	
	private ArrayList<Person> firstAncestors;
	
	public Community() {} //empty Community class constructor
	
	public ArrayList<InputStruct> readInputs (String fileName) throws IOException {
		ArrayList<InputStruct> inputs = new ArrayList<InputStruct>();
		
		File communityInfo = new File(fileName);		
		Scanner fileInput = new Scanner(communityInfo);
		String line;
		String[] tokens;
		int lineNo = 0;
		String[] tokenKeys = {"FIRST NAME", "LAST NAME", "SSN", "FATHER", "MOTHER", "FRIENDS"};
		int tokenKeyStage = 0;
		InputStruct inp = null;
		while(fileInput.hasNextLine()){														
			lineNo++;
			line = fileInput.nextLine();
			line = line.trim();
			if (line.equals("")){
				continue;
			}
			tokens = line.split(":");
			for (int i = 0; i < tokens.length; i++){
				tokens[i] = tokens[i].trim();
			}
			if (tokens.length != 2){
				throw new IOException("Could not parse line number " + lineNo);
			}
			if (!tokens[0].equals(tokenKeys[tokenKeyStage])){
				throw new IOException("Token " + tokens[0] + " appears in incorrect order in line number " + lineNo + 
						" Should be: First Name, Last Name, SSN, Father, Mother, Friends");
			}
			if (tokenKeyStage == 0) {
				inp = new InputStruct();
				inp.firstName = tokens[1];
			}
			else if (tokenKeyStage == 1){
				inp.lastName = tokens[1];
			}
			else if (tokenKeyStage == 2){
				inp.SSN = Integer.parseInt(tokens[1]);
			}
			else if (tokenKeyStage == 3){
				inp.fatherSSN = Integer.parseInt(tokens[1]);
			}
			else if (tokenKeyStage == 4){
				inp.motherSSN = Integer.parseInt(tokens[1]);
			}
				
			else if (tokenKeyStage == 5) {
				String[] strList = tokens[1].split(",");
				inp.friendSSNs = new int[strList.length];
				for (int i = 0; i < strList.length; i++){
					inp.friendSSNs[i] = Integer.parseInt(strList[i].trim());
				}
				inputs.add(inp);
			}
			tokenKeyStage = tokenKeyStage < 5 ? tokenKeyStage+1 : 0;
		}
		fileInput.close();
		if (tokenKeyStage != 0){
			throw new IOException("Unexpected End Of File reached");
		}
		return inputs;
	} //Creates inputs from file, places them in InputStruct object
	
	public void buildCommunity (ArrayList<InputStruct> inputs) throws Exception {
		createFamilyTrees(inputs);
		addFriends(inputs);
	}	
	
	public void processQueries (String fileName, String outputFile) throws IOException {		
		File queryInfo = new File(fileName);		
		Scanner fileInput = new Scanner(queryInfo);
		String line, outLine;
		String[] tokens;
		int ssn;
		ArrayList<String> names;
		FileWriter fw = new FileWriter(outputFile);
		
		while(fileInput.hasNextLine()){
			line = fileInput.nextLine();
			line = line.trim();
			if (line.equals("")){
				continue;
			}
			tokens = line.split(" ");
			for (int i = 0; i < tokens.length; i++){
				tokens[i] = tokens[i].trim();
			}
			if (tokens[0].equals("NAME-OF")) {
				ssn = Integer.parseInt(tokens[1]);
				outLine = tokens[0] + " " + ssn + ":" + name_of_ssn(ssn);				
			}
			else if (tokens[0].equals("FATHER-OF")) {
				ssn = Integer.parseInt(tokens[1]);
				outLine = tokens[0] + " " + ssn + ":" + father_of_ssn(ssn);
			}
			else if (tokens[0].equals("MOTHER-OF")) {
				ssn = Integer.parseInt(tokens[1]);
				outLine = tokens[0] + " " + ssn + ":" + mother_of_ssn(ssn);
			}
			else if (tokens[0].equals("HALF-SIBLINGS-OF")) {
				ssn = Integer.parseInt(tokens[1]);
				names = half_siblings_of_ssn(ssn);
				outLine = tokens[0] + " " + ssn + ":" + String.join(",", names);
			}
			else if (tokens[0].equals("FULL-SIBLINGS-OF")) {
				ssn = Integer.parseInt(tokens[1]);
				names = full_siblings_of_ssn(ssn);
				outLine = tokens[0] + " " + ssn + ":" + String.join(",", names);
			}
			else if (tokens[0].equals("CHILDREN-OF")) {
				ssn = Integer.parseInt(tokens[1]);
				names = children_of_ssn(ssn);
				outLine = tokens[0] + " " + ssn + ":" + String.join(",", names);
			}
			else if (tokens[0].equals("MUTUAL-FRIENDS-OF")) {
				ssn = Integer.parseInt(tokens[1]);
				names = mutual_friends_of_ssn(ssn);
				outLine = tokens[0] + " " + ssn + ":" + String.join(",", names);
			}
			else if (tokens[0].equals("INVERSE-FRIENDS-OF")) {
				ssn = Integer.parseInt(tokens[1]);
				names = inverse_friends_of_ssn(ssn);
				outLine = tokens[0] + " " + ssn + ":" + String.join(",", names);
			}
			else if (tokens[0].equals("WHO-HAS-MOST-MUTUAL-FRIENDS")) {
				outLine = tokens[0] + ":" + who_has_most_mutual_friends();
			}
			else
				throw new IOException("Unrecognized token " + tokens[0]);	
			outLine += "\n";
			fw.write(outLine);
		}
		fileInput.close();
		//fw.flush();
		fw.close();
	}//prints to output file according to queries
	
	private static int inputIndexBySSN (ArrayList<InputStruct> inputs, int ssn) {
		int len = inputs.size();
		for (int i = 0; i < len; i++)
			if (inputs.get(i).SSN == ssn)
				return i;
		return -1;
	}//Finds if SSN exists in arraylist of InputStruct objects
	
	private static Person createPerson (InputStruct inp) {
		Person person = new Person(inp.firstName, inp.lastName, inp.SSN);
		return person;
	}	
	private void createFirstAncestors (ArrayList<InputStruct> inputs) {
		ArrayList<InputStruct> outputs = new ArrayList<InputStruct>();
		for (InputStruct inp : inputs) {
			if (inputIndexBySSN(inputs, inp.fatherSSN) == -1 && 
					inputIndexBySSN(outputs, inp.fatherSSN) == -1)
				outputs.add(new InputStruct(inp.fatherSSN));
			if (inputIndexBySSN(inputs, inp.motherSSN) == -1 && 
					inputIndexBySSN(outputs, inp.motherSSN) == -1)
				outputs.add(new InputStruct(inp.motherSSN));				
		}
		firstAncestors = new ArrayList<Person>();
		for (InputStruct out : outputs){
			firstAncestors.add(createPerson(out));
		}
	}//Creates first ancestors
	
	private ArrayList<Person> addChildren (Person ancestor, ArrayList<InputStruct> inputs) throws Exception {
		int ancestorSNN = ancestor.getSSN();
		ArrayList<Person> children = new ArrayList<Person>();
		for (InputStruct inp : inputs) {
			if (inp.fatherSSN == ancestorSNN || inp.motherSSN == ancestorSNN) {
				Person child = inp.personCreated ? findBySSN(inp.SSN) : createPerson(inp);
				if (inp.fatherSSN == ancestorSNN)
					child.setFather(ancestor);				
				else
					child.setMother(ancestor);
				ancestor.addChild(child);
				if (!inp.personCreated) {
					children.add(child);
					inp.personCreated = true;
				}								
			}
		}
		return children;
	}//adds children nodes to tree
	
	private void createFamilyTrees (ArrayList<InputStruct> inputs) throws Exception {
		createFirstAncestors(inputs);
		ArrayList<Person> ancestors = firstAncestors;
		while (ancestors.size() > 0 ) {
			ArrayList<Person> allChildren = new ArrayList<Person>();
			for (Person parent : ancestors) {
				ArrayList<Person> children = addChildren(parent, inputs);
				allChildren.addAll(children);
			}
			ancestors = allChildren;	
		}	
		int problemLinks = 0;
		for (InputStruct inp : inputs) {
			if (!inp.personCreated) {
				System.out.println("Person " + inp.SSN + " has circular link");
				problemLinks++;
			}
		}
		if (problemLinks > 0)
			throw new Exception("Found " + problemLinks + " problem links");
	}
	private void addFriends (ArrayList<InputStruct> inputs) throws Exception {
		Person person, friend;
		for (InputStruct inp : inputs) {
			person = findBySSN(inp.SSN);
			for (int frSNN : inp.friendSSNs) {
				friend = findBySSN(frSNN);
				if(friend == null)
					throw new Exception("Friend with SSN " + frSNN + " does not exist");
				person.addFriend(friend);
			}
		}
	}
	private Person findBySSN(int ssn) {		
		for (Person fa : firstAncestors) {
			Person foundPerson = fa.findBySSN(ssn);
			if (foundPerson != null)
				return foundPerson;
		}
		return null;
	}
	
	private MostStruct mutualRecursive (Person newPerson, MostStruct mostData) {
		ArrayList<String> mfNames = mutual_friends_of_ssn(newPerson.getSSN());
		int mfNum = mfNames.size();
		if (mostData.mostPerson == null ||
			mfNum > mostData.maxMutualFriendsNum || 
			mfNum == mostData.maxMutualFriendsNum && 
				newPerson.getSSN() < mostData.mostPerson.getSSN()) {
			mostData.mostPerson = newPerson;
			mostData.maxMutualFriendsNum = mfNum;
		}
		for (Person child : newPerson.getChildren()) {
			mostData = mutualRecursive(child, mostData);
		}
		return mostData;
	}
	
	
	public String name_of_ssn(int SSN) {
		Person person = findBySSN(SSN);
		return person.getFullName();
	}
	public String mother_of_ssn(int SSN) {
		Person person = findBySSN(SSN);
		Person mother = person.getMother();
		return mother.getFullName();
	}
	public String father_of_ssn(int SSN) {
		Person person = findBySSN(SSN);
		Person father = person.getFather();
		return father.getFullName();		
	}
	public ArrayList<String> half_siblings_of_ssn(int SSN) {
		Person person = findBySSN(SSN);
		ArrayList<Person> motherChildren = person.getMother().getChildren();
		ArrayList<Person> fatherChildren = person.getFather().getChildren();
		ArrayList<String> halfSiblings = new ArrayList<String>();
		for (Person child : motherChildren)
			if (child.getSSN() != SSN && !fatherChildren.contains(child))
				halfSiblings.add(child.getFullName());
		for (Person child : fatherChildren)
			if (child.getSSN() != SSN && !motherChildren.contains(child))
				halfSiblings.add(child.getFullName());
		if (halfSiblings.size() == 0)
			halfSiblings.add("UNAVAILABLE");
		return halfSiblings;
	}
	public ArrayList<String> full_siblings_of_ssn(int SSN) {
		Person person = findBySSN(SSN);
		ArrayList<Person> motherChildren = person.getMother().getChildren();
		ArrayList<Person> fatherChildren = person.getFather().getChildren();
		ArrayList<String> fullSiblings = new ArrayList<String>();
		for (Person child : motherChildren)
			if (child.getSSN() != SSN && fatherChildren.contains(child))
				fullSiblings.add(child.getFullName());
		if (fullSiblings.size() == 0)
			fullSiblings.add("UNAVAILABLE");
		return fullSiblings;
	}
	public ArrayList<String> children_of_ssn(int SSN) {
		Person person = findBySSN(SSN);
		ArrayList<Person> children = person.getChildren();
		ArrayList<String> childrenNames = new ArrayList<String>();
		for (Person child : children)
			childrenNames.add(child.getFullName());
		if (childrenNames.size() == 0)
			childrenNames.add("UNAVAILABLE");
		return childrenNames;
	}
	public ArrayList<String> mutual_friends_of_ssn(int SSN) {
		Person person = findBySSN(SSN);
		ArrayList<Person> friends = person.getFriends();
		ArrayList<Person> invFriends = person.getInverseFriends();
		ArrayList<String> mutualFriends = new ArrayList<String>();
		for (Person fr : friends)
			if (invFriends.contains(fr))
				mutualFriends.add(fr.getFullName());
		if (mutualFriends.size() == 0)
			mutualFriends.add("UNAVAILABLE");
		return mutualFriends;
	}
	public ArrayList<String> inverse_friends_of_ssn(int SSN) {
		Person person = findBySSN(SSN);
		ArrayList<Person> invFriends = person.getInverseFriends();
		ArrayList<String> invFriendNames = new ArrayList<String>();
		for (Person fr : invFriends)
			invFriendNames.add(fr.getFullName());
		if (invFriendNames.size() == 0)
			invFriendNames.add("UNAVAILABLE");
		return invFriendNames;
	}
	public String who_has_most_mutual_friends() {
		MostStruct mostData = new MostStruct();
		for (Person fa : firstAncestors) {
			mostData = mutualRecursive(fa, mostData);
		}
		return mostData.mostPerson.getFullName();
	}
	
}