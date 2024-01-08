package mastermind.engine;

/**
 * Interfaz que define un gestor para los sensores del telefono
 */
public interface ISensorsManager {

    boolean register();
    boolean unregister();

    void registerAccelerometerListener(ISensorListener listener);
    void unregisterAccelerometerListener(ISensorListener listener);

}
