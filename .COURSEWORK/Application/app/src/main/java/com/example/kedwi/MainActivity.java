package com.example.kedwi;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kedwi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences _sharedPreference;
    private ActivityMainBinding _binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_view, CreateAccount.class, null).commit();

    }
    // TODO:
    // Сохраняем JSON
    // Если он не null то сразу переходим во второе активити
    // Если null(пустой) то проходим регистрацию и сохраняем данные
    // (в последнем фрагмете т.е. в выборе пола сгружаем в бд и сохраняем на локалке)
}