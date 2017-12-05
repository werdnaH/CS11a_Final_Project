/**
 * GameTimer is an object for recording time taken for each game
 * @author Zhaonan Li
 */
package CS11a_Final_Project;
public class GameTimer {

	public long startTime;
	public long endTime;

	public void start() {
		startTime = System.currentTimeMillis();
	}

	public void end() {
		endTime = System.currentTimeMillis();
	}

	public double getTime() {
		double time = (double) (endTime - startTime)/1000;//is this the milliseconds
		time = Double.parseDouble(String.format("%.1f", time));
		if(time>100000||time<0) {
			return -1;
		}
		return time;
	}
}
