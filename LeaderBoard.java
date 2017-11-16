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
    createTable();
  }
  //This method creates a table in the database named playData
  public static void createTable(){
	  Connection c = null;
	  Statement stmt = null;
	  try {
		  Class.forName("org.sqlite.JDBC");
		  c = DriverManager.getConnection("jdbc:sqlite:leaderboard.db");
		  stmt = c.createStatement();
		  String sql = "CREATE TABLE playData("
		  		+ "id  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
		  		+ "userName TEXT UNIQUE,"
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
