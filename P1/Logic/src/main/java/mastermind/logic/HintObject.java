package mastermind.logic;

import mastermind.engine.IGraphics;
import mastermind.engine.IScene;

public class HintObject extends GameObject{
    public HintObject(IScene scene) {
        super(scene);
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.drawRectangle(getX(),getY(),getWidth(),getHeight());

        super.render(graphics);
    }
}
