package com.projektarbeit.duplo.pedo;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.projektarbeit.duplo.pedo.fragments.DistanceFragment;
import com.projektarbeit.duplo.pedo.fragments.ExitFragment;
import com.projektarbeit.duplo.pedo.fragments.HeartRateFragment;
import com.projektarbeit.duplo.pedo.fragments.SmaFragment;
import com.projektarbeit.duplo.pedo.fragments.StopwatchFragment;


public class TrainingLaufen extends Activity {

    private static final long SCREEN_ON_TIMEOUT_MS = 80000;
    private static final String TAG = "TrainingLaufen";

    private ViewPager mPager;

    private StopwatchFragment mStopwatchPage;
    private HeartRateFragment mHRPage;
    private SmaFragment mSMAPage;
    private DistanceFragment mDistancePage;
    private ExitFragment mExitPage;

    private ImageView mSecondIndicator;
    private ImageView mFirstIndicator;
    private ImageView mThirdIndicator;
    private ImageView mFourthIndicator;
    private ImageView mFifthIndicator;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private TextView mXValueView = null;
    private TextView mYValueView = null;
    private TextView mZValueView = null;
    private TextView mSMAView = null;
    //private TextView mHRView;
    private long mLastUpdate;
    private static final int UPDATE_THRESHOLD = 500;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_laufen);
        setupViews();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


    }


    private void setupViews() {
        mPager = (ViewPager) findViewById(R.id.pager);
        mFirstIndicator = (ImageView) findViewById(R.id.indicator_0);
        mSecondIndicator = (ImageView) findViewById(R.id.indicator_1);
        mThirdIndicator = (ImageView) findViewById(R.id.indicator_2);
        mFourthIndicator = (ImageView) findViewById(R.id.indicator_3);
        mFifthIndicator = (ImageView) findViewById(R.id.indicator_4);

        final PagerAdapter adapter = new PagerAdapter(getFragmentManager());
        mStopwatchPage = new StopwatchFragment();
        mHRPage = new HeartRateFragment();
        mSMAPage = new SmaFragment();
        mDistancePage = new DistanceFragment();
        mExitPage = new ExitFragment();

        adapter.addFragment(mStopwatchPage);
        adapter.addFragment(mSMAPage);
        adapter.addFragment(mHRPage);
        adapter.addFragment(mDistancePage);
        adapter.addFragment(mExitPage);

        setIndicator(0);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
                setIndicator(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        mPager.setAdapter(adapter);
    }

    /**
     * Sets the page indicator for the ViewPager.
     */
    private void setIndicator(int i) {
        switch (i) {
            case 0:
                mFirstIndicator.setImageResource(R.drawable.full_10);
                mSecondIndicator.setImageResource(R.drawable.empty_10);
                mThirdIndicator.setImageResource(R.drawable.empty_10);
                mFourthIndicator.setImageResource(R.drawable.empty_10);
                mFifthIndicator.setImageResource(R.drawable.empty_10);
                break;
            case 1:
                mFirstIndicator.setImageResource(R.drawable.empty_10);
                mSecondIndicator.setImageResource(R.drawable.full_10);
                mThirdIndicator.setImageResource(R.drawable.empty_10);
                mFourthIndicator.setImageResource(R.drawable.empty_10);
                mFifthIndicator.setImageResource(R.drawable.empty_10);
                break;
            case 2:
                mFirstIndicator.setImageResource(R.drawable.empty_10);
                mSecondIndicator.setImageResource(R.drawable.empty_10);
                mThirdIndicator.setImageResource(R.drawable.full_10);
                mFourthIndicator.setImageResource(R.drawable.empty_10);
                mFifthIndicator.setImageResource(R.drawable.empty_10);
                break;
            case 3:
                mFirstIndicator.setImageResource(R.drawable.empty_10);
                mSecondIndicator.setImageResource(R.drawable.empty_10);
                mThirdIndicator.setImageResource(R.drawable.empty_10);
                mFourthIndicator.setImageResource(R.drawable.full_10);
                mFifthIndicator.setImageResource(R.drawable.empty_10);
                break;
            case 4:
                mFirstIndicator.setImageResource(R.drawable.empty_10);
                mSecondIndicator.setImageResource(R.drawable.empty_10);
                mThirdIndicator.setImageResource(R.drawable.empty_10);
                mFourthIndicator.setImageResource(R.drawable.empty_10);
                mFifthIndicator.setImageResource(R.drawable.full_10);
                break;

        }
    }

}