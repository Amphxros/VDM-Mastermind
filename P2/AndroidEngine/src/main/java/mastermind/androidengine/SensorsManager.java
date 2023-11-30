package mastermind.androidengine;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.widget.Toast;

import java.util.ArrayList;

import mastermind.engine.ISensorListener;
import mastermind.engine.ISensorsManager;
import mastermind.engine.SensorType;

public class SensorsManager implements ISensorsManager, SensorEventListener {
    SensorManager androidSensorManager;
    Sensor sensor;

    ArrayList<ISensorListener> sensorAccelListeners;

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
                for(ISensorListener listener: sensorAccelListeners){
                    listener.onSense();
                }
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
