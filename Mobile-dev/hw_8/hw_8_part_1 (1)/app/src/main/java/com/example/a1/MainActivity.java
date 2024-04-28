package com.example.a1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private String _name = "???";
    final static String KEY = "STRING_KEY";
    private ActivityMainBinding _binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(KEY, _name);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        _name = savedInstanceState.getString(KEY);
        _binding.nameBox.setText(_name);
    }

    public void saveName (View v) { _name = _binding.nameBox.getText().toString();
        Log.d("myLog", "SAVE");
    }
    public void getName (View v) { _binding.nameView.setText(_name);
        Log.d("myLog", "GET");
    }
}