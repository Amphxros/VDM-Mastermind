package mastermind.pcengine;

import mastermind.engine.IAudio;
import mastermind.engine.ISound;

public class PCAudio implements IAudio {
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
