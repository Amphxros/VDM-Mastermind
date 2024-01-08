package mastermind.logic.button.GoTo;


import mastermind.engine.IScene;
import mastermind.logic.scene.Scene;
import mastermind.logic.scene.ExploreWorldsScene;

public class GoToModeExplore extends GoToScene{

    public GoToModeExplore(IScene scene) {
        super(scene);

    }

    @Override
    protected Scene createScene() {
        return new ExploreWorldsScene(getEngine());
    }
}
