package com.projektarbeit.duplo.pedo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import android.widget.ImageView;

import com.projektarbeit.duplo.pedo.fragments.DistanceFragment;
import com.projektarbeit.duplo.pedo.fragments.ExitFragment;
import com.projektarbeit.duplo.pedo.fragments.HRFragment;
import com.projektarbeit.duplo.pedo.fragments.SpeedFragment;
import com.projektarbeit.duplo.pedo.fragments.StopwatchFragment;


public class TrainingLaufen extends Activity {

    private static final long SCREEN_ON_TIMEOUT_MS = 80000;
    private static final String TAG = "TrainingLaufen";

    private ViewPager mPager;

    private StopwatchFragment mStopwatchPage;
    private HRFragment mHRPage;
    private SpeedFragment mSpeedPage;
    private DistanceFragment mDistancePage;
    //private SaveAndExitFragment mSaveAndExitPage;
    private ExitFragment mExitPage;

    private ImageView mSecondIndicator;
    private ImageView mFirstIndicator;
    private ImageView mThirdIndicator;
    private ImageView mFourthIndicator;
    private ImageView mFifthIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_laufen);
        setupViews();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

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
        mHRPage = new HRFragment();
        mSpeedPage = new SpeedFragment();
        mDistancePage = new DistanceFragment();
        mExitPage = new ExitFragment();

        adapter.addFragment(mStopwatchPage);
        adapter.addFragment(mHRPage);
        adapter.addFragment(mSpeedPage);
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