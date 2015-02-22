/*

package com.projektarbeit.duplo.pedo;


import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Sensors extends Activity implements SensorEventListener {

    private static final int UPDATE_THRESHOLD = 500;
    // private static final int UPDATE_THRESHOLD = 1500;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mHeartRateSensor;

    private TextView mXValueView, mYValueView, mZValueView;
    private TextView mSMAView;
    private TextView mHRView;
    private long mLastUpdate;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sma_layout);

        mXValueView = (TextView) findViewById(R.id.x_value_view);
        mYValueView = (TextView) findViewById(R.id.y_value_view);
        mZValueView = (TextView) findViewById(R.id.z_value_view);

        mSMAView = (TextView) findViewById(R.id.sma_value_view);
        mHRView = (TextView) findViewById(R.id.hr_value_view);


        // Get reference to SensorManager
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Get reference to Accelerometer
        /*if (null == (mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER)))
            finish();

       /* if (null == (mHeartRateSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE)))
            finish();*/

   // }

/*
    // Register listener
    @Override
    protected void onResume() {
        super.onResume();

        //mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_GAME);

        mLastUpdate = System.currentTimeMillis();

    }

    // Unregister listener
    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(this);
        super.onPause();
    }

    // Process new reading
    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_HEART_RATE) {
            long actualTime = System.currentTimeMillis();
            mLastUpdate = actualTime;
            float hr = event.values[0];
            mHRView.setText(String.valueOf(hr));

        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            long actualTime = System.currentTimeMillis();

            if (actualTime - mLastUpdate > UPDATE_THRESHOLD) {

                mLastUpdate = actualTime;

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                mXValueView.setText(String.valueOf(x));
                mYValueView.setText(String.valueOf(y));
                mZValueView.setText(String.valueOf(z));

                // Calculate the Signal Magnitude Area via Moving Average Function
                float sma;
                Utils.SMA windowSize = new Utils.SMA(60);
                sma = windowSize.compute(Math.abs(x) + Math.abs(x) + Math.abs(z));

                mSMAView.setText(String.valueOf(sma));


                // Logging
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String currentDateAndTime = sdf.format(new Date());
                Log.d("Time", currentDateAndTime);

                String rawentry = String.valueOf(currentDateAndTime) + ","
                               + String.valueOf(x) + ","
                               + String.valueOf(y) + ","
                               + String.valueOf(z) + ","
                               + String.valueOf(sma);

                Log.d("test", rawentry);



                CSVWriter writer = null;                                                    // Permission WRITE EXTERNAL STORAGE
                //String outputFile = "accel_sensor_data.csv";


                try {
                    //FileOutputStream out = openFileInput(outputFile, Context.MODE_APPEND)     // create the file, wenns noch nich da; sonst daten anhaengen
                    //out.write( entry.getBytes() );                                          // Data aus "out" und dann in Byte umwandeln
                    //out.close();
                    writer = new CSVWriter(new FileWriter("/sdcard/accel_sensor_data.csv", true), ',');
                    String[] entries = rawentry.split(","); // array of your values

                    writer.writeNext(entries);
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // N/A
    }



}

*/