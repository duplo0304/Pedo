package com.projektarbeit.duplo.pedo.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projektarbeit.duplo.pedo.R;
import com.projektarbeit.duplo.pedo.Utils;

public class SmaFragment extends Fragment implements SensorEventListener {

    private TextView mRawX = null;
    private TextView mRawY = null;
    private TextView mRawZ = null;
    private TextView mSMA = null;

    private Activity parent;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private long mLastUpdate;
    //private static final int UPDATE_THRESHOLD = 500;
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
        //}
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

                /*
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
                }*/
            }
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
