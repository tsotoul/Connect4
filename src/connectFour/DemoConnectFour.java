package connectFour;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JOptionPane;

public class DemoConnectFour {

	private static final String fileName = "data.bin";

	public static void main(String[] args) {
		//Create a GamePlay [currentGame] object and welcome the user
		GamePlay currentGame;
		int reply = welcome();
		
		//Act according to the user's selection [New Game(0) - Load Game (1)]
		if (reply == 0) {
			currentGame = new GamePlay();
			currentGame.startGame();        
			saveGame(currentGame);		//call the saveGame method after the current ends or stops by the user
		}
		else {
			currentGame = loadGame();   //call the loadGame if the user selects to load the previous Game. The method returns a GamePlay type object
			currentGame.startGame();
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
								//Other methods//
	
	//First stage - Welcome message (start new game or load a previous one)
	public static int welcome() {
		String []options = {"New Game", "Load Game"};
		String title = "Welcome to the Connect Four game";
		String message = "To win Connect Four you must be the first player to get four of your coloured \n checkers in "
				+ "a row either horizontally or vertically.\n Select to start a new or load a previous game";
		int reply = JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		return reply;
	}//end welcome method
	
	//Method to save the current status of the game
	public static void saveGame(Serializable object) {
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
			os.writeObject(object);
			os.close();
			System.out.println("Save complete");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// end try
	}//end saveGame
	
	//Method to load a current game previously saved (returns a GamePlay type object)
	public static GamePlay loadGame() {
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
			GamePlay loaded = (GamePlay) is.readObject();
			is.close();
			System.out.println("Load complete");
			return loaded;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try
		return null;	//returns null if there is no game to load
	}//end loadGame
	
}