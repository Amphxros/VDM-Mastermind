package mastermind.logic.scene;

import mastermind.engine.Color;
import mastermind.engine.HorizontalAlignment;
import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.logic.Scene;
import mastermind.logic.Text;
import mastermind.logic.button.GoToChooseLevel;
import mastermind.logic.button.GoToModeExplore;

public class MenuScene extends Scene {
    public MenuScene(IEngine engine) {
        super(engine);
    }

    @Override
    public void init() {
        IFont font = getEngine().getGraphics().newFont("fonts/handwriting.ttf",40,false);
        IFont fonty = getEngine().getGraphics().newFont("fonts/handwriting.ttf",25,false);

        int maxWidth = getEngine().getGraphics().getWidth();
        int center = maxWidth / 2;

        int buttonW = (int) (maxWidth * 0.8);
        int buttonX = center - buttonW / 2;
        // Title
        addGameObject(new Text(this, "Mastermind", font)
                .setPosition(center, 150)
                .setStrokeColor(new Color(100,100,100))

        );
        Text t1= new Text(this, "partida rapida",fonty);
        t1.setPosition(center-50,25);
        t1.setAlignment(HorizontalAlignment.CENTRE);

        Text t2= new Text(this, "explorar mundos",fonty);
        t2.setPosition(center-50,25);
        t2.setAlignment(HorizontalAlignment.CENTRE);


        addGameObject(new GoToChooseLevel(this)
                .setPosition(center/3,250)
                .setSize(300,50)
                .setStrokeColor(new Color(150,150,150))
                .addChild(t1)
        );

        addGameObject(new GoToModeExplore(this)
                .setPosition(center/3,350)
                .setSize(300,50)
                .setStrokeColor(new Color(150,150,150))
                .addChild(t2)
        );
    }
}
