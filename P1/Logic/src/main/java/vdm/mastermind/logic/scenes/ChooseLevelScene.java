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
        IFont font= getEngine().getGraphics().newFont("fonts/Shade June Free Trial.ttf",1,true,true);
        IFont fontTittle= getEngine().getGraphics().newFont("fonts/Falling For Autumn.ttf",1,true,true);
        int maxWidth = getEngine().getGraphics().getWidth();
        int center = maxWidth / 2;
        int buttonW = (int) (maxWidth * 0.8);
        int buttonX = center - buttonW / 2;

        // Title
        GameObject tittle=(GameObject)new TextObject(this, fontTittle,20.0f,new Color(0,0,0),"¿En que dificultad quiere jugar?");
        tittle.setPosition(buttonX/2, 100);
        tittle.setSize(buttonW,100);
        addGameObject(tittle);


        GoToGameScene chooseDifficulty= new GoToGameScene(this);
        chooseDifficulty.setStrokeColor(new Color(200,200,120));
        chooseDifficulty.setPosition(buttonX, 200);
        chooseDifficulty.setSize(buttonW,40);

        addGameObject(chooseDifficulty);

        chooseDifficulty= new  GoToGameScene(this);
        chooseDifficulty.setStrokeColor(new Color(249,231,132));
        chooseDifficulty.setPosition(buttonX, 250);
        chooseDifficulty.setSize(buttonW,40);

        addGameObject(chooseDifficulty);

        chooseDifficulty= new GoToGameScene(this);
        chooseDifficulty.setStrokeColor(new Color(229,145,101));
        chooseDifficulty.setPosition(buttonX, 300);
        chooseDifficulty.setSize(buttonW,40);

        addGameObject(chooseDifficulty);

        chooseDifficulty= new GoToGameScene(this);
        chooseDifficulty.setStrokeColor(new Color(208,83,83));
        chooseDifficulty.setPosition(buttonX, 350);
        chooseDifficulty.setSize(buttonW,40);

        addGameObject(chooseDifficulty);

        TextObject textObject= new TextObject(this,font,30,new Color(0,0,0),"Facil");
        textObject.setPosition(buttonX *4, 230);
        addGameObject(textObject);

        textObject= new TextObject(this,font,30,new Color(0,0,0),"Medio");
        textObject.setPosition(buttonX + buttonW/3, 280);
        addGameObject(textObject);

        textObject= new TextObject(this,font,30,new Color(0,0,0),"Dificil");
        textObject.setPosition(buttonX + buttonW/3, 330);
        addGameObject(textObject);

        textObject= new TextObject(this,font,30,new Color(0,0,0),"Imposible");
        textObject.setPosition(buttonX + buttonW/3, 380);
        addGameObject(textObject);



    }
}
