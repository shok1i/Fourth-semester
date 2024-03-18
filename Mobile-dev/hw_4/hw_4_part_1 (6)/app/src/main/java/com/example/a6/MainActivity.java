package com.example.a6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> products = new ArrayList<Product>();
    BoxAdapter boxAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillData();
        boxAdapter = new BoxAdapter(this, products);

        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setAdapter(boxAdapter);
    }

    void fillData () {
        for (int i = 1; i <= 20; i++)
            products.add(new Product
        ("Product " + i, i * 1000, R.drawable.ic_launcher_foreground, false));
    }

    public void showResult(View view) {
        String res = "Товары в корзине:";
        for (Product p : boxAdapter.getBox())
            if (p.box) res += "\n" + p.name;
        Toast.makeText(this, res, Toast.LENGTH_LONG).show();
    }
}

