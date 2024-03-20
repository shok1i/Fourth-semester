package com.example.a1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.a1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Log.d(LOG_TAG, "MainActivity onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "MainActivity onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "MainActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "MainActivity onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "MainActivity onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MainActivity onDestroy");
    }
}