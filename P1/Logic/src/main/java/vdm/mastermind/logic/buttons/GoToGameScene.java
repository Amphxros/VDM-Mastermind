package vdm.mastermind.logic.buttons;

import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.logic.scenes.GameScene;
import vdm.mastermind.logic.scenes.Scene;

public class GoToGameScene extends GoToSceneButton{
    public GoToGameScene(IScene scene) {
        super(scene);
    }

    @Override
    protected Scene createScene() {
        return new GameScene(getEngine());
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.fillRoundRectangle(getX(),getY(),getWidth(),getHeight(),50);
    }
}
