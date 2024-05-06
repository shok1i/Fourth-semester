package com.shokii.kedwi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.shokii.kedwi.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding _binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_view, new HomePage()).commit();
    }
}