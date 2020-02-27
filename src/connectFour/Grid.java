package connectFour;

//This is the Grid class that takes an arrayList of Rows

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Grid implements Serializable{
	
	//Variables and collections
	final int ROWS = 6; //Grid rows
	private int number;
	ArrayList <Row> theGrid = new ArrayList <Row> ();
	
	//Constructor
	public Grid() {
		Row tempRow;
		for (int loop = ROWS;loop>=1;loop--) {
			tempRow = new Row(loop);
			this.theGrid.add(tempRow);
		}// end for loop
	}//end constructor
	
	//toString method
	public String toString () {
		String output = "This is the toString method";
		return output;
	}// end toString
	
	//Getters and setters
	public int getNumber () {
		return this.number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	//end getters and setters
	
}
