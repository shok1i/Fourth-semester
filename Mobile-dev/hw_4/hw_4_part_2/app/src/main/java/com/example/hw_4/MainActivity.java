package com.example.hw_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hw_4.databinding.ActivityMainBinding;
import com.example.hw_4.databinding.FooterBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ArrayList<Product> products = new ArrayList<Product>();
    CustomAdapter myAdapter;
    View footer;
    View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        fillData();
        initViews();
    }

    void initViews(){
        FooterBinding footerBinding = createFooter("Item selected: 0");
        footer = footerBinding.getRoot();
        header = getLayoutInflater().inflate(R.layout.header, null);

        myAdapter = new CustomAdapter(this, products, footerBinding.tvText);

        ListView lv = binding.lvMain;

        lv.addFooterView(footer, null, false);
        lv.addHeaderView(header, null, false);

        lv.setAdapter(myAdapter);

        footerBinding.btnChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCart();
            }
        });
    }

    void fillData(){
        Intent intent = getIntent();
        ArrayList<Product> list = new ArrayList<>(0);
        ArrayList<Integer> icons = new ArrayList<>();
        icons.add(R.drawable.item_1);
        icons.add(R.drawable.item_2);
        icons.add(R.drawable.item_3);
        icons.add(R.drawable.item_4);
        icons.add(R.drawable.item_5);
        icons.add(R.drawable.item_6);
        if (intent != null)
            list = intent.getParcelableArrayListExtra("data");
        for(int i = 1; i <= 20; i++){
            products.add(new Product("Product " + i, i, i * 1000,
                    icons.get(getRandomNumber(0, 6)), false, i));
        }
        if (list == null) return;
        for(int i = 0; i < list.size(); i++){
            Product selected = list.get(i);
            products.remove(selected.i - 1);
            products.add(selected.i - 1, new Product(selected.name, selected.id,
                    selected.price, selected.image, selected.box, selected.i));
        }
    }
    public void toCart(){
        Intent intent = new Intent(this, Cart.class);
        intent.putParcelableArrayListExtra("data", myAdapter.getBox());
        startActivity(intent);
    }

    FooterBinding createFooter(String text){
        FooterBinding footerBinding = FooterBinding.inflate(getLayoutInflater());
        footerBinding.tvText.setText(text);
        return footerBinding;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}