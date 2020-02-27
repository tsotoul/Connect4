# Connect4
A Connect 4 game in Java using Object Oriented Programming

1. Game design 
 
The Connect Four game, subject of the Software Development 1 module was implemented following Object Oriented Programming, creating and using objects of classes that associate to each other as per the diagram below, and as per the clarification following in the next chapters.  
The game was designed using four different classes for the Grid, another class for the GamePlay, and the Main class which handles the welcome(), saveGame() and loadGame() methods and creates the GamePlay objects. In particular: 
 
Grid: 
The grid was designed using four classes (Grid, Row, Square, and Disc). The grid will be thoroughly explained in the next chapter. In general, the four classes have the following structure. 
Disc ---- one-to-one ---- Square ---- one-to-many ---- Row ---- one-to-many ---- Grid  
The Disc class has one to one association with Square where Square has one to many association with Row and Row one to many association with Grid. 
 
GamePlay: 
The GamePlay class hosts all the functionality of the game. It creates a new Grid (as shown above), show it to the user (displayGrid()) and allows the user to select (assignColour()), play (addDiscToSquare()) and is also checking after each move horizontally (checkHorizontally()), vertically (checkVertically()) or diagonally (checkDiagonally()). If a user has won, or if the grid is full (checkGridIsFull()) the class calls the methods to show the winner (showWinner()) and display the final Grid (displayGrid2()).  
 
DemoConnectFour: 
Finally, the DemoConnectFour class, hosts the Main class that welcomes the user (welcome()), saves the current game (saveGame()), or loads the previous game (loadGame()) and assigns it to current before calling the startGame() method of the class GamePlay.

