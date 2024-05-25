package mastermind.androidengine;

import android.content.Context;
import android.media.SoundPool;

import mastermind.engine.IAudio;
import mastermind.engine.ISound;

public class AndroidAudio implements IAudio {
    Context context;
    SoundPool soundPool;
    public AndroidAudio(Context context) {
        this.context=context;
        this.soundPool= new SoundPool.Builder().setMaxStreams(10).build();
    }

    /**
     *
     * @param filename The filename to load the sound from.
     * @return El sonido que se encuentra en la ruta
     */
    @Override
    public AndroidSound createSound(String filename) {
        return new AndroidSound(filename,soundPool,context);
    }

    /**
     * Reproduce un sonido desde el principio
     * @param s The {@link ISound} to start playing.
     */
    @Override
    public void playSound(ISound s) {
        AndroidSound androidSound= (AndroidSound) s;
        androidSound.play();
    }

    /**
     * Para un sonido
     * @param s The {@link ISound} to stop playing.
     */
    @Override
    public void stopSound(ISound s) {
        AndroidSound androidSound= (AndroidSound) s;
        androidSound.stop();
    }
}
