package mastermind.logic.scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

import mastermind.engine.Color;
import mastermind.engine.EventType;
import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.engine.IGraphics;
import mastermind.engine.IImage;
import mastermind.engine.IInput;
import mastermind.engine.ISound;
import mastermind.engine.TouchEvent;
import mastermind.logic.ColouringCell;
import mastermind.logic.ColouringTable;
import mastermind.logic.Container;
import mastermind.logic.ContainerScroll;
import mastermind.logic.DaltonicListener;
import mastermind.logic.GameObject;
import mastermind.logic.IScrollable;
import mastermind.logic.Image;
import mastermind.logic.Logic;
import mastermind.logic.Scene;
import mastermind.logic.Table;
import mastermind.logic.Text;
import mastermind.logic.button.DaltonicButton;
import mastermind.logic.button.GoToChooseLevel;

/**
 * Clase que representa la escena de juego en el Mastermind.
 */
public class GameScene extends Scene {

    // Configuraciones de juego
    private int numColores;
    private int numIntentos;
    private int tamPassword;
    int currTable;

    private static final Color[] palette={

            new Color(80,15,80),
            new Color(120,35,120),
            new Color(180,40,125),
            new Color(210,45,105),
            new Color(225,80,110),
            new Color(250,160,120)



    };

    // Datos del juego
    Color[] colors;
    Table[] tables;
    ContainerScroll containerTables;
    int [] solution;
    ArrayList<DaltonicListener> daltonicObservers;
    boolean isRepeating;
    Text tryText;

    /**
     * Constructor de la clase GameScene.
     *
     * @param engine       El motor del juego.
     * @param numColores   Número de colores disponibles.
     * @param numIntentos  Número de intentos permitidos.
     * @param tamPassword  Tamaño de la contraseña.
     * @param isRepeating  Indica si la contraseña puede tener repeticiones.
     */
    public GameScene(IEngine engine,int numColores, int numIntentos, int tamPassword, boolean isRepeating) {
        super(engine);
        this.numColores=numColores;
        this.numIntentos=numIntentos;
        this.tamPassword=tamPassword;

        this.tables= new Table[this.numIntentos];
        this.solution= new int[this.tamPassword];
        this.colors= new Color[this.numColores];
        this.containerTables = new ContainerScroll(this, 75, 500);
        this.currTable=0;
        this.isRepeating=isRepeating;

        this.daltonicObservers= new ArrayList<>();
    }

    /**
     * Inicializa la escena del juego, generando datos, configurando elementos y mostrando la información del juego.
     */
    @Override
    public void init() {
        IFont font = getEngine().getGraphics().newFont("fonts/handwriting.ttf",20,false);

        IImage open= getEngine().getGraphics().newImage("images/eye_opened.png");
        IImage close= getEngine().getGraphics().newImage("images/eye_closed_icon.png");
        IImage back= getEngine().getGraphics().newImage("images/back_button.png");

        ISound sound= getEngine().getAudio().createSound("sounds/select.wav");
        generateData();

        tryText= (Text) new Text(this,"Tienes "+this.numIntentos+" intentos restantes",font)
                .setPosition(200,75)
                .setStrokeColor(Color.BLACK);
        addGameObject(tryText);

        addGameObject(new GoToChooseLevel(this)
                .setPosition(0,20)
                .setSize(50,50)
                .setStrokeColor(new Color(200,200,200))

                .addChild(new Image(this, back)
                        .setSize(50,50)
                )

        );




        addGameObject(new DaltonicButton(this, open, close)
                .setPosition(330,20)
                .setSize(50,50)
                .setStrokeColor(Color.BLACK)
        );


        int maxWidth = getEngine().getGraphics().getWidth();
        addGameObject(containerTables
                .setPosition(0,0)
                .setSize(maxWidth/2,maxWidth/2)
                .setStrokeColor(new Color(150,150,150,0)));


        for(int i=0;i<this.numIntentos; i++){
            Table t= (Table) createTable(i,20,50 + 50* (i+1), 350, 45, Color.BLACK,font,sound);
            addGameObject(t);

            daltonicObservers.add(t);
            containerTables.addChild(t);

            tables[i]=t;
        }
        ColouringTable c= (ColouringTable) new ColouringTable(this, this.numColores,this.colors)
        .setPosition(0,500)
        .setSize(400,70)
        .setStrokeColor(new Color(200,200,200));
        addGameObject(c);
        daltonicObservers.add(c);

        super.init();

    }

    /**
     * Método privado para generar datos iniciales del juego (contraseña y colores).
     */
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
        /**
         * Generate colors
         */
        for(int i=0;i<this.numColores;i++){

            colors[i]=palette[i];
        }
        for(int i=0;i<this.tamPassword;i++){
            System.out.println(this.solution[i] + " ");

        }
        System.out.println("\n");

    }

    /**
     * Método privado para crear un objeto de tipo Table.
     */
    private GameObject createTable(int index, int x, int y, int w, int h, Color color, IFont font, ISound sound){
        return new Table(this, this.tamPassword,font,true,sound)
                .setPosition(x,y)
                .setSize(w,h)
                .setStrokeColor(color)

                .addChild(new Text(this,String.valueOf(index+1),font)
                        .setPosition(10,3*h/4)
                );
    }

    /**
     * Maneja la entrada de eventos del usuario.
     *
     * @param input El motor {@link IInput} específico asignado.
     */
    @Override
    public void handleInput(IInput input) {
        super.handleInput(input);
        IGraphics graphics = getEngine().getGraphics();
        List<TouchEvent> events = input.getTouchEvents(graphics);

        for (TouchEvent event : events) {
            if(event.getType() == EventType.DRAG){
                containerTables.onVerticalScroll(input.getDeltaY());

                /*for(Table t: tables){
                    t.onScroll(0, input.getDeltaY());
                }*/

            }
        }

    }

    /**
     * Maneja la entrada de eventos de cambio de modo daltonismo.
     *
     * @param mode Modo daltonismo activado o desactivado.
     */
    public void onDaltonicMode(boolean mode){
        for(DaltonicListener d : daltonicObservers)
            d.setDaltonicMode(mode);
    }

    /**
     * Maneja el evento de selección de una celda de color en la tabla de colores.
     *
     * @param c     Color seleccionado.
     * @param value Valor asociado al color.
     */
    public void onColouringCellSelected(Color c,int value){
        System.out.println("Click " + value);
        tables[currTable].fillCell(c,value);
        if(tables[currTable].isComplete()){
           if(tables[currTable].correctHints(this.solution)){
               Logic l= (Logic)getEngine().getLogic();
               l.setScene(new WinScene(getEngine(),colors,solution,true));
           }
           else
           {
               currTable++;
               numIntentos--;
               if(numIntentos>0){
                   tryText.setText("Tienes "+this.numIntentos+" intentos restantes");
               }
               else{
                   Logic l= (Logic)getEngine().getLogic();
                   l.setScene(new WinScene(getEngine(),colors,solution,false));
               }
           }
        }
    }
}
