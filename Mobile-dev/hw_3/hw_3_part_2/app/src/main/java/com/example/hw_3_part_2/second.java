package com.example.hw_3_part_2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hw_3_part_2.databinding.ActivitySecondBinding;

public class second extends AppCompatActivity {

    private ActivitySecondBinding binding;


    ActivityResultLauncher<Intent> startSecondActivityForResult = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>(){
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        if (intent != null) {
                            // Bio Information
                            binding.bioField.setText(intent.getStringExtra ("BIO"));
                            binding.telField.setText(intent.getStringExtra ("TEL"));
                            binding.destField.setText(intent.getStringExtra("DES"));

                        }
                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        binding= ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}