package com.projektarbeit.duplo.pedo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by SL_duplo on 28.01.15.
 */
public class CountdownActivity extends Activity implements View.OnClickListener {

    private CountDownTimer countDownTimer;
    private Button skip;
    private Button renew;
    public TextView text;
    private final long startTime = 10 * 1000;       // 10 * 1000 entsprechen 10 Sek
    private final long intervall = 1 * 1000;        // Intervall beträgt immer 1 Sek

    // Variablen für Farbwechsel des Hintergrunds
    private RelativeLayout rl;
    int currentColor = 0;
    private final static int[] COLORS = {Color.CYAN, Color.GREEN, Color.LTGRAY, Color.GRAY, Color.DKGRAY,
                                 Color.MAGENTA, Color.RED, Color.YELLOW, Color.BLUE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        rl = (RelativeLayout) findViewById(R.id.CountdownID);
        renew = (Button) this.findViewById(R.id.btn_timer_renew);
        renew.setOnClickListener(this);
        skip = (Button) this.findViewById(R.id.btn_timer_skip);
        skip.setOnClickListener(this);
        text = (TextView) this.findViewById(R.id.timer);

        countDownTimer = new MyCountDownTimer(startTime, intervall);
        text.setText(text.getText() + String.valueOf(startTime / 1000));
        countDownTimer.start();

        /******************************************************************
         * Thread animiert den sekuendlichen Farbwechsel des Countdowns.
         ******************************************************************/
        new Thread (new Runnable() {
            @Override
            public void run(){
                while (true) {
                    try {
                        Thread.sleep(intervall);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    currentColor++;
                    if (currentColor > COLORS.length-1){
                        currentColor = 0;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rl.setBackgroundColor(COLORS[currentColor]);
                        }
                    });
                }
            }
        }).start();

    }


    @Override
    public void onClick(View v){

        if (v == renew) {
            countDownTimer.cancel();
            countDownTimer.start();

        }
        if (v == skip) {
            countDownTimer.cancel();
            startTraining();
        }

    }

    private void startTraining() {
        // nach Beenden den Countdowns wird TrainingLaufenActivity gestartet
        // gleichzeitig wird mit finish() diese CountDownActivity beendet / entfernt
        Intent start = new Intent ();
        String mPackage = "com.projektarbeit.duplo.pedo";
        String mClass = ".TrainingLaufen";
        start.setComponent(new ComponentName(mPackage, mPackage+mClass));
        startActivity(start);
        finish();
    }



    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long intervall) {
            super(startTime, intervall);
        }

        @Override
        public void onFinish() {
            startTraining();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            text.setText("" + millisUntilFinished / 1000);
        }

    }

/*
    class BackgroundColorThread extends Thread {

        public void run(){
            while (true) {
                try {
                    Thread.sleep(intervall);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                currentColor++;
                if (currentColor > COLORS.length-1){
                    currentColor = 0;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rl.setBackgroundColor(COLORS[currentColor]);
                    }
                });
            }
        }

    }*/


}
