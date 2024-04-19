package com.example.a5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class BlankFragment extends Fragment {
    private int pageNumber;
    public BlankFragment () {}
    public static BlankFragment newInstance(int page) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 1;
    }
    @Override
    public View onCreateView (
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View result = inflater.inflate(R.layout.fragment_blank, container, false);
        TextView pageHeader  = result.findViewById(R.id.displayText);
        String header = "Фрагмент " + (pageNumber + 1);
        pageHeader.setText(header);
        return result;
    }
}