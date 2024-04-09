package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    // POINT 1.2
    public interface onSomeEventListener {
        public void someEvent(String s);
    }
    onSomeEventListener someEventListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            someEventListener = (onSomeEventListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement on-SomeEventListener");
        }
    }
    final String LOG_TAG = "myLog";
    FragmentSecondBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d (LOG_TAG, "Button click in Fragment2");
            }
        });
        // POINT 1.2
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d (LOG_TAG, "Button click in Fragment2");
                someEventListener.someEvent("Test text to Fragment1");
            }
        });
        return view;
    }
}