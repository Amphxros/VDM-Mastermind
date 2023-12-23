package mastermind.logic.button;

import mastermind.engine.IGraphics;
import mastermind.engine.IImage;
import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.Image;
import mastermind.logic.Scene;
import mastermind.logic.scene.GameScene;

public class GoToGameScene extends GoToScene{

    int numColores;
    int numIntentos;
    int numPassword;
    boolean repeating;
    boolean colors;
    boolean fileScene;
    boolean locked;
    Image image;
    public GoToGameScene(IScene scene, int colores, int intentos, int tamPassword, boolean repeating,boolean colors, boolean fileScene, boolean locked) {
        super(scene);
        this.numColores=colores;
        this.numIntentos=intentos;
        this.numPassword=tamPassword;
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
             return new GameScene(getEngine(),numColores,numIntentos,numPassword,repeating,colors,fileScene);
    }
}
