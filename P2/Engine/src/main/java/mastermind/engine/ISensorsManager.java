package mastermind.engine;

public interface ISensorsManager {

    boolean register();


    boolean unregister();


    void registerAccelerometerListener(ISensorListener listener);
    void unregisterAccelerometerListener(ISensorListener listener);

    void registerTemperatureListener(ISensorListener listener);
    void unregisterTemperatureListener(ISensorListener listener);

    void registerLightListener(ISensorListener listener);
    void unregisterLightListener(ISensorListener listener);
}
