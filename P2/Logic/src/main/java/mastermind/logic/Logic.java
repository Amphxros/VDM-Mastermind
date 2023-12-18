package mastermind.logic;

import mastermind.engine.IEngine;
import mastermind.engine.IGraphics;
import mastermind.engine.IInput;
import mastermind.engine.ILogic;
import mastermind.engine.IScene;
import mastermind.logic.scene.MenuScene;

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
    public void init() {
        setScene(new MenuScene(getEngine()));

    }

    @Override
    public void update(double t) {
        if(currentScene!=null)
            currentScene.update(t);
    }

    @Override
    public void render(IGraphics graphics)
    {
        if(currentScene!=null)
            currentScene.render(graphics);
    }

    @Override
    public void handleEvents(IInput input) {
        if (currentScene!=null)
            currentScene.handleInput(input);
    }

    @Override
    public void onNotificationClicked() {

    }

    @Override
    public IEngine getEngine() {
        return this.engine;
    }
}