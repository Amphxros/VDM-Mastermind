package mastermind.logic.button;

import mastermind.logic.scene.IScene;
import mastermind.logic.Scene;
import mastermind.logic.scene.ChooseLevelScene;

/**
 * Clase que representa un botón para cambiar a la escena de selección de nivel.
 */
public class GoToChooseLevel extends GoToScene{

    /**
     * Constructor de la clase GoToChooseLevel.
     *
     * @param scene La escena actual a la que pertenece el botón.
     */
    public GoToChooseLevel(IScene scene) {
        super(scene);
    }

    /**
     * Crea y devuelve la escena de selección de nivel cuando se activa el botón.
     *
     * @return La nueva instancia de la escena de selección de nivel.
     */
    @Override
    protected Scene createScene() {
        return new ChooseLevelScene(getEngine());
    }
}
