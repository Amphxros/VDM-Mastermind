package mastermind.logic;

import java.util.ArrayList;
import java.util.List;

import mastermind.engine.EventType;
import mastermind.engine.IEngine;
import mastermind.engine.IGraphics;
import mastermind.engine.IInput;
import mastermind.logic.scene.IScene;
import mastermind.engine.TouchEvent;

public abstract class Scene implements IScene {
    private final IEngine engine;
    private final ArrayList<GameObject> objects = new ArrayList<>();
    private final ArrayList<IScrollable> scrollables= new ArrayList<>();

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

    public  void addScrollableObject(IScrollable scrollable){scrollables.add(scrollable);}
    /**
     * Signal the scene's {@link GameObject}s to render given a {@link IGraphics} engine.
     *
     * @param graphics The assigned platform-specific {@link IGraphics} engine.
     */
    @Override
    public void render(IGraphics graphics) {
        for (GameObject object : objects) {
            if (object.isEnabled())
                object.render(graphics);
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
            if (object.isEnabled())
                object.update(delta);
        }
    }

    /**
     * Signal the scene's {@link GameObject}s to handle {@link mastermind.engine.Input}'s events.
     *
     * @param input The assigned platform-specific {@link mastermind.engine.Input} engine.
     */
    @Override
    public void handleInput(IInput input) {
        IGraphics graphics = getEngine().getGraphics();
        List<TouchEvent> events = input.getTouchEvents(graphics);

        if (events.isEmpty()) return;
        for (TouchEvent event : events) {
            if (!event.isValid()) continue;
            for (GameObject object : objects) {
                if (object.isEnabled())
                    object.handleInput(event);
            }
            /*if(event.getType() == EventType.DRAG){
                for(IScrollable scroll : scrollables){
                    scroll.onScroll(0, input.getDeltaY());
                }
            }*/


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

}
