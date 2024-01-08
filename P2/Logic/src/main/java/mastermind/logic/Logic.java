package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IEngine;
import mastermind.engine.IGraphics;
import mastermind.engine.IInput;
import mastermind.engine.ILogic;
import mastermind.engine.ILogicData;
import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.scene.MenuScene;
/**
 * Clase Logic que implementa la interfaz ILogic para gestionar la lógica del juego en diferentes escenas.
 * Representa el core de el juego
 */
public class Logic implements ILogic {
    IEngine engine;
    IScene currentScene;
    PlayerData playerData; // datos del jugador
    public Logic(IEngine engine) {
        this.engine=engine;
        this.playerData= PlayerData.load(this.engine);
        setLogicData(this.playerData);

        engine.getGraphics().setResolution(400,700);
    }

    /**
     * Establece la escena actual y la inicializa, previamente liberando recursos de la escena anterior si existía.
     *
     * @param scene  {@link IScene} a establecer
     */
    @Override
    public void setScene(IScene scene) {
        while(this.playerData==null){
            //espera hasta se haya inicializado los datos de guardado
        }
        assert (scene!=null);
        if(this.currentScene!=null)
            this.currentScene.dispose();
        this.currentScene=scene;
        this.currentScene.init();
    }


    /**
     * Inicializa la lógica del juego estableciendo la escena inicial {@link MenuScene}
     */
    @Override
    public void init() {
       setScene(new MenuScene(getEngine()));
    }

    /**
     * Actualiza la escena actual
     * @param t
     */
    @Override
    public void update(double t) {
        if(currentScene!=null) {
            currentScene.update(t);
        }
    }

    /**
     * Limpiamos lo anterior con el fondo de la paleta actual
     *  y renderizamos la escena actual
     * @param graphics Instancia de {@link IGraphics} del motor actual
     */
    @Override
    public void render(IGraphics graphics)
    {
        graphics.clear(playerData.getBackground().getARGB());
        if(currentScene!=null){
            currentScene.render(graphics);
        }
    }

    /**
     * Maneja los eventos de entrada para la escena actual si existe.
     *
     * @param input instancia de {@link IInput} que se encarga de la gestion del input
     */
    @Override
    public void handleEvents(IInput input) {
        if (currentScene!=null)
            currentScene.handleInput(input);


    }

    /**
     * Comunicacion de la app con la logica al entrar a la app mediante una notificacion
     */
    @Override
    public void onNotificationClicked() {
        getLogicData().setCoins(getLogicData().getCoins() + 100);
    }

    /**
     * AL salir de la app guardamos el progreso
     */
    @Override
    public void onApplicationExit() {
        try {
            getLogicData().saveData();
            currentScene.dispose();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @return el motor en el que corre este juego
     */
    @Override
    public IEngine getEngine() {
        return this.engine;
    }

    /**
     *
     * @return Los datos del jugador
     */
    @Override
    public PlayerData getLogicData() {
        return this.playerData;
    }

    /**
     * Settea los datos del jugador
     * @param logicData
     */
    @Override
    public void setLogicData(ILogicData logicData) {
        this.playerData= (PlayerData) logicData;
    }

}