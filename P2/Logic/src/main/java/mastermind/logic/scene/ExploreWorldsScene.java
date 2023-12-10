package mastermind.logic.scene;

import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.logic.Scene;
import mastermind.logic.Text;

public class ExploreWorldsScene extends Scene {

    public ExploreWorldsScene(IEngine engine) {
        super(engine);
    }

    @Override
    public void init() {

        IFont font = getEngine().getGraphics().newFont("fonts/handwriting.ttf",20,false);

        int maxWidth = getEngine().getGraphics().getWidth();
        int centerX = maxWidth / 2;

        addGameObject(new Text(this,"Explorando mundos",font)
                .setPosition(centerX, 20));
    }
}
