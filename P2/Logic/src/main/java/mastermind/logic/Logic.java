package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IEngine;
import mastermind.engine.IGraphics;
import mastermind.engine.IInput;
import mastermind.engine.ILogic;
import mastermind.engine.ILogicData;
import mastermind.engine.IScene;
import mastermind.logic.scene.MenuScene;

public class Logic implements ILogic {
    IEngine engine;
    IScene currentScene;
    PlayerData playerData;
    public Logic(IEngine engine) {
        this.engine=engine;
        this.playerData= new PlayerData(engine);
        setLogicData(this.playerData);
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
        if(currentScene!=null) {
            currentScene.update(t);
        }
    }

    @Override
    public void render(IGraphics graphics)
    {
        graphics.clear(playerData.getBackground().getARGB());
        if(currentScene!=null){
            currentScene.render(graphics);
        }
    }

    @Override
    public void handleEvents(IInput input) {
        if (currentScene!=null){
            currentScene.handleInput(input);
        }
    }

    @Override
    public IEngine getEngine() {
        return this.engine;
    }

    @Override
    public PlayerData getLogicData() {
        return this.playerData;
    }

    @Override
    public void setLogicData(ILogicData logicData) {

    }

}