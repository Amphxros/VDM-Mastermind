package vdm.mastermind.pc_engine;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import vdm.mastermind.engine.interfaces.IAudio;
import vdm.mastermind.engine.interfaces.objects.ISound;

public final class PCAudio implements IAudio {
    @Override
    public ISound createSound(String path) {
        Clip sound;

        try {
            File audioFile = new File("assets/" + path + ".wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            sound = AudioSystem.getClip();
            sound.open(audioStream);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return new PCSound(sound);
    }

    @Override
    public void playSound(ISound s) {
        s.play();
    }

    @Override
    public void stopSound(ISound s) {
        s.stop();
    }
}