package vdm.mastermind.logic.scenes;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.interfaces.IEngine;
import vdm.mastermind.engine.interfaces.objects.IImage;
import vdm.mastermind.logic.Password;
import vdm.mastermind.logic.Table;
import vdm.mastermind.logic.buttons.DaltonicButton;

public class GameScene extends Scene{

    int numColors;
    int numIntentos;
    int tamPassword;

    Password solution;
    Color[] colors;

    Table table;

    public GameScene(IEngine engine, int numColors, int numIntentos, int tamPassword) {
        super(engine);
        this.numColors=numColors;
        this.numIntentos= numIntentos;
        this.tamPassword= tamPassword;
        this.table = new Table(this, numColors, numIntentos, tamPassword);
    }

    @Override
    public void init() {
        IImage open= getEngine().getGraphics().newImage("images/eye_opened.png");
        IImage close= getEngine().getGraphics().newImage("images/eye_closed_icon.png");

        this.solution= new Password(this.tamPassword,1,this.numColors);
        this.solution.generateRandom();

        this.colors= new Color[this.numColors];

        DaltonicButton daltonicButton= new DaltonicButton(this,open,close);
        daltonicButton.setSize(50,50);
        daltonicButton.setPosition(300,50);

        addGameObject(daltonicButton);


    }
    public void onDaltonicMode(boolean act){

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
