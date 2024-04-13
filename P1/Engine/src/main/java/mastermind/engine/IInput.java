package mastermind.engine;

import java.util.List;

public interface IInput {
    /**
     * @return A list of the received {@link TouchEvent}s.
     */
    List<TouchEvent> getTouchEvents(IGraphics graphics);

    /**
     * Useful for scroll
     */

    int getDeltaX();
    int getDeltaY();

    void setDeltaX(int deltaX);
    void setDeltaY(int deltaY);

}
