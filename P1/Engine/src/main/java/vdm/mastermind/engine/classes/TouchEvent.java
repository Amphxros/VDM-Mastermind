package vdm.mastermind.engine.classes;

import vdm.mastermind.engine.enums.EventType;

public class TouchEvent {
    private final int posX, posY;
    private final EventType eventType;
    TouchEvent(int x, int y, EventType eventType){
        this.posX=x;
        this.posY=y;
        this.eventType=eventType;
    }

    public int getPosX() {
        return posX;
    }
    public int getPosY(){
        return posY;
    }
    public EventType getEventType(){
        return eventType;
    }

    /**
     * Checks whether or not the event happened at a valid set of coordinates.
     *
     * @return Whether or not the event happened at a valid set of coordinates.
     */
    public boolean isValid() {
        return posX != -1 && posY != -1;
    }

}
