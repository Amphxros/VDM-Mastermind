package mastermind.engine;

import java.util.LinkedList;
import java.util.List;

public class Input implements IInput{
    protected final LinkedList<TouchEvent> events = new LinkedList<>(); // tambien puede ser una cola
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

    protected synchronized void addEvent(TouchEvent event) {
        events.add(event);
    }
}
