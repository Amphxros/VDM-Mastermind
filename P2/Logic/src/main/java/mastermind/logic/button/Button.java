package mastermind.logic.button;

import mastermind.engine.EventType;
import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.GameObject;
import mastermind.logic.Scene;

public abstract class Button extends GameObject {
    public Button(IScene scene) {
        super(scene);
    }

    public boolean handleInput(TouchEvent event) {
        if (event.getType() != EventType.DOWN && event.getType() != EventType.UP && event.getType() != EventType.MOVE) {
            return false;
        }

        int x = event.getX();
        int px = getPosition().getX();
        if (x < px || x > px + getWidth()) return false;

        int y = event.getY();
        int py = getPosition().getY();
        if (y < py || y > py + getHeight()) return false;

        return onAction(event);
    }

    public boolean onAction(TouchEvent event) {
        if (event.getType() == EventType.DOWN) return onTouchDown(event);
        if (event.getType() == EventType.UP) return onTouchUp(event);
        if(event.getType()==EventType.MOVE) return onScroll(event);
        return false;
    }

    public boolean onTouchDown(TouchEvent event) {
        return false;
    }

    public boolean onTouchUp(TouchEvent event) {
        return false;
    }

    public boolean onScroll(TouchEvent event){
        return false;
    }
}
