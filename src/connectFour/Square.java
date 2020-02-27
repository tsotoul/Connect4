package connectFour;

//This is the square class that can take a Disc

import java.io.Serializable;

public class Square implements Serializable{
	
	//Variables and collections
	private boolean empty;
	private int number;
	private Disc theDisc;
	
	//Constructors
	public Square(int number) {
		setNumber(number);
		setEmpty(true);
	}//end Constructor
	
	//Getters and setters
	public int getNumber() {
		return this.number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public boolean getEmpty() {
		return this.empty;
	}
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	//end getters and setters
	
	//addDisc method
	public void addDisc(Disc theDisc) {
		this.theDisc = theDisc;
		setEmpty(false);
	}//end addDisc
	
	//returnString method
	public String toString () {
		String output;
		if (this.empty == true) {
			output = "      |__________| ";
		} else {
			output = this.theDisc.getColour();
		}
		return output;
	}// end returnString
	
}
