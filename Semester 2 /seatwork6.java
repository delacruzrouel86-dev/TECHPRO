   class seatwork6 {
	String name;
	
	void displayName() {
		System.out.println("Worker name: " + name);
	}
}
public class Seatwork6 {

public static void main(String[ ]args) {
  	
  	
  	Worker w1 = new Worker( );
  	w1.name = "Rouel Dela Cruz";
  	
      try {
      	w1.displayName( );
      } catch (Exception e) {
      	System.out.println("Error: Worker Inputed isnt registered");
      }
  }
}
