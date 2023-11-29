package mastermind.engine;

import java.util.ArrayList;

public interface ISensorListener {

    SensorType getSensorType();
    void onSense();
    void registerSensorsTypes();
}
