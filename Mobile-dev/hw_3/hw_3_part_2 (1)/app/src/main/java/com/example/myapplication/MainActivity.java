package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;


    private static final String SHARES_PREF_NAME = "pref";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_TELEPHONE = "tell";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding= ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        sharedPreferences = getSharedPreferences(SHARES_PREF_NAME, MODE_PRIVATE);
        String temp = sharedPreferences.getString(KEY_NAME, null);
        if (temp != null) {
            binding.userName.setText(sharedPreferences.getString(KEY_NAME, null));
            binding.userSurname.setText(sharedPreferences.getString(KEY_SURNAME, null));
            binding.userTelephone.setText(sharedPreferences.getString(KEY_TELEPHONE, null));
        }
    }

    public void goToThird (View view) {
        Intent intent = new Intent(this, ThirdActivity.class);
        String bio = binding.userName.getText().toString() + " " + binding.userSurname.getText().toString();
        intent.putExtra("BIO", "BIO: " +  bio);
        intent.putExtra("TEL", "TEL: " +  binding.userTelephone.getText().toString());

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, binding.userName.getText().toString());
        editor.putString(KEY_SURNAME, binding.userSurname.getText().toString());
        editor.putString(KEY_TELEPHONE, binding.userTelephone.getText().toString());
        editor.apply();

        startActivity(intent);
    }
}