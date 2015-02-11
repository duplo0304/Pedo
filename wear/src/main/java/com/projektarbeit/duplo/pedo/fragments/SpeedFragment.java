package com.projektarbeit.duplo.pedo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projektarbeit.duplo.pedo.R;

public class SpeedFragment extends Fragment {

    private TextView mRawX;
    private TextView mRawY;
    private TextView mRawZ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.speed_layout, container, false);

        TextView mRawX = (TextView) view.findViewById(R.id.x_value_view);
        TextView mRawY = (TextView) view.findViewById(R.id.x_value_view);
        TextView mRawZ = (TextView) view.findViewById(R.id.x_value_view);


        return view;

    }
}
