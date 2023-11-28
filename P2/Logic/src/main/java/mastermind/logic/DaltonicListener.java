package mastermind.logic;

import mastermind.engine.IGraphics;

public interface DaltonicListener {

    /**
     * set the daltonic mode
     * @param mode value of the mode
     */
    void setDaltonicMode(boolean mode);

    /**
     * Draws the extra info to be accessible
     */
    void drawDaltonicInfo(IGraphics graphics);
}
