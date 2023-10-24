package vdm.mastermind.engine.classes;

import vdm.mastermind.engine.interfaces.*;
public class Engine implements IEngine {
    protected IGraphics graphics;
    protected IAudio audio;
    protected IInput input;
    protected ILogic logic;

    /**
     * @return An instance of {@link IGraphics}
     */
    @Override
    public IGraphics getGraphics() {
        assert (this.graphics!=null);
        return this.graphics;
    }

    @Override
    public void setGraphics(IGraphics graphics) {
        this.graphics=graphics;
    }

    /**
     * @return An instance of {@link IAudio}
     */
    @Override
    public IAudio getAudio() {
        assert (this.audio!=null);
        return this.audio;
    }

    @Override
    public void setAudio(IAudio audio)
    {
        this.audio=audio;
    }

    /**
     * @return An instance of {@link IInput}
     */
    @Override
    public IInput getInput() {
        assert (this.input!=null);
        return input;
    }

    @Override
    public void setInput(IInput input) {
        this.input=input;
    }

    /**
     * @return An instance of {@link ILogic}
     */
    @Override
    public ILogic getLogic() {
        assert (this.logic!=null);
        return this.logic;
    }

    @Override
    public void setLogic(ILogic logic) {
        this.logic=logic;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}