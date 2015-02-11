package com.projektarbeit.duplo.pedo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.wearable.view.DismissOverlayView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

import com.projektarbeit.duplo.pedo.fragments.ConnectSensorFragment;
import com.projektarbeit.duplo.pedo.fragments.ExportFragment;
import com.projektarbeit.duplo.pedo.fragments.StartTrainingFragment;



public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    /**
     * How long to keep the screen on when no activity is happening *
     */
    private static final long SCREEN_ON_TIMEOUT_MS = 200000; // in milliseconds



    private ViewPager mPager;
    private StartTrainingFragment mStartTrainingPage;
    private ConnectSensorFragment mConnectSensorPage;
    private ExportFragment mExportPage;
    private ImageView mSecondIndicator;
    private ImageView mFirstIndicator;
    private ImageView mThirdIndicator;
    private DismissOverlayView mDismissOverlay;
    private GestureDetector mDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        // Obtain the DismissOverlayView element
        mDismissOverlay = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
        mDismissOverlay.setIntroText(R.string.long_press_intro);
        mDismissOverlay.showIntroIfNecessary();

        // Configure a gesture detector & show the Dismiss Overlay
        mDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            public void onLongPress(MotionEvent ev) {
                mDismissOverlay.show();
            }
        });
    }

    // Capture long presses
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mDetector.onTouchEvent(ev) || super.onTouchEvent(ev);
    }


    private void setupViews() {
        mPager = (ViewPager) findViewById(R.id.pager);
        mFirstIndicator = (ImageView) findViewById(R.id.indicator_0);
        mSecondIndicator = (ImageView) findViewById(R.id.indicator_1);
        mThirdIndicator = (ImageView) findViewById(R.id.indicator_2);
        final PagerAdapter adapter = new PagerAdapter(getFragmentManager());
        mStartTrainingPage = new StartTrainingFragment();
        mConnectSensorPage = new ConnectSensorFragment();
        mExportPage = new ExportFragment();

        adapter.addFragment(mStartTrainingPage);
        adapter.addFragment(mConnectSensorPage);
        adapter.addFragment(mExportPage);
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
                // mFourthIndicator.setImageResource(R.drawable.empty_10);
                // mFifthIndicator.setImageResource(R.drawable.empty_10);
                break;
            case 1:
                mFirstIndicator.setImageResource(R.drawable.empty_10);
                mSecondIndicator.setImageResource(R.drawable.full_10);
                mThirdIndicator.setImageResource(R.drawable.empty_10);
                // mFourthIndicator.setImageResource(R.drawable.empty_10);
                // mFifthIndicator.setImageResource(R.drawable.empty_10);
                break;
           case 2:
                mFirstIndicator.setImageResource(R.drawable.empty_10);
                mSecondIndicator.setImageResource(R.drawable.empty_10);
                mThirdIndicator.setImageResource(R.drawable.full_10);
                // mFourthIndicator.setImageResource(R.drawable.empty_10);
                // mFifthIndicator.setImageResource(R.drawable.empty_10);
                break;
            /*
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
            */
        }
    }

}

