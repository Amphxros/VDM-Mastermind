package vdm.mastermind.logic;

import java.util.ArrayList;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.classes.Engine;
import vdm.mastermind.engine.classes.TouchEvent;
import vdm.mastermind.engine.interfaces.IEngine;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IInput;
import vdm.mastermind.engine.interfaces.ILogic;
import vdm.mastermind.engine.interfaces.IScene;

public class Logic implements ILogic {
    IScene currentScene;
    IEngine engine;
    public Logic(IEngine engine){
        super();
        System.out.println("Logic");
        this.engine=engine;
    }
    public void setCurrentScene(IScene scene){
        assert (scene!=null);
        if(currentScene!=null){
            currentScene.release();
        }
        currentScene=scene;
        currentScene.init();
    }

    @Override
    public void render(IGraphics graphics) {
        currentScene.render(graphics);
    }

    @Override
    public void update(float time) {
        currentScene.update(time);
    }

    @Override
    public void handleInput(IInput input) {
        currentScene.handleInput(input);
    }
}