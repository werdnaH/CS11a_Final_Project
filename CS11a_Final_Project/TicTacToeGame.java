import java.util.*;

public class TicTacToeGame{
  static String[][] board = {{"0","0","0"},{"0","0","0"},{"0","0","0"}};

  public static void main(String[] args){
    int count = 0;
    boolean b = false;
    Scanner scan = new Scanner(System.in);
    String s = "";
    String p1 = "";
    String p2 = "";

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

    b = true;

    do{
      System.out.println("Enter the coordinates (as integers) of where you wish to play: ");
      if(scan.hasNextInt()){
        int x = scan.nextInt();
        if(scan.hasNextInt()){
          int y = scan.nextInt();
          if(count%2==0){
            board[x][y] = "O";
            s = p1;
          }
          else{
            board[x][y] = "X";
            s = p2;
          }
          count++;
          b = gameOver();
          printBoard();
        }
        else{
          b = false;
          scan.next();
        }
      }
      else{
        b = false;
        scan.next();
      }

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
        if(board[i][j].equals("O")){
          if(i==2){
            if(board[i-1][j].equals("O")){
              if(board[i-2][j].equals("O")){
                return(true);
              }
            }
          }
        }
        if(board[i][j].equals("X")){
          if(i==2){
            if(board[i-1][j].equals("X")){
              if(board[i-2][j].equals("X")){
                return(true);
              }
            }
          }
        }
        if(board[i][j].equals("O")){
          if(i==0){
            if(board[i+1][j].equals("O")){
              if(board[i+2][j].equals("O")){
                return(true);
              }
            }
          }
        }
        if(board[i][j].equals("X")){
          if(i==0){
            if(board[i+1][j].equals("X")){
              if(board[i+2][j].equals("X")){
                return(true);
              }
            }
          }
        }
        if(board[i][j].equals("O")){
          if(j==0){
            if(board[i][j+1].equals("O")){
              if(board[i][j+2].equals("O")){
                return(true);
              }
            }
          }
        }
        if(board[i][j].equals("X")){
          if(j==0){
            if(board[i][j+1].equals("X")){
              if(board[i][j+2].equals("X")){
                return(true);
              }
            }
          }
        }
        if(board[i][j].equals("O")){
          if(j==2){
            if(board[i][j-1].equals("O")){
              if(board[i][j-2].equals("O")){
                return(true);
              }
            }
          }
        }
        if(board[i][j].equals("X")){
          if(j==2){
            if(board[i][j-1].equals("X")){
              if(board[i][j-2].equals("X")){
                return(true);
              }
            }
          }
        }
        if(board[i][j].equals("O")){
          if(i==0&&j==0){
            if(board[i+1][j+1].equals("O")){
              if(board[i+2][j+2].equals("O")){
                return(true);
              }
            }
          }
        }
        if(board[i][j].equals("X")){
          if(i==0&&j==0){
            if(board[i+1][j+1].equals("X")){
              if(board[i+2][j+2].equals("X")){
                return(true);
              }
            }
          }
        }
        if(board[i][j].equals("O")){
          if(i==2&&j==2){
            if(board[i-1][j-1].equals("O")){
              if(board[i-2][j-2].equals("O")){
                return(true);
              }
            }
          }
        }
        if(board[i][j].equals("X")){
          if(i==2&&j==2){
            if(board[i-1][j-1].equals("X")){
              if(board[i-2][j-2].equals("X")){
                return(true);
              }
            }
          }
        }
      }
    }
    return false;
  }
}
