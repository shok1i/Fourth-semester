package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {
    final String LOG_TAG = "myLog";
    FragmentFirstBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d (LOG_TAG, "Button click in Fragment1");

                // POINT 1.2
                ((Button) getActivity().findViewById(R.id.btnFind)).setText("Access from Fragment1");
            }
        });

        return view;
    }
}