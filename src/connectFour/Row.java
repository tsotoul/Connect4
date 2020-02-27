package connectFour;

//This is the Row class that can take an arrayList of Squares

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Row implements Serializable{
	
	//Variables and collections
	final int COLS = 7; //Grid columns
	private int number;
	ArrayList <Square> theRow = new ArrayList <Square> ();
	
	//Constructor
	public Row(int number) {
		Square tempSquare;
		setNumber(number);
		for (int loop = 1; loop <= COLS;loop++) {
			tempSquare = new Square(loop);
			this.theRow.add(tempSquare);	
		}//end for
	}//end Row
	
	//toString method
	public String toString () {
		String output = "This is the toString method";
		return output;
	}// end toString
		
	//Getters and Setters
	public int getNumber () {
		return this.number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	//end getters and setters
	
}
