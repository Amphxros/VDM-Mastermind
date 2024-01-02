package mastermind.pcengine;

import javax.sound.sampled.Clip;

import mastermind.engine.ISound;

public class PCSound implements ISound {
    Clip audioClip;
    boolean loop;

    public PCSound(Clip audioClip){
        this.audioClip=audioClip;
        this.loop=false;
    }
    @Override
    public void play() {
        this.audioClip.setFramePosition(0);
        this.audioClip.start();

    }

    @Override
    public void stop() {
        audioClip.stop();
    }

    @Override
    public void setLoop() {
        this.loop=!loop;
        if(loop){
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        else{
            audioClip.loop(0);
        }
    }
}
