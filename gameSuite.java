import java.util.*;

public class gameSuite{

  public static Scanner scan = new Scanner(System.in);

  public static void main(String[] args){
    System.out.println("Welcome to our game suite!");
    boolean b = true;
    do{
      System.out.println("Choose which game to play: Minesweeper (1), Battleship (2), or Tic Tac Toe (3)");
      System.out.println("Enter '1', '2', '3', or 'q' to quit");
      if(scan.hasNext()){
        String s = scan.next();
        if(s.equals("1")){
          MinesweeperLauncher.main(null);
        }
        else if(s.equals("2")){
          BattleshipGame.main(null);
        }
        else if(s.equals("3")){
          TicTacToeGame.main(null);
        }
        else if(s.equals("q")){
          b = false;
        }
        else{
          System.out.print("Your input is invalid.");
        }
      }
    }while(b);
  }
}
