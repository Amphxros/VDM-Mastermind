package mastermind.engine;

public interface IEngine {
    /**
     * @return An {@link IGraphics} instance.
     */
    IGraphics getGraphics();

    /**
     * Sets the {@link IGraphics} engine.
     *
     * @param graphics An {@link IGraphics} instance.
     */
    void setGraphics(IGraphics graphics);

    /**
     * @return An {@link Input} instance.
     */
    Input getInput();

    /**
     * Sets the {@link Input} engine.
     *
     * @param input An {@link Input} instance.
     */
    void setInput(Input input);

    /**
     * @return An {@link ILogic} instance.
     */
    ILogic getLogic();

    /**
     * Sets the {@link ILogic} engine.
     *
     * @param logic An {@link ILogic} instance.
     */
    void setLogic(ILogic logic);

    /**
     * @return An {@link IAudio} instance.
     */
    IAudio getAudio();

    /**
     * Sets the {@link IAudio} engine.
     *
     * @param audio An {@link IAudio} instance.
     */
    void setAudio(IAudio audio);

    /**
     *
     * @return The {@link ISensorsManager} instance.
     */
    ISensorsManager getSensorsManager();

    /**
     *
     * @param sensorsManager
     */
    void setSensorsManager(ISensorsManager sensorsManager);

    /**
     *
     * @return
     */
    IAdsManager getAdsManager();

    void setAdsManager(IAdsManager adsManager);

    IFileManager getFileManager();
    
    void setFileManager(IFileManager fileManager);
    
    INotificationHandler getNotificationHandler();
    
    void setNotificationHandler(INotificationHandler notificationHandler);

    IShareContentManager getShareContentManager();

    void setShareContentManager(IShareContentManager shareContentManager);


}
