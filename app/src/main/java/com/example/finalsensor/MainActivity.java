package com.example.finalsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    public Sensor sensor;
    public SensorManager sensorManager;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*********** Steps **********
         * Step 1 : Get Sensor Manager(Sensor Services)
         * Step 2 : Get required sensor using sensor manager
         * Step 3: Implement sensor event(onSensorChanged) Listener
         * Step 4: Call or register/unregister sensor event in onResume/OnPause callbacks
         * */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.TV);
        //To get the sensor service
        sensorManager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            float Xvalue=event.values[0];
            float Yvalue=event.values[1];
            float Zvalue=event.values[2];
            textView.setText("X :"+Xvalue+"\nY : "+Yvalue+"\nZ : "+Zvalue);
        }
        if(event.sensor.getType()==Sensor.TYPE_LIGHT){
            float value=event.values[0];
            textView.setText(" "+value);
        }

    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}