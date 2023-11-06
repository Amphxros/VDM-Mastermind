package vdm.mastermind.androidengine;

import android.media.SoundPool;

import vdm.mastermind.engine.interfaces.objects.ISound;

public final class AndroidSound implements ISound {
    SoundPool soundPool;
    private int soundID = -1;
    private int loop = 0;

    public AndroidSound(SoundPool _soundPool) {
        this.soundPool = _soundPool;
    }

    @Override
    public void play() {
            soundPool.play(soundID, 1, 1, 0, loop, 1);
    }

    @Override
    public void stop() {
            soundPool.stop(soundID);
    }

    @Override
    public void setLoop() {
            loop = 1;
    }

    public void dispose() {
        soundPool.unload(soundID);
    }
}
