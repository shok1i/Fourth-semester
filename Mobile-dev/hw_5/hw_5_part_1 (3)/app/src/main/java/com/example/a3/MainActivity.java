package com.example.a3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.a3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    FragmentTransaction fTrans;
    FistFragment frag1;
    SecondFragment frag2;

    private static final int CONTENT_VIEW_ID = 10101010;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.fragmentContainer.setId(CONTENT_VIEW_ID);
        frag1 = new FistFragment();
        frag2 = new SecondFragment();

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (savedInstanceState == null) {
                    fTrans = getSupportFragmentManager().beginTransaction();
                    fTrans.add(CONTENT_VIEW_ID, frag1);
                    if (binding.swStack.isChecked())
                        fTrans.addToBackStack(null);
                    fTrans.commit();
                }
            }
        });

        binding.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fTrans = getSupportFragmentManager().beginTransaction();
                fTrans.remove(frag1);
                if (binding.swStack.isChecked())
                    fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });

        binding.replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fTrans = getSupportFragmentManager().beginTransaction();
                fTrans.replace(CONTENT_VIEW_ID, frag2);
                if (binding.swStack.isChecked())
                    fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });
    }
}