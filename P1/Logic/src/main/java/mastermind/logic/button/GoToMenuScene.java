package mastermind.logic.button;

import mastermind.logic.scene.IScene;
import mastermind.logic.Scene;
import mastermind.logic.scene.MenuScene;

/**
 * Clase que representa un botón para cambiar a la escena de menú principal.
 */
public class GoToMenuScene extends GoToScene{

    /**
     * Constructor de la clase GoToMenuScene.
     *
     * @param scene La escena actual a la que pertenece el botón.
     */
    public GoToMenuScene(IScene scene) {
        super(scene);
    }

    /**
     * Crea y devuelve la escena del menú principal cuando se activa el botón.
     *
     * @return La nueva instancia de la escena del menú principal.
     */
    @Override
    protected Scene createScene() {
        return new MenuScene(getEngine());
    }
}
