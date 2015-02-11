package com.projektarbeit.duplo.pedo.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.ConfirmationActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.projektarbeit.duplo.pedo.MainActivity;
import com.projektarbeit.duplo.pedo.R;

/**
 * Created by SL_duplo on 08.02.15.
 */

public class ConnectSensorFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.connectsensor_layout, container, false);
        Button button = (Button) view.findViewById(R.id.btn_connectsensor);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ConfirmationActivity.class);
                intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE,
                        ConfirmationActivity.FAILURE_ANIMATION);
                intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE,
                        getString(R.string.device_not_connected));
                startActivity(intent);
            }
        });
        return view;
    }
}
