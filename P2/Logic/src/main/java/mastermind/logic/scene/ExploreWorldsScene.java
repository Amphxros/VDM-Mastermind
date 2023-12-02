package mastermind.logic.scene;

import mastermind.engine.IEngine;
import mastermind.logic.Scene;

public class ExploreWorldsScene extends Scene {

    public ExploreWorldsScene(IEngine engine) {
        super(engine);
    }

    @Override
    public void init() {
        int maxWidth = getEngine().getGraphics().getWidth();
        int centerX = maxWidth / 2;
    }
}
