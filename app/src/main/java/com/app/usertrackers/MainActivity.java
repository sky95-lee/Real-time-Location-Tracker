package com.app.usertrackers;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.app.usertrackers.service.IP2LocationService;
import com.app.usertrackers.worker.LocationWorker;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView locationView;
    private TextView ladLongView;
    private static final int updateInterval = 5000; // 5 seconds interval

    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize view
        locationView = findViewById(R.id.location_view);
        ladLongView = findViewById(R.id.lad_long_view);

        // Setup an observer for LiveData to update the UI when location changes
        LocationWorker.locationLiveData.observe(this, location -> {
            // Update the location view with new location data
            locationView.setText(location);
        });

        LocationWorker.ladLongLiveData.observe(this, ladLong -> {
            ladLongView.setText(ladLong);
        });

        // Schedule the WorkManager to run LocationWorker periodically
        PeriodicWorkRequest locationRequest = new PeriodicWorkRequest.Builder(LocationWorker.class, updateInterval, TimeUnit.MILLISECONDS)
                .build();
        WorkManager.getInstance(this).enqueue(locationRequest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Remove callbacks when the activity is destroyed to prevent memory leaks
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
}