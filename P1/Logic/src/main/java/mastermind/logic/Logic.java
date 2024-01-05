package mastermind.logic;

import mastermind.engine.IEngine;
import mastermind.engine.IGraphics;
import mastermind.engine.IInput;
import mastermind.engine.ILogic;
import mastermind.logic.scene.IScene;
import mastermind.logic.scene.MenuScene;

/**
 * Clase Logic que implementa la interfaz ILogic para gestionar la lógica del juego.
 */
public class Logic implements ILogic {
    IEngine engine; // Referencia al motor del juego.
    IScene currentScene; // Escena actual en ejecución.

    /**
     * Constructor de la clase Logic.
     *
     * @param engine El motor del juego al que está asociada la lógica.
     */
    public Logic(IEngine engine) {
        this.engine=engine;
    }

    /**
     * Establece la escena actual y la inicializa, previamente liberando recursos de la escena anterior si existía.
     *
     * @param scene Nueva escena a establecer.
     */
    public void setScene(IScene scene) {
        assert (scene!=null);

        // Libera los recursos de la escena anterior si existe.
        if(this.currentScene!=null)
            this.currentScene.dispose();

        // Establece la nueva escena y la inicializa.
        this.currentScene=scene;
        this.currentScene.init();
    }

    /**
     * Inicializa la lógica del juego estableciendo la escena inicial (MenuScene).
     */
    @Override
    public void init() {
        setScene(new MenuScene(getEngine()));

    }

    /**
     * Actualiza la escena actual si existe.
     *
     * @param t Tiempo transcurrido desde la última actualización.
     */
    @Override
    public void update(double t) {
        if(currentScene!=null)
        currentScene.update(t);
    }

    /**
     * Renderiza la escena actual si existe.
     *
     * @param graphics El contexto gráfico utilizado para el renderizado.
     */
    @Override
    public void render(IGraphics graphics)
    {
        if(currentScene!=null)
        currentScene.render(graphics);
    }

    /**
     * Maneja los eventos de entrada para la escena actual si existe.
     *
     * @param input El objeto de entrada que contiene la información sobre los eventos de entrada.
     */
    @Override
    public void handleEvents(IInput input) {
        if (currentScene!=null)
        currentScene.handleInput(input);
    }

    /**
     * Obtiene la referencia al motor del juego.
     *
     * @return La referencia al motor del juego.
     */
    @Override
    public IEngine getEngine() {
        return this.engine;
    }
}