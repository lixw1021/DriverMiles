package com.xianwei.drivermiles;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThirdFragment extends Fragment {

    @BindView(R.id.layout_recyclerView)
    LinearLayout layoutRecyclerView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.layout_third_fragment)
    LinearLayout layoutParent;
    @BindView(R.id.layout_infor)
    LinearLayout layoutInfor;
    @BindView(R.id.text_route)
    TextView routeTV;

    private CardAdapter cardAdapter;
    private List<RecyclerviewItemModel> listData;

    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        ButterKnife.bind(this, view);
        initialRecyclerview();
        return view;
    }

    private void initialRecyclerview() {
        initialData();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new CardAdapter(getContext(), listData));
    }

    private void initialData() {
        listData = new ArrayList<>();
        listData.add(new RecyclerviewItemModel(R.drawable.ic_flight_takeoff_black_24dp, "name", "sub"));
        listData.add(new RecyclerviewItemModel(R.drawable.ic_flight_takeoff_black_24dp, "name", "sub"));
        listData.add(new RecyclerviewItemModel(R.drawable.ic_flight_takeoff_black_24dp, "name", "sub"));
        listData.add(new RecyclerviewItemModel(R.drawable.ic_flight_takeoff_black_24dp, "name", "sub"));
        listData.add(new RecyclerviewItemModel(R.drawable.ic_flight_takeoff_black_24dp, "name", "sub"));
    }

    @OnClick(R.id.layout_third_fragment)
    void onTouch() {
        if (routeTV.getVisibility() == View.VISIBLE) {
            hideInforLayout();
            showList();
            hideRouteTv();
        } else {
            showInforLayout();
            hideList();
            showRouteTv();
        }
    }

    private void showInforLayout() {
        layoutInfor.setVisibility(View.VISIBLE);
        layoutInfor.animate()
                .alpha(1.0f).
                setDuration(300).
                start();
    }

    private void hideInforLayout() {
        layoutInfor.setVisibility(View.INVISIBLE);
        layoutInfor.animate()
                .alpha(0.0f).
                setDuration(300).
                start();
    }

    private void showList() {
        layoutRecyclerView.setVisibility(View.VISIBLE);
        layoutRecyclerView.animate()
                .alpha(1.0f).
                setDuration(300)
                .start();
    }

    private void hideList() {
        layoutRecyclerView.setVisibility(View.INVISIBLE);
        layoutRecyclerView.animate()
                .alpha(0.0f).
                setDuration(300)
                .start();
    }

    private void showRouteTv() {
        routeTV.setVisibility(View.VISIBLE);
        routeTV.animate()
                .alpha(1.0f)
                .setDuration(300)
                .start();
    }

    private void hideRouteTv() {
        routeTV.setVisibility(View.INVISIBLE);
        routeTV.animate()
                .alpha(0f)
                .setDuration(300)
                .start();
    }
}
