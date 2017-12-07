import java.util.*;

public class BattleshipGame{

  static String[][] board1 = new String[8][8];
  static String[][] board2 = new String[8][8];
  static boolean[] s1 = {true, true, true, true, true};
  static boolean[] s2 = {true, true, true, true, true};
  static int[][] p1ships = {{0,0},{0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
  static int[][] p2ships = {{0,0},{0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
  static boolean b = false;
  static String p1 = "";
  static String p2 = "";
  static String[][] p1board = new String[8][8];
  static String[][] p2board = new String[8][8];

  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args){

    getPlayerNames();

    board1Set(p1);
    board2Set(p2);
    System.out.println("We are ready to begin!");

    createPBoards();


    playGame();
  }

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
      int x = readTarget();
      int y = readTarget();
      missileFire(board2, x, y, pboard, ships);
      b = gameOver(ships);
      if(b){
      System.out.printf("Congratulations, %s, you won!%n", p);
      }
      count++;
    }while(!b);
  }

  public static int readTarget(){
    int x = 0;
    if(scan.hasNextInt()){
      x = scan.nextInt()-1;
      if(x<0||x>7){
        System.out.println("Enter a valid integer: ");
      }
    }
    return x;
  }


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

  public static void getPlayerNames(){
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

  public static void board1Set(String p1){
    System.out.printf("%s, set your ships!%n",p1);
    b = false;
    for(int i = 0; i<board1[0].length; i++){
      for(int j = 0; j<board1[1].length; j++){
        board1[i][j] = "0";
      }
    }
    do{
      setBoardCoordinates1();
      printBoard(board1);
      for(int i = 0; i<p1ships.length; i++){
        for(int j = 0; j<p1ships[i].length; j++){
          System.out.printf(p1ships[i][j] + " ");
        }
      }
    }while(!b);
    System.out.printf("%s is set!%n",p1);

  }

  public static void setBoardCoordinates1(){
    int w = getBoardInput();
    int x = getBoardInput();
    int y = getBoardInput();
    int z = getBoardInput();
    setShip1(w,x,y,z);
    b = setComplete(s1);
  }

  public static void board2Set(String p2){
    System.out.printf("%s, set your ships!%n",p2);
    b = false;
    for(int i = 0; i<board2[0].length; i++){
      for(int j = 0; j<board2[1].length; j++){
        board2[i][j] = "0";
      }
    }
    do{
      setBoardCoordinates2();
      printBoard(board2);
      for(int i = 0; i<p2ships.length; i++){
        for(int j = 0; j<p2ships[i].length; j++){
          System.out.printf(p2ships[i][j] + " ");
        }
      }
    }while(!b);
    System.out.printf("%s is set!%n",p2);

  }

  public static void setBoardCoordinates2(){
    int w = getBoardInput();
    int x = getBoardInput();
    int y = getBoardInput();
    int z = getBoardInput();
    setShip2(w,x,y,z);
    b = setComplete(s2);

  }

  public static int getBoardInput(){
    int x = 0;
    do
    if(scan.hasNextInt()){
      x = scan.nextInt()-1;
      if(x<0||x>7){
        System.out.printf("Enter valid integer coordinates: %n");
      }
    }
    else{
      scan.next();
      System.out.println("Enter valid integer coordinates: ");
    }while(x==0);
    return x;
  }

  public static void setShip1(int w, int x, int y, int z){

    if(w==y&&x==z){
      searchShipLength1(w, x, y, z, s1, board1, p1ships);
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

  public static void setShip2(int w, int x, int y, int z){

    if(w==y&&x==z){
      searchShipLength1(w, x, y, z, s2, board2, p2ships);
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

  public static void searchShipDiagonal(int w, int x, int y, int z, boolean[] s, String[][] board, int[][] pships){
    int a = y-w;
    boolean b = true;
    if(s[a]==true){
      int xcopy = x;
      loop1:
      for(int i = w; i<=y; i++){
        for(int j = xcopy; j<=z; j++){
          b = checkShip(x,i, board);
          if(b){
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
      System.out.println("You've already set that ship!");
    }
  }

  public static void searchShipVertical(int w, int x, int y, int z, boolean[] s, String[][] board, int[][] pships){
    int a = y-w;
    boolean b = true;
    if(s[a]==true){
      for(int i = w; i<=y; i++){
        b = checkShip(i,x, board);
        if(b){
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
    else{
      System.out.println("You've already set that ship!");
      }
  }


  }

  public static void searchShipLength1(int w, int x, int y, int z, boolean[] s, String[][] board, int[][] pships){
    int a = y-w;
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
    else{
      System.out.println("You've already set that ship!");
      }
    }
  }

  public static void searchShipHorizontal(int w, int x, int y, int z, boolean[] s, String[][] board, int[][] pships){
    int a = z-x;
    boolean b = true;
    if(s[a]==true){
      for(int i =x; i<=z; i++){
        b = checkShip(w,i, board);
        if(b){
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
    else{
      System.out.println("You've already set that ship!");
    }
  }
}


  public static boolean checkShip(int x, int y, String[][] board){
    boolean b = true;
    if(board[x][y].equals("X")){
      System.out.println("You already have a ship there!");
      b = false;
    }
    return b;
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
