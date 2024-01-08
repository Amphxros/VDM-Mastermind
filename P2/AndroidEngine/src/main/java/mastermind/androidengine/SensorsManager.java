package mastermind.androidengine;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

import java.util.ArrayList;

import mastermind.engine.ISensorListener;
import mastermind.engine.ISensorsManager;

public class SensorsManager implements ISensorsManager, SensorEventListener {
    SensorManager androidSensorManager;
    Sensor sensor;

    /**
     * Accelerometer data
     */
    ArrayList<ISensorListener> sensorAccelListeners; //escuchantes del accelerometro
    /**
     * The acceleration in m/s² for an acceleration to be considered a shake.
     */
    private static final float SHAKE_THRESHOLD = 3.25f; // Threshold de agitado
    /**
     * The minimum amount of time between shakes, in milliseconds.
     */
    private static final long SHAKE_PERIOD = 1000; //cooldown
    private long lastShakeTime = System.currentTimeMillis();


    public SensorsManager(Context context){
        super();
        this.androidSensorManager=(SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        this.sensorAccelListeners= new ArrayList<>();
        if(this.androidSensorManager==null){
            System.out.println("Sensor service not found");
            Toast.makeText(context, "Sensor not found", Toast.LENGTH_SHORT).show();
        }
        else{
            register();
        }
    }

    /**
     * Registra los sensores a escuchar
     * @return
     */
    @Override
    public boolean register() {
        if (androidSensorManager == null) {
            System.err.println("Could not detect a sensor service to register into");
            return false;
        }
        /**
         * Register accelerometer
         */
        sensor = androidSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor == null) {
            System.err.println("Could not get the accelerometer sensor");
            return false;
        }
        androidSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        //otros sensores aqui
        return true;
    }

    @Override
    public boolean unregister() {
        return false;
    }

    @Override
    public void registerAccelerometerListener(ISensorListener listener) {
        assert (androidSensorManager!=null);
        sensorAccelListeners.add(listener);
    }

    @Override
    public void unregisterAccelerometerListener(ISensorListener listener) {
        sensorAccelListeners.remove(listener);
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastShakeTime < SHAKE_PERIOD) return;

                //get the axis
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                double acceleration = Math.sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH;
                if (acceleration > SHAKE_THRESHOLD) {
                    System.out.println("Me agito");
                    for(ISensorListener listener: sensorAccelListeners){
                        listener.onSense();
                    }
                }
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
