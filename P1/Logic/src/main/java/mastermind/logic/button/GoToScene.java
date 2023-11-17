package mastermind.logic.button;

import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.Scene;

public abstract class GoToScene extends Button{
    public GoToScene(IScene scene) {
        super(scene);
    }

    @Override
    public boolean onTouchUp(TouchEvent event) {
        getEngine().getLogic().setScene(createScene());
        return true;
    }

    protected abstract Scene createScene();
}
