package com.example.a2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    final static String KEY = "STRING_KEY";
    private ActivityMainBinding _binding;
    private User _user = new User("undefined", 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(KEY, _user);
        super.onSaveInstanceState(outState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        _user = (User) savedInstanceState.getSerializable(KEY);
        _binding.dataView.setText("Name: " + _user.get_name() + " Age: " + _user.get_age());
    }

    public void saveName (View v) {
        String name = _binding.nameBox.getText().toString();
        int age = 0;
        try {
            age = Integer.parseInt(_binding.yearBox.getText().toString());
        }
        catch (NumberFormatException ignored) {}
        _user = new User(name, age);
    }

    @SuppressLint("SetTextI18n")
    public void getName (View v) {
        _binding.dataView.setText("Name: " + _user.get_name() + " Age: " + _user.get_age());
    }
}