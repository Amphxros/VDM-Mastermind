package mastermind.engine;

import java.util.List;

public interface IInput {
    /**
     * @return Lista de eventos que han sucedido
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
