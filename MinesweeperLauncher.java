/**
This class launches the minesweeper program, and connects with the LeaderBoard
database.
*/

import java.util.Scanner;
import CS11a_Final_Project.*;
public class MinesweeperLauncher {
  /**
  This main method runs the entire program.
  @param args is a string of arrays which is ignored.
  */
  public static void main(String[] args){
    Field test = new Field();
    test.generateb();
    test.generaten();
    test.generateb1();
    System.out.println("Loading...");
    test.operate();
    if (test.iswin()) {
        double time = test.t.getTime();
        /*
        System.out.println("What's your name?");
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();
        test.lb.updateName(userName);
        test.lb.updateGame(userName,time,test.dif);
        test.records = test.lb.orderByTime(test.dif);
        test.rd.printResult(test.records);
        */
    }
  }
}
