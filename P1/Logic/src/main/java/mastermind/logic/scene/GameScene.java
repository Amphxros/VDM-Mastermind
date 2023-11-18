package mastermind.logic.scene;

import java.util.Random;
import java.util.random.RandomGenerator;

import mastermind.engine.Color;
import mastermind.engine.EventType;
import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.engine.IInput;
import mastermind.engine.TouchEvent;
import mastermind.logic.GameObject;
import mastermind.logic.Scene;
import mastermind.logic.Table;
import mastermind.logic.Text;

public class GameScene extends Scene {

    private int numColores;
    private int numIntentos;
    private int tamPassword;

    Color[] colors;
    int [] solution;


    Table[] tables;
    int currTable;

    Text tryText;
    public GameScene(IEngine engine,int numColores, int numIntentos, int tamPassword) {
        super(engine);
        this.numColores=numColores;
        this.numIntentos=numIntentos;
        this.tamPassword=tamPassword;

        this.tables= new Table[this.numIntentos];
        this.solution= new int[this.tamPassword];
        this.colors= new Color[this.numColores];
        this.currTable=0;
    }

    @Override
    public void init() {
        IFont font = getEngine().getGraphics().newFont("fonts/handwriting.ttf",40,false);
        IFont fonty = getEngine().getGraphics().newFont("fonts/handwriting.ttf",15,false);


        generateData();

        tryText= (Text) new Text(this,"Tienes "+this.numIntentos+" intentos restantes",fonty)
                .setPosition(200,100)
                .setStrokeColor(Color.BLACK);

        addGameObject(tryText);


        for(int i=0;i<this.numIntentos; i++){
            Table t= (Table) createTable(i,50,100 + 50* (i+1), 300, 45, Color.BLACK,fonty);
            addGameObject(t);
            tables[i]=t;
        }



        super.init();


    }

    private void generateData(){

        for(int i=0;i<this.tamPassword;i++){
            this.solution[i]= (int)Math.floor(Math.random() * (this.numColores + 1));
            System.out.println( this.solution[i]);
        }
        for(int i=0;i<this.numColores;i++){
            int r= (int)Math.floor(Math.random() * (255));
            int g= (int)Math.floor(Math.random() * (255));
            int b= (int)Math.floor(Math.random() * (255));

            this.colors[i]= new Color(r,g,b);

        }

    }

    private GameObject createTable(int index, int x, int y, int w, int h, Color color, IFont font){
        return new Table(this, this.tamPassword,font)
                .setPosition(x,y)
                .setSize(w,h)
                .setStrokeColor(color)

                .addChild(new Text(this,String.valueOf(index),font)
                        .setPosition(10,3*h/4))
                ;
    }

    @Override
    public void handleInput(IInput input) {
        super.handleInput(input);
    }
}
