package mastermind.logic.button;

import mastermind.logic.scene.IScene;
import mastermind.logic.Scene;
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
