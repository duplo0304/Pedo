package com.projektarbeit.duplo.pedo.fragments;

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
import android.widget.Toast;

import com.projektarbeit.duplo.pedo.R;



public class StepsFragment extends Fragment implements SensorEventListener{

    SensorManager mSensorManager;
    Sensor mStepCounter;
    TextView steps;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.steps_layout, container, false);

        TextView steps = (TextView) view.findViewById(R.id.stepcount);

        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        //mStepDetector = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

        mStepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (mStepCounter != null){
            mSensorManager.registerListener(this, mStepCounter, mSensorManager.SENSOR_DELAY_GAME);
        }
        else {
            Toast.makeText(getActivity(), "nicht verfügbar", Toast.LENGTH_SHORT).show();
        }

    }

/*
    @Override
    public void onPause() {
        super.onPause();
        activityRunning = false;
        // if you unregister the last listener, the hardware will stop detecting step events
//        sensorManager.unregisterListener(this);
    } */

    @Override
    public void onSensorChanged(SensorEvent event) {

        Sensor sensor = event.sensor;
        float[] values = event.values;
        int value = -1;

        if (values.length > 0) {
            value = (int) values[0];
        }

        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            steps.setText("Step Counter Detected : " + value);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
