package vdm.mastermind.logic.scenes;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.interfaces.IEngine;
import vdm.mastermind.engine.interfaces.objects.IFont;
import vdm.mastermind.engine.interfaces.objects.IImage;
import vdm.mastermind.logic.buttons.Button;
import vdm.mastermind.logic.buttons.GoToShopScene;
import vdm.mastermind.logic.buttons.GoToWorldsScene;
import vdm.mastermind.logic.gameobjects.GameObject;
import vdm.mastermind.logic.buttons.GoToChooseLevelScene;
import vdm.mastermind.logic.gameobjects.ImageObject;
import vdm.mastermind.logic.gameobjects.TextObject;

public class MenuScene extends Scene{
    public MenuScene(IEngine engine) {
        super(engine);
    }

    @Override
    public void init() {

        int maxWidth = getEngine().getGraphics().getWidth();
        int center = maxWidth / 2;
        int buttonW = (int) (maxWidth/3);
        int buttonX = 40;

        IFont fontTittle= getEngine().getGraphics().newFont("fonts/Shade June Free Trial.ttf",70,true,true);
        IFont font= getEngine().getGraphics().newFont("fonts/Falling For Autumn.ttf",40,true,true);
        IImage back= getEngine().getGraphics().newImage("images/capybara/basic-1.png");

        // Title
        addGameObject(new TextObject(this,font,"Mastermind")
                .setPosition(100, 50));

        // Quick Match
        addGameObject(new GoToChooseLevelScene(this)
                .setPosition(buttonX, 110)
                .setSize(buttonW, 50)
                .setStrokeColor(new Color(200,100,100)));

        addGameObject(new TextObject(this,font,"Partida Rapida")
                .setPosition(75, 150));

        // Story Mode
        addGameObject(new GoToWorldsScene(this)
                .setPosition(buttonX, 210)
                .setSize(buttonW, 50)
                .setStrokeColor(new Color(200,100,100))
        );
        addGameObject(new TextObject(this,font,"Modo Historia")
                .setPosition(75, 250)


        );

        // Story Mode
        addGameObject(new GoToShopScene(this,back)
                .setPosition(35, 400)
                .setSize(150, 150)
                .setStrokeColor(new Color(100,100,100))
        );

        addGameObject(new GoToShopScene(this,back)
                .setPosition(125, 400)
                .setSize(150, 150)
                .setStrokeColor(new Color(100,100,100))
        );
        addGameObject(new GoToShopScene(this,back)
                .setPosition(245, 400)
                .setSize(150, 150)
                .setStrokeColor(new Color(100,100,100))
        );






    }

    private GameObject createButtonWithImage(GameObject button, IImage image) {
        return button.addChild(new ImageObject(this, image)
                .setPosition(button.getPosition())
                .setSize(button.getSize()));
    }
}
