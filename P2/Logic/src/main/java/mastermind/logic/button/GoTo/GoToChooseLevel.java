package mastermind.logic.button.GoTo;

import mastermind.engine.IScene;
import mastermind.logic.scene.Scene;
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
