package connectFour;

//This is the Disc class which has only one String attribute, colour.

import java.io.Serializable;

public class Disc implements Serializable{
	
	//Variables and collections
	private String colour;
	
	//Constructors
	public Disc(){}
	public Disc(String colour) {
		setColour(colour);
	}
	
	//toString method
	public String toString () {
		String output = "This is the toString method";
		return output;
	}// end toString
	
	//Getters and setters
	public String getColour () {
		return this.colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	//end getters and setters
}
