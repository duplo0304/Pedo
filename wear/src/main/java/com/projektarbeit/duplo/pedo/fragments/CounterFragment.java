package com.projektarbeit.duplo.pedo.fragments;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projektarbeit.duplo.pedo.R;
import com.projektarbeit.duplo.pedo.Utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple fragment for showing the count of Jumping Jacks
 */
public class CounterFragment extends Fragment {

    private static final long ANIMATION_INTERVAL_MS = 1000; // in milliseconds
    private TextView mCounterText;
    private Timer mAnimationTimer;
    private Handler mHandler;
    private TimerTask mAnimationTask;
    private boolean up = false;
    private Drawable mDownDrawable;
    private Drawable mUpDrawable;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.counter_layout, container, false);
        //mDownDrawable = getResources().getDrawable(R.drawable.heart_1);
        //mUpDrawable = getResources().getDrawable(R.drawable.heart_2);
        mDownDrawable = getResources().getDrawable(R.drawable.jump_down_50);
        mUpDrawable = getResources().getDrawable(R.drawable.jump_up_50);
        mCounterText = (TextView) view.findViewById(R.id.counter);
        mCounterText.setCompoundDrawablesWithIntrinsicBounds(mUpDrawable, null, null, null);
        setCounter(Utils.getCounterFromPreference(getActivity()));
        mHandler = new Handler();
        startAnimation();
        return view;

    }


    private void startAnimation() {
        mAnimationTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCounterText.setCompoundDrawablesWithIntrinsicBounds(
                                up ? mUpDrawable : mDownDrawable, null, null, null);
                        up = !up;
                    }
                });
            }
        };
        mAnimationTimer = new Timer();
        mAnimationTimer.scheduleAtFixedRate(mAnimationTask, ANIMATION_INTERVAL_MS,
                ANIMATION_INTERVAL_MS);
    }

    public void setCounter(String text) {
        mCounterText.setText(text);
    }

    public void setCounter(int i) {
        setCounter(i < 0 ? "0" : String.valueOf(i));
    }

    @Override
    public void onDetach() {
        mAnimationTimer.cancel();
        super.onDetach();
    }

}

