package vdm.mastermind.logic.scenes;

import java.util.ArrayList;
import java.util.List;

import vdm.mastermind.engine.classes.TouchEvent;
import vdm.mastermind.engine.interfaces.IEngine;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IInput;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.logic.buttons.GameObject;

public class Scene implements IScene {
    ArrayList<GameObject> gameObjects;
    private final IEngine engine;
    public Scene(IEngine engine){
        this.engine=engine;
        gameObjects= new ArrayList<>();
    }
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
        List<TouchEvent> events = input.getTouchEvents();
        if (events.isEmpty()) return;

        IGraphics graphics = getEngine().getGraphics();
        for (TouchEvent event : events) {
            event.defineLogicCoordinates(graphics);
            if (!event.isValid()) continue;

            for (GameObject object : gameObjects) {
                if (object.isEnabled())
                    object.handleInput(event);
            }
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void release() {

    }

    @Override
    public IEngine getEngine() {
        return engine;
    }

    public void addGameObject(GameObject g){
        this.gameObjects.add(g);
    }
}
