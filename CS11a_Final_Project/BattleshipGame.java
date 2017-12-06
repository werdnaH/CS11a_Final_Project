import java.util.*;

public class BattleshipGame{

  static String[][] board1 = new String[8][8];
  static String[][] board2 = new String[8][8];
  static boolean[] s1 = {true, true, true, true, true};
  static boolean[] s2 = {true, true, true, true, true};
  static int[][] p1ships = {{0,0},{0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
  static int[][] p2ships = {{0,0},{0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
  static boolean b = false;

  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args){

    String p1 = "";
    String p2 = "";

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

    System.out.printf("%s, set your ships!%n",p1);
    board1Set();
    System.out.printf("%n");
    for(int i = 0; i<p1ships.length; i++){
      for(int j = 0; j<p1ships[i].length; j++){
        System.out.print(p1ships[i][j]+" ");
      }
      System.out.println();
    }
    System.out.printf("%s is set!%n",p1);
    System.out.printf("%s, set your ships!%n",p2);
    board2Set();
    System.out.printf("%s is set!%n",p2);
    System.out.println("We are ready to begin!");

    String[][] p1board = new String[8][8];
    for(int i = 0; i<p1board.length; i++){
      for(int j = 0; j<p1board[i].length;j++){
        p1board[i][j] = "0";
      }
    }
    String[][] p2board = new String[8][8];
    for(int i = 0; i<p2board.length; i++){
      for(int j = 0; j<p2board[i].length;j++){
        p2board[i][j] = "0";
      }
    }

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
      if(scan.hasNextInt()){
        int x = scan.nextInt()-1;
        if(x<0||x>7){
          System.out.println("Enter a valid integer: ");
        }
        else{
          if(scan.hasNextInt()){
            int y = scan.nextInt()-1;
            if(x<0||x>7){
              System.out.println("Enter a valid integer: ");
            }
            else{
              missileFire(board2, x, y, pboard, ships);
              b = gameOver(ships);
              if(b){
                System.out.printf("Congratulations, %s, you won!%n", p);
              }
            }
          }
        }
      }
      count++;
    }while(!b);
  }

  public static void board1Set(){

    for(int i = 0; i<board1[0].length; i++){
      for(int j = 0; j<board1[1].length; j++){
        board1[i][j] = "0";
      }
    }
    do{
      if(scan.hasNextInt()){
        int w = scan.nextInt()-1;
        if(w<0||w>7){
          System.out.printf("Enter valid integer coordinates: %n");
          b = false;
        }
        else if(scan.hasNextInt()){
          int x = scan.nextInt()-1;
          if(x<0||x>7){
            System.out.printf("Enter valid integer coordinates: %n");
            b = false;
          }
          else if(scan.hasNextInt()){
            int y = scan.nextInt()-1;
            if(y<0||y>7||y<w){
              System.out.printf("Enter valid integer coordinates: %n");
              b = false;
            }
            else if(scan.hasNextInt()){
              int z = scan.nextInt()-1;
              if(z<0||z>7||z<x){
                System.out.printf("Enter valid integer coordinates: %n");
                b = false;
              }
              else{
                setShip1(w,x,y,z);
                b = setComplete(s1);
              }
            }
            else{
              scan.next();
              b = false;
            }
          }
          else{
            scan.next();
            b = false;
          }
        }
        else{
          scan.next();
          b = false;
        }
      }
      else{
        scan.next();
        b = false;
      }
      printBoard(board1);
      for(int i = 0; i<p1ships.length; i++){
        for(int j = 0; j<p1ships[i].length; j++){
          System.out.printf(p1ships[i][j] + " ");
        }
      }
    }while(!b);

  }

  public static void board2Set(){

    for(int i = 0; i<board2[0].length; i++){
      for(int j = 0; j<board2[1].length; j++){
        board2[i][j] = "0";
      }
    }
    do{
      if(scan.hasNextInt()){
        int w = scan.nextInt()-1;
        if(w<0||w>7){
          System.out.printf("Enter valid integer coordinates: %n");
          b = false;
        }
        else if(scan.hasNextInt()){
          int x = scan.nextInt()-1;
          if(x<0||x>7){
            System.out.printf("Enter valid integer coordinates: %n");
            b = false;
          }
          else if(scan.hasNextInt()){
            int y = scan.nextInt()-1;
            if(y<0||y>7||y<w){
              System.out.printf("Enter valid integer coordinates: %n");
              b = false;
            }
            else if(scan.hasNextInt()){
              int z = scan.nextInt()-1;
              if(z<0||z>7||z<x){
                System.out.printf("Enter valid integer coordinates: %n");
                b = false;
              }
              else{
                setShip2(w,x,y,z);
                b = setComplete(s2);
              }
            }
            else{
              scan.next();
              b = false;
            }
          }
          else{
            scan.next();
            b = false;
          }
        }
        else{
          scan.next();
          b = false;
        }
      }
      else{
        scan.next();
        b = false;
      }
      printBoard(board2);
    }while(!b);

  }

  public static void setShip1(int w, int x, int y, int z){

    if(w==y&&x==z){
      int a = y-w;
      boolean b = true;
      if(s1[a]==true){
          if(board1[w][x].equals("X")){
            System.out.println("You already have a ship there!");
            b = false;
          }
        if(b){
          int count = 0;
          board1[w][x] = "X";
          p1ships[a][count] = w+1;
          count++;
          p1ships[a][count] = x+1;
          s1[a]=false;
        }
      }
      else{
        System.out.println("You've already set that ship!");
      }
    }

    else if(w==y&&(z!=y||w!=x)){
      int a = z-x;
      boolean b = true;
      if(s1[a]==true){
        for(int i =x; i<=z; i++){
          if(board1[w][i].equals("X")){
            System.out.println("You already have a ship there!");
            b = false;
            break;
          }
        }
        if(b){
          int count = 0;
          for(int i = x; i<=z; i++){
            board1[w][i] = "X";
            p1ships[a][count] = w+1;
            count++;
            p1ships[a][count] = i+1;
            count++;
          }
          s1[a]=false;
        }
      }
      else{
        System.out.println("You've already set that ship!");
      }
    }

    else if(x==z&&(w!=z||y!=x)){
      int a = y-w;
      boolean b = true;
      if(s1[a]==true){
        for(int i = w; i<=y; i++){
          if(board1[i][x].equals("X")){
            System.out.println("You already have a ship there!");
            b = false;
            break;
          }
        }
        if(b){
          int count = 0;
          for(int i = w; i<=y; i++){
            board1[i][x] = "X";
            p1ships[a][count] = i+1;
            count++;
            p1ships[a][count] = x+1;
            count++;
          }
          s1[a]=false;
        }
      }

      else{
        System.out.println("You've already set that ship!");
      }
    }

    else if(y-w==z-x){
      int a = y-w;
      boolean b = true;
      if(s1[a]==true){
        int xcopy = x;
        loop1:
        for(int i = w; i<=y; i++){
          for(int j = xcopy; j<=z; j++){
            if(board1[i][j].equals("X")){
              System.out.println("You already have a ship there!");
              b= false;
              break;
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
              board1[i][j] = "X";
              p1ships[a][count] = x+1;
              count++;
              p1ships[a][count] = i+1;
              count++;
              x++;
              continue loop2;
            }
          }
          s1[a]=false;
        }
      }
      else{
        System.out.println("You've already set that ship!");
      }
    }

    else{
      System.out.println("Your coordinates are invalid");
    }
  }

  public static void setShip2(int w, int x, int y, int z){

    if(w==y&&x==z){
      int a = y-w;
      boolean b = true;
      if(s2[a]==true){
          if(board2[w][x].equals("X")){
            System.out.println("You already have a ship there!");
            b = false;
          }
        if(b){
          int count = 0;
          board2[w][x] = "X";
          p2ships[a][count] = w+1;
          count++;
          p2ships[a][count] = x+1;
          s2[a]=false;
        }
      }
      else{
        System.out.println("You've already set that ship!");
      }
    }

    else if(w==y&&(z!=y||w!=x)){
      int a = z-x;
      boolean b = true;
      if(s2[a]==true){
        for(int i =x; i<=z; i++){
          if(board2[w][i].equals("X")){
            System.out.println("You already have a ship there!");
            b = false;
            break;
          }
        }
        if(b){
          int count = 0;
          for(int i = x; i<=z; i++){
            board2[w][i] = "X";
            p2ships[a][count] = w+1;
            count++;
            p2ships[a][count] = i+1;
            count++;
          }
          s2[a]=false;
        }
      }
      else{
        System.out.println("You've already set that ship!");
      }
    }

    else if(x==z&&(w!=z||y!=x)){
      int a = y-w;
      boolean b = true;
      if(s2[a]==true){
        for(int i = w; i<=y; i++){
          if(board2[x][i].equals("X")){
            System.out.println("You already have a ship there!");
            b = false;
            break;
          }
        }
        if(b){
          int count = 0;
          for(int i = w; i<=y; i++){
            board2[x][i] = "X";
            p2ships[a][count] = x+1;
            count++;
            p2ships[a][count] = i+1;
            count++;
          }
          s2[a]=false;
        }
      }

      else{
        System.out.println("You've already set that ship!");
      }
    }

    else if(y-w==z-x){
      int a = y-w;
      boolean b = true;
      if(s2[a]==true){
        int xcopy = x;
        loop1:
        for(int i = w; i<=y; i++){
          for(int j = xcopy; j<=z; j++){
            if(board2[i][j].equals("X")){
              System.out.println("You already have a ship there!");
              b= false;
              break;
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
              board2[i][j] = "X";
              p2ships[a][count] = x+1;
              count++;
              p2ships[a][count] = i+1;
              count++;
              x++;
              continue loop2;
            }
          }
          s2[a]=false;
        }
      }
      else{
        System.out.println("You've already set that ship!");
      }
    }

    else{
      System.out.println("Your coordinates are invalid");
    }
  }

  public static void printBoard(String[][] board){
    for(int i = 0; i < board[0].length; i++){
      for(int j = 0; j < board[1].length; j++){
        System.out.print(board[i][j]);
      }
      System.out.println();
    }
  }

  public static boolean setComplete(boolean[] s){
    for(int i = 0; i <s.length; i++){
      if(s[i]==true){
        return false;
      }
    }
    return true;
  }

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

  public static boolean shipLife(int[][] ships, int i){
    for(int j = 0; j<ships[i].length; j++){
      if(ships[i][j]!=0){
        return false;
      }
    }
    return true;
  }

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
