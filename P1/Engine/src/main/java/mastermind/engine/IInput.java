package mastermind.engine;

import java.util.List;

public interface IInput {
    /**
     * @return A list of the received {@link TouchEvent}s.
     */
    List<TouchEvent> getTouchEvents();
    boolean isScrolling();
}
