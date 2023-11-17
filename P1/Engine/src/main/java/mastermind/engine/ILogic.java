package mastermind.engine;

public interface ILogic {
    void setScene(IScene scene);

    void update(double t);

    void render(IGraphics graphics);

    void handleEvents(IInput input);

    IEngine getEngine();
}
