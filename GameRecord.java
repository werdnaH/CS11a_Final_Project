/**
 * GameRecord is an object for storing information for each game
 * @author Zhaonan Li
 */
 package CS11a_Final_Project;
public class GameRecord {
	public String name;
	public String date;
	public String time;
	public String level;
	public GameRecord(String nm, String dt, String tm,
			String lv) {
		name = nm;
		date = dt;
		time = tm;
		level = lv;
	}
	public String toString() {
		return name + " " + date + " "
				+ time + " " + level;
	}
}
