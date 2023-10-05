package vdm.mastermind.engine.interfaces;

public interface IEngine {
    /**
     * @return An instance of {@Link IGraphics}
     */
    IGraphics getGraphics();
    void setGraphics(IGraphics graphics);

    /**
     * @return An instance of {@Link IAudio }
     */
    IAudio getAudio();
    void setAudio(IAudio audio);

    /**
      * @return An instance of {@Link IInput }
     */
    IInput getInput();
    void setInput(IInput input);

    /**
     * @return An instance of {@Link ILogic}
     */
    ILogic getLogic();
    void setLogic(ILogic logic);
}
