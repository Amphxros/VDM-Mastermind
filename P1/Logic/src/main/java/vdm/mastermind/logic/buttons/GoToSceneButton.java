package vdm.mastermind.logic.buttons;

import vdm.mastermind.engine.classes.TouchEvent;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.logic.Logic;
import vdm.mastermind.logic.Scene;

public abstract class GoToSceneButton extends Button{
    public GoToSceneButton(IScene scene) {
        super(scene);
    }

    @Override
    public boolean onTouchDown(TouchEvent event) {
        Logic l= (Logic)getEngine().getLogic();
        l.setCurrentScene(createScene());
        return true;
    }

    protected abstract Scene createScene();
}
