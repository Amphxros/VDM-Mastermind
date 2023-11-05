package vdm.mastermind.pc_engine;

import javax.sound.sampled.Clip;


import vdm.mastermind.engine.interfaces.objects.ISound;

public final class PCSound implements ISound {
    private final Clip sound;

    public PCSound(Clip sound) {
        this.sound = sound;
    }

    @Override
    public void play() {
        sound.start();
    }

    @Override
    public void stop() {
        sound.stop();
        sound.setFramePosition(0);

    }

    @Override
    public void setLoop() {
        sound.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
