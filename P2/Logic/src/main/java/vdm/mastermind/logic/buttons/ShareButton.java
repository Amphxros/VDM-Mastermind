package vdm.mastermind.logic.buttons;

import vdm.mastermind.engine.classes.TouchEvent;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;

public class ShareButton extends Button{
    public ShareButton(IScene scene) {
        super(scene);
    }

    @Override
    public boolean onTouchUp(TouchEvent event) {
        //TODO share stuff
        return super.onTouchUp(event);
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.fillRoundRectangle(getX(), getY(),getWidth(), getHeight() ,50);
        super.render(graphics);
    }
}
