package com.projektarbeit.duplo.pedo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.WearableListView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

//import android.util.Log;


/**********************************************************************************************
 *  Activity zur Bewertung des subjektiven Empfindens nach der Trainingseinheit.
 *  Bewertung erfolgt nach Borg-RPE-Skala
 *
 *  Darstellung der Activity als AdvancedWearableListView
 *********************************************************************************************/

public class BorgScale extends Activity implements WearableListView.ClickListener  {

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
        setContentView(R.layout.borgscale_layout);

        mDefaultCircleRadius = getResources().getDimension(R.dimen.default_circle_radius);
        mSelectedCircleRadius = getResources().getDimension(R.dimen.selected_circle_radius);

        // 15 Bilder für die 15 Stufen der Borg-RPE-Skala
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

        mListHeader = (TextView) findViewById(R.id.header);
        mListView = (WearableListView) findViewById(R.id.wearable_List);

        // Zuweisen eines Adapters zu der Liste
        mAdapter = new MyListAdapter();
        mListView.setAdapter(mAdapter);

        mListView.setClickListener(BorgScale.this);



        // erzeugt die Overlay-Information für die Trainingsbewertung
        topLevelLayout = findViewById(R.id.top_layout);
        // Overlay taucht nur beim erster App-Ausführung auf
        if (isFirstTime()) {
            topLevelLayout.setVisibility(View.INVISIBLE);
        }

        afterTrainingLayout = findViewById(R.id.after_training);
        afterTrainingLayout.setVisibility(View.INVISIBLE);
    }

    /**********************************************************************************************
     *  onClick-Methode, die folgende Dinge einleitet:
     *  - Auswahl des entsprechenden Borg-RPE-Wertes
     *  - Speichern dieser Auswahl in die SharedPreferences
     *  - Erzeugen einer %TIMESTEMP%_info_data.csv-Datei, in der Startzeit, Endzeit,
     *      Art des Trainings und Borg-Wert gespeichert sind.
     *  @param viewHolder
     *********************************************************************************************/
    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
        //Toast.makeText(this, String.format("You selected item #%s", viewHolder.getPosition()+1), Toast.LENGTH_SHORT).show();
        int a = viewHolder.getPosition();

        // Speichert den angewählten Borg-Skalen Wert in den SharedPrefs zwischen
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("borg", a);
        editor.commit();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd_");
        String currentDate = sdfDate.format(new Date());

        String startTime = pref.getString("time", "null");
        String endTime = sdf.format(new Date());
        int borg = 7+ pref.getInt("borg", 1337);
        int activity = pref.getInt("activity", 1337);

        String rawentry = String.valueOf(startTime) + ","
                + String.valueOf(endTime) + ","
                + String.valueOf(activity) + ","
                + String.valueOf(borg);
        CSVWriter writer = null;


        switch(a) {
            case 0:

                try {
                    String newDirectory = "/Rad-IO-Aktiv";
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    File myNewFolder = new File(extStorageDirectory + newDirectory);

                    if (!myNewFolder.exists()){
                        myNewFolder.mkdir();
                    }

                                      String file = (currentDate + "info_data.csv");
                    writer = new CSVWriter(new FileWriter(extStorageDirectory + newDirectory + "/" + file , true), ',');
                    String[] header = {"Starzeit", "Endzeit", "Art der Aktivität", "Borg-Skala Wert"};
                    String[] entries = rawentry.split(","); // array of your values

                    writer.writeNext(header);
                    writer.writeNext(entries);
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                afterTrainingInfo();
                break;
            case 1:

                try {
                    String newDirectory = "/Rad-IO-Aktiv";
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    File myNewFolder = new File(extStorageDirectory + newDirectory);

                    if (!myNewFolder.exists()){
                        myNewFolder.mkdir();
                    }

                    String file = (currentDate + "info_data.csv");
                    writer = new CSVWriter(new FileWriter(extStorageDirectory + newDirectory + "/" + file , true), ',');
                    String[] header = {"Starzeit", "Endzeit", "Art der Aktivität", "Borg-Skala Wert"};
                    String[] entries = rawentry.split(","); // array of your values

                    writer.writeNext(header);
                    writer.writeNext(entries);
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                afterTrainingInfo();
                break;
            case 2:

                try {
                    String newDirectory = "/Rad-IO-Aktiv";
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    File myNewFolder = new File(extStorageDirectory + newDirectory);

                    if (!myNewFolder.exists()){
                        myNewFolder.mkdir();
                    }

                    String file = (currentDate + "info_data.csv");
                    writer = new CSVWriter(new FileWriter(extStorageDirectory + newDirectory + "/" + file , true), ',');
                    String[] header = {"Starzeit", "Endzeit", "Art der Aktivität", "Borg-Skala Wert"};
                    String[] entries = rawentry.split(","); // array of your values

                    writer.writeNext(header);
                    writer.writeNext(entries);
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                afterTrainingInfo();
                break;
            case 3:

                try {
                    String newDirectory = "/Rad-IO-Aktiv";
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    File myNewFolder = new File(extStorageDirectory + newDirectory);

                    if (!myNewFolder.exists()){
                        myNewFolder.mkdir();
                    }

                    String file = (currentDate + "info_data.csv");
                    writer = new CSVWriter(new FileWriter(extStorageDirectory + newDirectory + "/" + file , true), ',');
                    String[] header = {"Starzeit", "Endzeit", "Art der Aktivität", "Borg-Skala Wert"};
                    String[] entries = rawentry.split(","); // array of your values

                    writer.writeNext(header);
                    writer.writeNext(entries);
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                afterTrainingInfo();
                break;
            case 4:

                try {
                    String newDirectory = "/Rad-IO-Aktiv";
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    File myNewFolder = new File(extStorageDirectory + newDirectory);

                    if (!myNewFolder.exists()){
                        myNewFolder.mkdir();
                    }

                    String file = (currentDate + "info_data.csv");
                    writer = new CSVWriter(new FileWriter(extStorageDirectory + newDirectory + "/" + file , true), ',');
                    String[] header = {"Starzeit", "Endzeit", "Art der Aktivität", "Borg-Skala Wert"};
                    String[] entries = rawentry.split(","); // array of your values

                    writer.writeNext(header);
                    writer.writeNext(entries);
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                afterTrainingInfo();
                break;
            case 5:

                try {
                    String newDirectory = "/Rad-IO-Aktiv";
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    File myNewFolder = new File(extStorageDirectory + newDirectory);

                    if (!myNewFolder.exists()){
                        myNewFolder.mkdir();
                    }

                    String file = (currentDate + "info_data.csv");
                    writer = new CSVWriter(new FileWriter(extStorageDirectory + newDirectory + "/" + file , true), ',');
                    String[] header = {"Starzeit", "Endzeit", "Art der Aktivität", "Borg-Skala Wert"};
                    String[] entries = rawentry.split(","); // array of your values

                    writer.writeNext(header);
                    writer.writeNext(entries);
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                afterTrainingInfo();
                break;
            case 6:

                try {
                    String newDirectory = "/Rad-IO-Aktiv";
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    File myNewFolder = new File(extStorageDirectory + newDirectory);

                    if (!myNewFolder.exists()){
                        myNewFolder.mkdir();
                    }

                    String file = (currentDate + "info_data.csv");
                    writer = new CSVWriter(new FileWriter(extStorageDirectory + newDirectory + "/" + file , true), ',');
                    String[] header = {"Starzeit", "Endzeit", "Art der Aktivität", "Borg-Skala Wert"};
                    String[] entries = rawentry.split(","); // array of your values

                    writer.writeNext(header);
                    writer.writeNext(entries);
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                afterTrainingInfo();
                break;
            case 7:

                try {
                    String newDirectory = "/Rad-IO-Aktiv";
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    File myNewFolder = new File(extStorageDirectory + newDirectory);

                    if (!myNewFolder.exists()){
                        myNewFolder.mkdir();
                    }

                    String file = (currentDate + "info_data.csv");
                    writer = new CSVWriter(new FileWriter(extStorageDirectory + newDirectory + "/" + file , true), ',');
                    String[] header = {"Starzeit", "Endzeit", "Art der Aktivität", "Borg-Skala Wert"};
                    String[] entries = rawentry.split(","); // array of your values

                    writer.writeNext(header);
                    writer.writeNext(entries);
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                afterTrainingInfo();
                break;
            case 8:

                try {
                    String newDirectory = "/Rad-IO-Aktiv";
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    File myNewFolder = new File(extStorageDirectory + newDirectory);

                    if (!myNewFolder.exists()){
                        myNewFolder.mkdir();
                    }

                    String file = (currentDate + "info_data.csv");
                    writer = new CSVWriter(new FileWriter(extStorageDirectory + newDirectory + "/" + file , true), ',');
                    String[] header = {"Starzeit", "Endzeit", "Art der Aktivität", "Borg-Skala Wert"};
                    String[] entries = rawentry.split(","); // array of your values

                    writer.writeNext(header);
                    writer.writeNext(entries);
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                afterTrainingInfo();
                break;
            case 9:

                try {
                    String newDirectory = "/Rad-IO-Aktiv";
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    File myNewFolder = new File(extStorageDirectory + newDirectory);

                    if (!myNewFolder.exists()){
                        myNewFolder.mkdir();
                    }

                    String file = (currentDate + "info_data.csv");
                    writer = new CSVWriter(new FileWriter(extStorageDirectory + newDirectory + "/" + file , true), ',');
                    String[] header = {"Starzeit", "Endzeit", "Art der Aktivität", "Borg-Skala Wert"};
                    String[] entries = rawentry.split(","); // array of your values

                    writer.writeNext(header);
                    writer.writeNext(entries);
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                afterTrainingInfo();
                break;
            case 10:

                try {
                    String newDirectory = "/Rad-IO-Aktiv";
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    File myNewFolder = new File(extStorageDirectory + newDirectory);

                    if (!myNewFolder.exists()){
                        myNewFolder.mkdir();
                    }

                    String file = (currentDate + "info_data.csv");
                    writer = new CSVWriter(new FileWriter(extStorageDirectory + newDirectory + "/" + file , true), ',');
                    String[] header = {"Starzeit", "Endzeit", "Art der Aktivität", "Borg-Skala Wert"};
                    String[] entries = rawentry.split(","); // array of your values

                    writer.writeNext(header);
                    writer.writeNext(entries);
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                afterTrainingInfo();
                break;
            case 11:

                try {
                    String newDirectory = "/Rad-IO-Aktiv";
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    File myNewFolder = new File(extStorageDirectory + newDirectory);

                    if (!myNewFolder.exists()){
                        myNewFolder.mkdir();
                    }

                    String file = (currentDate + "info_data.csv");
                    writer = new CSVWriter(new FileWriter(extStorageDirectory + newDirectory + "/" + file , true), ',');
                    String[] header = {"Starzeit", "Endzeit", "Art der Aktivität", "Borg-Skala Wert"};
                    String[] entries = rawentry.split(","); // array of your values

                    writer.writeNext(header);
                    writer.writeNext(entries);
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                afterTrainingInfo();
                break;
            case 12:

                try {
                    String newDirectory = "/Rad-IO-Aktiv";
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    File myNewFolder = new File(extStorageDirectory + newDirectory);

                    if (!myNewFolder.exists()){
                        myNewFolder.mkdir();
                    }

                    String file = (currentDate + "info_data.csv");
                    writer = new CSVWriter(new FileWriter(extStorageDirectory + newDirectory + "/" + file , true), ',');
                    String[] header = {"Starzeit", "Endzeit", "Art der Aktivität", "Borg-Skala Wert"};
                    String[] entries = rawentry.split(","); // array of your values

                    writer.writeNext(header);
                    writer.writeNext(entries);
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                afterTrainingInfo();
                break;
        }
    }

    @Override
    public void onTopEmptyRegionClick() {
        Toast.makeText(this, "Wie fandest du das Training?", Toast.LENGTH_SHORT).show();
    }


    /**********************************************************************************************
     * Adapter Klasse verbindet Activity mit der WearableListView-Klasse
     *********************************************************************************************/

    public class MyListAdapter extends WearableListView.Adapter {

        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new WearableListView.ViewHolder(new MyItemView(BorgScale.this));
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
     *  Klasse: hier werden die einzelnen Listenelemente (Bild und Text) definiert
     *********************************************************************************/
    private final class MyItemView extends FrameLayout
                                   implements WearableListView.OnCenterProximityListener {

        final CircledImageView imgView;
        final TextView txtView;

        public MyItemView(Context context) {
            super(context);
            View.inflate(context, R.layout.borgscale_item_layout, this);
            imgView = (CircledImageView) findViewById(R.id.image);
            txtView = (TextView) findViewById(R.id.text);

        }


        /****************************************************************************************
         *  Methoden für Animation von selektierten Listen-Items
         *  onCenterPosition() --> selektiertes Element wird vergroessert
         *                          und nicht transparent (alpha = 1)
         *  onNonCenterPosition() --> nicht selektierte Elemente werden auf 70% verkleinert
         *                          und 70% transparent gesetzt
         *  @param b
         ****************************************************************************************/
        @Override
        public void onCenterPosition(boolean b) {

            imgView.animate().scaleX(1f).scaleY(1f).alpha(1f);
            txtView.animate().scaleX(1f).scaleY(1f).alpha(1);

        }

        @Override
        public void onNonCenterPosition(boolean b) {

            imgView.animate().scaleX(0.7f).scaleY(0.7f).alpha(0.7f);
            txtView.animate().scaleX(0.7f).scaleY(0.7f).alpha(0.7f);

        }
    }

    /*********************************************************************************
     *  Hilfsmethode:
     *  - überprüft, ob App das erste Mal ausgeführt wurde
     *  - wenn ja, wird Overlay angezeigt, dass dem User sagt, dass er sein Training
     *      bewerten soll
     *  - isFirstTime() arbeitet mit SharedPreferenes
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

    /*************************************************************************************
     *  Methode, die nach dem Training ein verstecktes Layout für 3 Sekunden wieder
     *  sichtbar macht und so dem User visualisiert, dass das Training vorbei ist
     *************************************************************************************/
    private void afterTrainingInfo() {
        afterTrainingLayout.setVisibility(View.VISIBLE);
        /*afterTrainingLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent mv) {
                afterTrainingLayout.setVisibility(View.INVISIBLE);
                return false;
            }
        });*/

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                finish();
            }
        }, 3000);

    }
}