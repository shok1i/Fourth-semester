package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivitySecondBinding;
import com.example.myapplication.databinding.ActivityThirdBinding;

public class ThirdActivity extends AppCompatActivity {
    private ActivityThirdBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        binding= ActivityThirdBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.userBio.setText(getIntent().getStringExtra("BIO"));
        binding.userTelephone.setText(getIntent().getStringExtra("TEL"));
        binding.userAddress.setText(getIntent().getStringExtra("ADDRESS"));

        if (binding.userAddress.getText() != "")
            binding.btnCall.setVisibility(View.VISIBLE);
    }

    public void goToSecond (View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("BIO", binding.userBio.getText().toString());
        intent.putExtra("TEL", binding.userTelephone.getText().toString());
        startActivity(intent);
    }

    public void createToast (View view) {
        Toast toast = Toast.makeText(this, "Wait for Taxi. Good lack!", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0,0);
        toast.show();
    }
}