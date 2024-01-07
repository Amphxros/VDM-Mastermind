package mastermind.logic.scene;

import mastermind.engine.Color;
import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.engine.IImage;
import mastermind.logic.Container;
import mastermind.logic.GameObject;
import mastermind.logic.Image;
import mastermind.logic.Scene;
import mastermind.logic.Text;
import mastermind.logic.button.GoToChooseLevel;
import mastermind.logic.button.GoToGameScene;
import mastermind.logic.button.GoToMenuScene;

/**
 * Clase que representa la escena para elegir la dificultad de juego en el Mastermind.
 */
public class ChooseLevelScene extends Scene {

    /**
     * Constructor de la clase ChooseLevelScene.
     *
     * @param engine El motor del juego.
     */
    public ChooseLevelScene(IEngine engine) {
        super(engine);
    }

    /**
     * Inicializa la escena para elegir la dificultad de juego.
     */
    @Override
    public void init() {
        IFont font = getEngine().getGraphics().newFont("fonts/handwriting.ttf",15,false);
        IImage back= getEngine().getGraphics().newImage("images/back_button.png");

        int maxWidth = getEngine().getGraphics().getWidth();
        int center = maxWidth / 2;

        int buttonW = (int) (maxWidth * 0.8);
        int buttonX = center - buttonW / 2;

        addGameObject(new GoToMenuScene(this)
                .setPosition(0,20)
                .setSize(50,50)
                .setStrokeColor(new Color(200,200,200))

                .addChild(new Image(this, back)
                        .setSize(50,50)
                )

        );

        // Título
        addGameObject(new Text(this,"¿en que dificultad quieres jugar?",font)
                .setPosition(center, 50));

        addGameObject(new Container(this)
                .setPosition(100, 100)
                .setSize(center, center)
                .setStrokeColor(new Color(150,150,150,0))

                .addChild(createGameButton(0,0,center,center/4,new Color(200,200,120),"facil",font,4,6,4,false))
                .addChild(createGameButton(0,center/3,center,center/4,new Color(249,231,132),"Medio",font,6,6,5,false))
                .addChild(createGameButton(0,2*center/3,center,center/4,new Color(229,145,101),"Dificil",font,6,6,6,true))
                .addChild(createGameButton(0,center,center,center/4,new Color(208,83,83),"Imposible",font,6,10,6,true))
        );
    }

    /**
     * Método privado para crear un botón de juego con la configuración especificada.
     */
    private GameObject createGameButton(int x, int y, int w, int h,
                                        Color color, String text, IFont font,
                                        int numColors, int numIntentos, int tamPassword,boolean repeating ){
        return new GoToGameScene(this,numColors,numIntentos,tamPassword,repeating)
                .setPosition(x,y)
                .setSize(w,h)
                .setStrokeColor(color)
                .addChild(new Text(this,text,font)
                        .setPosition(w,3*h - h/3)
                        .setStrokeColor(Color.BLACK)

                );
    }
}
