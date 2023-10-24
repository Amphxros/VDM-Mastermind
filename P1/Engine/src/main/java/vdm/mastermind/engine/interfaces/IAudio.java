package vdm.mastermind.engine.interfaces;

import vdm.mastermind.engine.interfaces.objects.ISound;

public interface IAudio {

    ISound playSound(String file, boolean loop);
    void stopSound(String file);
}
