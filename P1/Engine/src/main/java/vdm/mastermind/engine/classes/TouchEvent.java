
package vdm.mastermind.engine.classes;

import vdm.mastermind.engine.enums.EventType;
import vdm.mastermind.engine.interfaces.IGraphics;

public class TouchEvent {
    private final int posX, posY;
    private int X, Y;

    private final EventType eventType;
    public TouchEvent(int x, int y, EventType eventType){
        this.posX=x;
        this.posY=y;
        this.eventType=eventType;
        this.X=-1;
        this.Y=-1;
    }

    /**
     * @return The horizontal position where the TouchEvent happened
     */
    public int getPosX() {
        return X;
    }

    /**
     * @return The vertical position where the TouchEvent happened
     */
    public int getPosY(){
        return Y;
    }

    /**
     * @return The type of event (down, up)
     */
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

    public void defineLogicCoordinates(IGraphics graphics) {
        X = graphics.getLogicPointX(posX);
        Y = graphics.getLogicPointY(posY);
    }
}
