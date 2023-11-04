package vdm.mastermind.logic.scenes;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.interfaces.IEngine;
import vdm.mastermind.engine.interfaces.objects.IFont;
import vdm.mastermind.logic.buttons.GoToChooseLevelScene;
import vdm.mastermind.logic.buttons.GoToGameScene;
import vdm.mastermind.logic.gameobjects.GameObject;
import vdm.mastermind.logic.gameobjects.TextObject;

public class ChooseLevelScene extends Scene{
    public ChooseLevelScene(IEngine engine) {
        super(engine);
    }

    @Override
    public void init() {
        IFont font= getEngine().getGraphics().newFont("fonts/Shade June Free Trial.ttf",30,true,true);
        IFont fontTittle= getEngine().getGraphics().newFont("fonts/Falling For Autumn.ttf",20,true,true);

        int maxWidth = getEngine().getGraphics().getWidth();
        int center = maxWidth / 2;
        int buttonW = (int) (maxWidth/4);
        int buttonX = center -(int)(maxWidth * 0.9) / 2;

        // Title
        GameObject tittle=(GameObject)new TextObject(this, fontTittle,"¿En que dificultad quiere jugar?");
        tittle.setPosition(buttonX/2, 100);
        tittle.setSize(buttonW,100);
        addGameObject(tittle);

        GoToGameScene chooseDifficultyA= new GoToGameScene(this,4,5,4);
        chooseDifficultyA.setStrokeColor(new Color(200,200,120));
        chooseDifficultyA.setPosition(buttonX, 210);
        chooseDifficultyA.setSize(buttonW,50);

        addGameObject(chooseDifficultyA);

        GoToGameScene chooseDifficultyB= new  GoToGameScene(this,6,5,4);
        chooseDifficultyB.setStrokeColor(new Color(249,231,132));
        chooseDifficultyB.setPosition(buttonX, 260);
        chooseDifficultyB.setSize(buttonW,50);

        addGameObject(chooseDifficultyB);

        GoToGameScene chooseDifficultyC= new GoToGameScene(this,6,6,6);
        chooseDifficultyC.setStrokeColor(new Color(229,145,101));
        chooseDifficultyC.setPosition(buttonX, 310);
        chooseDifficultyC.setSize(buttonW,50);

        addGameObject(chooseDifficultyC);

        GoToGameScene chooseDifficultyD= new GoToGameScene(this,10,10,5);
        chooseDifficultyD.setStrokeColor(new Color(208,83,83));
        chooseDifficultyD.setPosition(buttonX, 360);
        chooseDifficultyD.setSize(buttonW,50);

        addGameObject(chooseDifficultyD);

        TextObject textObject= new TextObject(this,font,"Facil");
        textObject.setPosition(buttonX , 230);
        addGameObject(textObject);

        textObject= new TextObject(this,font,"Medio");
        textObject.setPosition(buttonX , 280);
        addGameObject(textObject);

        textObject= new TextObject(this,font,"Dificil");
        textObject.setPosition(buttonX, 330);
        addGameObject(textObject);

        textObject= new TextObject(this,font,"Imposible");
        textObject.setPosition(buttonX, 380);
        addGameObject(textObject);



    }
}
