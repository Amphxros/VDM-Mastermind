package vdm.mastermind.engine.classes;

import java.util.ArrayList;

import vdm.mastermind.engine.interfaces.IInput;

public class Input implements IInput {

    /** ArrayList of {@Link TouchEvent} */
    protected final ArrayList<TouchEvent> events= new ArrayList<>();

    /**
     * Adds a TouchEvent to process
     * @param event {@link TouchEvent} to add
     */
    public synchronized void addEvent(TouchEvent event){
        events.add(event);
    }

    /**
     * @return The events to process in that moment and clears the queue
     */
    @Override
    public synchronized ArrayList<TouchEvent> getTouchEvents() {
        ArrayList<TouchEvent> tmp;

        synchronized (this){
            tmp= new ArrayList<>(events);
            events.clear();
        }

        return tmp;
    }
}
