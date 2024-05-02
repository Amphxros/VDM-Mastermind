package mastermind.engine;

public interface IEngine {
    /**
     * @return An {@link IGraphics} instance.
     */
    IGraphics getGraphics();


    /**
     * @return An {@link Input} instance.
     */
    IInput getInput();


    /**
     * @return An {@link ILogic} instance.
     */
    ILogic getLogic();

    /**
     * Sets the {@link ILogic} engine.
     *
     * @param logic An {@link ILogic} instance.
     */
    void setLogic(ILogic logic);

    /**
     * @return An {@link IAudio} instance.
     */
    IAudio getAudio();

}
