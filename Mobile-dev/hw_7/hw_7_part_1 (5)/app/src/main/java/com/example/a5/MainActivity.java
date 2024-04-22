package com.example.a5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a5.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Removable{
    private ActivityMainBinding _binding;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());
        ArrayList<String> phones = new ArrayList<>();
        phones.add("Apple iPhone 14");
        phones.add("Nokia XR20");
        phones.add("Pixel 3A");
        phones.add("Xiaomi 14");
        adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, phones
        );
        _binding.phonesList.setAdapter(adapter);
        _binding.phonesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedPhone = adapter.getItem(position);
                CustomDialogFragment dialog = new CustomDialogFragment();
                Bundle args = new Bundle();
                args.putString("phone", selectedPhone);
                dialog.setArguments(args);
                dialog.show(getSupportFragmentManager(), "custom");
            }
        });
    }

    @Override
    public void remove(String name) {
        adapter.remove(name);
    }
}