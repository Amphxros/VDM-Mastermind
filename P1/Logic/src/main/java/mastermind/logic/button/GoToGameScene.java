package mastermind.logic.button;

import mastermind.engine.IScene;
import mastermind.logic.Scene;
import mastermind.logic.scene.GameScene;

public class GoToGameScene extends GoToScene{

    int numColores;
    int numIntentos;
    int numPassword;
    boolean repeating;

    public GoToGameScene(IScene scene, int colores, int intentos, int tamPassword, boolean repeating) {
        super(scene);
        this.numColores=colores;
        this.numIntentos=intentos;
        this.numPassword=tamPassword;
        this.repeating=repeating;
    }

    @Override
    protected Scene createScene() {
        return new GameScene(getEngine(),numColores,numIntentos,numPassword,repeating);
    }
}
