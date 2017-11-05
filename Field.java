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
    generateb(f,nb);
    generaten(f);
    generatebl(f);
  }
  // generate the bombs, x stands for numboer of bombs
  public void generateb(Block[][] obj, int x) {
    while (true){
      for(Block[] i: obj){
        for(Block j : i) {
          if(x != 0){
            double a;
            double p = x/(m*n); // p stands fro possiblity
            a = Math.random();
            if(a < x) {
              j = new Bomb();
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
  public void generaten(Block[][] obj) {
    for(int i =0; i < obj.length; i++) {
      for(int j = 0; j < obj[0].length; j++) {
        int x = i-1;
        int y = j-1;
        int a = 0;
        bigloop: while (x <= i+1) {
          try {
              smallloop: while(y <= j+1) {
              try {
                if(obj[x][y].type.equals("Bomb") && (x != i || y!=j) && obj[i][j] == null) {
                  obj[i][j] = new Number();
                  a++;
                } else if(obj[x][y].type.equals("Bomb") && (x != i || y!=j)) {
                  a++;
                }
                y++;
              } catch(ArrayIndexOutOfBoundsException e) {
                y++;
                continue smallloop;
              }
            }
            x++;
            y = 0;
          } catch (ArrayIndexOutOfBoundsException e) {
            x++;
            continue bigloop;
          }
        }
        obj[i][j].s = String.valueOf(a);
      }
    }
  }

  public void generatebl(Block[][] obj) {
    for(Block[] i: obj) {
      for(Block j : i) {
        if(j == null) {
          j = new Blank();
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
    test.print();
  }
}
