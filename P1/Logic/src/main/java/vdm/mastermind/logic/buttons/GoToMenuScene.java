package vdm.mastermind.logic.buttons;

import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.engine.interfaces.objects.IImage;
import vdm.mastermind.logic.scenes.MenuScene;
import vdm.mastermind.logic.scenes.Scene;

public class GoToMenuScene extends GoToSceneButton{
    IImage image;
    public GoToMenuScene(IScene scene, IImage image) {
        super(scene);
        this.image=image;
    }

    @Override
    protected Scene createScene() {
        return new MenuScene(getEngine());
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.drawRectangle(getX(),getY(), getWidth(), getHeight());
    }
}
