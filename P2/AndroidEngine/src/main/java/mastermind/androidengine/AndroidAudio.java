package mastermind.androidengine;

import android.content.Context;

import mastermind.engine.IAudio;
import mastermind.engine.ISound;

public class AndroidAudio implements IAudio {
    Context context;
    public AndroidAudio(Context context) {
        this.context=context;
    }

    @Override
    public ISound createSound(String filename) {
        return null;
    }

    @Override
    public void playSound(ISound s) {

    }

    @Override
    public void stopSound(ISound s) {

    }
}
