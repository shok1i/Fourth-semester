package com.example.a5;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a5.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding _binding;
    private ArrayAdapter<Cat> _adapter;
    private List<Cat> _cats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());
        _cats = new ArrayList<>();
        _adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, _cats);
        _binding.list.setAdapter(_adapter);
    }
    public void add(View view) {
        String name = _binding.nameText.getText().toString();
        int age = Integer.parseInt(_binding.ageText.getText().toString());
        Cat cat = new Cat(name, age);
        _cats.add(cat);
        _adapter.notifyDataSetChanged();
    }
    public void save(View view) {
        boolean result = myJSON.exportToJSON(this, _cats);
        if (result)
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_SHORT).show();

    }
    public void open(View view) {
        _cats = myJSON.importFromJSON(this);
        if (_cats != null) {
            _adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, _cats);
            _binding.list.setAdapter(_adapter);
            Toast.makeText(this, "Данные востановлены", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Не удалось открыть данные", Toast.LENGTH_SHORT).show();
    }
}