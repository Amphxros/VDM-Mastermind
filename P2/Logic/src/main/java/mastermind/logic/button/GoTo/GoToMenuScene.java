package mastermind.logic.button.GoTo;

import mastermind.engine.IScene;
import mastermind.logic.scene.Scene;
import mastermind.logic.scene.MenuScene;

public class GoToMenuScene extends GoToScene{
    public GoToMenuScene(IScene scene) {
        super(scene);
    }

    @Override
    protected Scene createScene() {
        return new MenuScene(getEngine());
    }
}
