package com.projektarbeit.duplo.pedo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.projektarbeit.duplo.pedo.R;


public class ExportFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*
        View view = inflater.inflate(R.layout.export_layout, container, false);
        Button button = (Button) view.findViewById(R.id.btn_export);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ConfirmationActivity.class);
                intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE,
                        ConfirmationActivity.SUCCESS_ANIMATION);
                intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE,
                        getString(R.string.data_exported));
                startActivity(intent);
            }
        });
        */

        View view = inflater.inflate(R.layout.export_layout, container, false);
        Button button = (Button) view.findViewById(R.id.btn_export);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent start = new Intent(getActivity(), CountdownActivity.class);
                //Intent start = new Intent(getActivity(), Sensors.class);
                // startActivity(start);
            }
        });
        return view;
    }
}
