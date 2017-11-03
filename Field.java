import java.util.Scanner;
public class Field<T> {
  public T[][] f;// f stands for field
  private int m,n;
  private int nb; //num of bombs
  public Field() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter # of rows");
    m = sc.nextInt();
    System.out.println("Enter # of columns");
    n = sc.nextInt();
    System.out.println("Enter # of Bombs");
    nb = sc.nextInt();
    f = (T[][]) new Object[m][n];
    generate(f,nb);

  }
  // generate the field
  public <T> void generate(T[][] obj, int x) {
    ;
  }
  //print the field
  public void print(){
    ;
  }
  public void operate(){
    ;
  }
  public static void main(String[] args){
    Field<Block> test = new Field();

    System.out.println(test.m);
    System.out.println(test.n);
    System.out.println(test.f);
  }
}
