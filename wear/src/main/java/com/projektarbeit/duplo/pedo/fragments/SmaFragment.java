package com.projektarbeit.duplo.pedo.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.opencsv.CSVWriter;
import com.projektarbeit.duplo.pedo.R;
import com.projektarbeit.duplo.pedo.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SmaFragment extends Fragment implements SensorEventListener {

    private TextView mRawX = null;
    private TextView mRawY = null;
    private TextView mRawZ = null;
    private TextView mSMA = null;

    private Activity parent;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private long mLastUpdate;
    private static final int UPDATE_THRESHOLD = 1000; // jede Sekunde



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sma_layout, container, false);

        mRawX = (TextView) view.findViewById(R.id.x_value_view);
        mRawY = (TextView) view.findViewById(R.id.y_value_view);
        mRawZ = (TextView) view.findViewById(R.id.z_value_view);
        mSMA = (TextView) view.findViewById(R.id.sma_value_view);


        return view;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parent = getActivity();
        mSensorManager = (SensorManager) parent.getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


    }


    @Override
    public void onResume() {
        super.onResume();
        if (mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)){
            //if (Log.isLoggable(TAG, Log.DEBUG)) {
            //    Log.d(TAG, "erfolgreich registriert");
            //}
        }
    }


    @Override
    public void onPause() {
        super.onPause();

    }


    @Override
    public void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
        //if (Log.isLoggable(TAG, Log.DEBUG)) {
        //    Log.d(TAG, "Unregistered for sensor events");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            long actualTime = System.currentTimeMillis();

            if (actualTime - mLastUpdate > UPDATE_THRESHOLD) {

                mLastUpdate = actualTime;

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];


                mRawX.setText(String.valueOf(x));
                mRawY.setText(String.valueOf(y));
                mRawZ.setText(String.valueOf(z));

                // Calculate the Signal Magnitude Area via Moving Average Function
                float sma;
                Utils.SMA windowSize = new Utils.SMA(60);
                sma = windowSize.compute(Math.abs(x) + Math.abs(x) + Math.abs(z));

                mSMA.setText(String.valueOf(sma));


                // Logging
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
                SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd_");
                String currentDateAndTime = sdf.format(new Date());
                String currentDate = sdfDate.format(new Date());
                Log.d("Time", currentDateAndTime);

                String rawentry = String.valueOf(currentDateAndTime) + ","
                        + String.valueOf(x) + ","
                        + String.valueOf(y) + ","
                        + String.valueOf(z) + ","
                        + String.valueOf(sma);

                Log.d("test", rawentry);


                try {
                    String newDirectory = "/Rad-IO-Aktiv";
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    File myNewFolder = new File(extStorageDirectory + newDirectory);

                    if (!myNewFolder.exists()){
                        myNewFolder.mkdir();
                    }

                    String file = (currentDate + "accel_data.csv");


                    // OpenCSVWriter Library genutzt fuer Loggen von Daten
                    // FileWriter Konstruktur ist 'offen' für Anhaengen neuer Messdaten
                    CSVWriter writer = null;
                    writer = new CSVWriter(new FileWriter(extStorageDirectory + newDirectory + "/" + file , true), ',');
                    //String[] header = {"Date and Time", "Accel X", "Accel Y", "Accel Z", "SMA", "HR", "Accuracy", "BorgValue", "Activity"};
                    String[] entries = rawentry.split(","); // array of your values

                    //writer.writeNext(header);
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

    }
}
