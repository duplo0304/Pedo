package com.projektarbeit.duplo.pedo;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.projektarbeit.duplo.pedo.fragments.ExitFragment;
import com.projektarbeit.duplo.pedo.fragments.HeartRateFragment;
import com.projektarbeit.duplo.pedo.fragments.SmaFragment;

import java.io.File;
import java.util.List;


public class TrainingLaufen extends Activity {

    //private static final String TAG = "TrainingLaufen";

    private ViewPager mPager;

    //private StopwatchFragment mStopwatchPage;
    private HeartRateFragment mHRPage;
    private SmaFragment mSMAPage;
    //private StepsFragment mStepsPage;
    private ExitFragment mExitPage;

    private ImageView mFirstIndicator;
    private ImageView mSecondIndicator;
    private ImageView mThirdIndicator;
    //private ImageView mFourthIndicator;
    //private ImageView mFifthIndicator;


    //global variables
    public static final String file = ("data.txt");
    public static  File myData = null;
    public static  File myDataCollection = null;
    public static final String DATA_COLLECTION_FILE = ("DataCollection.txt");
    public SensorEventListener mSensorListener ;
    public SensorManager sensorManager;
    public List<Sensor> listSensor;
    private Sensor mAccelerometer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_laufen);
        setupViews();

        /* AB HIER NEU
        String newDirectory = "/Rad(I/O)Aktiv";
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        File myNewFolder = new File(extStorageDirectory + newDirectory);
        myNewFolder.mkdir();

        myData = new File(extStorageDirectory + newDirectory + "/" + file);
        try {
            if (!myData.exists()) {
                myData.createNewFile();
            }
        } catch(IOException ioExp){
            Log.d("AndroidSensorList:", "Fehler beim Datei erzeugen");
        }*/


    }


    private void setupViews() {
        mPager = (ViewPager) findViewById(R.id.pager);
        mFirstIndicator = (ImageView) findViewById(R.id.indicator_0);
        mSecondIndicator = (ImageView) findViewById(R.id.indicator_1);
        mThirdIndicator = (ImageView) findViewById(R.id.indicator_2);
        //mFourthIndicator = (ImageView) findViewById(R.id.indicator_3);
        //mFifthIndicator = (ImageView) findViewById(R.id.indicator_4);

        final PagerAdapter adapter = new PagerAdapter(getFragmentManager());
        //mStopwatchPage = new StopwatchFragment();
        mHRPage = new HeartRateFragment();
        mSMAPage = new SmaFragment();
        //mStepsPage = new StepsFragment();
        mExitPage = new ExitFragment();

        //adapter.addFragment(mStopwatchPage);
        adapter.addFragment(mSMAPage);
        adapter.addFragment(mHRPage);
        //adapter.addFragment(mStepsPage);
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
                //mFourthIndicator.setImageResource(R.drawable.empty_10);
                //mFifthIndicator.setImageResource(R.drawable.empty_10);
                break;
            case 1:
                mFirstIndicator.setImageResource(R.drawable.empty_10);
                mSecondIndicator.setImageResource(R.drawable.full_10);
                mThirdIndicator.setImageResource(R.drawable.empty_10);
                //mFourthIndicator.setImageResource(R.drawable.empty_10);
                //mFifthIndicator.setImageResource(R.drawable.empty_10);
                break;
            case 2:
                mFirstIndicator.setImageResource(R.drawable.empty_10);
                mSecondIndicator.setImageResource(R.drawable.empty_10);
                mThirdIndicator.setImageResource(R.drawable.full_10);
                //mFourthIndicator.setImageResource(R.drawable.empty_10);
                //mFifthIndicator.setImageResource(R.drawable.empty_10);
                break;
            /*case 3:
                mFirstIndicator.setImageResource(R.drawable.empty_10);
                mSecondIndicator.setImageResource(R.drawable.empty_10);
                mThirdIndicator.setImageResource(R.drawable.empty_10);
                mFourthIndicator.setImageResource(R.drawable.full_10);
                //mFifthIndicator.setImageResource(R.drawable.empty_10);
                break; */
            /*case 4:
                mFirstIndicator.setImageResource(R.drawable.empty_10);
                mSecondIndicator.setImageResource(R.drawable.empty_10);
                mThirdIndicator.setImageResource(R.drawable.empty_10);
                mFourthIndicator.setImageResource(R.drawable.empty_10);
                mFifthIndicator.setImageResource(R.drawable.full_10);
                break;*/

        }
    }



}