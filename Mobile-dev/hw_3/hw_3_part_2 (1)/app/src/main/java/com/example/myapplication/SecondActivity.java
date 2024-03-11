package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        binding= ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void goToThird (View view) {
        String BIO = getIntent().getStringExtra("BIO"), TEL = getIntent().getStringExtra("TEL");
        Intent intent = new Intent(this, ThirdActivity.class);
        String pointFrom = binding.fromStreet.getText().toString() + binding.fromHouse.getText().toString() + " " + binding.fromFlat.getText().toString();
        String pointTo   = binding.toStreet.getText().toString() + binding.toHouse.getText().toString() + " " + binding.toFlat.getText().toString();
        intent.putExtra("ADDRESS", "From: " + pointFrom + "\nTo: " + pointTo);
        intent.putExtra("BIO", BIO);
        intent.putExtra("TEL", TEL);
        startActivity(intent);
    }


}