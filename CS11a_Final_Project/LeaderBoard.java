/**
*This class connects to a SQLite database for storing relevant data
*and contains algorithms for calculating each player's scores.It is 
*responsible for storing relevant information and displaying the 
*leader board whenever needed.
*@author Zhaonan Li
*/
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LeaderBoard{
  
  public Connection c = null;
  public Statement stmt = null;
  public String sql = new String();//sql is command in SQL
  public ArrayList<GameRecord> records = new ArrayList<GameRecord>();
  
  /**
   *This method helps to connect to the leader board database, mainly to avoids
   *duplicating code
   */
  public void connect() {
	  try {
	  Class.forName("org.sqlite.JDBC");
	  c = DriverManager.getConnection("jdbc:sqlite:leaderboard.db");
	  //System.out.println("Connected to database successfully...");
	  } catch (Exception e) {
		  System.out.println("Connection fails because of an exception");
		  System.out.println(e.getClass().getName() +
				  ": " + e.getMessage());
	  }
  }
  
  /**
   *This method inserts user names to the player table. Names are always 
   *stored in upper cases. If the name is not in the database, the method
   *will automatically create an entry in players table
   *@param String name 
   */
  public void updateName(String name) {
	  if (nameId(name) != -1) {
		  //System.out.println("name exists");
	  } else {
		  try {
			  connect();
			  c.setAutoCommit(false);
			  stmt = c.createStatement();
			  sql = "INSERT INTO players (userName) " 
			  		+"VALUES(\""+ name.toUpperCase() +"\");";
			  stmt.executeUpdate(sql);
			  stmt.close();
			  c.commit();
			  c.close();
			  //System.out.println("name inserted successfully");
		  } catch(Exception e) {
			  System.out.println("There's an error:"+e.getClass().getName()
					  +": "+e.getMessage());
			  System.exit(0);
		  }
	  }
  }
  
  /**
   * This method updates information inside playData. un is user name, tm is 
   * time taken in second and level is level
   * @param un user name
   * @param tm time taken for one game
   * @param level difficulty level of the game
   */
  public void updateGame(String un, double tm, int level) {
	   try {
		   updateName(un);
		   int id = nameId(un);
		   connect();
		   c.setAutoCommit(false);
		   stmt = c.createStatement();
		   sql = "INSERT INTO GameData (userName_id,date,time,level) " 
				  	+"VALUES("+ id +","
				  	+ "\"" + getDateTime() +"\""
				  	+ ","+ tm + "," + level
				  	+ ");";
		   stmt.executeUpdate(sql);
		   stmt.close();
		   c.commit();
		   c.close();
		   //System.out.println("game data inserted successfully");
	   } catch (Exception e) {
			  System.out.println("There's an error:"+e.getClass().getName()
					  +": "+e.getMessage());
			  System.exit(0);
		  }
	   
  }
  
  /**
   *This method returns current time in desired format
   *@return String current time in the local time zone
   */
  public String getDateTime() {
	  LocalDateTime now = LocalDateTime.now();
	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
			  "yyyy-MM-dd HH:mm:ss");
	  String time = now.format(formatter);
	  return time;
  }
  
  /**
   * This method checks if the user name is taken. If name doesn't
   * exist, it returns -1
   * @return int the id of the username in the player table
   * @param String name
   */
  public int nameId(String name) {
	  try {
	  connect();
	  stmt = c.createStatement();
	  ResultSet rs = stmt.executeQuery("SELECT * FROM players;");
	  while (rs.next()) {
		  int id = rs.getInt("id");
		  String userName = rs.getString("userName");
		  if (userName.toLowerCase().equals(name.toLowerCase())) {
			  rs.close();stmt.close();c.close();
			  return id;
		  }
	  	} rs.close();stmt.close();c.close();
	  	
	  } catch(Exception e){
		  System.out.println("There's an error:"+e.getClass().getName()
				  +": "+e.getMessage());
		  System.exit(0);
	  }
	  return -1;
  }
  
  /**
   * This method returns an arraylist of object GameRecord, based on
   * time taken for certain level of difficulty for all players
   * @return ArrayList<GameRecord> all the game records ordered by time
   * @param int level an integer indicating difficulty
   */
  public ArrayList<GameRecord> orderByTime (int level) {
	  records.clear();
	  try {
		  connect();
		  stmt = c.createStatement();
		  ResultSet rs = stmt.executeQuery("SELECT "
		  		+ "players.userName, "
		  		+ "GameData.date, GameData.time,"
		  		+ "GameData.level FROM players JOIN "
		  		+ "GameData ON GameData.userName_id = players.id WHERE "
		  		+ "level=" + level +" ORDER BY GameData.time"
		  				+ " ASC;");
		  System.out.println("---Leader Board---");
		  while (rs.next()) {
			  String name = rs.getString("userName");
			  String date = rs.getString("date");
			  String time = String.valueOf(rs.getDouble("time"));
			  String lev = String.valueOf(rs.getInt("level"));
			  GameRecord gr = new GameRecord(name,date,time,lev);
			  records.add(gr);
		  }
		  rs.close();
		  stmt.close();
		  c.close();
	  } catch (Exception e){
		  System.out.println("There's an error:"+e.getClass().getName()
				  +": "+e.getMessage());
		  System.exit(0);
	  }
	  return records;
  }
  
  /**
   * This methods returns an arraylist of GameRecord object sorted
   * based on the date the game is played
   * @return ArrayList<GameRecord> all the game records ordered by date
   * @param String user name
   */
  public ArrayList<GameRecord> playerDateSort(String un){
	  records.clear();
	  int id = nameId(un);
	  try {
		  connect();
		  stmt = c.createStatement();
		  ResultSet rs = stmt.executeQuery("SELECT "
		  		+ "players.userName, "
		  		+ "GameData.date, GameData.time,"
		  		+ "GameData.level FROM players JOIN "
		  		+ "GameData ON GameData.userName_id = players.id WHERE "
		  		+ "userName_id =" + id +" ORDER BY GameData.date"
		  				+ " DESC;");
		  System.out.println("---Five of Your Most Recent Games---");
		  while (rs.next()) {
			  String name = rs.getString("userName");
			  String date = rs.getString("date");
			  String time = String.valueOf(rs.getDouble("time"));
			  String lev = String.valueOf(rs.getInt("level"));
			  GameRecord gr = new GameRecord(name,date,time,lev);
			  records.add(gr);
		  }
		  rs.close();
	      stmt.close();
	      c.close();
	  } catch (Exception e){
		  System.out.println("There's an error:"+e.getClass().getName()
				  +": "+e.getMessage());
		  System.exit(0);
	  }
	  return records;
  }
  
  /**
   * This methods returns an array list of GameRecord object sorted
   * based by an user's best performance of selected difficulty
   * @return ArrayList<GameRecord> all the game records ordered by time
   * @param String un user name
   * @param int lv level of difficulty
   */
  public ArrayList<GameRecord> playerTimeSort(String un, int lv){
	  records.clear();
	  int id = nameId(un);
	  try {
		  connect();
		  stmt = c.createStatement();
		  ResultSet rs = stmt.executeQuery("SELECT "
		  		+ "players.userName, "
		  		+ "GameData.date, GameData.time,"
		  		+ "GameData.level FROM players JOIN "
		  		+ "GameData ON GameData.userName_id = players.id WHERE "
		  		+ "userName_id =" + id +" AND level=" +lv
		  		+ " ORDER BY GameData.time"
		  		+ " ASC;");
		  System.out.printf("---Five of Your Best Performance at Level %d---",lv);
		  while (rs.next()) {
			  String name = rs.getString("userName");
			  String date = rs.getString("date");
			  String time = String.valueOf(rs.getDouble("time"));
			  String lev = String.valueOf(rs.getInt("level"));
			  GameRecord gr = new GameRecord(name,date,time,lev);
			  records.add(gr);
		  }
		  rs.close();
	      stmt.close();
	      c.close();
	  } catch (Exception e){
		  System.out.println("There's an error:"+e.getClass().getName()
				  +": "+e.getMessage());
		  System.exit(0);
	  }
	  return records;
  }
  
  /**
   * This method creates a table in the database for storing players' user names
   * named player
   */
  public void createPlayerTable(){
	  try {
		  connect();
		  stmt = c.createStatement();
		  String sql = "DROP TABLE IF EXISTS players;"
				+  "CREATE TABLE players("
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
  
  /**
   * This method creates a table in the database for storing all relevant information
   * for every game named game data
   */
  public void createDataTable(){
	  try {
		  connect();
		  stmt = c.createStatement();
		  String sql = "DROP TABLE IF EXISTS GameData;"
				+  "CREATE TABLE GameData("
		  		+ "id  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
		  		+ "userName_id INTEGER,"
		  		+ "date DATETIME,"
		  		+ "time DOUBLE,"
		  		+ "level INTEGER)";
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
}