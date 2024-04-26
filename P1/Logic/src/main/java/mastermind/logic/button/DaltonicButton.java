package mastermind.logic.button;

import mastermind.engine.IGraphics;
import mastermind.engine.IImage;
import mastermind.engine.ISound;
import mastermind.logic.scene.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.scene.GameScene;

/**
 * Clase final que representa un botón para habilitar/deshabilitar el modo daltónico.
 */
public final class DaltonicButton extends Button{
    IImage open, close; // Imágenes para el botón en los estados de habilitado y deshabilitado
    boolean daltonic= false; // Estado actual del modo daltónico

    ISound mySound;

    /**
     * Constructor de la clase DaltonicButton.
     *
     * @param scene   La escena a la que pertenece el botón.
     * @param enable  Imagen del botón cuando el modo daltónico está habilitado.
     * @param disable Imagen del botón cuando el modo daltónico está deshabilitado.
     */
    public DaltonicButton(IScene scene, IImage enable, IImage disable, ISound sound) {
        super(scene);
        this.open=enable;
        this.close=disable;
        this.mySound = sound;
    }

    /**
     * Renderiza el botón según el estado actual del modo daltónico.
     *
     * @param graphics El motor gráfico {@link IGraphics} específico asignado.
     */
    @Override
    public void render(IGraphics graphics) {
        if(daltonic){
            graphics.drawImage(open, getX(), getY(), getWidth(), getHeight());
        }
        else{
            graphics.drawImage(close, getX(), getY(), getWidth(), getHeight());
        }

        super.render(graphics);
    }

    /**
     * Maneja la acción cuando se presiona sobre el botón, cambiando el estado del modo daltónico.
     *
     * @param event El evento táctil de presión.
     * @return Verdadero si se maneja la acción, de lo contrario, falso.
     */
    @Override
    public boolean onTouchDown(TouchEvent event) {
        daltonic=!daltonic;
        GameScene scene= (GameScene) getScene();
        if(scene!=null){
            scene.onDaltonicMode(daltonic);
            mySound.play();
        }

        return daltonic;
    }
}
