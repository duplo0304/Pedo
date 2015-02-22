package com.projektarbeit.duplo.pedo;

import android.app.Activity;
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

    private TextView mXValueView, mYValueView, mZValueView;
    private TextView mSMAView;
    //private TextView mHRView;
    private long mLastUpdate;
    private static final int UPDATE_THRESHOLD = 500;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_laufen);
        setupViews();

        //mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


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
        adapter.addFragment(mHRPage);
        adapter.addFragment(mSMAPage);
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


    /*
    @Override
    public void onResume() {
        super.onResume();
        if (mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)){
            if (Log.isLoggable(TAG, Log.DEBUG)) {
                Log.d(TAG, "erfolgreich registriert");
                }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, "Unregistered for sensor events");
        }
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

                mXValueView = (TextView) findViewById(R.id.x_value_view);
                mYValueView = (TextView) findViewById(R.id.y_value_view);
                mZValueView = (TextView) findViewById(R.id.z_value_view);
                mSMAView = (TextView) findViewById(R.id.sma_value_view);

                mXValueView.setText(String.valueOf(x));
                mYValueView.setText(String.valueOf(y));
                mZValueView.setText(String.valueOf(z));

                // Calculate the Signal Magnitude Area via Moving Average Function
                float sma;
                Utils.SMA windowSize = new Utils.SMA(60);
                sma = windowSize.compute(Math.abs(x) + Math.abs(x) + Math.abs(z));

                mSMAView.setText(String.valueOf(sma));



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
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    } */
}