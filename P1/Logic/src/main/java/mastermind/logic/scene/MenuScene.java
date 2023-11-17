package mastermind.logic.scene;

import mastermind.engine.Color;
import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.logic.Scene;
import mastermind.logic.Text;

public class MenuScene extends Scene {
    public MenuScene(IEngine engine) {
        super(engine);
    }

    @Override
    public void init() {
        IFont font = getEngine().getGraphics().newFont("fonts/handwriting.ttf",40,false);

        int maxWidth = getEngine().getGraphics().getWidth();
        int center = maxWidth / 2;

        int buttonW = (int) (maxWidth * 0.8);
        int buttonX = center - buttonW / 2;
        // Title
        addGameObject(new Text(this, "Mastermind", font).setPosition(center, 150)
                .setStrokeColor(new Color(100,100,100))
                .addChild(new Text(this, "Partida Rapida", font).setPosition(0, 150))
                .addChild(new Text(this, "Partida Rapida", font).setPosition(0, 250))
        );


    }
}
