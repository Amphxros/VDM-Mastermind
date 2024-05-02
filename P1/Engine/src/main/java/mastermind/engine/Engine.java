package mastermind.engine;

public abstract class Engine implements IEngine {
    private IGraphics graphics;
    private IAudio audio;
    private IInput input;
    private ILogic logic;

    /**
     * @return An {@link ILogic} instance.
     */
    @Override
    public ILogic getLogic() {
        return logic;
    }

    /**
     *
     * @param logic An {@link ILogic} instance.
     */
    @Override
    public void setLogic(ILogic logic) {
        this.logic = logic;
    }

    /**
     * @return An {@link IGraphics} instance.
     */
    @Override
    public IGraphics getGraphics() {
        return graphics;
    }

    protected void setGraphics(IGraphics graphics) {
        this.graphics = graphics;
    }

    /**
     * @return An {@link Input} instance.
     */
    @Override
    public IInput getInput() {
        return input;
    }

    protected void setInput(IInput input) {
        this.input = input;
    }

    /**
     * @return An {@link IAudio} instance.
     */
    @Override
    public IAudio getAudio() {
        return audio;
    }

    protected void setAudio(IAudio audio) {
        this.audio = audio;
    }
}