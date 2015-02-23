package com.projektarbeit.duplo.pedo.fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projektarbeit.duplo.pedo.R;
import com.projektarbeit.duplo.pedo.TrainingLaufen;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;


public class HeartRateFragment extends Fragment implements SensorEventListener {

    // Variablen fuer Herz-Animation
    private static final long ANIMATION_INTERVAL_MS = 1000; // in milliseconds
    private TextView mHR;
    private Timer mAnimationTimer;
    private Handler mHandler;
    private TimerTask mAnimationTask;
    private boolean full = false;
    private Drawable mHeart1;
    private Drawable mHeart2;

    // Variablen fuer Heart-Rate-Sensor
    private static final String TAG = TrainingLaufen.class.getName();
    private TextView HRvalue;
    private SensorManager mSensorManager;
    private Sensor mHeartRateSensor;
    private CountDownLatch latch;
    private TextView accuracy;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hr_layout, container, false);

        // Animation initialisieren
        mHeart1 = getResources().getDrawable(R.drawable.symbol_hr);
        mHeart2 = getResources().getDrawable(R.drawable.symbol_hr_full);
        mHR = (TextView) view.findViewById(R.id.image_hr);
        mHR.setCompoundDrawablesWithIntrinsicBounds(mHeart2, null, null, null);
        mHandler = new Handler();
        startAnimation();

        // Heart-Rate-Sensor initialisieren
        latch = new CountDownLatch(1);
        HRvalue = (TextView) view.findViewById(R.id.HR_field_value);
        HRvalue.setText("Suche...");
        latch.countDown();
        accuracy = (TextView) view.findViewById(R.id.accuracy);
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mHeartRateSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);

        /*if (mHeartRateSensor) == null {
            Log.d(TAG, "heart rate sensor is null");
        }*/

        return view;

    }


    private void startAnimation() {
        mAnimationTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mHR.setCompoundDrawablesWithIntrinsicBounds(
                                full ? mHeart2 : mHeart1, null, null, null);
                        full = !full;
                    }
                });
            }
        };
        mAnimationTimer = new Timer();
        mAnimationTimer.scheduleAtFixedRate(mAnimationTask, ANIMATION_INTERVAL_MS,
                ANIMATION_INTERVAL_MS);
    }



    @Override
    public void onDetach() {
        mAnimationTimer.cancel();
        super.onDetach();
    }

    @Override
    public void onStart() {
        super.onStart();

        mSensorManager.registerListener(this, this.mHeartRateSensor, 3);
    }



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        try {
            latch.await();
            if(sensorEvent.values[0] > 0){
                Log.d(TAG, "sensor event: " + sensorEvent.accuracy + " = " + sensorEvent.values[0]);
                HRvalue.setText(String.valueOf(sensorEvent.values[0]));
                accuracy.setText("Genauigkeit: "+sensorEvent.accuracy);
            }

        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

        Log.d(TAG, "accuracy changed: " + i);
        accuracy.setText("Genauigkeit: " + Integer.toString(i));
    }

    @Override
    public void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }



}
