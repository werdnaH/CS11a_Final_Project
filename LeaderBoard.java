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
    //createPlayerTable();
	  createDataTable();
  }
  //This method creates a table in the database for storing players' user names
  public static void createPlayerTable(){
	  Connection c = null;
	  Statement stmt = null;
	  try {
		  Class.forName("org.sqlite.JDBC");
		  c = DriverManager.getConnection("jdbc:sqlite:leaderboard.db");
		  stmt = c.createStatement();
		  String sql = "CREATE TABLE players("
		  		+ "id  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
		  		+ "userName TEXT UNIQUE)";
		  stmt.executeUpdate(sql);
		  stmt.close();
		  c.close();
	  } catch (Exception e) {
		  System.out.println("There's an error:"+e.getClass().getName()
				  +": "+e.getMessage());
		  System.exit(0);
	  }
	  System.out.println("Table created successfully!");
  }
  
  //This method creates a table in the database for storing all relevant information
  //for every game
  public static void createDataTable(){
	  Connection c = null;
	  Statement stmt = null;
	  try {
		  Class.forName("org.sqlite.JDBC");
		  c = DriverManager.getConnection("jdbc:sqlite:leaderboard.db");
		  stmt = c.createStatement();
		  String sql = "CREATE TABLE GameData("
		  		+ "id  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
		  		+ "userName_id INTEGER"
		  		+ "date DATETIME,"
		  		+ "time DOUBLE,"
		  		+ "score INTEGER)";
		  stmt.executeUpdate(sql);
		  stmt.close();
		  c.close();
	  } catch (Exception e) {
		  System.out.println("There's an error:"+e.getClass().getName()
				  +": "+e.getMessage());
		  System.exit(0);
	  }
	  System.out.println("Table created successfully!");
  }
  //This method update information inside playData 
  public static void updateTable() {
	  
  }
  //this method gives score based on time and level of difficulty
  public int scoreGenerator(double time, int size, int nb){
	  int score;
	  score = (int) time+size+nb;
	  return score;
  }
}
