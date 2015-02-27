package com.projektarbeit.duplo.pedo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.WearableListView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AdvancedList extends Activity implements WearableListView.ClickListener  {

    private static ArrayList<Integer> mListItems;
    private TextView mListHeader;
    private WearableListView mListView;
    private MyListAdapter mAdapter;
    private float mDefaultCircleRadius;
    private float mSelectedCircleRadius;
    private View topLevelLayout;
    private View afterTrainingLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_main);

        mDefaultCircleRadius = getResources().getDimension(R.dimen.default_circle_radius);
        mSelectedCircleRadius = getResources().getDimension(R.dimen.selected_circle_radius);

        // Sample image set for the list
        mListItems = new ArrayList<Integer>();
        mListItems.add(R.drawable.borg_0);
        mListItems.add(R.drawable.borg_0);
        mListItems.add(R.drawable.borg_0);
        mListItems.add(R.drawable.borg_0);
        mListItems.add(R.drawable.borg_1);
        mListItems.add(R.drawable.borg_1);
        mListItems.add(R.drawable.borg_1);
        mListItems.add(R.drawable.borg_2);
        mListItems.add(R.drawable.borg_2);
        mListItems.add(R.drawable.borg_2);
        mListItems.add(R.drawable.borg_3);
        mListItems.add(R.drawable.borg_3);
        mListItems.add(R.drawable.borg_3);

        // This is our list header
        mListHeader = (TextView) findViewById(R.id.header);

        // Get the list component from the layout of the activity
        mListView = (WearableListView) findViewById(R.id.wearable_List);

        // Assign an adapter to the list
        mAdapter = new MyListAdapter();
        mListView.setAdapter(mAdapter);

        mListView.setClickListener(AdvancedList.this);



        // erzeugt die Overlay-Information für die Trainingsbewertung
        topLevelLayout = findViewById(R.id.top_layout);
        // Overlay taucht nur beim erster App-Ausführung auf
        if (isFirstTime()) {
            topLevelLayout.setVisibility(View.INVISIBLE);
        }

        afterTrainingLayout = findViewById(R.id.after_training);
        afterTrainingLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
        //Toast.makeText(this, String.format("You selected item #%s", viewHolder.getPosition()+1), Toast.LENGTH_SHORT).show();
        int a = viewHolder.getPosition();
        switch(a) {
            case 0:
                //Toast.makeText(this, "sehr sehr leicht", Toast.LENGTH_SHORT).show();
                afterTrainingInfo();
                break;
            case 12:
                //Toast.makeText(this, "sehr sehr schwer", Toast.LENGTH_SHORT).show();
                afterTrainingInfo();
                break;
        }
    }

    @Override
    public void onTopEmptyRegionClick() {
        Toast.makeText(this, "Wie fandest du das Training?", Toast.LENGTH_SHORT).show();
    }

    public class MyListAdapter extends WearableListView.Adapter {

        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new WearableListView.ViewHolder(new MyItemView(AdvancedList.this));
        }

        @Override
        public void onBindViewHolder(WearableListView.ViewHolder viewHolder, int i) {
            MyItemView itemView = (MyItemView) viewHolder.itemView;

            TextView txtView = (TextView) itemView.findViewById(R.id.text);

            //txtView.setText(String.format("RPE-Wert %d", i+7));

            switch(i){
                case 0:
                    txtView.setText("sehr sehr leicht");
                    break;
                case 1:
                    txtView.setText("...");
                    break;
                case 2:
                    txtView.setText("sehr leicht");
                    break;
                case 3:
                    txtView.setText("...");
                    break;
                case 4:
                    txtView.setText("leicht");
                    break;
                case 5:
                    txtView.setText("...");
                    break;
                case 6:
                    txtView.setText("etwas anstrengend");
                    break;
                case 7:
                    txtView.setText("...");
                    break;
                case 8:
                    txtView.setText("schwer");
                    break;
                case 9:
                    txtView.setText("...");
                    break;
                case 10:
                    txtView.setText("sehr schwer");
                    break;
                case 11:
                    txtView.setText("...");
                    break;
                case 12:
                    txtView.setText("sehr sehr schwer");
                    break;

            }

            Integer resourceId = mListItems.get(i);
            CircledImageView imgView = (CircledImageView) itemView.findViewById(R.id.image);
            imgView.setImageResource(resourceId);
        }

        @Override
        public int getItemCount() {
            return mListItems.size();
        }
    }

    /*********************************************************************************
     *
     *********************************************************************************/
    private final class MyItemView extends FrameLayout implements WearableListView.OnCenterProximityListener {

        final CircledImageView imgView;
        final TextView txtView;

        public MyItemView(Context context) {
            super(context);
            View.inflate(context, R.layout.advanced_item_layout, this);
            imgView = (CircledImageView) findViewById(R.id.image);
            txtView = (TextView) findViewById(R.id.text);

        }


        @Override
        public void onCenterPosition(boolean b) {

            //Animation example to be ran when the view becomes the centered one
            imgView.animate().scaleX(1f).scaleY(1f).alpha(1f);
            txtView.animate().scaleX(1f).scaleY(1f).alpha(1);

        }

        @Override
        public void onNonCenterPosition(boolean b) {

            //Animation example to be ran when the view is not the centered one anymore
            imgView.animate().scaleX(0.7f).scaleY(0.7f).alpha(0.7f);
            txtView.animate().scaleX(0.7f).scaleY(0.7f).alpha(0.7f);

        }
    }

    /*********************************************************************************
     *
     *********************************************************************************/
    private boolean isFirstTime() {
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        boolean ranBefore = pref.getBoolean("RanBefore", false);
        if (!ranBefore) {
            SharedPreferences.Editor editor = pref.edit();
            //editor.putBoolean("RanBefore", true);                     // true ist korrekt, wenn nur bei erster Ausführung auftauchen soll
            editor.putBoolean("RanBefore", false);                      // für Demozwecke soll Overlay jedes Mal auftauchen
            editor.commit();
            topLevelLayout.setVisibility(View.VISIBLE);
            topLevelLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent ev) {
                    topLevelLayout.setVisibility(View.INVISIBLE);
                    return false;
                }
            });
        }
        return ranBefore;
    }

    private void afterTrainingInfo() {
        afterTrainingLayout.setVisibility(View.VISIBLE);
        afterTrainingLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent mv) {
                afterTrainingLayout.setVisibility(View.INVISIBLE);
                return false;
            }
        });

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                finish();
            }
        }, 3000);

    }
}