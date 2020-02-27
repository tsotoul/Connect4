package connectFour;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class GamePlay implements Serializable{
	
	//Declare constants
	private static final int ROWS = 6;
	private static final int COLS = 7;
	private static final int WINNINGSCORE = 4;
	private static final int FULLGRID = ROWS * COLS;
	//Declare private variables
	private Disc player1, player2;
	private Disc tempDisc;
	private Grid game;
	
	//GamePlay method to run only for a New Game
	public GamePlay () {
		
		/*
		GameGridLayout testGrid = new GameGridLayout();   //NOT IMPLEMENTED IN THE PROJECT
		*/
				
		//Create the player objects (no colour yet)
		player1 = new Disc();
		player2 = new Disc();
		
		//Ask player1 to pick a colour	
		int colourSelection = playerColour(1);
		//Assign colours as per player1's choice
		assignColour(player1, player2, colourSelection);

		//Create a new game and give player1 a disc
		game = new Grid();
		tempDisc = player1;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////
												// OTHER METHODS //	

	public void startGame() {
				
		//Declare variables
		int input = 0;
		int row = 0;		
				
		//Gameplay
		do {
			String output = displayGrid(game, player1, player2, tempDisc);
			String title = "Welcome to the Connect Four game";
			String []options = {"SAVE & EXIT", "1", "2", "3", "4", "5", "6", "7"};
			int reply = JOptionPane.showOptionDialog(null, output, title, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			input = reply;
			if (input == 0){
				JOptionPane.showMessageDialog(null, "Thank you for playing, see you soon!");
				break;				
			}//end if
	
			//check if the column is full
			if (checkColumnOverload(row)){ 
				tempDisc = changePlayer(player1, player2, tempDisc); //change player
			}
			//add disc to selected square
			row = addDiscToSquare(game, tempDisc, input);
			
			//display the grid
			displayGrid(game, player1, player2, tempDisc);
			//change player
			tempDisc = changePlayer(player1, player2, tempDisc);
				
		} while (!checkHorizontally(game) && !checkVertically(game, input) && !checkIfGridIsFull(game) && !checkDiagonally(game, input, row));
		
		//Conditions to show relevant message according to the result
		if (checkHorizontally(game)){
			JOptionPane.showMessageDialog(null, "YOU WIN! \nYOU CONNECTED FOUR IN ROW " + row);
			showWinner(player1, player2, tempDisc);
			displayGrid2(game); //display the winning grid
		}//end if
		else if(checkVertically(game, input)){
			JOptionPane.showMessageDialog(null, "YOU WIN! \nYOU CONNECTED FOUR VERTICALLY IN COLUMN " + input);
			showWinner(player1, player2, tempDisc);
			displayGrid2(game); //display the winning grid
		}//end else if
		else if(checkDiagonally(game, input, row)){
			JOptionPane.showMessageDialog(null, "YOU WIN! \nYOU CONNECTED FOUR DIAGONALLY");
			showWinner(player1, player2, tempDisc);
			displayGrid2(game); //display the winning grid
		}//end else if
	}

	//Method for Player 1 to select his colour [returns 0-(RED) or 1-(YELLOW)]
	public static int playerColour(int playerNumber) {
		String []options = {"RED", "YELLOW"};
		String title = "Colour selection";
		String message = "Player " + playerNumber + ", please select your colour.";
		int reply = JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		return reply;
	}//end playerColour

	//Method to assignColour to players
	public static void assignColour(Disc player1, Disc player2, int colourSelection) {
		if (colourSelection == 0) {
			player1.setColour("  |____ R_____|");
			player2.setColour("  |____ Y_____|");
			JOptionPane.showMessageDialog(null, "Player 1 takes the RED colour.\nPlayer 2 takes the YELLOW colour");
		}//end if
		else {
			player1.setColour("  |____ Y_____|");
			player2.setColour("  |____ R_____|");
			JOptionPane.showMessageDialog(null, "Player 1 takes the YELLOW colour.\nPlayer 2 takes the RED colour");
		}//end else
	}//end assignColour method

	//Method to access and print grid in OptionPane
	public static String displayGrid(Grid grid, Disc player1, Disc player2, Disc tempDisc) {
		String output;
		if (tempDisc == player1 ){
			output = "Player 1, it's your turn.\n\n";
		}
		else {
			output = "Player 2, it's your turn.\n\n";
		}
		for ( Row tempRow : grid.theGrid) {
			String rowOutput = "";  //the output of each row
			for (Square tempSquare : tempRow.theRow) {
				rowOutput = rowOutput + "   " + tempSquare.toString();
			}//end inner for loop
			output = output + rowOutput + "\n" + "\n";
		}//end outer for loop
		return output;
	}//end showGridinConsole method

	//Method to display the winning grid
	public static String displayGrid2(Grid grid) {
		String output = "GAME OVER\n\n";
		for ( Row tempRow : grid.theGrid) {
			String rowOutput = "";  //the output of each row
			for (Square tempSquare : tempRow.theRow) {
				rowOutput = rowOutput + "   " + tempSquare.toString();
			}//end inner for loop
			output = output + rowOutput + "\n" + "\n";
		}//end outer for loop
		JOptionPane.showMessageDialog(null, output);
		return output;
	}//end showGridinConsole method

	//Access the grid and add player's disc to desired column
	public static int addDiscToSquare (Grid grid, Disc theDisc, int colNum) {
		int rowNum = 0;
		for (int row = 1;row<=ROWS;row++){			
			for ( Row tempRow : grid.theGrid) {
				for (Square tempSquare : tempRow.theRow) {
					if (tempSquare.getNumber() == colNum && tempRow.getNumber() == row && tempSquare.getEmpty()) {
						tempSquare.addDisc(theDisc);
						rowNum = row;
						return rowNum;
					}//end if 
				}//end inner for loop
			}//end outer for loop3		
		}// end row iteration loop
		return rowNum;
	}//end addDiscToSquare method

	//check Column method to check if the column has already 6 discs
	public static boolean checkColumnOverload (int row) {
		if (row == ROWS) {
			String output = "This column is full. Please select another column";
			JOptionPane.showMessageDialog(null, output);
			return true;
		}//end if
		return false;
	}//end checkColumn method

	//method to check connect four in a row
	public static boolean checkHorizontally (Grid grid) {		
		boolean fourInARow = false;
		for ( Row tempRow : grid.theGrid) {
			String item = "";
			int score = 1;
			for (Square tempSquare : tempRow.theRow) {
				if (tempSquare.toString() == item && !tempSquare.getEmpty()) {
					score++;
				} 
				else if (tempSquare.toString() != item || tempSquare.getEmpty()){
					score = 1;
				}
				item = tempSquare.toString();
				if (score == WINNINGSCORE) {
					fourInARow = true;
				}//end if
			}//End loop through squares
		}//end loop through rows
		return fourInARow;
	}//end checkRow

	//method to check connect four in a column
	public static boolean checkVertically(Grid grid, int colNum) {
		boolean fourInACol = false;
		int score = 0;
		String item = "";  //temporary variable to hold the tempSquare
		for (int row = 1;row<=ROWS;row++){	
			for ( Row tempRow : grid.theGrid) {
				for (Square tempSquare : tempRow.theRow) {
					if (tempSquare.getNumber() == colNum && tempRow.getNumber() == row && !tempSquare.getEmpty()) {
						if (tempSquare.toString() == item) {
							score++;
						}//end if
						else {
							score = 1;
						}//end else
						item = tempSquare.toString(); //change to the next tempSquare
					}//end if					
				}//end inner for loop				
			}//end outer for loop
		}// end row iteration loop
		//System.out.println("item: " + item);
		//System.out.println("score: " + score);
		if (score == WINNINGSCORE) {
			fourInACol = true;
		}//end if
		return fourInACol;
	}//end checkVertically

	//method to check four diagonally	
	public static boolean checkDiagonally (Grid grid, int colNum, int rowNum) {
		Boolean fourInaDiagonal = false;
		String item = "";  //variable to keep the played colour
		for ( Row tempRow : grid.theGrid) {
			for (Square tempSquare : tempRow.theRow) {
				if (tempSquare.getNumber() == colNum && tempRow.getNumber() == rowNum && !tempSquare.getEmpty()) {
					item = tempSquare.toString(); //assign to item the played colour
				}//end if 
			}//end inner for loop
		}//end outer for loop
		//												CHECK AXIS (/)
		for (int counter1 = WINNINGSCORE-1, counter2 = 0;counter1>=0 && counter2<WINNINGSCORE;counter1--, counter2++){
			int score45degrees = 0; //score for axis (/)
			for (int row=rowNum-counter1, col=colNum-counter1;row<=rowNum  + counter2 && col<=colNum + counter2;row++, col++) {
				for ( Row tempRow : grid.theGrid) {
					for (Square tempSquare : tempRow.theRow) {
						if (tempRow.getNumber() == row && tempSquare.getNumber() == col && tempSquare.toString() == item) {
							score45degrees++;
						}//end if
						if (score45degrees == WINNINGSCORE) {
							fourInaDiagonal = true;
							return fourInaDiagonal;
						}//end if
					}//end Square loop
				}//end Row loop 
			}//end loop
		}//end loop
		// 												CHECK AXIS (\)
		for (int counter1 = WINNINGSCORE-1, counter2 = 0;counter1>=0 && counter2<WINNINGSCORE;counter1--, counter2++){
			int score315degrees = 0; //score for axis (\)
			for (int row=rowNum + counter1, col=colNum-counter1;row>=rowNum - counter2 && col<=colNum + counter2;row--, col++) {
				for ( Row tempRow : grid.theGrid) {
					for (Square tempSquare : tempRow.theRow) {
						if (tempRow.getNumber() == row && tempSquare.getNumber() == col && tempSquare.toString() == item) {
							score315degrees++;
						}//end if
						if (score315degrees == WINNINGSCORE) {
							fourInaDiagonal = true;
							return fourInaDiagonal;
						}//end if
					}//end Square loop
				}//end Row loop 
			}//end loop
		}//end loop
		return fourInaDiagonal;
	}

	//method to check if the grid is full
	public static boolean checkIfGridIsFull (Grid grid) {
		boolean full = false;
		int gridCounter = 0;
		for (Row tempRow: grid.theGrid) {
			for (Square tempSquare : tempRow.theRow) {
				if (tempSquare.getEmpty() == false) {
					gridCounter++;
					//System.out.println(gridCounter);
				}//end if
			}//end inner loop
		}//end outer loop
		if (gridCounter == FULLGRID) {
			JOptionPane.showMessageDialog(null, "THE GRID IS FULL - IT'S A DRAW!");
			full = true;
		}
		return full;
	}//end checkIfGridIsFull

	//method to change players after they play	
	public static Disc changePlayer(Disc player1, Disc player2, Disc tempDisc) {
		if (tempDisc == player1) {
			tempDisc = player2;
		} else {
			tempDisc = player1;
		}
		return tempDisc;
	}

	//method to show winner
	public static void showWinner(Disc player1, Disc player2, Disc tempDisc){	
		if (tempDisc == player1){
			JOptionPane.showMessageDialog(null, "PLAYER 2, YOU WIN.");
		}//end if
		else {
			JOptionPane.showMessageDialog(null, "PLAYER 1, YOU WIN.");
		}//end else
	}//end showWinner
	
	
}
