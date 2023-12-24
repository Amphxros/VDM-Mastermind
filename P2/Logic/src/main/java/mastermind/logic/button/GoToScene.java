package mastermind.logic.button;

import mastermind.engine.IGraphics;
import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.scene.Scene;

public abstract class GoToScene extends Button{
    public GoToScene(IScene scene) {
        super(scene);
    }

    @Override
    public boolean onTouchUp(TouchEvent event) {
        getEngine().getLogic().setScene(createScene());
        return true;
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.fillRoundRectangle(getX(),getY(),getWidth(),getHeight(),20);
        super.render(graphics);

    }

    protected abstract Scene createScene();
}
