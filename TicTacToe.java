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
    //Values based on traditional Tic Tac Toe board
    this.board = new char[9];
    this.numColumns = 3;
    this.numRows = 3;
    this.numRounds = 0;
    this.sizeToWin = 3;
    this.gameState = GameState.PLAYING;
    this.currentPlayer = CellValue.EMPTY;

    //Makes the board empty
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

    //Makes the board empty
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
    //At first the value of current player is empty.
    if (this.currentPlayer == CellValue.EMPTY){ 
      return CellValue.X;
    }
    else if (this.currentPlayer == CellValue.O){
      return CellValue.X;
    }
    else if (this.currentPlayer == CellValue.X) {
      return CellValue.O;
    }
    else{
      return null;
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
    //converts 'play position' to 'array position'
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

    //2D position position converted to 1D position, valid for all sizes of arrays
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
    //First part of string array is visual representation of board, second part based on game state.
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
    //Following section for invalid positions.
    if(valueAt(position) == CellValue.INVALID){
      message = "The value should be between 1 and " + this.board.length;
      return message;
    }
    else if(valueAt(position) == CellValue.X){
      message = "Cell " + position + " has already been played with X";
      return message;
    }
    else if(valueAt(position) == CellValue.O){
      message = "Cell " + position + " has already been played with O";
      return message;
    }
    //Following section for valid positions.
    else{
      //Following section executes if x plays.
      if (nextPlayer() == CellValue.X) {
        
        //Adds their play to the board array and increments the round counter.
        this.board[position - 1] = 'X';
        this.numRounds+= 1;

        //if win occurs, doesnt change game state.
        if(this.gameState == GameState.OWIN || this.gameState == GameState.XWIN){
          this.currentPlayer = nextPlayer();
          return null;
        }
        
        //following if statements check if there is a winner or not, then update game state, change current player and prompts user regarding game state.
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
      
      //Following section executes if o plays.
      if (nextPlayer() == CellValue.O) {
        //Adds their play to the board array and increments the round counter.
        this.board[position - 1] = 'O';
        this.numRounds+= 1;
        
        //If there is already a winner, doesnt change game state
        if(this.gameState == GameState.XWIN || this.gameState == GameState.OWIN){
          this.currentPlayer = nextPlayer();
          return null;
        }

        //following if statements check if there is a winner or not, then update game state, change current player and prompts user regarding game state.
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
    //The following booleans are used to ensure that the next cell, in the direction being checked, is to be counted towards a potential win.
    //the following are used to check for a horizontal -
    boolean horizontalRight = true;
    boolean horizontaLeft = true;
    //the following are used to check for a forward diagonal: /
    boolean upRight = true;
    boolean downLeft = true;
    //the following are used to check for a backward diagonal: \
    boolean upLeft = true;
    boolean downRight = true;
    //the following are used to check for a vertical |
    boolean up = true;
    boolean down = true;
    //the position's row is precalculated and will be used to compare to subsequent values in the array.
    int row = (position - 1) / this.numColumns;
    //The counter is used to count how many Xs or Os are in a row, the counter starts at 1 because we are checking after a play.
    int counter = 1;
    //an increment value is used for checking the different cases eg: vertical, forward diagonal, etc.
    int increment = 0;
    //check is the cell value to compared to based on the player, the state is who wins based on who played. 
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
    //iterates from 1 till size of win. Checks both directions simultaneously. 
    //if a cell is not in the same row or does not have desired value, the boolean in that direction is set to false and the program stops checking in that direction.
    for(int i = 1; i < this.sizeToWin; i++){
      //The following 2 if statements detect if the cell value in the next horizontal position (both directions) is invalid
      if (valueAt(position + i) != check || (((position - 1) + i) / this.numColumns) != row){
        horizontalRight = false;
      }
      if (valueAt(position - i) != check || (((position - 1) - i) / this.numColumns) != row){
        horizontaLeft = false;
      }
      
      //the following 2 if statements will increment the counter if the next cell value is valid (both directions) 
      //if the counter reaches the size to win it will return which player won.
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
      
    }

    //Check Vertical
    //iterates from 1 till size of win. Checks both directions simultaneously. 
    //if a cell does not have desired value, the boolean in that direction is set to false and the program stops checking in that direction.
    increment = 0;
    counter = 1;
    for(int i = 1; i < this.sizeToWin; i++){
      //The increment is set to the number of columns to ensure that the program checks in the same column.
      increment = i * this.numColumns;

      //The following 2 if statements detect if the cell value in the next vertical position (both directions) is invalid
      if (valueAt(position + increment) != check){
        down = false;
      }
      if (valueAt(position - increment) != check ){
        up = false;
      }

      //the following 2 if statements will increment the counter if the next cell value is valid (both directions) 
      //if the counter reaches the size to win it will return which player won.
      if(valueAt(position + increment) == check && down == true){
        counter += 1;
        if (counter == this.sizeToWin){
          return state;
        }
      }
      if(valueAt(position - increment) == check && up == true){
        counter += 1;
        if (counter == this.sizeToWin){
          return state;
        }
      }
    }

    //Check forward diagonal
    //iterates from 1 till size of win. Checks both directions simultaneously. 
    //if a cell does not have desired value or is not in the same diagonal, the boolean in that direction is set to false and the program stops checking in that direction.
    increment = 0;
    counter = 1;
    for(int i = 1; i < this.sizeToWin; i++){
      //the increment is set to num of columns - 1 to traverse the board in a forward diagonal
      increment = i * (this.numColumns - 1);

      //The following 2 if statements detect if the cell value in the next forward diagonal position (both directions) is invalid or if it is not in the same diagonal
      if (valueAt(position + increment) != check || ((position - 1 + increment - (this.numColumns - 1)) / this.numColumns) == ((position - 1 + increment) / this.numColumns)){
        downLeft = false;
      }
      if (valueAt(position - increment) != check || ((position - 1 - increment + (this.numColumns - 1)) / this.numColumns) == ((position - 1 - increment) / this.numColumns)){
        upRight = false;
      }

      //the following 2 if statements will increment the counter if the next cell value is valid (both directions) 
      //if the counter reaches the size to win it will return which player won.
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
    //iterates from 1 till size of win. Checks both directions simultaneously. 
    //if a cell does not have desired value or is not in the same diagonal, the boolean in that direction is set to false and the program stops checking in that direction.
    increment = 0;
    counter = 1;
    for(int i = 1; i < this.sizeToWin; i++){
      //the increment is set to num of columns - 1 to traverse the board in a forward diagonal
      increment = i * (this.numColumns+1);

      //The following 2 if statements detect if the cell value in the next backward diagonal position (both directions) is invalid or if it is not in the same diagonal
      if (valueAt(position + increment) != check || ((position - 1 + increment - (this.numColumns + 1)) / this.numColumns) == ((position - 1 + increment) / this.numColumns)){
        downRight = false;
      }
      if (valueAt(position - increment) != check || ((position - 1 - increment + (this.numColumns + 1)) / this.numColumns) == ((position - 1 - increment) / this.numColumns)){
        upLeft = false;
      }

      //the following 2 if statements will increment the counter if the next cell value is valid (both directions) 
      //if the counter reaches the size to win it will return which player won.
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
    //checks for a draw. If a cell value is empty it will keep the gamestate as playing; if the board is full and no winner has been found the game state is a draw.
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
    int cellPosition; 

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
        //Following section adds the dashed lines between the rows
        for (int k = 0; k < this.numColumns; k++){
          //following statement is at the last set of dashed lines
          if(k == this.numColumns -1){
            slot += "---" + "\n";
          }
          //following statement is evaluated normally
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
