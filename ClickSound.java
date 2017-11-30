/*
reference: https://www.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
*/
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class ClickSound {
  private Clip clip;
  private Mixer mixer;
  public ClickSound() {
    makeSound();
  }
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
      URL soundURL = ClickSound.class.getResource("/Click.wav");
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
    new ClickSound();
  }
}
