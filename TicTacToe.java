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
  GameState gameState;

  /**
   * How many cells of the same type must be
   * aligned (vertically, horizontally, or diagonally)
   * to determine a winner of the game
   */
  int sizeToWin;

  /**
   * Who is the current player?
   */
  CellValue currentPlayer;


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
    this.gameState = GameState.PLAYING;
    this.currentPlayer = CellValue.EMPTY;

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
    this.gameState = GameState.PLAYING;
    this.currentPlayer = CellValue.EMPTY;

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
    CellValue value = CellValue.EMPTY;
    if (this.currentPlayer == CellValue.EMPTY){ 
      value = CellValue.X;
    }
    if (this.currentPlayer == CellValue.O){
      value = CellValue.X;
    }
    if (this.currentPlayer == CellValue.X) {
      value = CellValue.O;
    }
    return value;
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
    position = position -1;
    if (position < 0 || position > ((this.board.length) - 1)){
       return CellValue.INVALID;
     }
     if (this.board[position] == 'X'){
       return CellValue.X;
     }
     if (this.board[position] == 'O'){
       return CellValue.O;
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

    int position = (((row - 1) * this.numColumns) + column) - 1;

    if (position < 0 || position > ((this.board.length) - 1)){
       return CellValue.INVALID;
    }
    if (this.board[position] == 'X'){
       return CellValue.X;
     }
    if (this.board[position] == 'O'){
       return CellValue.O;
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
    String[] view = new String[2];
    view[0] = toString();
    if (this.gameState == GameState.XWIN){
      view[1] = "Result: XWIN";
    }
    if (this.gameState == GameState.OWIN){
      view[1] = "Result: OWIN";
    }
    if (this.gameState == GameState.DRAW){
      view[1] = "Result: DRAW";
    }
    if (nextPlayer() == CellValue.X){
      view[1] = "X to play: ";
    }
    if (nextPlayer()  == CellValue.O) {
      view[1] = "O to play: ";
    }
    
    return view;
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
    String message = "";
    if(valueAt(position) == CellValue.INVALID){
      message = "The value should be between 1 and " + this.board.length;
      return message;
    }
    if(valueAt(position) == CellValue.X){
      message = "Cell " + position + " has already been played with X";
      return message;
    }
    if(valueAt(position) == CellValue.O){
      message = "Cell " + position + " has already been played with O";
      return message;
    }
    else{
      if (nextPlayer() == CellValue.X) {

        // if( this.currentPlayer == CellValue.EMPTY){
        //   this.currentPlayer = nextPlayer();
        // }
        
        this.board[position - 1] = 'X';
        this.numRounds+= 1;

        if(this.gameState == GameState.OWIN){
          this.currentPlayer = nextPlayer();
          return null;
        }
        
        if(checkForWinner(position) == GameState.XWIN){
          this.gameState = GameState.XWIN;
          this.currentPlayer = nextPlayer();
          message = "Result: XWIN";
          return message;
        }
        if(checkForWinner(position) == GameState.PLAYING){
          this.gameState = GameState.PLAYING;
          this.currentPlayer = nextPlayer();
          return null;
        }
        if(checkForWinner(position) == GameState.DRAW){
          this.gameState = GameState.DRAW;
          this.currentPlayer = nextPlayer();
          message = "Result: DRAW";
          return message;
        }
      }
      

      if (nextPlayer() == CellValue.O) {

        this.board[position - 1] = 'O';
        this.numRounds+= 1;
        
        if(this.gameState == GameState.XWIN){
          this.currentPlayer = nextPlayer();
          return null;
        }

        if(checkForWinner(position) == GameState.OWIN){
          this.gameState = GameState.OWIN;
          this.currentPlayer = nextPlayer();
          message = "Result: OWIN";
          return message;
        }
        if(checkForWinner(position) == GameState.PLAYING){
          this.gameState = GameState.PLAYING;
          this.currentPlayer = nextPlayer();
          return null;
        }
        if(checkForWinner(position) == GameState.DRAW){
          this.gameState = GameState.DRAW;
          this.currentPlayer = nextPlayer();
          message = "Result: DRAW";
          return message;
        }
      }
      
    }

    return null;
   
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
    boolean horizontalRight = true;
    boolean horizontaLeft = true;
    boolean upRight = true;
    boolean upLeft = true;
    boolean downRight = true;
    boolean downLeft = true;
    boolean up = true;
    boolean down = true;
    int row = (position -1) / this.numColumns;
    int counter = 1;
    int increment = 0;
    CellValue check = CellValue.EMPTY;
    GameState state = GameState.PLAYING;

    if(nextPlayer() == CellValue.X){
      check = CellValue.X;
      state = GameState.XWIN;
    }

    if(nextPlayer() == CellValue.O){
      check = CellValue.O;
      state = GameState.OWIN;
    }


    
    //Check Horizontal
    for(int i = 1; i < this.sizeToWin; i++){
      if(valueAt(position + i) == check && horizontalRight == true){
        counter += 1;
        if (counter == this.sizeToWin){
          return state;
        }
      }
      if(valueAt(position - i) == check && horizontaLeft == true){
        counter += 1;
        if (counter == this.sizeToWin){
          return state;
        }
      }
      if (valueAt(position + i) != check || ((position - 1 + i) / this.numColumns) != row){
        horizontalRight = false;
      }
      if (valueAt(position - i) != check || ((position - 1 - i) / this.numColumns) != row){
        horizontaLeft = false;
      }
    }

    //Check Vertical
    increment = 0;
    counter = 1;
    for(int i = 1; i < this.sizeToWin; i++){
      increment = i * this.numColumns;
      if(valueAt(position + increment) == check && up == true){
        counter += 1;
        if (counter == this.sizeToWin){
          return state;
        }
      }
      if(valueAt(position - increment) == check && down == true){
        counter += 1;
        if (counter == this.sizeToWin){
          return state;
        }
      }
      if (valueAt(position + increment) != check){
        up = false;
      }
      if (valueAt(position - increment) != check ){
        down = false;
      }
    }

    //Check forward diagonal
    increment = 0;
    counter = 1;
    for(int i = 1; i < this.sizeToWin; i++){
      increment = i * (this.numColumns - 1);
      if (valueAt(position + increment) != check || ((position - 1 + increment - (this.numColumns - 1)) / this.numColumns) == ((position - 1 + increment) / this.numColumns)){
        downLeft = false;
      }
      if (valueAt(position - increment) != check || ((position - 1 - increment + (this.numColumns - 1)) / this.numColumns) == ((position - 1 - increment) / this.numColumns)){
        upRight = false;
      }
      if(valueAt(position + increment) == check && downLeft == true){
        counter += 1;
        if (counter == this.sizeToWin){
          return state;
        }
      }
      if(valueAt(position - increment) == check && upRight == true){
        counter += 1;
        if (counter == this.sizeToWin){
          return state;
        }
      }
      
    }

    //Check backward diagonal
    increment = 0;
    counter = 1;
    for(int i = 1; i < this.sizeToWin; i++){
      increment = i * (this.numColumns+1);
      if (valueAt(position + increment) != check || ((position - 1 + increment - (this.numColumns + 1)) / this.numColumns) == ((position - 1 + increment) / this.numColumns)){
        downRight = false;
      }
      if (valueAt(position - increment) != check || ((position - 1 - increment + (this.numColumns + 1)) / this.numColumns) == ((position - 1 - increment) / this.numColumns)){
        upLeft = false;
      }
      if(valueAt(position + increment) == check && downRight == true){
        counter += 1;
        if (counter == this.sizeToWin){
          return state;
        }
      }
      if(valueAt(position - increment) == check && upLeft == true){
        counter += 1;
        if (counter == this.sizeToWin){
          return state;
        }
      }
      
    }

    counter = 0;
    for(int i = 0; i < this.board.length; i++){
      if(valueAt(i+1) == CellValue.EMPTY){
        return GameState.PLAYING;
      }
      else{
        counter += 1;
        if(counter == this.board.length){
          return GameState.DRAW;
        }
      }
    }
  return null;
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
            slot += " " + this.board[cellPosition] +" ";
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
            slot += " " + this.board[cellPosition] +" \n" ;
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
