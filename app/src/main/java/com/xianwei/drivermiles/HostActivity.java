package com.xianwei.drivermiles;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HostActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener {

    @BindView(R.id.btn_navigation)
    AHBottomNavigation bottomNavigation;
    @BindView(R.id.pager)
    NoSwipeViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        ButterKnife.bind(this);

        initialTabBar();

        fetchLocationEveryOneMin();
    }

    private void fetchLocationEveryOneMin() {
        final Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                handler.postDelayed(this,5000);
            }
        };
        handler.post(runnable);
    }

    private void initialTabBar() {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Location", R.drawable.ic_location_on_black, R.color.colorAccent);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("PieChart", R.drawable.ic_pie_chart_black_24dp, R.color.colorAccent);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Flight", R.drawable.ic_flight_black_24dp, R.color.colorAccent);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.setOnTabSelectedListener(this);
        bottomNavigation.setCurrentItem(0);


        BottomBarAdapter bottomBarAdapter = new BottomBarAdapter(getSupportFragmentManager());
        bottomBarAdapter.addFragment(new FirstFragment());
        bottomBarAdapter.addFragment(new SecondFragment());
        bottomBarAdapter.addFragment(new ThirdFragment());

        viewPager.setAdapter(bottomBarAdapter);
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        viewPager.setCurrentItem(position);
        return true;
}
}
