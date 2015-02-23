package com.projektarbeit.duplo.pedo;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.ConfirmationActivity;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;



public class BorgScaleActivity extends Activity implements WearableListView.ClickListener  {

    // Sample dataset for the list
    String[] elements = {"sehr sehr leicht", "sehr leicht", "leicht", "etwas anstrengend",
                            "anstrengend", "sehr schwer", "sehr sehr schwer" };


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooseactivity_layout);

        // Get the list component from the layout of the activity
        WearableListView listView=
                (WearableListView)findViewById(R.id.list);

        // Assign an adapter to the list
        listView.setAdapter(new Adapter(this,elements));

        // Set a click listener
        listView.setClickListener(this);


    }



    // WearableListView click listener
    @Override
    public void onClick(WearableListView.ViewHolder v) {
        Integer tag = (Integer) v.itemView.getTag();

        switch (tag) {

            case 0:
                //Toast.makeText(this, "sehr sehr leicht", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ConfirmationActivity.class);
                intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE,
                        ConfirmationActivity.SUCCESS_ANIMATION);
                intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE,
                        getString(R.string.data_exported));
                startActivity(intent);

                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                break;
            case 1:
                Toast.makeText(this, "sehr leicht", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "etwas anstrengend", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "etwas anstrengend!", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "anstrengend!", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(this, "sehr schwer!", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Toast.makeText(this, "sehr sehr schwer!", Toast.LENGTH_SHORT).show();
                break;

        }
    }


    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    public void onTopEmptyRegionClick(){
        Toast.makeText(this, "Wie fandest du das Training? :)", Toast.LENGTH_SHORT).show();
    }

    private static final class Adapter extends WearableListView.Adapter {
        private String[] mDataset;
        private final Context mContext;
        private final LayoutInflater mInflater;

        // Provide a suitable constructor (depends on the kind of dataset)
        public Adapter(Context context, String[] dataset) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
            mDataset = dataset;
        }

        // Provide a reference to the type of views you're using
        public static class ItemViewHolder extends WearableListView.ViewHolder {
            private TextView textView;

            public ItemViewHolder(View itemView) {
                super(itemView);
                // find the text view within the custom item's layout
                textView = (TextView) itemView.findViewById(R.id.name);
            }
        }

        // Create new views for list items
        // (invoked by the WearableListView's layout manager)
        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
            // Inflate our custom layout for list items
            return new ItemViewHolder(mInflater.inflate(R.layout.chooseactivity_item_layout, null));
        }

        // Replace the contents of a list item
        // Instead of creating new views, the list tries to recycle existing ones
        // (invoked by the WearableListView's layout manager)
        @Override
        public void onBindViewHolder(WearableListView.ViewHolder holder,
                                     int position) {
            // retrieve the text view
            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            TextView view = itemHolder.textView;
            // replace text contents
            view.setText(mDataset[position]);
            // replace list item's metadata
            holder.itemView.setTag(position);
        }

        // Return the size of your dataset
        // (invoked by the WearableListView's layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}
