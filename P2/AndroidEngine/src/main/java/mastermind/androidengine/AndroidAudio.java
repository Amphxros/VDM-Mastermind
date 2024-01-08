package mastermind.androidengine;

import android.content.Context;
import android.media.SoundPool;

import mastermind.engine.IAudio;
import mastermind.engine.ISound;

/**
 * Gestor de audio del motor
 */
public class AndroidAudio implements IAudio {
    Context context; //contexto de la aplicacion
    SoundPool soundPool; // SoundPool

    public AndroidAudio(Context context) {
        this.context=context;
        this.soundPool= new SoundPool.Builder().setMaxStreams(10).build();
    }

    @Override
    public ISound createSound(String filename) {
        return new AndroidSound(filename,soundPool,context);
    }

    @Override
    public void playSound(ISound s) {
        AndroidSound androidSound= (AndroidSound) s;
        androidSound.play();
    }

    @Override
    public void stopSound(ISound s) {
        AndroidSound androidSound= (AndroidSound) s;
        androidSound.stop();
    }
}
