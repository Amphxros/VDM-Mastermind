package vdm.mastermind.logic.scenes;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.interfaces.IEngine;
import vdm.mastermind.engine.interfaces.objects.IFont;
import vdm.mastermind.logic.buttons.GameObject;
import vdm.mastermind.logic.buttons.GoToSceneButton;
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


    }
}
