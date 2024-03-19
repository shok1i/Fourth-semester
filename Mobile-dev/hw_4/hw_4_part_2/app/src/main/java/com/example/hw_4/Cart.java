package com.example.hw_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hw_4.databinding.ActivityCartBinding;
import com.example.hw_4.databinding.CartFooterBinding;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {


    private ActivityCartBinding binding;
    CustomAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initViews();
    }

    void initViews(){
        Intent intent = getIntent();
        ArrayList<Product> list = intent.getParcelableArrayListExtra("data");
        myAdapter = new CustomAdapter(this, list, null);
        //View footer = getLayoutInflater().inflate(R.layout.cart_footer, null);
        CartFooterBinding footerBinding = CartFooterBinding.inflate(getLayoutInflater());
        View footer = footerBinding.getRoot();

        ListView lv = binding.lvMain;
        lv.addFooterView(footer, null, false);

        lv.setAdapter(myAdapter);

        int price = 0;
        for(Product p : list)
            price += p.price;
        footerBinding.tvText.setText("Final price: " + price + " rub");

        footerBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, MainActivity.class);
                intent.putParcelableArrayListExtra("data", list);
                startActivity(intent);
            }
        });
    }
}