package mastermind.pcengine;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import mastermind.engine.IAudio;
import mastermind.engine.ISound;

public class PCAudio implements IAudio {

    public PCAudio(){

    }
    @Override
    public ISound createSound(String filename) {
       Clip audioClip=null;
        try {
            File audio = new File(filename);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audio);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioInputStream);
        } catch (Exception e) {
            System.err.println("error while loading audio in PC");
            e.printStackTrace();
        }

        return new PCSound(audioClip);
    }

    @Override
    public void playSound(ISound s) {
        assert (s!=null);
        s.play();
    }

    @Override
    public void stopSound(ISound s) {
        assert (s!=null);
        s.stop();
    }
}
