/*
reference: https://www.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
*/
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class BombSound {
  private Clip clip;
  private Mixer mixer;
  public BombSound() {
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
      URL soundURL = BombSound.class.getResource("/Boom.wav");
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
      clip.open(audioStream);
    } catch(Exception e) {
      System.out.println(e);
    }

    clip.start();
    stop(3000);
    clip.stop();
  }

    public static void stop(int time){
      try {
        Thread.sleep(5000);
      } catch (Exception e){
        System.out.println(e);
      }
  }



  public static void main(String[] args) {
    new BombSound();
  }
}
