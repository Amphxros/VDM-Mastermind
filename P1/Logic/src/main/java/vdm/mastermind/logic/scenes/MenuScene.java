package vdm.mastermind.logic.scenes;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.interfaces.IEngine;
import vdm.mastermind.engine.interfaces.objects.IFont;
import vdm.mastermind.logic.gameobjects.GameObject;
import vdm.mastermind.logic.buttons.GoToChooseLevelScene;
import vdm.mastermind.logic.gameobjects.TextObject;

public class MenuScene extends Scene{
    public MenuScene(IEngine engine) {
        super(engine);
    }

    @Override
    public void init() {
        IFont font= getEngine().getGraphics().newFont("fonts/Falling For Autumn.ttf",32,false,false);
        int maxWidth = getEngine().getGraphics().getWidth();
        int center = maxWidth / 2;

        int buttonW = (int) (maxWidth * 0.8);
        int buttonX = center - buttonW / 2;
        // Title
        GameObject tittle=(GameObject)new TextObject(this, font,new Color(0,0,0),"Mastermind");
        tittle.setPosition(buttonX, 150);
        tittle.setSize(buttonW,100);
        addGameObject(tittle);

        //button
        GameObject button =(GameObject)new TextObject(this, font,new Color(0,0,0),"Jugar");
        button.setPosition(buttonX, 450);
        button.setSize(buttonW,100);

        GoToChooseLevelScene toChooseLevelScene= new GoToChooseLevelScene(this);
        toChooseLevelScene.setPosition(buttonX, 450);
        toChooseLevelScene.setSize(buttonW,100);

        addGameObject(toChooseLevelScene);




    }
}
