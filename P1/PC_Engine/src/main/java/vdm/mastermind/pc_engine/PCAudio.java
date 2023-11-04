package vdm.mastermind.pc_engine;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import vdm.mastermind.engine.interfaces.IAudio;
import vdm.mastermind.engine.interfaces.objects.ISound;

public final class PCAudio implements IAudio {
    @Override
    public PCSound createSound(String path) {
        Clip sound;
        try{
            File audio= new File("Assets/"+ path);
            AudioInputStream stream= AudioSystem.getAudioInputStream(audio);
            sound=AudioSystem.getClip();
            sound.open(stream);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return  new PCSound(sound);
    }

    @Override
    public void playSound(ISound s) {
        PCSound pcSound= (PCSound) s;
        assert (pcSound!=null);
        pcSound.play();

    }

    @Override
    public void stopSound(ISound s) {
        PCSound pcSound= (PCSound) s;
        assert (pcSound!=null);
        pcSound.stop();
    }
}