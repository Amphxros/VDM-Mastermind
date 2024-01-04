package mastermind.logic;

import mastermind.engine.IGraphics;
import mastermind.logic.scene.IScene;

/**
 * Objeto vacio, su funcionalidad es contener otros objetos
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
