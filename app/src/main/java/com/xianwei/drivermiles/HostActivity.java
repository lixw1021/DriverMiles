package com.xianwei.drivermiles;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.EventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HostActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener {

    @BindView(R.id.btn_navigation)
    AHBottomNavigation bottomNavigation;
    @BindView(R.id.pager)
    NoSwipeViewPager viewPager;
    BottomBarAdapter bottomBarAdapter;
    Fragment fragment1;

    private Location gpsLocation = null;
    private LocationManager locMan;
    private Retrofit retrofit;
    private PostService postService;
    private LocationUpdateListener locationUpdateListener;
    private FusedLocationProviderClient mFusedLocationClient;
    public static final int LOCATION_PERMISSION_CODE = 1000;
    public static final String BASE_URL = "https://hooks.slack.com/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        ButterKnife.bind(this);

        initialTabBar();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        grantLocationPermission();
        initialRetrofit();
    }


    private void grantLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Ask permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
        } else {
            fetchLocationEveryOneMin();
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // fetch data after grant location permission
                    fetchLocationEveryOneMin();

                } else {
                    Toast.makeText(this, "Please enable GPS to use service.", Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }

    private void fetchLocationEveryOneMin() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @SuppressLint("MissingPermission")
            @Override
            public void run() {
                mFusedLocationClient.getLastLocation().addOnSuccessListener(HostActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        Log.i("12345", location.getLatitude() + " " + location.getLongitude());
                        fragment1 = (FirstFragment)bottomBarAdapter.getItem(0);
                        if (fragment1 != null && fragment1.getView() != null) {
                            ((TextView)fragment1.getView().findViewById(R.id.tv_latitude)).setText(String.valueOf(location.getLatitude()));
                            ((TextView)fragment1.getView().findViewById(R.id.tv_longitude)).setText(String.valueOf(location.getLongitude()));
                            postLocationDateToSlack(location.getLatitude(), location.getLongitude());
                        }
//                        updateFragmentLocation(location.getLatitude(), location.getLongitude());
                    }
                });

                handler.postDelayed(this,60000);
            }
        };
        handler.post(runnable);
    }

    private void postLocationDateToSlack(double latitude, double longitude) {
        Call<Void> call = postService.postLocation(new DataModel("Name: xianwei" + " Latitude: " + latitude + " Longitude: " + longitude));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("12345", "post success");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("12345", "post failed");
            }
        });
    }

    private void initialRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postService = retrofit.create(PostService.class);
    }

    private void updateFragmentLocation(double latitude, double longitude) {
        Log.i("12345", latitude + " " + longitude);
        locationUpdateListener.updateLocation(latitude, longitude);
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


        bottomBarAdapter = new BottomBarAdapter(getSupportFragmentManager());
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

    public void setLocationUpdateListener(LocationUpdateListener locationUpdateListener){
        this.locationUpdateListener = locationUpdateListener;
    }

    public interface LocationUpdateListener{
        void updateLocation(double lat, double log);
    }
}
