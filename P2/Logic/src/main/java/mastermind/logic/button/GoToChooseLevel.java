package mastermind.logic.button;

import mastermind.engine.IScene;
import mastermind.logic.Scene;
import mastermind.logic.scene.ChooseLevelScene;

public class GoToChooseLevel extends GoToScene{
    public GoToChooseLevel(IScene scene) {
        super(scene);
    }

    @Override
    protected Scene createScene() {
        return new ChooseLevelScene(getEngine());
    }
}
