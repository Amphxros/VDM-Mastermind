package vdm.mastermind.androidengine;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.SoundPool;

import vdm.mastermind.engine.interfaces.IAudio;
import vdm.mastermind.engine.interfaces.objects.ISound;

public final class AndroidAudio implements IAudio {
    private final Context context;

    SoundPool soundPool = new SoundPool.Builder().setMaxStreams(10).build();

    public AndroidAudio(Context context) {
        this.context = context;
    }

    // Sound
    @Override
    public AndroidSound createSound(String filename){
        int soundID = -1;
        try {
            AssetFileDescriptor afd = context.getAssets().openFd(filename);
            soundID = soundPool.load(afd, 1);
        }catch (Exception e) {
            throw new RuntimeException("Couldn't load sound."+  e);
        }

        return new AndroidSound(soundPool, soundID);
    }



    @Override
    public void playSound(ISound soundId) {
        soundId.play();
    }

    @Override
    public void stopSound(ISound s) {
        s.stop();
    }

    public void pauseMusic(){ };
}