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

    @Override
    public ISound createSound(String filename) {
        MediaPlayer player = new MediaPlayer();
        try {
            AssetFileDescriptor afd = context.getAssets().openFd(filename + ".ogg");
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            player.prepare();
        } catch (Exception e) { // Error reading file
            e.printStackTrace();
            return null;
        }

        return new AndroidSound(player);
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