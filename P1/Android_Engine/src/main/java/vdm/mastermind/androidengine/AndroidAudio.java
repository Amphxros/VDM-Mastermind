package vdm.mastermind.androidengine;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;

import vdm.mastermind.engine.interfaces.IAudio;
import vdm.mastermind.engine.interfaces.objects.ISound;

public final class AndroidAudio implements IAudio {
    private final Context context;
    private final AssetManager manager;

    public AndroidAudio(Context context) {
        this.context = context;
        this.manager= context.getAssets();
    }

    @Override
    public AndroidSound createSound(String filename) {
        MediaPlayer player = new MediaPlayer();
        try {
            AssetFileDescriptor afd = manager.openFd(filename);
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            player.prepare();
        } catch (Exception e) { // Error reading file
            e.printStackTrace();
            return null;
        }
        AndroidSound androidSound= new AndroidSound(player);
        assert (androidSound!=null);
        return androidSound;
    }

    @Override
    public void playSound(ISound s) {
        AndroidSound androidSound= (AndroidSound)s;
        assert (androidSound!=null);
        androidSound.play();
    }

    @Override
    public void stopSound(ISound s)
    {
        AndroidSound androidSound= (AndroidSound)s;
        assert (androidSound!=null);
        androidSound.stop();
    }
}