package mastermind.logic.scene;

import mastermind.engine.Color;
import mastermind.engine.HorizontalAlignment;
import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.logic.Scene;
import mastermind.logic.Text;
import mastermind.logic.button.GoToChooseLevel;

/**
 * Clase que representa la escena del menú en el juego.
 */
public class MenuScene extends Scene {

    /**
     * Constructor de la clase MenuScene.
     *
     * @param engine El motor del juego.
     */
    public MenuScene(IEngine engine) {
        super(engine);
    }

    /**
     * Inicializa la escena del menú, mostrando el título y opciones para iniciar una partida rápida.
     */
    @Override
    public void init() {
        // Crea fuentes para el texto.
        IFont font = getEngine().getGraphics().newFont("fonts/handwriting.ttf",40,false);
        IFont fonty = getEngine().getGraphics().newFont("fonts/handwriting.ttf",25,false);

        // Obtiene el ancho máximo de la ventana y calcula el centro.
        int maxWidth = getEngine().getGraphics().getWidth();
        int center = maxWidth / 2;

        // Calcula dimensiones y posición del botón de partida rápida.
        int buttonW = (int) (maxWidth * 0.8);
        int buttonX = center - buttonW / 2;

        // Título
        addGameObject(new Text(this, "Mastermind", font)
                .setPosition(center, 150)
                .setStrokeColor(new Color(100,100,100))

        );

        // Texto para partida rápida
        Text t= new Text(this, "partida rapida",fonty);
        t.setPosition(center - 40,25);


        // Botón para iniciar una partida rápida
        addGameObject(new GoToChooseLevel(this)
                .setPosition(buttonX,250)
                .setSize(buttonW,50)
                .setStrokeColor(new Color(150,150,150))

                .addChild(t)
        );
    }
}
