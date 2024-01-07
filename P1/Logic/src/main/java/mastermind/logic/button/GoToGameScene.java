package mastermind.logic.button;

import mastermind.logic.scene.IScene;
import mastermind.logic.Scene;
import mastermind.logic.scene.GameScene;

/**
 * Clase que representa un botón para cambiar a la escena de juego con parámetros específicos.
 */
public class GoToGameScene extends GoToScene{

    // Parámetros para la creación de la nueva escena de juego
    int numColores;
    int numIntentos;
    int numPassword;
    boolean repeating;

    /**
     * Constructor de la clase GoToGameScene.
     *
     * @param scene      La escena actual a la que pertenece el botón.
     * @param colores    Número de colores en el juego.
     * @param intentos   Número de intentos disponibles.
     * @param tamPassword Tamaño de la contraseña.
     * @param repeating  Booleano que indica si la contraseña puede tener repeticiones.
     */
    public GoToGameScene(IScene scene, int colores, int intentos, int tamPassword, boolean repeating) {
        super(scene);
        this.numColores=colores;
        this.numIntentos=intentos;
        this.numPassword=tamPassword;
        this.repeating=repeating;
    }

    /**
     * Crea y devuelve la escena de juego con los parámetros específicos cuando se activa el botón.
     *
     * @return La nueva instancia de la escena de juego.
     */
    @Override
    protected Scene createScene() {
        return new GameScene(getEngine(),numColores,numIntentos,numPassword,repeating);
    }
}
