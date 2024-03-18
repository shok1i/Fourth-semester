package com.example.a7;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> products = new ArrayList<Product>();
    BoxAdapter boxAdapter;
    View header1, header2, footer1, footer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header1 = createHeader("header 1");
        header2 = createHeader("header 2");
        footer1 = createFooter("footer 1");
        footer2 = createFooter("footer 2");

        fillData();
        boxAdapter = new BoxAdapter(this, products);

        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.addHeaderView(header1);
        lvMain.addHeaderView(header2, "some text for header 2", false);
        lvMain.addFooterView(footer1);
        lvMain.addFooterView(footer2, "some text for footer 2", false);
        lvMain.setAdapter(boxAdapter);
    }
    View createHeader(String text){
        View v = getLayoutInflater().inflate(R.layout.header, null);
        ((TextView)v.findViewById(R.id.tvText)).setText(text);
        return v;
    }
    View createFooter(String text){
        View v = getLayoutInflater().inflate(R.layout.footer, null);
        ((TextView)v.findViewById(R.id.tvText)).setText(text);
        return v;
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

