package mastermind.engine;

public interface ILogic {
    void setScene(IScene scene);

    void init();

    void update(double t);

    void render(IGraphics graphics);

    void handleEvents(IInput input);

    void onNotificationClicked();

    IEngine getEngine();
}
