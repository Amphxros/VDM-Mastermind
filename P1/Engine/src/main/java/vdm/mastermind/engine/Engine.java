package vdm.mastermind.engine;

import vdm.mastermind.engine.interfaces.*;
public class Engine implements IEngine {
    protected IGraphics graphics;
    protected IAudio audio;
    protected  IInput input;
    protected  ILogic logic;

    @Override
    public IGraphics getGraphics() {
        assert (this.graphics!=null);
        return this.graphics;
    }

    @Override
    public void setGraphics(IGraphics graphics) {
        this.graphics=graphics;
    }

    @Override
    public IAudio getAudio() {
        assert (this.audio!=null);
        return this.audio;
    }

    @Override
    public void setAudio(IAudio audio) {
        this.audio=audio;
    }

    @Override
    public IInput getInput() {
        assert (this.input!=null);
        return input;
    }

    @Override
    public void setInput(IInput input) {
        this.input=input;
    }

    @Override
    public ILogic getLogic() {
        assert (this.logic!=null);
        return this.logic;
    }

    @Override
    public void setLogic(ILogic logic) {
        this.logic=logic;
    }
}