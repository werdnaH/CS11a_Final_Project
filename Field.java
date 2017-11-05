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
      for(int i = 0; i < f.length; i++) {
        for(int j = 0; j < f[i].length; j++) {
          if(x != 0){
            double a = Math.random();
            double p = (double)x/(m*n); // p stands fro possiblity
            //System.out.println("a=" + a); debugging
            //System.out.println("p=" + p); debugging
            if(a < p && f[i][j] == null) {
              f[i][j] = new Bomb();
              x--;
            }
          }
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
        if(f[i][j].ic) {
          System.out.print(f[i][j].s + " ");
        } else {
          System.out.print(". ");
        }
      }
      System.out.println();
    }
  }
  public void operate(){
    ;
  }
  public static void main(String[] args){
    Field test = new Field();

    System.out.println(test.m);
    System.out.println(test.n);
    System.out.println(test.f);
    test.generateb();
    test.generaten();
    test.generatebl();
    System.out.println("...");
    for(int i = 0; i < test.f.length; i++) {
      for(int j = 0; j < test.f[0].length; j++) {
        if(test.f[i][j] != null)
          System.out.print(test.f[i][j].s + " ");
        else
          System.out.print(0);
      }
      System.out.println();
    }
    //test.print();
  }
}
