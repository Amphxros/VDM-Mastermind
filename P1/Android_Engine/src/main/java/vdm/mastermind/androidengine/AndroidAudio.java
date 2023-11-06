package vdm.mastermind.androidengine;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import vdm.mastermind.engine.interfaces.IAudio;
import vdm.mastermind.engine.interfaces.objects.ISound;

public final class AndroidAudio implements IAudio {
    private final Context context;

    public AndroidAudio(Context context) {
        this.context = context;
    }

    //TODO implements with sound pool
    @Override
    public ISound createSound(String filename) {
        return null;
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