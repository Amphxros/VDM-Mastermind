package mastermind.logic.button;


import mastermind.engine.IScene;
import mastermind.logic.Scene;
import mastermind.logic.scene.ExploreWorldsScene;

public class GoToModeExplore extends Button{

    public GoToModeExplore(IScene scene) {
        super(scene);

    }

    @Override
    protected Scene createScene() {
        return new ExploreWorldsScene(getEngine());
    }
}
