package CS11a_Final_Project;
import java.util.ArrayList;
import java.util.Scanner;
public class Field {
  public Block[][] f;// f stands for field
  public static int m,n; //m and n should be larger than 2
  public static int nb; //num of bombs
  public String s;
  public boolean b = true;
  public GameTimer t = new GameTimer();
  public boolean timehelper = true;// this allows to start timing only at the first time
  public LeaderBoard lb = new LeaderBoard();
  public ArrayList<GameRecord> records = new ArrayList<GameRecord>();
  public int dif = 0;//difficulty
  public ResultDisplay rd = new ResultDisplay();

  public Field(){
    getLevel();
    f = new Block[m][n];
  }
  public void getLevel(){
    do{
      System.out.printf("Choose a level: Easy, medium or hard: %n");
      Scanner sc = new Scanner(System.in);
      if(sc.hasNextLine()){
        s = sc.nextLine();
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
  public void print() {
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

public void operate(){
  Scanner sc = new Scanner(System.in);
  int row, col;
  String s;
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

public void click(int i, int j) {
  if(f[i][j].type == "Bomb"){
    System.out.println("Game Over");
    f[i][j].ic = true;
    print();
    return;
  }
  if(f[i][j].type == "Number") {
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
  }
  if(f[i][j].type == "Blank") {
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
  }
  print();
}
public void lrClick(int a, int b){
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
public boolean lrClickCheck(int a, int b) {
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
    public boolean iswin() {
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
    /*public static void main(String[] args){
      Field test = new Field();
      //The sequence should be 1 generateb 2 generaten 3 generateb1
      test.generateb();
      test.generaten();
      test.generateb1();
      System.out.println("Loading...");
      System.out.println();
      test.print();
      test.operate();
      if (test.iswin()) {
    	  	double time = test.t.getTime();
    	  	System.out.println("What's your name?");

    	  	Scanner sc = new Scanner(System.in);
    	  	String userName = sc.nextLine();
    	  	test.lb.updateName(userName);
    	  	test.lb.updateGame(userName,time,test.dif);
    	  	test.records = test.lb.orderByTime(test.dif);
    	  	test.rd.printResult(test.records);
      }
    }*/

  }
