package mastermind.logic;

import mastermind.engine.IGraphics;
import mastermind.logic.scene.IScene;

/**
 * Empty game object
 */
public class Container extends GameObject{
    public Container(IScene scene) {
        super(scene);
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.drawRectangle(getX(),getY(),getWidth(),getHeight());
        super.render(graphics);
    }
}
