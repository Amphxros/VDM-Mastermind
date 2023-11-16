package vdm.mastermind.logic.buttons;

import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.logic.scenes.Scene;
import vdm.mastermind.logic.scenes.WorldsScene;

public class GoToWorldsScene extends GoToSceneButton{
    public GoToWorldsScene(IScene scene) {
        super(scene);
    }

    @Override
    protected Scene createScene() {
        return new WorldsScene(getEngine());
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.fillRoundRectangle(getX(),getY(), getWidth(),getHeight(),50);
        super.render(graphics);
    }
}
