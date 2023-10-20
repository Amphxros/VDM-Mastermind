package vdm.mastermind.engine.classes;

import java.util.ArrayList;

import vdm.mastermind.engine.interfaces.IInput;

public class Input implements IInput {
    protected ArrayList<TouchEvent> events;

    /**
     * Adds a TouchEvent to process
     * @param event {@link TouchEvent} to add
     */
    public synchronized void addEvent(TouchEvent event){
        events.add(event);
    }

    /**
     * @return The events to process in that moment
     */
    @Override
    public synchronized ArrayList<TouchEvent> getTouchEvents() {
        

        return events;
    }
}
