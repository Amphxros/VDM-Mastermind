package mastermind.logic.button;

import mastermind.engine.EventType;
import mastermind.logic.scene.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.GameObject;

public class Button extends GameObject {
    public Button(IScene scene) {
        super(scene);
    }

    public boolean handleInput(TouchEvent event) {
        if (event.getType() != EventType.DOWN && event.getType() != EventType.UP) {
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
        return false;
    }

    public boolean onTouchDown(TouchEvent event) {
        return false;
    }

    public boolean onTouchUp(TouchEvent event) {
        return false;
    }
}
