package vdm.mastermind.logic.buttons;

import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.logic.scenes.GameScene;
import vdm.mastermind.logic.scenes.Scene;

public class GoToGameScene extends GoToSceneButton{
    int colors;
    int intentos;
    int tamPassword;
    public GoToGameScene(IScene scene, int colors, int intentos, int tamPassword ) {
        super(scene);
        this.colors=colors;
        this.intentos=intentos;
        this.tamPassword=tamPassword;

    }

    @Override
    protected Scene createScene() {
        return new GameScene(getEngine(),colors,intentos,tamPassword);
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.fillRoundRectangle(getX(),getY(), getWidth(), getHeight(),50);
    }
}
