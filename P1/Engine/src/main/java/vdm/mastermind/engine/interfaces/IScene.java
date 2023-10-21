package vdm.mastermind.engine.interfaces;

public interface IScene {

    /**
     * Signal the scene's objects to render given a {@link IGraphics} engine.
     *
     * @param graphics The assigned platform-specific {@link IGraphics} engine.
     */
    void render(IGraphics graphics);

    /**
     * Signal the scene's objects to handle a frame update.
     *
     * @param t The amount of time elapsed since the previous frame.
     */
    void update(double t);

    /**
     * Signal the scene's objects to handle {@link Input}'s events.
     *
     * @param input The assigned platform-specific {@link Input} engine.
     */
    void handleInput(IInput input);

    /**
     * An event method called once the scene has been constructed. By default, this will call the
     * init method on GameObjects.
     */
    void init();

    /**
     * Called when the scene is destroyed.
     */
    void release();

    /**
     * @return The {@link IEngine} that instantiated this scene.
     */
    IEngine getEngine();
}
