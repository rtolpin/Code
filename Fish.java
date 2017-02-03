

public class Fish {
	
	public Fish(){}
	
	public static void main(String[] args){
		Fish a = new Tuna();
		Tuna t = new Tuna();
		
		String s = "";
		
		s = "abcde";
		
		char a1 = 'a';
		
		s += a1;
		
		System.out.println(s);
		
		//a.foo(t);
	}
	
	public void foo(Fish f){
		System.out.println("I am a fish!");
	}
}

class Tuna extends Fish{
	public Tuna(){}
	
	public void foo(Tuna t){
		System.out.println("I am a tuna!");
	}
}
