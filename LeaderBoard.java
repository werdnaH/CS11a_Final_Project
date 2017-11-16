/**
*This class connects to a SQLite database for storing relevant data
*and contains algorithms for calculating each player's scores.It is 
*responsible for storing relevant information and displaying the 
*leader board whenever needed.
*@author Zhaonan Li
*/
import java.sql.*;
public class LeaderBoard{
  public static void main(String[] args){
    Connection c = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:leaderboard.db");
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Opened database successfully");
  }
  public void dbConnect(){

  }
  //this method gives score based on time and level of difficulty

  //public int scoreGenerator(double time, int size, int nb){

  //}
}
