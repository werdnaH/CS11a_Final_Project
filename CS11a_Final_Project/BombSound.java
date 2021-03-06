/**
This class lets the Minesweeper program play a bomb sound whenever someone clicks on a mine.
reference: https://www.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
*/
package CS11a_Final_Project;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class BombSound {
  private Clip clip;
  private Mixer mixer;
  /**
  * Constructor
  */
  public BombSound() {
    makeSound();
  }
  /**
  * Perferm the operation of making sounds
  */
  public void makeSound() {
    Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
    mixer = AudioSystem.getMixer(mixInfos[0]);
    DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
    try {
      clip = (Clip)mixer.getLine(dataInfo);
    } catch(Exception e) {
      System.out.println(e);
    }
    try {
      URL soundURL = BombSound.class.getResource("/Boom.wav");
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
      clip.open(audioStream);
    } catch(Exception e) {
      System.out.println(e);
    }

    clip.start();
    do {
      try {
        Thread.sleep(50);
      } catch (Exception e){
        System.out.println(e);
      }
    } while(clip.isActive());
  }

  public static void main(String[] args) {
    new BombSound();
  }
}
