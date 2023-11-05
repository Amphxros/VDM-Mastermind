package vdm.mastermind.logic.scenes;

import java.util.ArrayList;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.interfaces.IEngine;
import vdm.mastermind.engine.interfaces.objects.IFont;
import vdm.mastermind.engine.interfaces.objects.IImage;
import vdm.mastermind.engine.interfaces.objects.ISound;
import vdm.mastermind.logic.DaltonicListener;
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
    Table[] tables;

    ArrayList<DaltonicListener> daltonicListeners;
    public GameScene(IEngine engine, int numColors, int numIntentos, int tamPassword) {
        super(engine);
        this.numColors=numColors;
        this.numIntentos= numIntentos;
        this.tamPassword= tamPassword;

        this.daltonicListeners= new ArrayList<>();
    }

    @Override
    public void init() {
        IImage open= getEngine().getGraphics().newImage("images/eye_opened.png");
        IImage close= getEngine().getGraphics().newImage("images/eye_closed_icon.png");
        ISound eyeSound= getEngine().getAudio().createSound("sounds/2.wav");
        IFont font= getEngine().getGraphics().newFont("fonts/Shade June Free Trial.ttf",30,true,true);

        this.solution= new Password(this.tamPassword,1,this.numColors, this.numColors==4);
        this.solution.generateRandom();

        this.colors= new Color[this.numColors];

        //Create an array to save the tables
        this.tables = new Table[this.numIntentos];

        DaltonicButton daltonicButton= new DaltonicButton(this,open,close, eyeSound);
        daltonicButton.setSize(50,50);
        daltonicButton.setPosition(270,0);

        addGameObject(daltonicButton);

        for(int i = 0; i < this.numIntentos; ++i){
            this.tables[i] = createTable(i+1,10,50 + (40 * i));
            addGameObject((GameObject)this.tables[i]);
        }
        //addGameObject(createTable(1,0,50));
        //addGameObject(createTable(1,0,105));

    }
    public void onDaltonicMode(boolean act){
        for(DaltonicListener g: daltonicListeners){
            //set daltonicMode
            g.setDaltonicMode(act);
            g.onDaltonicMode();
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

    private Table createTable(int index,int posX, int posY){
        Table t= new Table(this, index, this.numIntentos,this.numColors);
        t.setSize(300,35);
        t.setPosition(posX,posY);

        return t;
    }
}
