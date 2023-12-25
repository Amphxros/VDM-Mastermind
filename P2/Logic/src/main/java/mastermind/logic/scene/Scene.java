package mastermind.logic.scene;

import java.util.ArrayList;
import java.util.List;

import mastermind.engine.IEngine;
import mastermind.engine.IGraphics;
import mastermind.engine.IInput;
import mastermind.engine.ILogicData;
import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.GameObject;
import mastermind.logic.PlayerData;
import mastermind.logic.ScrollEventListener;

public abstract class Scene implements IScene {
    private final IEngine engine;
    private final ArrayList<GameObject> objects = new ArrayList<>();

    private final ArrayList<ScrollEventListener> scrollEventListeners= new ArrayList<>();

    public Scene(IEngine engine) {
        this.engine = engine;
    }

    /**
     * @return The {@link IEngine} that instantiated this scene.
     */
    @Override
    public IEngine getEngine() {
        return engine;
    }

    public void addGameObject(GameObject object) {
        objects.add(object);
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    public void addScrollListener(ScrollEventListener scrollEventListener){scrollEventListeners.add(scrollEventListener);}
    /**
     * Signal the scene's {@link GameObject}s to render given a {@link IGraphics} engine.
     *
     * @param graphics The assigned platform-specific {@link IGraphics} engine.
     */
    @Override
    public void render(IGraphics graphics) {
        for (GameObject object : objects) {
            if (object.isEnabled()) object.render(graphics);
        }
    }

    /**
     * Signal the scene's {@link GameObject}s to handle a frame update.
     *
     * @param delta The amount of time elapsed since the previous frame.
     */
    @Override
    public void update(double delta) {
        for (GameObject object : objects) {
            if (object.isEnabled()) object.update(delta);
        }
    }

    /**
     * Signal the scene's {@link GameObject}s to handle {@link mastermind.engine.Input}'s events.
     *
     * @param input The assigned platform-specific {@link mastermind.engine.Input} engine.
     */
    @Override
    public void handleInput(IInput input) {
        List<TouchEvent> events = input.getTouchEvents();
        if (events.isEmpty()) return;

        IGraphics graphics = getEngine().getGraphics();
        for (TouchEvent event : events) {
            event.defineLogicCoordinates(graphics);
            if (!event.isValid()) continue;

            for (GameObject object : objects) {
                if (object.isEnabled()) object.handleInput(event);
            }
        }

        if(input.getDeltaY()>10){
            for(ScrollEventListener scrollEventListener: scrollEventListeners){
                GameObject g= (GameObject) scrollEventListener;
                scrollEventListener.onScroll((int)input.getDeltaY());

            }
        }
    }

    /**
     * An event method called once the scene has been constructed. By default, this will call the
     * awake method on GameObjects.
     */
    @Override
    public void init() {
        for (GameObject object : objects) {
            object.init();
        }
    }

    /**
     * Called when the scene is destroyed.
     */
    @Override
    public void dispose() {
    }

    @Override
    public PlayerData getLogicData() {
        return (PlayerData) getEngine().getLogic().getLogicData();
    }
}
