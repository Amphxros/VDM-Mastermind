package mastermind.engine;

import java.util.LinkedList;
import java.util.List;

public class Input implements IInput{
    protected final LinkedList<TouchEvent> events = new LinkedList<>();
    protected int lastX, lastY;
    protected int deltaX, deltaY;
    /**
     * @return Returns a list of all the queued events, clears the queue upon call.
     */
    @Override
    public synchronized LinkedList<TouchEvent> getTouchEvents() {
        LinkedList<TouchEvent> tmp;

        synchronized (this) {
            // Copy the queued events into a list:
            tmp = new LinkedList<>(events);
            events.clear();
        }

        return tmp;
    }

    @Override
    public int getDeltaX() {
        return deltaX;
    }

    @Override
    public int getDeltaY() {
        return deltaY;
    }

    @Override
    public void setDeltaX(int deltaX) {
        this.deltaX=deltaX;
    }

    @Override
    public void setDeltaY(int deltaY) {
        this.deltaY=deltaY;
    }


    protected synchronized void addEvent(TouchEvent event) {
        events.add(event);
    }
}
