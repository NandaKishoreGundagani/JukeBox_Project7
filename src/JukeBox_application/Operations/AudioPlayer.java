package JukeBox_application.Operations;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class AudioPlayer {
    Clip clip;
    static String filepath;
    Long currentFrame;
    String status;
    AudioInputStream audioInputStream;

    public AudioPlayer() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static int operation(String path) throws
            UnsupportedAudioFileException, LineUnavailableException, IOException {
        filepath = path;
        AudioPlayer aud = new AudioPlayer();
        aud.play();
        Scanner sc = new Scanner(System.in);
        boolean b=true;
        int res=0;
        while (b==true) {
            System.out.println("1. pause");
            System.out.println("2. resume");
            System.out.println("3. restart");
            System.out.println("4. stop");
            System.out.println("5. stop the playlist");
            int action = sc.nextInt();
            System.out.println("==========================================================");
            aud.getChoice(action);
            if(action==5){
                res=1;
            }
            if(action==4 || action==5){
                b=false;
            }
        }
        return res;
    }

    // Method to play the audio
    public void play() {
        //start the clip
        clip.start();
        status = "play";
    }

    public void getChoice(int a) throws
            UnsupportedAudioFileException, LineUnavailableException, IOException {
        switch (a) {
            case 1:
                pause();
                break;
            case 2:
                resume();
                break;
            case 3:
                restart();
                break;
            case 4:
                stop();
                break;
            case 5:
                stop();
                break;

        }
    }

    public void pause() {
        if (status.equals("paused")) {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    public void resume() throws
            UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (status.equals("play")) {
            System.out.println("Audio is already being played");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    public void restart() throws
            UnsupportedAudioFileException, LineUnavailableException, IOException {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    public void stop() {
        currentFrame = 0L;
        clip.stop();
        clip.close();


    }

    public void resetAudioStream() throws
            UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File(filepath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}


