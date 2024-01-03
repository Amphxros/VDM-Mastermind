package mastermind.logic.scene;

import java.util.ArrayList;

import mastermind.engine.Color;
import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.engine.IImage;
import mastermind.engine.IInput;
import mastermind.engine.ISensorListener;
import mastermind.engine.ISensorsManager;
import mastermind.engine.ISound;
import mastermind.logic.BackgroundImage;
import mastermind.logic.ColouringCell;
import mastermind.logic.ColouringTable;
import mastermind.logic.DaltonicListener;
import mastermind.logic.GameObject;
import mastermind.logic.Image;
import mastermind.logic.ScrollEventListener;
import mastermind.logic.Table;
import mastermind.logic.Text;
import mastermind.logic.button.DaltonicButton;
import mastermind.logic.button.GoToChooseLevel;
import mastermind.logic.button.GoToModeExplore;

public class GameScene extends Scene implements ISensorListener {

    ColouringTable c;
    private int numColores;
    private int numIntentos;
    private int tamPassword;

    int currTable;
    Color[] colors;
    Table[] tables;
    int [] solution;
    ArrayList<DaltonicListener> daltonicObservers;
    private ArrayList<ScrollEventListener> scrollEventListeners;
    boolean isRepeating;
    boolean colorsEnable;
    boolean fileScene;
    Text tryText;
    ISound sonidoWin, sonidoGameover;
    int num_world, level;
    public GameScene(IEngine engine,int numColores, int numIntentos, int tamPassword, boolean isRepeating, boolean circles, boolean fileScene,int world, int level) {
        super(engine);
        this.numColores=numColores;
        this.numIntentos=numIntentos;
        this.tamPassword=tamPassword;

        this.num_world=world;
        this.level=level;

        this.tables= new Table[this.numIntentos];
        this.solution= new int[this.tamPassword];
        this.colors= new Color[this.numColores];
        this.currTable=0;
        this.isRepeating=isRepeating;
        this.colorsEnable=circles;

        this.daltonicObservers= new ArrayList<>();
        this.scrollEventListeners = new ArrayList<>();
        this.fileScene=fileScene;
    }
    @Override
    public String getID() {
        return "Game";
    }
    @Override
    public void init() {
        IFont font = getEngine().getGraphics().newFont("fonts/handwriting.ttf",40,false);
        IFont fonty = getEngine().getGraphics().newFont("fonts/KIN668.ttf",15,false);

        IImage open= getEngine().getGraphics().newImage("images/eye_opened.png");
        IImage background= getEngine().getGraphics().newImage("images/backgrounds/background-" + (num_world+1) + ".png");

        IImage close= getEngine().getGraphics().newImage("images/eye_closed_icon.png");
        IImage back= getEngine().getGraphics().newImage("images/back_button.png");

        ISound sonidoDaltonic = getEngine().getAudio().createSound("sonido/button_click.mp3");
        this.sonidoWin = getEngine().getAudio().createSound("sonido/street_fighter_you_win.mp3");
        this.sonidoGameover = getEngine().getAudio().createSound("sonido/gameover.mp3");

        ISensorsManager sensors= getEngine().getSensorsManager();
        if(sensors!=null){
            sensors.registerAccelerometerListener(this);
        }



        addGameObject(new BackgroundImage(this,background));

        generateData();

        tryText= (Text) new Text(this,"Tienes "+this.numIntentos+" intentos restantes",fonty)
                .setPosition(200,50)
                .setColor(Color.BLACK);
        addGameObject(tryText);

        if(fileScene){
            addGameObject(new GoToModeExplore(this)
                    .setPosition(20,20)
                    .setSize(50,50)
                    .setColor(new Color(200,200,200,150))

                    .addChild(new Image(this, back)
                            .setSize(50,50)
                    )

            );
        }
        else{
        addGameObject(new GoToChooseLevel(this)
                .setPosition(20,20)
                .setSize(50,50)
                .setColor(new Color(200,200,200,150))

                .addChild(new Image(this, back)
                        .setSize(50,50)
                )

        );
        }


        addGameObject(new DaltonicButton(this, open, close, sonidoDaltonic)
                .setPosition(330,20)
                .setSize(50,50)
                .setColor(Color.BLACK)
        );

        for(int i=0;i<this.numIntentos; i++){
            Table t= (Table) createTable(i,20,50 + 50* (i+1), 350, 45, getLogicData().getButtons(),fonty);
            addGameObject(t);
            daltonicObservers.add(t);
            tables[i]=t;
        }

        c= (ColouringTable) new ColouringTable(this, this.numColores,this.colors)
            .setPosition(0,500)
            .setSize(400,70)
            .setColor(getLogicData().getFont());
        addGameObject(c);
        daltonicObservers.add(c);

        super.init();

    }


    private void generateData(){
        /**
         * Generate password with repetitions
         */
        if(isRepeating){
            for(int i=0;i<this.tamPassword;i++){
                this.solution[i]= (int)Math.floor(Math.random() * (this.numColores));
                System.out.println( this.solution[i]);
            }
        }
        /**
         * Generate password without repetitions
         */
        else{
            boolean[] repetition= new boolean[this.numColores+1];
            for(int i=0;i<this.tamPassword;i++){
               repetition[i]=false;
            }
            int i=0;
            int r= (int)Math.floor(Math.random() * (this.numColores));

            while (i<this.tamPassword){
                if(!repetition[r]){
                    repetition[r]=true;
                    this.solution[i]=r;
                    i++;
                    System.out.println(r);
                }
                r=(int)Math.floor(Math.random() * (this.numColores));
            }


        }
        generateColors();
        String aux = "";
        for(int i = 0; i < solution.length; i++){
            aux = aux + solution[i] + ", ";
        }
        System.out.println("Password: " + aux);
    }

    /**
     * Generate colors
     */
    private void generateColors(){
        if(colorsEnable){
            for(int i=0;i<this.numColores;i++){
                int r= (int)Math.floor(Math.random() * (255));
                int g= (int)Math.floor(Math.random() * (255));
                int b= (int)Math.floor(Math.random() * (255));

                this.colors[i]= new Color(r,g,b);

            }
        }
    }

    private GameObject createTable(int index, int x, int y, int w, int h, Color color, IFont font){
        return new Table(this, this.tamPassword,font,true)
                .setPosition(x,y)
                .setSize(w,h)
                .setColor(color)

                .addChild(new Text(this,String.valueOf(index+1),font)
                        .setPosition(10,3*h/4)
                );
    }

    @Override
    public void handleInput(IInput input) {
        super.handleInput(input);
    }



    public void onDaltonicMode(boolean mode){
        for(DaltonicListener d : daltonicObservers)
            d.setDaltonicMode(mode);
    }

    public void onColouringCellSelected(Color c,int value, IImage image){
        System.out.println("Click " + value);
        tables[currTable].fillCell(c,value,image);
        if(tables[currTable].isComplete()){
           if(tables[currTable].correctHints(this.solution)){
               this.sonidoWin.play();
               if(fileScene){
                   if(this.num_world ==getLogicData().getLastWorld() && this.level ==getLogicData().getLastLevel()){
                       getLogicData().onLevelCompleted();
                       try{
                        getLogicData().saveData();
                       }
                       catch (Exception e){
                           e.printStackTrace();
                       }
                   }
               }

               getEngine().getLogic().setScene(new WinScene(getEngine(),colors,solution,true,10* (numIntentos),fileScene));


           }
           else
           {
               currTable++;
               numIntentos--;
               if(numIntentos>0){
                   tryText.setText("Tienes "+this.numIntentos+" intentos restantes");
               }
               else{
                   this.sonidoGameover.play();
                   getEngine().getLogic().setScene(new WinScene(getEngine(),colors,solution,false,0,fileScene));
               }
           }
        }
    }

    @Override
    public void onSense() {
        generateColors();
        int i=0;
        for (GameObject col : c.getChildren()){
            if((ColouringCell) col !=null ){
                col.setColor(colors[i]);
                i++;
            }
        }

    }


}
