package vdm.mastermind.logic.scenes;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.interfaces.IEngine;
import vdm.mastermind.engine.interfaces.objects.IFont;
import vdm.mastermind.logic.Password;
import vdm.mastermind.logic.buttons.GoToChooseLevelScene;
import vdm.mastermind.logic.gameobjects.GameObject;
import vdm.mastermind.logic.gameobjects.Table;
import vdm.mastermind.logic.gameobjects.TextObject;

public class WinScene extends Scene{

    Password password;

    boolean hasWon;
    int tries;
    int tamPassword;

    Color[] colors;

    public WinScene(IEngine engine, boolean hasWon, int numTries, int tam, Color[] colors, Password password) {
        super(engine);
        this.hasWon= hasWon;
        this.tries=numTries;
        this.colors=colors;
        this.password=password;
        this.tamPassword=tam;
        for(int i=0;i<colors.length;i++){
            this.colors[i]=colors[i];
        }
    }

    @Override
    public void init() {
        IFont font= getEngine().getGraphics().newFont("fonts/Shade June Free Trial.ttf",30,true,true);
        IFont fontTittle= getEngine().getGraphics().newFont("fonts/Falling For Autumn.ttf",40,true,true);

        if(hasWon){
            addGameObject(createText(fontTittle,"Enhorabuena", 20,30));
            addGameObject(createText(font, "Resuelto en" + this.tries + "intentos!",20,60));
            addGameObject(createText(font, "codigo",20,80));
        }
        else{
            addGameObject(createText(fontTittle,"Game Over", 20,30));
            addGameObject(createText(font, "Te has quedado sin intentos" + this.tries + "intentos!",20,60));
            addGameObject(createText(font, "codigo",20,80));

        }


        Table t= createTable(tries,font, 20, 150,tamPassword,colors.length);

        t.setSize(300,100);

        addGameObject(t);


        super.init();
    }

    private TextObject createText(IFont font, String msg, int posX, int posY){
        TextObject text= new TextObject(this, font,msg);
        text.setPosition(posX, posY);
        text.setSize(200,20);
        text.setStrokeColor(new Color(0,0,0));

        return text;
    }

    private Table createTable(int index,IFont font,int posX, int posY, int tamPassword, int numColors){
        Table t= new Table(this, index, font,tamPassword,numColors);
        t.setSize(300,40);
        t.setStrokeColor(new Color(0,0,0));
        t.setPosition(posX,posY);

        return t;
    }
}
