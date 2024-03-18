package com.example.hw_4;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hw_4.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<Product> products = new ArrayList<Product>();
    BoxAdapter boxAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        fillData();
        boxAdapter = new BoxAdapter(this, products);

        ListView lvMain = (ListView) binding.lvMain;
        lvMain.setAdapter(boxAdapter);
    }

    void fillData () {
        for (int i = 1; i <= 20; i++)
            products.add(new Product
        ("Product " + i, i * 100, R.drawable.ic_launcher_foreground, false));
    }

    public void showResult(View view) {
        String res = "Товары в корзине:";
        for (Product p : boxAdapter.getBox())
            if (p.box) res += "\n" + p.name;
        Toast.makeText(this, res, Toast.LENGTH_LONG).show();
    }
}

