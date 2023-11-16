package vdm.mastermind.logic.buttons;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.logic.scenes.ChooseLevelScene;
import vdm.mastermind.logic.scenes.Scene;

public class GoToChooseLevelScene extends GoToSceneButton{
    public GoToChooseLevelScene(IScene scene) {
        super(scene);
    }

    @Override
    protected Scene createScene() {
        return new ChooseLevelScene(getEngine());

    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.fillRoundRectangle(getX(),getY(), getWidth(), getHeight(), 50);
    }
}
