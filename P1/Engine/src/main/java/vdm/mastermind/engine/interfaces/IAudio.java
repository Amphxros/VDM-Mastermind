package vdm.mastermind.engine.interfaces;

import vdm.mastermind.engine.interfaces.objects.ISound;

public interface IAudio {


    ISound createSound(String filename);
    void playSound(ISound s);
    void stopSound(ISound s);
}
