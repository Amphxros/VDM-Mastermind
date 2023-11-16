package vdm.mastermind.logic.buttons;

import vdm.mastermind.engine.classes.TouchEvent;
import vdm.mastermind.engine.enums.EventType;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.logic.gameobjects.GameObject;

public abstract class Button extends GameObject {
    public Button(IScene scene) {
        super(scene);
    }

    @Override
    public boolean handleInput(TouchEvent event) {
        int x= event.getPosX();
        int y= event.getPosY();

        int px = getX();
        if (x < px || x > px + getWidth()) return false;


        int py = getY();
        if (y < py || y > py + getHeight()) return false;

        return onAction(event);
    }

    public boolean onAction(TouchEvent event) {
        if (event.getEventType() == EventType.DOWN) return onTouchDown(event);
        if (event.getEventType() == EventType.UP) return onTouchUp(event);
        return false;
    }

    public boolean onTouchDown(TouchEvent event) {
        return false;
    }

    public boolean onTouchUp(TouchEvent event) {
        return false;
    }
}
