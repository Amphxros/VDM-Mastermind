package mastermind.logic;

import mastermind.engine.IScene;
import mastermind.logic.button.Button;


public class Level extends Button {
    int mCodeSize;
    int mCodeOpt;
    boolean mRepeat;
    int mAttempts;
    public Level(IScene scene, String fileJSON) {
        super(scene);

    }
}
