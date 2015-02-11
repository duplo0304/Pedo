package com.projektarbeit.duplo.pedo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by SL_duplo on 28.01.15.
 */
public class CountdownActivity extends Activity implements View.OnClickListener {

    private CountDownTimer countDownTimer;
    // private boolean timerHasStarted = true;
    // private Button startB;
    private Button skip;
    private Button renew;
    public TextView text;
    private final long startTime = 11 * 1000;       // 10 * 1000 entsprechen 10 Sek
    private final long intervall = 1 * 1000;        // Intervall beträgt immer 1 Sek

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        renew = (Button) this.findViewById(R.id.btn_timer_renew);
        renew.setOnClickListener(this);
        skip = (Button) this.findViewById(R.id.btn_timer_skip);
        skip.setOnClickListener(this);
        text = (TextView) this.findViewById(R.id.timer);
        countDownTimer = new MyCountDownTimer(startTime, intervall);
        text.setText(text.getText() + String.valueOf(startTime / 1000));
        countDownTimer.start();

    }


    @Override
    public void onClick(View v){

        if (v == renew) {
            countDownTimer.cancel();
            countDownTimer.start();
        }
        else {
            countDownTimer.cancel();
            countDownTimer.onFinish();
        }
    }
    /*
    @Override
    public void onClick(View v) {
        if (!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;
            startB.setText("STOP");
        }
        else {
            countDownTimer.cancel();
            timerHasStarted = false;
            startB.setText("RESTART");
        }
    }
    */

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long intervall) {
            super(startTime, intervall);
        }

        @Override
        public void onFinish() {
            // nach Beenden den Countdowns wird TrainingLaufenActivity gestartet
            Intent start = new Intent ();
            String mPackage = "com.projektarbeit.duplo.pedo";
            String mClass = ".TrainingLaufen";
            start.setComponent(new ComponentName(mPackage, mPackage+mClass));
            startActivity(start);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            text.setText("" + millisUntilFinished / 1000);
        }
    }


}
