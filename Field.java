import java.util.Scanner;
public class Field {
  public Block[][] f;// f stands for field
  private int m,n; // m and n should larger than 2
  private int nb; //num of bombs
  public Field() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter # of rows");
    m = sc.nextInt();
    System.out.println("Enter # of columns");
    n = sc.nextInt();
    System.out.println("Enter # of Bombs");
    nb = sc.nextInt();
    while(nb > m*n) {
      System.out.println("Invalid number of Bomb, please re-enter");
      nb = sc.nextInt();
    }
    f =  new Block[m][n];
  }
  // generate the bombs, x stands for numboer of bombs
  public void generateb() {
    int x = nb;
    while(true) {
      int m1 = (int)(Math.random()*m);
      int n1 = (int)(Math.random()*n);
      if(x != 0){
        double a = Math.random();
        double p = (double)x/(m*n); // p stands fro possiblity
        //System.out.println("a=" + a); debugging
        //System.out.println("p=" + p); debugging
        if(a < p && f[m1][n1] == null) {
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
  /* repeated codes
  for(Block[] i: obj) {
    for(Block j : i) {
      for(int i =0; i < obj[].length; i++) {
        for(int j = 0; j < obj.length; j++) {
          ;
        }
      }
    }
  }
  */
  // generate the numbers
  public void generaten() {
    for(int i =0; i < f.length; i++) {
      for(int j = 0; j < f[0].length; j++) {
        int x = i-1;
        int y = j-1;
        int a = 0;
        bigloop: while (x <= i+1) {
          try {
              smallloop: while(y <= j+1) {
              try {
                if(f[x][y].type.equals("Bomb") && (x != i || y != j) && f[i][j] == null) {
                  f[i][j] = new Number();
                  a++;
                  f[i][j].s = String.valueOf(a);
                } else if(f[x][y].type.equals("Bomb") && (x != i || y != j) && f[i][j].s != "B") {
                  a++;
                  f[i][j].s = String.valueOf(a);
                  f[i][j].n = a;
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
  public void generatebl() {
    for(int i =0; i < f.length; i++) {
      for(int j = 0; j < f[i].length; j++) {
        if(f[i][j] == null) {
          f[i][j] = new Blank();
        }
      }
    }
  }
  //print the field
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
    System.out.println("Click: x,y");
    System.out.println("Set Flag: f x,y ");
    System.out.println("Set Question Mark: q x,y");
    do {
      try {
        s = sc.nextLine();

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
          default:
          row = Integer.parseInt(s.replaceAll("\\s+","").substring(0,s.replaceAll("\\s+","").indexOf(",")));
          col = Integer.parseInt(s.substring(s.indexOf(",")+1));
          click(row,col);
          break;
        }
        if(!iswin())
        System.out.println("Continue");
      } catch(NumberFormatException e) {
        continue;
      }
    } while(!iswin());





  }
  public void click(int i, int j) {
    if(f[i][j].type == "Bomb") {
      System.out.println("Game Over");
      f[i][j].ic = true;
      print();
      return;
    }
    if(f[i][j].type == "Number") {
      f[i][j].ic = true;
    }
    if(f[i][j].type == "Blank") {
      f[i][j].click();
      BlankHelper(i,j);
    }
    print();
  }
  public void BlankHelper(int i, int j) {
    int x = i-1;
    int y = j-1;
    int a = 0;
    bigloop: while (x <= i+1) {
      try {
          smallloop: while(y <= j+1) {
          try {
            if(f[x][y].type == "Blank" && f[x][y].ic == false && (x != i || y != j) && f[x][y].isf == false) {
              f[x][y].click();
              this.BlankHelper(x,y);
            }
            if(f[x][y].type == "Number" && f[x][y].isf == false) {
              f[x][y].click();
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
  //deterine if the game is end
  public boolean iswin() {
    return false;
  }
  public static void main(String[] args){
    Field test = new Field();

    System.out.println(test.m);
    System.out.println(test.n);
    System.out.println(test.f);
    //The sequence should be 1 generateb 2 generaten 3 generatebl
    test.generateb();
    test.generaten();
    test.generatebl();
    System.out.println("Loading...");
    for(int i = 0; i < test.f.length; i++) {
      for(int j = 0; j < test.f[0].length; j++) {
        if(test.f[i][j] != null)
          System.out.print(test.f[i][j].s + " ");
        else
          System.out.print(0);
      }
      System.out.println();
    }
    System.out.println();
    test.operate();
    //test.click(2,2);
    //test.print();
  }
}
