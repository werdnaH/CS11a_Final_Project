/*
This class allows the Minesweeper program to play a sound whenever a user sets a flag.
reference: https://www.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
*/
package CS11a_Final_Project;
import java.io.*;

import java.net.URL;
import javax.sound.sampled.*;
public class FlagSound {
  private Clip clip;
  private Mixer mixer;
  /**
  * Constructor
  */
  public FlagSound() {
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
      URL soundURL = FlagSound.class.getResource("/Flag.wav");
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
      clip.open(audioStream);
    } catch(Exception e) {
      System.out.println(e);
    }
    clip.start();
    stop(1000);
    clip.stop();
  }

    public static void stop(int time){
      try {
        Thread.sleep(1000);
      } catch (Exception e){
        System.out.println(e);
      }
    }


  public static void main(String[] args) {
    new FlagSound();
  }
}
