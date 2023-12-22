package mastermind.logic.scene;

import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.engine.IJsonObject;
import mastermind.logic.Scene;
import mastermind.logic.Text;
import mastermind.logic.WorldObject;

public class ExploreWorldsScene extends Scene {

    private WorldObject mWorldsObject [];

    public ExploreWorldsScene(IEngine engine) {
        super(engine);
    }

    @Override
    public void init() {

        IFont font = getEngine().getGraphics().newFont("fonts/handwriting.ttf",20,false);
        IJsonObject levels= getEngine().getFileManager().readJSON("levels/world1/level_1_01.json");
        int maxWidth = getEngine().getGraphics().getWidth();
        int centerX = maxWidth / 2;
         addGameObject(new Text(this,"Explorando mundos",font)
                .setPosition(centerX, 20));

         System.out.println(levels.getIntKey("attempts"));


    }
}
