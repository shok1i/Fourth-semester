package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.databinding.ActivitySecondBinding;
import com.google.android.material.textfield.TextInputEditText;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        binding= ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent i = new Intent(this, MusicService.class);
        binding.switchCompat.setChecked(true);
        startService(i);

        binding.switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    startService(i);
                } else {
                    stopService(i);
                }
            }
        });
    }

    public void goToThird (View view) {
        String BIO = getIntent().getStringExtra("BIO"), TEL = getIntent().getStringExtra("TEL");
        Intent intent = new Intent(this, ThirdActivity.class);
        String pointFrom = binding.fromStreet.getText().toString() + " " + binding.fromHouse.getText().toString() + " " + binding.fromFlat.getText().toString();
        String pointTo   = binding.toStreet.getText().toString() + " " + binding.toHouse.getText().toString() + " " + binding.toFlat.getText().toString();
        if (!pointTo.equals("  ") | !pointFrom.equals("  ")) {
            intent.putExtra("ADDRESS", "From: " + pointFrom + "\nTo: " + pointTo);
        }
        intent.putExtra("BIO", BIO);
        intent.putExtra("TEL", TEL);

        stopService(new Intent(this, MusicService.class));
        startActivity(intent);
    }


}