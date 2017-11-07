package com.starks.stepcounter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    SensorManager sensorManager;
    TextView t1;
    boolean running = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (TextView)findViewById(R.id.textView1);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

    }

    protected void onResume (){
        super.onResume();
        running = true;
        Sensor countsensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countsensor!=null){
            sensorManager.registerListener(this,countsensor,SensorManager.SENSOR_DELAY_UI);
        }
        else{
            Toast.makeText(this,"Sensor not found ",Toast.LENGTH_LONG).show();
        }

    }
    protected void onPause(){
        super.onPause();
        running=false;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(running){
            t1.setText(String.valueOf(sensorEvent.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
