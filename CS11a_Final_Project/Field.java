/**
Field contains the majority of the methods that make the Minesweeper program
run.
*/

package CS11a_Final_Project;
import java.util.ArrayList;
import java.util.Scanner;
public class Field {
  /**
  f is the 2D array in which the bombs are stored. f stands for field.
  */
  public static Block[][] f;
  public static int m,n; //m and n should be larger than 2
  /**
  nb is the number of bombs
  */
  public static int nb;
  /**
  b is a boolean accessed by multiple methods
  */
  public boolean b = true;
  /**
  t is a timer used to time how long it takes the user to beat the game
  */
  public GameTimer t = new GameTimer();
  /**
  timehelper allows the timing to start only once.
  */
  public boolean timehelper = true;
  /**
  lb is a leaderboard that can return the leaderboard
  */
  public LeaderBoard lb = new LeaderBoard();
  /**
  records is an object that stores the times and difficulty
  */
  public ArrayList<GameRecord> records = new ArrayList<GameRecord>();
  /**
  dif is the difficulty level
  */
  public int dif = 0;
  /**
  rd is an instance of a format of the leaderboard.
  */
  public ResultDisplay rd = new ResultDisplay();
  /**
  * Constructor
  */
  public Field(){
    getLevel();
    f = new Block[m][n];
  }
  /**
  * Perferm the operation of getting level
  */
  public void getLevel(){
    do{
      System.out.printf("Choose a level: Easy, medium or hard: %n");
      Scanner sc = new Scanner(System.in);
      if(sc.hasNextLine()){
        String s = sc.nextLine();
        m = Ls(s);
        n = Ls(s);
        if(s.toUpperCase().equals("EASY")){
          nb = 2;
          dif = 1;
        }
        else if(s.toUpperCase().equals("MEDIUM")){
          nb = 10;
          dif = 2;
        }
        else if(s.toUpperCase().equals("HARD")){
          nb = 40;
          dif = 3;
        }
      }
    }while(m == 0);
  }
  /**
  * Perferm the operation of making sounds
  * @param s the input of choosing the level
  * @return the size of the board
  */
  public static int Ls(String s) {
    if(s.toUpperCase().equals("EASY")){
      return 3;
    }
    else if(s.toUpperCase().equals("MEDIUM")){
      return 9;
    }
    else if(s.toUpperCase().equals("HARD")){
      return 16;
    }
    return 0;
  }

  // generate the bombs, x stands for number of Bombs
  /**
  * Perferm the operation of generating bombs
  */
  public void generateb() {
    int x = nb;
    while(true) {
      int m1 = (int)(Math.random()*m);
      int n1 = (int)(Math.random()*n);
      if(x != 0){
        double a = Math.random();
        double p = (double)x/(m*n); // p stands for possibility
        //System.out.println("a=" + a); debugging
        //System.out.println("p=" + p); debugging
        if(a< p && f[m1][n1] == null){
          f[m1][n1] = new Bomb();
          x--;
        }
      }
      if(x > 0)
        continue;
      else
        break;
    }
  }
  // generate the numbers
  /**
  * Perferm the operation of generating the numbers
  */
  public void generaten() {
    for(int i = 0; i < f.length; i++){
      for(int j = 0; j < f[0].length; j++){
        int x = i-1;
        int y = j-1;
        bigloop: while (x <= i+1) {
          try {
            smallloop: while(y <= j+1){
              try{
                if(f[x][y].type.equals("Bomb") && (x != i || y != j) && f[i][j] == null){
                  f[i][j] = new Number();
                  ((Number)f[i][j]).n++;
                  f[i][j].s = String.valueOf(((Number)f[i][j]).n);
                } else if (f[x][y].type.equals("Bomb")&&(x != i || y != j) && f[i][j].s != "B") {
                  ((Number)f[i][j]).n++;
                  f[i][j].s = String.valueOf(((Number)f[i][j]).n);
                }
                y++;
                //} catch (ArrayIndexOutOfBoundsException e) {
              } catch (Exception e) {
                y++;
                continue smallloop;
              }
            }
            x++;
            y = j - 1;
            //} catch (ArrayIndexOutOfBoundsException e) {
          } catch (Exception e) {
            x++;
              continue bigloop;
          }
        }
      }
    }
  }
  //generate blanks
  /**
  * Perferm the operation of generating blanks
  */
  public void generateb1() {
    for(int i = 0; i < f.length; i++){
      for(int j =0; j<f[i].length; j++) {
        if(f[i][j] == null) {
          f[i][j] = new Blank();
        }
      }
    }
  }

  //print the Field
  /**
  * Perferm the operation of printing the board
  */
  public static void print() {
    for(int i = 0; i < f.length; i++) {
      for(int j = 0; j < f[0].length; j++) {
        if(f[i][j].ic || f[i][j].isf || f[i][j].isq) {
          System.out.print(f[i][j].s + " ");
        } else {
          System.out.print(". ");
        }
      }
      System.out.println();
    }
  }
  /**
  * Perferm the operation of main function of minesweepr
  */
public void operate(){
  Scanner sc = new Scanner(System.in);
  String s = "";
  System.out.println("Click: x, y");
  System.out.println("Set Flag: f x,y");
  System.out.println("Set Question Mark: q x,y");
  do{
    try{
      s = sc.nextLine();
      if(timehelper) {
        t.start();
        timehelper = false;
      }
      parseUserInput(s);
      if(isLose()) {
        System.out.println("You Lose");
        t.end();
        return;
      }
      if(!iswin())
      System.out.println("Continue");
    }catch(Exception e){
      System.out.println("Invalid Input, Enter Again");
      continue;
    }
  } while(!iswin());
  t.end();
  System.out.println("You Win");
}

/**
Performs the operation of parsing the input of the user.
@param s is the input to be parsed.
*/

public void parseUserInput(String s){
  int row, col;
  switch(s.substring(0,1)) {
    case"f":
    row = Integer.parseInt(s.replaceAll("\\s+","").substring(1,s.replaceAll("\\s+","").indexOf(",")));
    col = Integer.parseInt(s.substring(s.indexOf(",")+1));
    f[row][col].setFlag();
    print();
    break;
    case"q":
    row = Integer.parseInt(s.replaceAll("\\s+","").substring(1,s.replaceAll("\\s+","").indexOf(",")));
    col = Integer.parseInt(s.substring(s.indexOf(",")+1));
    f[row][col].setqm();
    print();
    break;
    case"l":
    row = Integer.parseInt(s.replaceAll("\\s+","").substring(1,s.replaceAll("\\s+","").indexOf(",")));
    col = Integer.parseInt(s.substring(s.indexOf(",")+1));
    lrClick(row,col);
    break;
    default:
    row = Integer.parseInt(s.replaceAll("\\s+","").substring(0,s.replaceAll("\\s+","").indexOf(",")));
    col = Integer.parseInt(s.substring(s.indexOf(",")+1));
    click(row,col);
    break;
  }
}
/**
* Perferm the operation of if the user lose the Game
* @return true if the user loses
*/
public boolean isLose() {
  for(Block[] i: f){
    for(Block j : i){
      if(j.type == "Bomb" && j.ic) {
	      new BombSound();
        return true;
      }
    }
  }
  return false;
}
/**
* Perferm the operation of clicking
* @param i the row coordinator
* @param j the column coordinator
*/
public void click(int i, int j) {
  if(f[i][j].type == "Bomb"){
    clickBomb(i,j);
  }
  if(f[i][j].type == "Number") {
    clickNumber(i,j);
  }
  if(f[i][j].type == "Blank") {
    clickBlank(i,j);
  }
}

/**
Performs the operation of click on a square with a bomb
@param i the row coordinator
@param j the column coordinator
*/
public static void clickBomb(int i, int j){
  System.out.println("Game Over");
  f[i][j].ic = true;
  print();
  return;
}

/**
Performs the operation of click on a square with a number
@param i the row coordinator
@param j the column coordinator
*/

public static void clickNumber(int i, int j){
  Thread thread = new Thread() {
    public void run() {
      new ClickSound();
      try {
        this.sleep(50);
      } catch(Exception e) {
        ;
      }
    }
  };
  thread.start();
  f[i][j].ic = true;
  if(iswin()) {
    for(int i1 = 0; i1 < f.length; i1++){
      for(int j1 = 0; j1 < f[0].length; j1++){
        if(f[i1][j1].type.equals("Bomb")) {
          f[i1][j1].setFlag();
        }
      }
    }
  }
  print();
}

/**
Performs the operation of click on a blank square
@param i the row coordinator
@param j the column coordinator
*/
public void clickBlank(int i, int j){
  Thread thread = new Thread() {
    public void run() {
      new ClickSound();
      try {
        this.sleep(50);
      } catch(Exception e) {
        ;
      }
    }
  };
  thread.start();
  ((Blank)f[i][j]).click();
  BlankHelper(i,j);
  print();

}

/**
* Perferm the operation of right left click that click all the sorrounding
* blocks if the the block is nuber and the # of surronding flags equals to
* the numbers
*@param a the row coordinator
*@param b the column coordinator
*/
public static void lrClick(int a, int b){
  if(!lrClickCheck(a,b))
	  return;
  for(int i = a+1; i>a-2; i--){
    for(int j = b+1; j>b-2; j--){
      if(j<0||i<0||i>=m||j>=n){
        continue;
      }
      if(f[i][j].isf)
        continue;
      else
        f[i][j].click();
    }
  }
  print();
}
/**
* check if the block satisfy the condition to be right left right clicked
* @param a the row coordinator
* @param b the column coordinator
* @return true if it satisfies
*/
public static boolean lrClickCheck(int a, int b) {
	if(!(f[a][b]).type.equals("Number") || f[a][b].ic != true)
		return false;
	int x = 0;
	for(int i = a+1; i>a-2; i--){
	    for(int j = b+1; j>b-2; j--){
	      if(j<0||i<0||i>=m||j>=n){
	        continue;
	      }
	      if(f[i][j].isf) {
	    	  x++;
	      }
	    }
	}
	if(x == ((Number)f[a][b]).n)
		return true;
	else
		return false;
}
/**
* Help to click the blanks
* @param i the row coordinator
* @param j the column coordinator
*/
public void BlankHelper(int i, int j){
	for(int x = i+1; x>i-2; x--){
	    for(int y = j+1; y>j-2; y--){
	      if(x<0||y<0||x>=m||y>=n){
	        continue;
	      }
	      if(f[x][y].type == "Blank" && f[x][y].ic == false && (x != i || y != j) && f[x][y].isf == false){
              f[x][y].click();
              this.BlankHelper(x,y);
          }
	      if(f[x][y].type == "Number" && f[x][y].isf == false) {
              f[x][y].click();
          }
	    }
	}
}
    //determine if the game is end
    /**
    * Perferm the operation of checking if the user wins
    * @return true if the user win
    */
    public static boolean iswin() {
      for(Block[] i : f) {
        for(Block j : i) {
          if(j.type.equals("Number")) {
            if(j.ic) {
              ;
            } else {
              return false;
            }
          }
        }
      }
      return true;
    }
}
