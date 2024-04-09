package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity /* POINT 1.2 */ implements SecondFragment.onSomeEventListener {
    // POINT 1.2
    @Override
    public void someEvent(String s) {
        Fragment frag1 = getSupportFragmentManager().findFragmentById(R.id.fragment1);
        ((TextView) frag1.getView().findViewById(R.id.textView)).setText("Text from Fragment 2: " + s);
    }

    ActivityMainBinding binding;
    private static final int CONTENT_VIEW_ID = 10101010;
    SecondFragment frag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.fragment2.setId(CONTENT_VIEW_ID);
        frag2 = new SecondFragment();

        FragmentTransaction fTrans;
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(CONTENT_VIEW_ID, frag2);
        fTrans.commit();
    }

    // POINT 1.1
    public void onClick (View view) {
        Fragment frag1 = getSupportFragmentManager().findFragmentById(R.id.fragment1);
        ((TextView) frag1.getView().findViewById(R.id.textView)).setText("Access to Fragment 1 from Activity");
        ((TextView) frag2.getView().findViewById(R.id.textView)).setText("Access to Fragment 2 from Activity");
    }
}