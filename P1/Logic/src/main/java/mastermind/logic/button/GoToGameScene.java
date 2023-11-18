package mastermind.logic.button;

import mastermind.engine.IScene;
import mastermind.logic.Scene;
import mastermind.logic.scene.GameScene;

public class GoToGameScene extends GoToScene{

    int numColores;
    int numIntentos;
    int numPassword;

    public GoToGameScene(IScene scene, int colores, int intentos, int tamPassword) {
        super(scene);
        this.numColores=colores;
        this.numIntentos=intentos;
        this.numPassword=tamPassword;
    }

    @Override
    protected Scene createScene() {
        return new GameScene(getEngine());
    }
}
