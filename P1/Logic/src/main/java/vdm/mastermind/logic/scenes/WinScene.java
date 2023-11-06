package vdm.mastermind.logic.scenes;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.interfaces.IEngine;
import vdm.mastermind.engine.interfaces.objects.IFont;
import vdm.mastermind.logic.Password;
import vdm.mastermind.logic.buttons.GoToChooseLevelScene;
import vdm.mastermind.logic.gameobjects.GameObject;
import vdm.mastermind.logic.gameobjects.TextObject;

public class WinScene extends Scene{

    boolean hasWon;
    public WinScene(IEngine engine, boolean hasWon, int numTries, Color[] colors, Password password) {
        super(engine);
        this.hasWon= hasWon;
    }

    @Override
    public void init() {
        IFont font= getEngine().getGraphics().newFont("fonts/Shade June Free Trial.ttf",30,true,true);
        IFont fontTittle= getEngine().getGraphics().newFont("fonts/Falling For Autumn.ttf",40,true,true);

        int maxWidth = getEngine().getGraphics().getWidth();
        int center = maxWidth / 2;
        int buttonW = (int) (maxWidth/4);
        int buttonX = (int)center - (int)(maxWidth * 0.9) / 2;

        if(hasWon){
            // Title
            GameObject tittle=(GameObject)new TextObject(this, fontTittle,"Enhorabuena");
            tittle.setPosition(buttonX, 100);
            tittle.setSize(buttonW,100);
            addGameObject(tittle);
        }
        else{
            // Title
            GameObject tittle=(GameObject)new TextObject(this, fontTittle,"Game Over");
            tittle.setStrokeColor(new Color(0,0,0));
            tittle.setPosition(buttonX, 100);
            tittle.setSize(buttonW,100);
            addGameObject(tittle);
        }

        GoToChooseLevelScene levelScene= new GoToChooseLevelScene(this);
        levelScene.setPosition(buttonX, 400);
        levelScene.setSize(2*buttonW, 50);
        levelScene.setStrokeColor(new Color(0,200,200));
        addGameObject(levelScene);

        levelScene= new GoToChooseLevelScene(this);
        levelScene.setPosition(buttonX, 500);
        levelScene.setSize(2*buttonW, 50);
        levelScene.setStrokeColor(new Color(0,200,200));
        addGameObject(levelScene);
        super.init();





    }
}
