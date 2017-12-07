/**
TicTacToeGame
This program allows the user to play a simple game of two person Tic Tac Toe.
*/

import java.util.*;

public class TicTacToeGame{
  /**
  This is the board on which the game will be played
  */
  public static String[][] board = {{"0","0","0"},{"0","0","0"},{"0","0","0"}};
  /**
  This is the scanner, to read user input
  */
  public static Scanner scan = new Scanner(System.in);
  /**
  p1 and p2 are the names of the players. p keeps track of who the current
  player is.
  */
  public static String p1 = "";
  public static String p2 = "";
  public static String p = "";

  /**
  x and y are variables to store coordinates on the game board
  */
  public static int x = 0;
  public static int y = 0;

  /**
  The main method introduces the game, asks the users for their names, and
  then calls playGame() to play a Tic Tac Toe Game.
  @param args an array of Strings which we ignore
  It returns nothing.
  */
  public static void main(String[] args){

    System.out.println("Welcome to Tic Tac Toe! Obtain three squares in a row to win!");

    System.out.println("Player Number One, enter your name: ");
    if(scan.hasNext()){
      p1 = scan.next();
    }
    System.out.println("Player Number Two, enter your name: ");
    if(scan.hasNext()){
      p2 = scan.next();
    }

    playGame();


  }

  /**
  playGame()
  This method allows the player to keep playing the game, by calling
  playOneMove(), until a victor is decided. Then it congraluates the winner.
  It takes no input.
  It returns nothing.
  */

  public static void playGame(){
    boolean b = false;

    do{
      b = playOneMove();

    }while(!b);

    System.out.println(p+" wins!");
  }

  /**
  printBoard()
  This method prints out the current state of the Tic Tac Toe board.
  It takes no input.
  It returns nothing.
  */

  public static void printBoard(){
    System.out.println("_____________");
    for(int i = 0; i<board[0].length; i++){
      System.out.print("|");
      for(int j = 0; j<board[1].length; j++){
        if(board[i][j].equals("0")){
          System.out.print("   |");
        }
        else{
          System.out.print(" " + board[i][j] + " |");
        }
      }
      System.out.println();
      System.out.print("_____________");
      System.out.println();
    }
  }

  /**
  gameOver()
  This method checks to see if a player has won the game yet.
  It returns true if a player has won the game, and false if a player has not.
  */

  public static boolean gameOver(){
    for(int i = 0; i< board[0].length; i++){
      for(int j = 0; j< board[1].length; j++){
        if(!(board[i][j].equals("0"))){
          String a = board[i][j];
          boolean b = horizontalSearch(i, j, a);
          if(b){
            return true;
          }

          b = verticalSearch(i,j,a);
          if(b){
            return true;
          }

          b = diagonalSearch(i,j,a);
          if(b){
            return true;
          }

          return false;
        }
      }
    }
    return false;
  }

  /**
  horizontalSearch(i,j,a)
  This method searches for wins on the board in the horizontal direction.
  @param i and @param j are the starting coordinates. @param a is an "X" or an
  "O", keeping track of what letters to check for.
  @return is a boolean. If true, a win has been found. If false, no win has
  been found.
  */

  public static boolean horizontalSearch(int i, int j, String a){
    if(j==0){
      if(board[i][j+1].equals(a)){
        if(board[i][j+2].equals(a)){
          return(true);
        }
      }
    }
    if(j==2){
      if(board[i][j-1].equals(a)){
        if(board[i][j-2].equals(a)){
          return(true);
        }
      }
    }
    return false;
  }

  /**
  verticalSearch(i,j,a)
  This method searches for wins on the board in the vertical direction.
  @param i and @param j are the starting coordinates. @param a is an "X" or an
  "O", keeping track of what letters to check for.
  @return is a boolean. If true, a win has been found. If false, no win has
  been found.
  */

  public static boolean verticalSearch(int i, int j, String a){
    if(i==2){
      if(board[i-1][j].equals(a)){
        if(board[i-2][j].equals(a)){
          return(true);
        }
      }
    }
    if(i==0){
      if(board[i+1][j].equals(a)){
        if(board[i+2][j].equals(a)){
          return(true);
        }
      }
    }
    return false;
  }

  /**
  diagonalSearch(i,j,a)
  This method searches for wins on the board in the diagonal direction.
  @param i and @param j are the starting coordinates. @param a is an "X" or an
  "O", keeping track of what letters to check for.
  @return is a boolean. If true, a win has been found. If false, no win has
  been found.
  */

  public static boolean diagonalSearch(int i, int j, String a){
    if(i==0&&j==0){
      if(board[i+1][j+1].equals(a)){
        if(board[i+2][j+2].equals(a)){
          return(true);
        }
      }
    }
    if(i==2&&j==2){
      if(board[i-1][j-1].equals(a)){
        if(board[i-2][j-2].equals(a)){
          return(true);
        }
      }
    }
    if(i==2&&j==0){
      if(board[i-1][j+1].equals(a)){
        if(board[i-2][j+2].equals(a)){
          return(true);
        }
      }
    }
    return false;
  }

  /**
  playOneMove()
  This method allows the user to play one move of the game, by entering in the
  coordinates of the square they wish to occupy. It checks to see whether the
  game has been won yet.
  It takes no input.
  It returns a value, true if a player has won the game and false if a player
  has not yet won the game.
  */

  public static boolean playOneMove(){
    boolean b = false;
    int count = 0;

    do{
      if(count%2==0){
        p = p1;
      }
      else{
        p = p2;
      }
      System.out.println(p + ", enter the coordinates (as integers) of where you wish to play: ");
      getCoordinates();
      count++;
      b = gameOver();
      printBoard();

    }while(!b);


    return b;
  }

  /**
  getCoordinates()
  This method reads the coordinates the user has entered, along with
  readSecondInput().
  It takes no input.
  It returns nothing.
  */

  public static void getCoordinates(){
    boolean b = false;

    do{
      if(scan.hasNextInt()){
        x = scan.nextInt();
        if(scan.hasNextInt()){
          b = readSecondInput();
        }
        else{
          scan.next();
        };
      }
      else{
        scan.next();
      }
    }while(!b);
  }

  /**
  readSecondInput()
  This method reads in the second coordinate number the user enters and checks
  to ensure the input is valid.
  It takes no input.
  It returns a boolean, true if the input is valid and false if it isn't.
  */

  public static boolean readSecondInput(){
    y = scan.nextInt();
    if((x>0&&x<4)&&(y>0&&y<4)){
      x--;
      y--;
      if(board[x][y].equals("0")){
          if(p.equals(p1)){
            board[x][y] = "O";
            return true;
          }
          else{
            board[x][y] = "X";
            return true;
          }
        }
        else{
          System.out.println("Enter valid coordinates.");
        }
      }
    else{
      System.out.println("Enter valid coordinates. ");
    }
    return false;
  }
}
