package mastermind.logic.button.GoTo;

import mastermind.engine.IGraphics;
import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.button.Button;
import mastermind.logic.scene.Scene;

/**
 * Boton que al pulsarlo vas a una escena a definir por herencia
 */
public abstract class GoToScene extends Button {
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
        graphics.setColor(color);
        graphics.fillRoundRectangle(getX(),getY(),getWidth(),getHeight(),20);
        super.render(graphics);

    }

    protected abstract Scene createScene();
}
