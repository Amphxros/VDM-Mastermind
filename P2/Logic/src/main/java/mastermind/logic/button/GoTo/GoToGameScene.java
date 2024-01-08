package mastermind.logic.button.GoTo;

import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.Image;
import mastermind.logic.scene.Scene;
import mastermind.logic.scene.GameScene;

public class GoToGameScene extends GoToScene{

    int numColores;
    int numIntentos;
    int numPassword;

    int numWorld;
    int numLevel;

    boolean repeating;
    boolean colors;
    boolean fileScene;
    boolean locked;
    Image image;
    public GoToGameScene(IScene scene, int colores, int intentos, int tamPassword, boolean repeating,boolean colors, boolean fileScene, boolean locked,int numWorld, int numLevel) {
        super(scene);
        this.numColores=colores;
        this.numIntentos=intentos;
        this.numPassword=tamPassword;
        this.numLevel=numLevel;
        this.numWorld=numWorld;
        this.repeating=repeating;
        this.colors=colors;
        this.fileScene=fileScene;
        this.locked=locked;

    }

    @Override
    public boolean handleInput(TouchEvent event) {
        if(!locked) {
            return super.handleInput(event);
        }
        return false;
    }

    @Override
    protected Scene createScene() {
             return new GameScene(getEngine(),numColores,numIntentos,numPassword,repeating,colors,fileScene,numWorld,numLevel);
    }
}
