/**
This program allows users to play a two-person game of Battleship. Each player
places their ships strategically in an 8x8 game board, and then each player tries
to guess the location of the other player's ships. The first to guess all
correctly wins!
*/

import java.util.*;

public class BattleshipGame{

  /**
  board1 is the board that player 1 places their ships on.
  */
  static String[][] board1 = new String[8][8];
  /**
  board2 is is the board that player 2 places their ships on.
  */
  static String[][] board2 = new String[8][8];
  /**
  s1 represents the 5 ships player one has to place on their board.
  */
  static boolean[] s1 = {true, true, true, true, true};
  /**
  s2 represents the 5 ships player two has to place on their board.
  */
  static boolean[] s2 = {true, true, true, true, true};
  /**
  p1ships stores the locations of player one's ships.
  */
  static int[][] p1ships = {{0,0},{0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
  /**
  p2ships stores the locations of player two's ships.
  */
  static int[][] p2ships = {{0,0},{0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
  /**
  b is a boolean that many classes communicate with.
  */
  static boolean b = false;
  /**
  p1 is the name of player one.
  */
  static String p1 = "";
  /**
  p2 is the name of player two.
  */
  static String p2 = "";
  /**
  p1board is the board of player one's guesses.
  */
  static String[][] p1board = new String[8][8];
  /**
  p2board is the board of player two's guesses.
  */
  static String[][] p2board = new String[8][8];

  /**
  The scanner reads input from the user.
  */
  static Scanner scan = new Scanner(System.in);


  /**
  The main method operates a game of battleship, by setting up the boards and
  then calling playGame().
  @param args is a String array that we ignore.
  */
  public static void main(String[] args){

    getPlayerNames();
    System.out.print("Enter the starting coordinates, followed by the ending ");
    System.out.println("coordinates, of where you want to play each ship.");
    System.out.print("To set the coordinates, you will enter four digits, ");
    System.out.print("one on each line. The first two numbers will be the ");
    System.out.print("the starting coordinates of your ship, the last two ");
    System.out.println("will be the ending coordinates of your ship.");
    System.out.println("Enter digits 1-8. As: x /newline y /newline x /newline y");
    System.out.print("You must enter valid digits so that the ship would lay ");
    System.out.print("flat on a physical board. Either the x coordinates ");
    System.out.print("must be the same, the y coordinates must be the same, or ");
    System.out.println("both the x and y coordinates must differ by the same amount.");
    System.out.println("You will place five ships: Of lengths 1, 2, 3, 4, and 5.");
    board1Set(p1);
    board2Set(p2);
    System.out.println("We are ready to begin!");
    System.out.println("Set the coordinates of the ship you want to hit!");


    createPBoards();

    playGame();

    b = false;
  }

  /**
  playGame()
  playGame operates the actual playing of the game. It allows each user to
  choose a target on their turn. It checks for whether a player is won, and
  eventually congraluates the winner.
  It takes no input.
  It returns nothing.
  */

  public static void playGame(){
    int count = 0;
    do{
      String p = "";
      String[][] board = board2;
      String[][] pboard = p1board;
      int[][] ships = p2ships;
      if(count%2==0){
        p = p1;
      }
      else{
        p = p2;
        board = board1;
        pboard = p2board;
        ships = p1ships;
      }
      System.out.printf("%s, choose a target: %n",p);
      int x = readInt();
      int y = readInt();
      missileFire(board2, x, y, pboard, ships);
      b = gameOver(ships);
      if(b){
      System.out.printf("Congratulations, %s, you won!%n", p);
      }
      count++;
    }while(!b);
  }

  /**
  readInt()
  This method reads in the coordinate the user enters.
  It takes no input.
  @return returns the input.
  */
  public static int readInt(){
    int x = 0;
    boolean d = false;
    do{
      if(scan.hasNextInt()){
        x = scan.nextInt()-1;
        if(x<0||x>7){
          System.out.println("Enter a valid integer: ");
        }
        else{
          d = true;
        }
      }
      else{
        scan.next();
        System.out.println("Enter a valid integer: ");
      }
    }while(!d);
    return x;
  }

  /**
  createsPBoards()
  This method creates empty boards for each user, Which they will use to track
  their attacks on the opponent's ships.
  It takes no input.
  It returns nothing.
  */
  public static void createPBoards(){
    for(int i = 0; i<p1board.length; i++){
      for(int j = 0; j<p1board[i].length;j++){
        p1board[i][j] = "0";
      }
    }
    for(int i = 0; i<p2board.length; i++){
      for(int j = 0; j<p2board[i].length;j++){
        p2board[i][j] = "0";
      }
    }
  }

  /**
  getPlayerNames()
  This method gets the names of the players.
  It takes no input.
  It returns nothing.
  */

  public static void getPlayerNames(){
    System.out.println("Welcome to Battleship. Sink your opponents' ships to win!");
    do{
      System.out.println("Player Number One, enter your name: ");
      if(scan.hasNext()){
        p1 = scan.next();
      }
      System.out.println("Player Number Two, enter your name: ");
      if(scan.hasNext()){
        p2 = scan.next();
      }
    }while(b);
  }

  /**
  board1Set(p1)
  This method allows player one to place her ships on his board.
  @param p1 is the name of player one.
  It retuns nothing.
  */
  public static void board1Set(String p1){
    System.out.printf("%s, set your ships!%n",p1);
    b = false;
    for(int i = 0; i<board1[0].length; i++){
      for(int j = 0; j<board1[1].length; j++){
        board1[i][j] = "0";
      }
    }
    printBoard(board1);
    do{
      setBoardCoordinates1();
      printBoard(board1);
    }while(!b);
    System.out.printf("%s is set!%n",p1);

  }

  /**
  setBoardCoordinates()
  This method allows player one to set the coordinates of their ships.
  It takes no input.
  It returns nothing.
  */
  public static void setBoardCoordinates1(){
    int w = readInt();
    int x = readInt();
    int y = readInt();
    int z = readInt();
    setShip1(w,x,y,z);
    b = setComplete(s1);
  }

  /**
  board2Set(p1)
  This method allows player two to place his ships on his board.
  @param p2 is the name of player two.
  It retuns nothing.
  */
  public static void board2Set(String p2){
    System.out.printf("%s, set your ships!%n",p2);
    b = false;
    for(int i = 0; i<board2[0].length; i++){
      for(int j = 0; j<board2[1].length; j++){
        board2[i][j] = "0";
      }
    }
    printBoard(board2);
    do{
      setBoardCoordinates2();
      printBoard(board2);
    }while(!b);
    System.out.printf("%s is set!%n",p2);

  }

  /**
  setBoardCoordinates2()
  This method allows player two to set the coordinates of their ships.
  It takes no input.
  It returns nothing.
  */
  public static void setBoardCoordinates2(){
    int w = readInt();
    int x = readInt();
    int y = readInt();
    int z = readInt();
    setShip2(w,x,y,z);
    b = setComplete(s2);

  }

  /**
  setShip1(w,x,y,z)
  This method takes in the coordinates player one entered that instruct where to
  place their ship, and calls methods that ensure they are valid and then
  place them on the board.
  @param w the first x coordinate.
  @param x the first y coordinate.
  @param y the second x coordinate.
  @param z the second y coordinate.
  It returns nothing.
  */
  public static void setShip1(int w, int x, int y, int z){

    if(w==y&&x==z){
      searchShipArea1(w, x, y, z, s1, board1, p1ships);
    }

    else if(w==y&&(z!=y||w!=x)){
      searchShipHorizontal(w, x, y, z, s1, board1, p1ships);
    }



    else if(x==z&&(w!=z||y!=x)){
      searchShipVertical(w, x, y, z, s1, board1, p1ships);
    }

    else if(y-w==z-x){
      searchShipDiagonal(w, x, y, z, s1, board1, p1ships);
    }

    else{
      System.out.println("Your coordinates are invalid");
    }
  }

  /**
  setShip2(w,x,y,z)
  This method takes in the coordinates player two entered that instruct where to
  place their ship, and calls methods that ensure they are valid and then
  place them on the board.
  @param w the first x coordinate.
  @param x the first y coordinate.
  @param y the second x coordinate.
  @param z the second y coordinate.
  It returns nothing.
  */

  public static void setShip2(int w, int x, int y, int z){

    if(w==y&&x==z){
      searchShipArea1(w, x, y, z, s2, board2, p2ships);
    }

    else if(w==y&&(z!=y||w!=x)){
      searchShipHorizontal(w, x, y, z, s2, board2, p2ships);
    }



    else if(x==z&&(w!=z||y!=x)){
      searchShipVertical(w, x, y, z, s2, board2, p2ships);
    }

    else if(y-w==z-x){
      searchShipDiagonal(w, x, y, z, s2, board2, p2ships);
    }

    else{
      System.out.println("Your coordinates are invalid");
    }
  }

  /**
  searchShipDiagonal(w, x, y, z, s, board, pships)
  This method sets a ship that is oriented diagonally.
  @param w the first x coordinate.
  @param x the first y coordinate.
  @param y the second x coordinate.
  @param z the second y coordinate.
  @param s the proper array of ships to be placed
  @param board the proper board the user is placing their ships on
  @param pships the proper array of ship placement.
  It returns nothing.
  */

  public static void searchShipDiagonal(int w, int x, int y, int z, boolean[] s, String[][] board, int[][] pships){
    int a = y-w;
    if(a<0||a>5){
      System.out.println("Retry. The second coordinates must be greater than the first: ");
    }
    else{
      boolean b = true;
      if(s[a]==true){
        int xcopy = x;
        loop1:
        for(int i = w; i<=y; i++){
          for(int j = xcopy; j<=z; j++){
            System.out.println(i + " " + j);
            b = checkShip(i,j, board);
            if(!b){
              break loop1;
            }
            xcopy++;
            continue loop1;
          }
        }
        if(b){
          int count = 0;
          loop2:
          for(int i = w; i<=y; i++){
            for(int j = x; j<=z; j++){
              board[i][j] = "X";
              pships[a][count] = x+1;
              count++;
              pships[a][count] = i+1;
              count++;
              x++;
              continue loop2;
            }
          }
          s[a]=false;
        }
      }
      else{
        System.out.println("You've already set a ship of that length!");
      }
    }
  }

  /**
  searchShipVertical(w, x, y, z, s, board, pships)
  This method sets a ship that is oriented vertically.
  @param w the first x coordinate.
  @param x the first y coordinate.
  @param y the second x coordinate.
  @param z the second y coordinate.
  @param s the proper array of ships to be placed
  @param board the proper board the user is placing their ships on
  @param pships the proper array of ship placement.
  It returns nothing.
  */

  public static void searchShipVertical(int w, int x, int y, int z, boolean[] s, String[][] board, int[][] pships){
    int a = y-w;
    if(a<0||a>5){
      System.out.println("Retry. The second coordinates must be greater than the first: ");
    }
    else{
      boolean b = true;
      if(s[a]==true){
        for(int i = w; i<=y; i++){
          b = checkShip(i,x, board);
          if(!b){
            break;
        }
      }
      if(b){
        int count = 0;
        for(int i = w; i<=y; i++){
          board[i][x] = "X";
          pships[a][count] = i+1;
          count++;
          pships[a][count] = x+1;
          count++;
        }
        s[a]=false;
      }
    }
    else{
      System.out.println("You've already set a ship of that length!");
    }
  }


  }

  /**
  searchShipLength1(w, x, y, z, s, board, pships)
  This method sets a ship that has an area of 1.
  @param w the first x coordinate.
  @param x the first y coordinate.
  @param y the second x coordinate.
  @param z the second y coordinate.
  @param s the proper array of ships to be placed
  @param board the proper board the user is placing their ships on
  @param pships the proper array of ship placement.
  It returns nothing.
  */

  public static void searchShipArea1(int w, int x, int y, int z, boolean[] s, String[][] board, int[][] pships){
    int a = y-w;
    if(a<0||a>5){
      System.out.println("Retry. The second coordinates must be greater than the first: ");
    }
    else{
      boolean b = true;
      if(s[a]==true){
        b = checkShip(w,x, board);
        if(b){
          int count = 0;
          board[w][x] = "X";
          pships[a][count] = w+1;
          count++;
          pships[a][count] = x+1;
          s[a]=false;
        }
      }
      else{
        System.out.println("You've already set a ship of that length!");
      }
    }
  }

  /**
  searchShipHorizontal(w, x, y, z, s, board, pships)
  This method sets a ship that is oriented horizontally.
  @param w the first x coordinate.
  @param x the first y coordinate.
  @param y the second x coordinate.
  @param z the second y coordinate.
  @param s the proper array of ships to be placed
  @param board the proper board the user is placing their ships on
  @param pships the proper array of ship placement.
  It returns nothing.
  */

  public static void searchShipHorizontal(int w, int x, int y, int z, boolean[] s, String[][] board, int[][] pships){
    int a = z-x;
    boolean b = true;
    if(a<0||a>5){
      System.out.println("Retry. The second coordinates must be greater than the first: ");
    }
    else{
      if(s[a]==true){
        for(int i =x; i<=z; i++){
          b = checkShip(w,i, board);
          if(!b){
            break;
          }
        }

        if(b){
          int count = 0;
          for(int i = x; i<=z; i++){
            board[w][i] = "X";
            pships[a][count] = w+1;
            count++;
            pships[a][count] = i+1;
            count++;
          }
          s[a]=false;
        }
      }
    else{
      System.out.println("You've already set a ship of that length!");
    }
  }
}

  /**
  checkShip(x, y, board)
  This method checks to ensure that the user is not overlapping two ships.
  @param x is the x coordinate to checks
  @param y is the y coordinate to check
  @param board is the proper board of the user
  @return is a boolean, true if there is no ship there and false if there is
  */

  public static boolean checkShip(int x, int y, String[][] board){
    boolean b = true;
    if(board[x][y].equals("X")){
      System.out.println("You already have a ship there!");
      b = false;
    }
    return b;
  }

  /**
  printBoard(board)
  This method prints out a game board.
  @param board is the board to be printed.
  It returns nothing.
  */

  public static void printBoard(String[][] board){
    for(int i = 0; i < board[0].length; i++){
      for(int j = 0; j < board[1].length; j++){
        System.out.print(board[i][j]);
      }
      System.out.println();
    }
  }

  /**
  setComplete(s)
  This method checks to see whether or not the user has completed setting their
  ships
  @param s is the proper ship array of the user
  @return is a boolean, true if the user has finished setting and false if they
  haven't
  */

  public static boolean setComplete(boolean[] s){
    for(int i = 0; i <s.length; i++){
      if(s[i]==true){
        return false;
      }
    }
    return true;
  }

  /**
  missileFire(board, x, y, pboard, ships)
  This method allows the user to fire a missile at their opponent. It returns
  whether it was a hit or a miss, and updates the player's pboard accordingly.
  @param board the proper board the user is placing their ships on
  @param x is the x coordinate the user is firing at.
  @param y is the y coordinate the user is firing at.
  @param pboard is the proper array of the user's guesses.
  @param ships the proper array of ships to be placed

  It returns nothing.
  */

  public static void missileFire(String[][] board, int x, int y, String[][] pboard, int[][] ships){
    if(board[x][y].equals("X")){
      System.out.println("You hit!");
      pboard[x][y] = "X";
      hitShip(x, y, ships);
    }
  else{
    System.out.println("You missed!");
    pboard[x][y]="N";
    }
  printBoard(pboard);
  }

  /**
  hitShip(x,y,ships)
  This method checks whether a missile fire hit a ship or not.
  @param x is the x coordinate the user is firing at.
  @param y is the y coordinate the user is firing at.
  @param ships is the proper ship array to check.
  It returns nothing.
  */
  public static void hitShip(int x, int y, int[][] ships){
    x++;
    y++;
    for(int i = 0; i<ships.length; i++){
      for(int j = 0; j<ships[i].length; j+=2){
        if((ships[i][j]==x)&&(ships[i][j+1]==y)){
          ships[i][j] = 0;
          ships[i][j+1] = 0;
          boolean b = shipLife(ships, i);
          if(b){
            System.out.println("Congratulations, you sunk a ship!");
          }
        }
      }
    }
  }

  /**
  shipLife(ships, i)
  This method checks to see whether a ship is dead or not.
  @param ships is the proper ship array to check
  @param i is the proper ship to check the coordinates of
  @return is a boolean, true if the ship is dead and false if it is not
  */

  public static boolean shipLife(int[][] ships, int i){
    for(int j = 0; j<ships[i].length; j++){
      if(ships[i][j]!=0){
        return false;
      }
    }
    return true;
  }

  /**
  gameOver(ships)
  This method checks to see whether a player has won - whether all of a players'
  ships have been vanquished.
  @param ships is the proper array of ships to check.
  @return boolean is true if the game is over, and false if it is not.
  */

  public static boolean gameOver(int[][] ships){
    for(int i = 0; i<ships.length; i++){
      for(int j = 0; j<ships[i].length; j++){
        if(ships[i][j]!=0){
          return false;
        }
      }
    }
    return true;
  }
}
