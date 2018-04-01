package com.xianwei.drivermiles;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondFragment extends Fragment {

    @BindView(R.id.pieChart)
    PieChart pieChart;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        ButterKnife.bind(this, view);
        initialPieChart();
        return view;

    }

    private void initialPieChart() {
        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(30 / 100f, "Ecuador"));
        entries.add(new PieEntry(10 / 100f, "Brasil"));
        entries.add(new PieEntry(10 / 100f, "Argentina"));
        entries.add(new PieEntry(9 / 100f, "Peru"));
        entries.add(new PieEntry(8 / 100f, "Colombia"));
        entries.add(new PieEntry(8 / 100f, "Boilvia"));
        entries.add(new PieEntry(25 / 100f, "Others"));

        PieDataSet set = new PieDataSet(entries, "Election Results");
        set.setColors(new int[]{R.color.red, R.color.purple, R.color.darkPurple, R.color.darkBlue, R.color.lightBlue, R.color.green, R.color.yellow}, getActivity());
        PieData data = new PieData(set);
        pieChart.setData(data);


        pieChart.invalidate(); // refresh
    }
}
