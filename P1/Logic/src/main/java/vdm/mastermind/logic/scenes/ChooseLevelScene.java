package vdm.mastermind.logic.scenes;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.interfaces.IEngine;
import vdm.mastermind.engine.interfaces.objects.IFont;
import vdm.mastermind.engine.interfaces.objects.IImage;
import vdm.mastermind.logic.buttons.GoToChooseLevelScene;
import vdm.mastermind.logic.buttons.GoToGameScene;
import vdm.mastermind.logic.buttons.GoToMenuScene;
import vdm.mastermind.logic.gameobjects.GameObject;
import vdm.mastermind.logic.gameobjects.ImageObject;
import vdm.mastermind.logic.gameobjects.TextObject;

public class ChooseLevelScene extends Scene{
    public ChooseLevelScene(IEngine engine) {
        super(engine);
    }

    @Override
    public void init() {
        IFont font= getEngine().getGraphics().newFont("fonts/Shade June Free Trial.ttf",30,true,true);
        IFont fontTittle= getEngine().getGraphics().newFont("fonts/Falling For Autumn.ttf",20,true,true);

        IImage back= getEngine().getGraphics().newImage("images/back_button.png");

        int maxWidth = getEngine().getGraphics().getWidth();
        int center = maxWidth / 2;
        int buttonW = (int) (maxWidth/3);
        int buttonX = 20;

        addGameObject(createText("¿En que dificultad quiere jugar?",fontTittle,new Color(0,0,0),70, 50));

        ImageObject img= new ImageObject(this,back);
        img.setPosition(buttonX,20);
        img.setStrokeColor(new Color(0,0,0));
        img.setSize(50,50);
        addGameObject(img);

        GoToMenuScene menuScene= new GoToMenuScene(this);
        menuScene.setPosition(buttonX,20);
        menuScene.setSize(60,50);
        menuScene.setStrokeColor(new Color(0,0,0));
        addGameObject(menuScene);

        addGameObject(createButton(4,5,4,new Color(200,200,120),buttonX, 110,buttonW, 45));
        addGameObject(createButton(6,5,4,new Color(249,231,132),buttonX,160,buttonW,45));
        addGameObject(createButton(6,6,6,new Color(229,145,101),buttonX,210,buttonW,45));
        addGameObject(createButton(10,10,5,new Color(208,83,83),buttonX,260,buttonW,45));

        addGameObject(createText("Facil",font,new Color(0,0,0),buttonX + buttonW/3, 140));
        addGameObject(createText("Medio",font,new Color(0,0,0),buttonX + buttonW/3, 190));
        addGameObject(createText("Dificil",font,new Color(0,0,0),buttonX + buttonW/3, 240));
        addGameObject(createText("Imposible",font,new Color(0,0,0),buttonX + buttonW/3, 290));

    }

    private GoToGameScene createButton(int colors, int tries, int tamPassword, Color c, int posX, int posY, int width, int height){
        GoToGameScene chooseDifficultyA= new GoToGameScene(this,colors,tries,tamPassword);
        chooseDifficultyA.setStrokeColor(c);
        chooseDifficultyA.setPosition(posX, posY);
        chooseDifficultyA.setSize(width,height);


        return chooseDifficultyA;
    }

    private TextObject createText(String tittle, IFont font, Color c,int posX, int posY){
        TextObject textObject= new TextObject(this,font,tittle);
        textObject.setPosition(posX , posY);
        textObject.setStrokeColor(c);
        return textObject;
    }
}
