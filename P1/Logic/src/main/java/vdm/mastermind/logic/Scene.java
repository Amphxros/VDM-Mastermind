package vdm.mastermind.logic;

import java.lang.reflect.Array;
import java.util.ArrayList;

import vdm.mastermind.engine.interfaces.IEngine;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IInput;
import vdm.mastermind.engine.interfaces.IScene;

public class Scene implements IScene {
    ArrayList<GameObject> gameObjects;
    @Override
    public void render(IGraphics graphics) {
        for(GameObject g : gameObjects){
            g.render(graphics);
        }
    }

    @Override
    public void update(double t) {
        for(GameObject g : gameObjects){
            g.update(t);
        }
    }

    @Override
    public void handleInput(IInput input) {

    }

    @Override
    public void init() {

    }

    @Override
    public void release() {

    }

    @Override
    public IEngine getEngine() {
        return null;
    }
}
