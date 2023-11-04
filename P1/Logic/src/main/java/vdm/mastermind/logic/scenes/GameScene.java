package vdm.mastermind.logic.scenes;

import java.util.ArrayList;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.interfaces.IEngine;
import vdm.mastermind.engine.interfaces.objects.IImage;
import vdm.mastermind.engine.interfaces.objects.ISound;
import vdm.mastermind.logic.Password;
import vdm.mastermind.logic.buttons.DaltonicButton;
import vdm.mastermind.logic.gameobjects.GameObject;
import vdm.mastermind.logic.gameobjects.Table;

public class GameScene extends Scene{

    int numColors;
    int numIntentos;
    int tamPassword;

    Password solution;
    Color[] colors;


    ArrayList<GameObject> daltonicListeners;
    public GameScene(IEngine engine, int numColors, int numIntentos, int tamPassword) {
        super(engine);
        this.numColors=numColors;
        this.numIntentos= numIntentos;
        this.tamPassword= tamPassword;

    }

    @Override
    public void init() {
        IImage open= getEngine().getGraphics().newImage("images/eye_opened.png");
        IImage close= getEngine().getGraphics().newImage("images/eye_closed_icon.png");
        ISound eyeSound= getEngine().getAudio().createSound("sounds/2.wav");

        this.solution= new Password(this.tamPassword,1,this.numColors, this.numColors==4);
        this.solution.generateRandom();

        this.colors= new Color[this.numColors];

        DaltonicButton daltonicButton= new DaltonicButton(this,open,close, eyeSound);
        daltonicButton.setSize(50,50);
        daltonicButton.setPosition(290,0);

        addGameObject(daltonicButton);

        Table t= new Table(this, 1, 4,this.numColors);
        t.setSize(300,50);
        t.setPosition(10,50);
        addGameObject(t);

        t= new Table(this, 2, 4,this.numColors);
        t.setSize(300,50);
        t.setPosition(10,110);
        addGameObject(t);

        t= new Table(this, 3, 4,this.numColors);
        t.setSize(300,50);
        t.setPosition(10,170);
        addGameObject(t);

        t= new Table(this, 4, 4,this.numColors);
        t.setSize(300,50);
        t.setPosition(10,230);
        addGameObject(t);

        t= new Table(this, 5, 4,this.numColors);
        t.setSize(300,50);
        t.setPosition(10,290);
        addGameObject(t);


    }
    public void onDaltonicMode(boolean act){
        for(GameObject g: daltonicListeners){
            //set daltonicMode
        }
    }
    private void generateColors(int numColors){
        for(int i=0;i<this.numColors;i++){
            switch (i){
                case 0:
                    colors[i]= new Color(208,83,83);
                    break;
                case 1:
                    colors[i]= new Color(229,145,10);
                    break;
                case 2:
                    colors[i]= new Color(249,231,132);
                    break;
                case 3:
                    colors[i]= new Color(200,200,120);
                    break;
                case 4:
                    colors[i]= new Color(96,120,68);
                    break;
                case 5:
                    colors[i]= new Color(216,230,198);
                    break;
                case 6:
                    colors[i]= new Color(56,160,198);
                    break;
                case 7:
                    colors[i]= new Color(133,183,150);
                    break;
                case 8:
                    colors[i]= new Color(143,184,222);
                    break;

            }
        }
    }
}
