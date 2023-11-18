package mastermind.logic;

import mastermind.engine.IGraphics;
import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.button.Button;

public class Cell extends Button {
    public Cell(IScene scene) {
        super(scene);
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.fillCircle(getX(),getY(),getWidth()/2);
        super.render(graphics);
    }

    @Override
    public boolean onTouchDown(TouchEvent event) {
        return super.onTouchDown(event);
    }
}
