package vdm.mastermind.pc_engine;

import vdm.mastermind.engine.interfaces.IAudio;
import vdm.mastermind.engine.interfaces.objects.ISound;

public class PCAudio implements IAudio {

    public PCAudio(){

    }
    @Override
    public ISound playSound(String file, boolean loop) {

        return null;
    }

    @Override
    public void stopSound(String file) {

    }
}
