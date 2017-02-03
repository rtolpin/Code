
import java.util.ArrayList;

public class Person {
	private String firstName;
	private String lastName;
	private int SSN;
	private Person father;
	private Person mother;
	private ArrayList<Person> children;
	private ArrayList<Person> friends;
	private ArrayList<Person> inverseFriends;
	private String parentType;
	
	public Person(String firstName, String lastName, int ssn) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.SSN = ssn;
		father = null;
		mother = null;
		parentType = "none";
	}
	public Person(int ssn) {
		this.firstName = null;
		this.lastName = null;
		this.SSN = ssn;
		father = null;
		mother = null;
		parentType = "none";
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getFullName() {
		return firstName + " " + lastName;
	}
	public int getSSN() {
		return SSN;
	}
	public Person getFather() {
		return father;
	}
	public void setFather(Person father) throws Exception {
		if (father.getParentType() == "mother")
			throw new Exception("Parent cannot be both mother and father");
		this.father = father;
		father.setParentType("father");
	}
	public Person getMother() {
		return mother;
	}
	public void setMother(Person mother) throws Exception {
		if (mother.getParentType() == "father")
			throw new Exception("Parent cannot be both mother and father");
		this.mother = mother;
		mother.setParentType("mother");
	}
	public void addChild(Person child) {
		if (children == null)
			children = new ArrayList<Person>();
		children.add(child);
	}
	public ArrayList<Person> getChildren() {
		if (children == null)
			children = new ArrayList<Person>();
		return children;
	}
	public void addFriend(Person friend) {
		if (friends == null)
			friends = new ArrayList<Person>();
		friends.add(friend);
		if (friend.inverseFriends == null)
			friend.inverseFriends = new ArrayList<Person>();
		friend.inverseFriends.add(this);
	}
	public ArrayList<Person> getFriends() {
		if (friends == null)
			friends = new ArrayList<Person>();
		return friends;
	}
	public ArrayList<Person> getInverseFriends() {
		if (inverseFriends == null)
			inverseFriends = new ArrayList<Person>();
		return inverseFriends;
	}
	public String getParentType() {
		return parentType;
	}
	public void setParentType(String parentType) {
		this.parentType = parentType;
	}
	public Person findBySSN(int ssn) {
		if (SSN == ssn)
			return this;
		Person person;
		if (children != null) {
			for (Person child : children) {
				person = child.findBySSN(ssn);
				if (person != null)
					return person;
			}
		}
		return null;
	}
}

