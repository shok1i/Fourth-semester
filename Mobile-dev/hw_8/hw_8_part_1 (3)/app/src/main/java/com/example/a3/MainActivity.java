package com.example.a3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_FILE = "Account", PREF_NAME = "Name";
    private SharedPreferences _settings;
    private ActivityMainBinding _binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());

        _settings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);

        String name = _settings.getString(PREF_NAME, "не определенно");
        _binding.dataView.setText(name);
    }
    @Override
    protected void onPause() {
        super.onPause();

        String name = _binding.nameBox.getText().toString();

        SharedPreferences.Editor prefEditor = _settings.edit();
        prefEditor.putString(PREF_NAME, name);
        prefEditor.apply();
    }
        public void saveName (View v) {
        String name = _binding.nameBox.getText().toString();

        SharedPreferences.Editor prefEditor = _settings.edit();
        prefEditor.putString(PREF_NAME, name);
        prefEditor.apply();
    }
    public void getName (View v) {
        String name = _settings.getString(PREF_NAME, "не определенно");
        _binding.dataView.setText(name);
    }

}