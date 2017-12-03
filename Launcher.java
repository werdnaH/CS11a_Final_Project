import java.util.Scanner;
public class Launcher {
  public static void main(String[] args){
    Field test = new Field();
    //The sequence should be 1 generateb 2 generaten 3 generateb1
    test.generateb();
    test.generaten();
    test.generateb1();
    System.out.println("Loading...");
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
  }
}
