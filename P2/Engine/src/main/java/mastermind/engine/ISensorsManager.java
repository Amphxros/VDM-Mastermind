package mastermind.engine;

public interface ISensorsManager {

    boolean register();


    boolean unregister();


    void registerAccelerometerListener(ISensorListener listener);
    void unregisterAccelerometerListener(ISensorListener listener);

}
