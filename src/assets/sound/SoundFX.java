package assets.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Objects;
public class SoundFX {
    private Clip clip;
    public SoundFX(String filePath){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream(filePath)));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e.getMessage());
        }
    }
    public void play(){
        if(clip.isRunning()){
            clip.stop();
        }
        clip.setFramePosition(0);
        clip.start();
    }
}
