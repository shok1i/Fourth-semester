package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void changBase (View view) {
        MaterialButton btn = (MaterialButton) view;
        String btnText = btn.getText().toString();
        binding.solutionBase.setText(btnText);
    }

    public void addSymbol (View view) {
        MaterialButton btn = (MaterialButton) view;
        String btnText = btn.getText().toString();
        binding.solution.append(btnText);
    }

    public void clear (View view) {
        binding.solution.setText("");
    }

    public void getResult (View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("SOLUTION", binding.solution.getText().toString());
        intent.putExtra("SOLUTION_BASE", binding.solutionBase.getText().toString());
        startActivity(intent);
    }

}