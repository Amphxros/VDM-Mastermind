package vdm.mastermind.logic;

import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.logic.buttons.GameObject;

public class Hint extends GameObject {

    private HintState state = HintState.NOTSOLUTION;

    public Hint(IScene scene) {
        super(scene);
    }

    public final void isWrong(){state = HintState.NOTSOLUTION;}
    public final void isDisplaced(){state = HintState.DISPLACED;}
    public final void isCorrect(){state = HintState.CORRECT;}

}