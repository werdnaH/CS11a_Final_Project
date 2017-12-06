import java.util.*;

public class TicTacToeGame{
  public static String[][] board = {{"0","0","0"},{"0","0","0"},{"0","0","0"}};
  public static Scanner scan = new Scanner(System.in);
  public static String p1 = "";
  public static String p2 = "";
  public static int x = 0;
  public static int y = 0;
  public static boolean b = false;
  public static String s = "";
  public static int count = 0;
  public static String p = "";
  public static boolean c = false;

  public static void main(String[] args){

    System.out.println("Welcome to Tic Tac Toe! Obtain three squares in a row to win!");

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

    playGame();


  }

  public static void playGame(){

    do{
      boolean b = playOneMove();

    }while(!b);

    System.out.println(s+" wins!");
  }

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

  public static boolean gameOver(){
    for(int i = 0; i< board[0].length; i++){
      for(int j = 0; j< board[1].length; j++){
        if(!(board[i][j].equals("0"))){
          String a = board[i][j];
          boolean b = boardSearch(i,j,a);
          if(b){
            return true;
          }
        }
      }
    }
    return false;
  }

  public static boolean boardSearch(int i, int j, String a){
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

  public static boolean playOneMove(){

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

  public static void getCoordinates(){
    if(scan.hasNextInt()){
      x = scan.nextInt();
      c = false;
      getSecondCoordinate();
    }
    else{
      b = false;
      scan.next();
    }
  }

  public static void getSecondCoordinate(){
    do{
      if(scan.hasNextInt()){
        readSecondInput();
      }
      else{
        b = false;
        scan.next();
      }
    }while(!c);
  }

  public static void readSecondInput(){
    y = scan.nextInt();
    if((x>0&&x<4)&&(y>0&&y<4)){
      x--;
      y--;
      if(board[x][y].equals("0")){
          if(p.equals(p1)){
            board[x][y] = "O";
          }
          else{
            board[x][y] = "X";
          }
          c = true;
        }
        else{
          System.out.println("Enter valid coordinates.");
        }
      }
    else{
      System.out.println("Enter valid coordinates. ");
      c = false;
    }
  }
}
