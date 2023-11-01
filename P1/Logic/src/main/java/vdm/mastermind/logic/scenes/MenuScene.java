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
        int maxWidth = getEngine().getGraphics().getWidth();
        int center = maxWidth / 2;
        int buttonW = (int) (maxWidth * 0.8);
        int buttonX = center - buttonW / 2;

        IFont font= getEngine().getGraphics().newFont("fonts/Shade June Free Trial.ttf",50,true,true);
        IFont fontTittle= getEngine().getGraphics().newFont("fonts/Falling For Autumn.ttf",45,true,true);

        // Title
        GameObject tittle=(GameObject)new TextObject(this, fontTittle,new Color(0,0,0),"Mastermind");
        tittle.setPosition(buttonX, 150);
        tittle.setSize(buttonW,100);
        addGameObject(tittle);

        //button
        GameObject button =(GameObject)new TextObject(this, font,new Color(255,255,255),"Jugar");
        button.setPosition(buttonX+ buttonW/4, 400);
        button.setSize(buttonW/2,45);

        GoToChooseLevelScene toChooseLevelScene= new GoToChooseLevelScene(this);
        toChooseLevelScene.setStrokeColor(new Color(133,183,150));
        toChooseLevelScene.setPosition(buttonX, 350);
        toChooseLevelScene.setSize(buttonW,70);

        addGameObject(toChooseLevelScene);
        addGameObject(button);



    }
}
