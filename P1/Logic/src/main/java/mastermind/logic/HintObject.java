package mastermind.logic;

import mastermind.engine.IGraphics;
import mastermind.engine.IScene;

public class HintObject extends GameObject{
    public HintObject(IScene scene,int numCell) {
        super(scene);
    }

    @Override
    public void init() {

        super.init();
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.drawRoundRectangle(getX(),getY(),getWidth(),getHeight(),30);

        super.render(graphics);
    }
}
