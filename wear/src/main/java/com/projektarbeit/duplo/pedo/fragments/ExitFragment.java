package com.projektarbeit.duplo.pedo.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.projektarbeit.duplo.pedo.BorgScale;
import com.projektarbeit.duplo.pedo.R;



public class ExitFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exit_layout, container, false);
        Button button = (Button) view.findViewById(R.id.btn_exit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent borg = new Intent (getActivity(), BorgScale.class);
                startActivity(borg);
                getActivity().finish();

            }
        });
        return view;

    }
}
