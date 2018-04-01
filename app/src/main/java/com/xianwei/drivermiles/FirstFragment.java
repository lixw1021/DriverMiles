package com.xianwei.drivermiles;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirstFragment extends Fragment implements HostActivity.LocationUpdateListener {

    @BindView(R.id.tv_latitude)
    TextView latTv;
    @BindView(R.id.tv_longitude)
    TextView logTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            latTv.setText(savedInstanceState.getString("lat"));
            logTv.setText(savedInstanceState.getString("log"));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("lat", latTv.getText().toString());
        outState.putString("log", logTv.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void updateLocation(double lat, double log) {
        latTv.setText(String.valueOf(lat));
        logTv.setText(String.valueOf(log));
    }
}
