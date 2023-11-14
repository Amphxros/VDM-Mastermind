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
import vdm.mastermind.logic.gameobjects.ColorTable;
import vdm.mastermind.logic.gameobjects.GameObject;
import vdm.mastermind.logic.gameobjects.Table;

public class GameScene extends Scene{

    int numColors;
    int numIntentos;
    int tamPassword;

    Password solution;
    Color[] colors;
    Table[] tables;

    int currentTries;
    ArrayList<DaltonicListener> daltonicListeners;
    public GameScene(IEngine engine, int numColors, int numIntentos, int tamPassword) {
        super(engine);
        this.numColors = numColors;
        this.numIntentos = numIntentos;
        this.tamPassword = tamPassword;
        this.currentTries = 0;
        this.daltonicListeners = new ArrayList<>();
    }

    @Override
    public void init() {
        IImage open= getEngine().getGraphics().newImage("images/eye_opened.png");
        IImage close= getEngine().getGraphics().newImage("images/eye_closed_icon.png");
        ISound eyeSound= getEngine().getAudio().createSound("sounds/2.wav");
        ISound buttonSound= getEngine().getAudio().createSound("sounds/6.wav");
        IFont font= getEngine().getGraphics().newFont("fonts/Shade June Free Trial.ttf",30,true,true);

        int maxHeight= getEngine().getGraphics().getHeight();
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
            this.tables[i] = createTable(i+1,font,0,50 + (45 * i));
            addGameObject((GameObject)this.tables[i]);
            daltonicListeners.add(this.tables[i]);
        }
        generateColors(this.numColors);

        ColorTable colorTable = new ColorTable(this, font,this.numColors, colors);
        colorTable.setPosition(10,500);
        colorTable.setSize(300,50);
        colorTable.setStrokeColor(new Color(100,100,100));
        addGameObject(colorTable);
        daltonicListeners.add(colorTable);

        super.init();

    }
    public void onDaltonicMode(boolean act){
        for(DaltonicListener g: daltonicListeners){
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
                case 9:
                    colors[i]= new Color(143,184,222);
                    break;
                case 10:
                    colors[i]= new Color(143,184,222);
                    break;

            }
        }
    }

    private Table createTable(int index,IFont font,int posX, int posY){
        Table t= new Table(this,index,font,this.tamPassword,this.numColors);
        t.setSize(300,40);
        t.setPosition(posX,posY);

        return t;
    }

    public void onColorClicked(int index) {
        System.out.println("clickado color " + index);
        if(currentTries < numIntentos){
            if(this.tables[currentTries].onColorSelected(colors[index], index)){
                currentTries++;
                //SE DA LA PISTA

                if(currentTries >=numIntentos/*&& y no has acertado*/){
                    //PIERDES TODO
                }
            };
        }
    }
}
