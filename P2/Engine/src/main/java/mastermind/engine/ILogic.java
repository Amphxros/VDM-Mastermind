package mastermind.engine;

public interface ILogic {
    void setScene(IScene scene);

    void init();

    void update(double t);

    void render(IGraphics graphics);

    void handleEvents(IInput input);

    void onNotificationClicked();

    void onApplicationExit();

    IEngine getEngine();

    ILogicData getLogicData();
    void setLogicData(ILogicData logicData);
}
