/**
 * The class <b>TicTacToe</b> is the
 * class that implements the Tic Tac Toe Game.
 * It contains the grid and tracks its progress.
 * It automatically maintain the current state of
 * the game as players are making moves.
 *
 * Originally written by Guy-Vincent Jourdan, University of Ottawa
 */
public class TicTacToe {

  // FINISH THE VARIABLE DECLARATION
  // then remove this comment

  /**
   * The internal representation of the board
   * as a one dimensional array, but visualized
   * as a 2d board based on the number of rows
   * and number of columns.
   *
   * For example, below is a board of 3 rows
   * and 4 columns.  The board would be an array
   * of size 12 shown below.
   *
   *  1  |  2  | 3  | 4
   * --------------------
   *  5  |  6  | 7  | 8
   * --------------------
   *  9  | 10  | 11 | 12
   *
   */
  char[] board;

  /**
   * The number of rows in your grid.
   */
  int numRows;

  /**
   * The number of columns in your grid.
   */
  int numColumns;

  /**
   * How many rounds have the players played so far.
   */
  int numRounds;

  /**
   * What is the current state of the game
   */
  String gameState;

  /**
   * How many cells of the same type must be
   * aligned (vertically, horizontally, or diagonally)
   * to determine a winner of the game
   */
  int sizeToWin;

  /**
   * Who is the current player?
   */
  char currentPlayer;


  /**
   * The default empty constructor.  The default game
   * should be a 3x3 grid with 3 cells in a row to win.
   */
  public TicTacToe() {
    this.board = new char[9];
    this.numColumns = 3;
    this.numRows = 3;
    this.numRounds = 0;
    this.sizeToWin = 3;
    this.gameState = "PLAYING";
    this.currentPlayer = 'X';

    for (int i = 0; i < 9; i++){
      this.board[i] = ' ';
    }
  }

  /**
   * A constructor where you can specify the dimensions
   * of your game as rows x coluns grid, and a sizeToWin
   *
   * @param aNumRows the number of lines in the game
   * @param aNumColumns the number of columns in the game
   * @param aSizeToWin the number of cells that must be aligned to win.
   */
  public TicTacToe(int aNumRows, int aNumColumns, int aSizeToWin) {
    this.board = new char[(aNumColumns * aNumRows)];
    this.numColumns = aNumColumns;
    this.numRows = aNumRows;
    this.numRounds = 0;
    this.sizeToWin = aSizeToWin;
    this.gameState = "PLAYING";
    this.currentPlayer = 'X';

    for (int i = 0; i < (aNumColumns * aNumRows); i++){
      this.board[i] = ' ';
    }

  }

  /**
   * Who should play next (X or O).
   *
   * This method does not modify the state of the game.
   * Instead it tells you who should play next.
   *
   * @return The player that should play next.
   */
  public CellValue nextPlayer() {
    if (this.currentPlayer == CellValue.EMPTY ||this.currentPlayer == CellValue.O){
      return CellValue.X;
    }
    else if (this.currentPlayer == CellValue.x)  {
      return CellValue.X;
    }
  }

  /**
   * What is the value at the provided cell based on the
   * grid of numRows x numColumns as illustrated below.
   *
   *  1  |  2  | 3  | 4
   * --------------------
   *  5  |  6  | 7  | 8
   * --------------------
   *  9  | 10  | 11 | 12
   *
   * Note that the input is 1-based (HINT: arrays are 0-based)
   *
   * If the position is invalid, return CellValue.INVALID.
   *
   * @param position The position on the board to look up its current value
   * @return The CellValue at that position
   */
  public CellValue valueAt(int position) {
     if (this.board[position -1] == 'X'){
       return CellValue.X;
     }
     else if (this.board[position -1] == 'O'){
       return CellValue.O;
     }
     else if ((position - 1) < 0 || (position - 1) > ((this.numRows * this.numColumns) -1)){
       return CellValue.INVALID;
     }
     else {
       return CellValue.EMPTY;
     }
  }

  /**
   * What is the value at the provided row and column number.
   *
   *  [1,1]  | [1,2]  | [1,3]  | [1,4]
   * ----------------------------------
   *  [2,1]  | [2,2]  | [2,3]  | [2,4]
   * ----------------------------------
   *  [3,1]  | [3,2]  | [3,3] | [3,4]
   *
   * Note that the input is 1-based (HINT: arrays are 0-based)
   *
   * If the row/column is invalid, return CellValue.INVALID.
   *
   * @param position The position on the board to look up its current value
   * @return The CellValue at that row/column
   */
  public CellValue valueAt(int row, int column) {

    int position = (((row - 1) * this.numColumns) + column);

    if (this.board[position -1] == 'X'){
       return CellValue.X;
     }
     else if (this.board[position -1] == 'O'){
       return CellValue.O;
     }
     else if ((position - 1) < 0 || (position - 1) > ((this.numRows * this.numColumns) -1)){
       return CellValue.INVALID;
     }
     else {
       return CellValue.EMPTY;
     }

  }

  /**
   * Display the state of the board
   * And ask the next player to play.
   * Return the messages as an array of
   * Strings so that the caller can decide
   * how to display them (and it makes things
   * easier to test)
   * @return An array of messages to display.
   */
  public String[] show() {

    // YOUR CODE HERE

  }

  /**
   * The next player has decided their move to the
   * provided position.
   *
   *
   *  1  |  2  | 3  | 4
   * --------------------
   *  5  |  6  | 7  | 8
   * --------------------
   *  9  | 10  | 11 | 12
   *
   * A position is invalid if:
   * a) It's off the board (e.g. based on the above < 1 or > 12)
   * b) That cell is not empty (i.e. it's no longer available)
   *
   * If the position is invalid, an error should be printed out.
   *
   * If the position is valid, then
   * a) Update the board
   * b) Update the state of the game
   * c) Allow the next player to play.
   *
   * A game can continue even after a winner is declared.
   * If that is the case, a message should be printed out
   * (that the game is infact over), but the move should
   * still be recorded.
   *
   * The winner of the game is the player who won first.
   * @param position The position that has been selected by the next player.
   * @return A message about the current play (see tests for details)
   */
  public String play(int position) {


    // YOUR CODE HERE
    // HINT: use checkForWinner(position)
  }

  /**
   * A help method to determine if the game has been won
   * to be called after a player has played
   *
   * This method is called after the board has been updated
   * and provides the last position that was played
   * (to help you analyze the board).
   *
   * @param position The middle position to start our check
   * @return GameState to show if XWIN or OWIN.  If the result was a DRAW, or if
   *         the game is still being played.
   */
  private GameState checkForWinner(int position) {
    boolean win = false;
    int row = (position -1) / this.numColumns;
    int counter = 0;
    if(this.currentPlayer == 'X'){
      //Check Horizontal
    
      for(int i = 1; i < this.sizeToWin; i++){

        if(valueAt(position + i) == CellValue.X){
          counter += 1;
          if (counter == this.sizeToWin){
            return GameState.XWIN;
          }
        }
        else if (valueAt(position + i) != CellValue.X || ((position - 1 + i) / this.numColumns) != row){
          
        }
        

      }







    }
      }
      
      
    }
    while(win == true){
      //Check for horizontal

    }
    // YOUR CODE HERE
    // HINT: call this within your `play` method

  }

  /**
   * A text based representation of the 2D grid, and
   * should include all played Xs and Os.  For example
   * On a 3x3 board.  (HINT: \n for newlines)
   *
   *    | X | O
   * -----------
   *  O |   |
   * -----------
   *    |   |
   *
   * @return String representation of the game
   */
  public String toString() {
    String slot = "";
    int cellPosition; // = (((row - 1) * number of col.) + column) - 1

    for(int i = 0; i < this.numRows; i++){
      //following statement is evalulated when at the last row (no divider)
      if(i == this.numRows -1){
        for (int j = 0; j < this.numColumns; j++){
          cellPosition = ((i * this.numColumns) + j);
          //following statement is evaluated when at the last slot in a row
          if (j == this.numColumns - 1){
            slot += " " + this.board[cellPosition] +" " + "\n";
          }
          //following statement is evaluated normally
          else{
            slot += " " + this.board[cellPosition] +" |";
          }
        }
      }
      //following statement is evaulated normally
      else{
        for (int j = 0; j < this.numColumns; j++){
          cellPosition = ((i * this.numColumns) + j);
          //following statement is evaluated when at the last slot in a row
          if (j == this.numColumns - 1){
            slot += " " + this.board[cellPosition] +" " + "\n";
          }
          //following statement is evaluated normally
          else{
            slot += " " + this.board[cellPosition] +" |";
          }
        }
        for (int k = 0; k < this.numColumns; k++){
          if(k == this.numColumns -1){
            slot += "---" + "\n";
          }
          else{
            slot += "----";
          }
        }
      }
    }

    return slot;

  }

  /**
   * Expose all internal data for debugging purposes.
   *
   * @return String representation of the game
   */
  public String toDebug() {
    StringBuilder b = new StringBuilder();
    b.append("Grid (rows x columns): " + numRows + " x " + numColumns);
    b.append("\n");
    b.append("Size To Win: " + sizeToWin);
    b.append("\n");
    b.append("Num Rounds: " + numRounds);
    b.append("\n");
    b.append("Game State: " + gameState);
    b.append("\n");
    b.append("Current Player: " + currentPlayer);
    b.append("\n");
    b.append("Next Player: " + nextPlayer());
    b.append("\n");

    b.append("Board (array): [");
    for (int i=0; i<board.length; i++) {
      if (i > 0) {
        b.append(",");
      }
      b.append(board[i]);
    }
    b.append("]\n");

    return b.toString();
  }
}
