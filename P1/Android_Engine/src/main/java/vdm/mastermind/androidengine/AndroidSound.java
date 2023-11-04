package vdm.mastermind.androidengine;

import android.media.MediaPlayer;

import vdm.mastermind.engine.interfaces.objects.ISound;

public final class AndroidSound implements ISound {
    private final MediaPlayer player;

    public AndroidSound(MediaPlayer player) {
        this.player = player;
    }

    @Override
    public void play() {
        if (!player.isPlaying())
            player.start();
    }

    @Override
    public void stop() {
        if (player.isPlaying()) {
            player.stop();
        }
    }

    @Override
    public void setLoop() {
        player.setLooping(true);
    }

    public void dispose() {
        player.release();
    }
}
