package mastermind.logic;

import mastermind.engine.IEngine;
import mastermind.engine.IGraphics;
import mastermind.engine.IInput;
import mastermind.engine.ILogic;
import mastermind.engine.IScene;

public class Logic implements ILogic {
    IEngine engine;
    IScene currentScene;
    public Logic(IEngine engine) {
        this.engine=engine;
    }

    @Override
    public void setScene(IScene scene) {
        assert (scene!=null);
        if(this.currentScene!=null)
            this.currentScene.dispose();
        this.currentScene=scene;
        this.currentScene.init();
    }

    @Override
    public void update(double t) {
        currentScene.update(t);
    }

    @Override
    public void render(IGraphics graphics) {
        currentScene.render(graphics);
    }

    @Override
    public void handleEvents(IInput input) {
        currentScene.handleInput(input);
    }

    @Override
    public IEngine getEngine() {
        return this.engine;
    }
}