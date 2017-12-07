/**
 *This class formats and prints out results when called
 *@author Zhaonan Li
 */
package CS11a_Final_Project;

import java.util.ArrayList;

public class ResultDisplay {
	
	public ArrayList<GameRecord> addToFive(ArrayList<GameRecord> gr){
		GameRecord blank = new GameRecord("","","","");
		ArrayList<GameRecord> copy = gr;
		while(copy.size()<5) {
			copy.add(blank);
		}
		return copy;
	}
	
	/**
	 * @param ArrayList<GameRecord> gr gr is all the game
	 * records sorted by database
	 */
	public void printResult(ArrayList<GameRecord> gr) {
		printDash();
		ArrayList<GameRecord> copy = addToFive(gr);
		System.out.print(String.format("|%-20s", "NAME")
				+ String.format("|%-23s", "DATE")
				+ String.format("|%-10s", "TIME")
				+ String.format("|%-5s|", "LEVEL"));
		System.out.println();
		printDash();
		for(int i=0; i<5; i++) {
			GameRecord cur = copy.get(i);
			String name = String.format("|%-20s", cur.name);
			String date = String.format("|%-23s", cur.date); 
			String time = String.format("|%-10s", cur.time); 
			String level = String.format("|%-5s|", cur.level);
			System.out.print(name+date+time+level);
			System.out.println();
			printDash();
		} 
	}
	
	/**
	 * A helper method printing out dashes
	 */
	public void printDash() {
		String str = 
		"+--------------------+-----------------------+----------+-----+";
		System.out.print(str);
		System.out.println();
	}
}
