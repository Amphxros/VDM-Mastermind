
package vdm.mastermind.engine.classes;

import vdm.mastermind.engine.enums.EventType;
import vdm.mastermind.engine.interfaces.IGraphics;

public class TouchEvent {
    private final int posX, posY; //screen position
    private int X, Y; //logic position
    private final EventType eventType; //Type of event
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

    /**
     * Transforms the screen position in the logic position
     * @param graphics {@link IGraphics} that we use
     * but we use the {@link GraphicsTransformer} defined on it
     */
    public void defineLogicCoordinates(IGraphics graphics) {
        X = graphics.getLogicPointX(posX);
        Y = graphics.getLogicPointY(posY);
    }
}
