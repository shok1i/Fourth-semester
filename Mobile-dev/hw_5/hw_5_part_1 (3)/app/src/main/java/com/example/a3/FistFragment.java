package com.example.a3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a3.databinding.FragmentFistBinding;

public class FistFragment extends Fragment {
    FragmentFistBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFistBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}