package com.projektarbeit.duplo.pedo;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**********************************************************************************************
 *  Activity zur Auswahl der Art des Trainings.
 *  Unterscheidung in Laufen, Jumping Jacks, Radfahren und Cardio-Workout
 *********************************************************************************************/

public class ChooseActivity extends Activity implements WearableListView.ClickListener {

    // Beispieldatensatz: Elemente, die in der Liste angezeigt werden sollen
    String[] elements = {"Laufen", "Jumping Jacks", "Radfahren", "Cardio-Workout" };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooseactivity_layout);

        // Finden der Listen-Komponente per ID in entsprechender XML-Layout-File
        WearableListView listView= (WearableListView)findViewById(R.id.list);

        // Zuweisung eines Adapters an die Liste
        listView.setAdapter(new Adapter(this,elements));

        // Setzen von ClickListener
        listView.setClickListener(this);


    }


    /**********************************************************************************************
     * WearableListView ClickListener
     * @param v
     *********************************************************************************************/
    @Override
    public void onClick(WearableListView.ViewHolder v) {
        Integer tag = (Integer) v.itemView.getTag();

        // Speichert auswählte Aktivität in den SharedPrefs zwischen
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("activity", tag);
        editor.commit();

        switch (tag) {

            case 0:
                Intent laufen = new Intent(this, CountdownActivity.class);
                startActivity(laufen);
                finish();
                break;
            case 1:
                Intent jumpingjack = new Intent(this, TrainingJJ.class);
                startActivity(jumpingjack);
                finish();
                break;
            case 2:
                Toast.makeText(this, "noch nicht implementiert :)", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Intent workout = new Intent(this, TrainingWorkout.class);
                startActivity(workout);
                finish();
                break;

        }
    }


    @Override
    public void onResume(){
        super.onResume();
    }


    /****************************************************************************************
     * ClickListener für die obere, freie Region in dieser List-View
     ***************************************************************************************/
    @Override
    public void onTopEmptyRegionClick(){
        Toast.makeText(this, "Wähle deine Aktivitäts aus!", Toast.LENGTH_SHORT).show();
    }


    /*****************************************************************************************
     * Adapter-Klasse: verbindet ChooseActivity mit WearableListView
     ****************************************************************************************/
    private static final class Adapter extends WearableListView.Adapter {
        private String[] mDataset;
        private final Context mContext;
        private final LayoutInflater mInflater;

        // Konstruktor (hier passend für oberen String Datensatz)
        public Adapter(Context context, String[] dataset) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
            mDataset = dataset;
        }

        // Zuordnung zu verwendeten View Arten
        public static class ItemViewHolder extends WearableListView.ViewHolder {
            private TextView textView;

            public ItemViewHolder(View itemView) {
                super(itemView);
                // Finden/Zuordnen des TextViews im Custom-Item-Layout
                textView = (TextView) itemView.findViewById(R.id.name);
            }
        }



        /*********************************************************************************
         * Erstellt neue Views für die Listen-Elemente / -Items
         * (aufgerufen durch WearableListView's Layout Manager
         * @param parent
         * @param viewType
         * @return erzeugt Listenelement nach Custom Layout und gibt View wieder
         *********************************************************************************/
        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
            // erzeugt (unser) Custom Layout für Listenelemente
            return new ItemViewHolder(mInflater.inflate(R.layout.chooseactivity_item_layout, null));
        }


        /**********************************************************************************
         * Ersetzt den Inhalt eines Listenelements /-items
         * anstatt neue Views zu erzeugen, versucht Liste bestehende Views wiederzuverwenden
         * @param holder
         * @param position
         **********************************************************************************/
        @Override
        public void onBindViewHolder(WearableListView.ViewHolder holder,
                                 int position) {
            // Abrufen des Text Views
            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            TextView view = itemHolder.textView;
            // Ersetzen des Textinhalts
            view.setText(mDataset[position]);
            // Ersetzen von Metadaten der List-Items
            holder.itemView.setTag(position);
        }


        /**********************************************************************************
         * @return Listengröße bzw. Anzahl der Listenelemente
         *********************************************************************************/
        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }

}