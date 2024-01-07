package mastermind.logic.button;

import mastermind.engine.EventType;
import mastermind.logic.scene.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.GameObject;

/**
 * Clase que representa un objeto interactivo que reacciona a eventos de pulsación dentro de su rectángulo.
 */
public class Button extends GameObject {

    /**
     * Constructor de la clase Button.
     *
     * @param scene La escena a la que pertenece el botón.
     */
    public Button(IScene scene) {
        super(scene);
    }

    /**
     * Maneja la entrada táctil y activa la acción correspondiente si la pulsación ocurre dentro del rectángulo del botón.
     *
     * @param event El evento táctil.
     * @return Verdadero si la pulsación está dentro del rectángulo y se activa una acción, de lo contrario, falso.
     */
    public boolean handleInput(TouchEvent event) {
        if (event.getType() != EventType.DOWN && event.getType() != EventType.UP) {
            return false;
        }

        int x = event.getX();
        int px = getPosition().getX();
        if (x < px || x > px + getWidth()) return false;

        int y = event.getY();
        int py = getPosition().getY();
        if (y < py || y > py + getHeight()) return false;

        return onAction(event);
    }

    /**
     * Activa la acción correspondiente al tipo de evento táctil.
     *
     * @param event El evento táctil.
     * @return Verdadero si se activa una acción, de lo contrario, falso.
     */
    public boolean onAction(TouchEvent event) {
        if (event.getType() == EventType.DOWN) return onTouchDown(event);
        if (event.getType() == EventType.UP) return onTouchUp(event);
        return false;
    }

    /**
     * Maneja la acción cuando se presiona sobre el botón.
     *
     * @param event El evento táctil de presión.
     * @return Verdadero si se maneja la acción, de lo contrario, falso.
     */
    public boolean onTouchDown(TouchEvent event) {
        return false;
    }

    /**
     * Maneja la acción cuando se levanta el dedo del botón.
     *
     * @param event El evento táctil de liberación.
     * @return Verdadero si se maneja la acción, de lo contrario, falso.
     */
    public boolean onTouchUp(TouchEvent event) {
        return false;
    }
}
