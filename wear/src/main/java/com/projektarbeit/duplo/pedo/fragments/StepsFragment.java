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

import com.projektarbeit.duplo.pedo.R;
import com.projektarbeit.duplo.pedo.TrainingWorkout;


public class StepsFragment extends Fragment implements SensorEventListener{

    SensorManager mSensorManager;
    Sensor mStepCounter;
    TextView steps;
    private static final String TAG = TrainingWorkout.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.steps_layout, container, false);

        TextView steps = (TextView) view.findViewById(R.id.stepcount);
        steps.setText("---");

        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mStepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, this.mStepCounter, mSensorManager.SENSOR_DELAY_GAME);

        /*if (mStepCounter != null){
            mSensorManager.registerListener(this, this.mStepCounter, mSensorManager.SENSOR_DELAY_GAME);
        }
        else {
            Toast.makeText(getActivity(), "nicht verfügbar", Toast.LENGTH_SHORT).show();
        }*/

    }



    @Override
    public void onSensorChanged(SensorEvent event) {

        /*
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            float x = event.values[0];
            int i = (int) x;
            steps.setText(String.valueOf(i));
        }*/



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
