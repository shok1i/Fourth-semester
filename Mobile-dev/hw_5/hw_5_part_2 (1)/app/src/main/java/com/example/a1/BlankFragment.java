package com.example.a1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a1.databinding.FragmentBlankBinding;

public class BlankFragment extends Fragment {
    private onBtnClick _listener;
    private FragmentBlankBinding _binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            _listener = (onBtnClick) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " должен реализовывать onBtnClick");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _binding = FragmentBlankBinding.inflate(inflater, container, false);

        View.OnClickListener changBase = new View.OnClickListener() {
            public void onClick(View v) {
                Button btn = (Button) v;
                _listener.ChangeBaseOnActivity(btn.getText().toString());
            }
        };

        View.OnClickListener addSymbol = new View.OnClickListener() {
            public void onClick(View v) {
                Button btn = (Button) v;
                _listener.ChangeSolutionOnActivity(btn.getText().toString());
            }
        };

        View.OnClickListener clear = new View.OnClickListener() {
            public void onClick(View v) {
                Button btn = (Button) v;
                _listener.ClearSolutionOnActivity(btn.getText().toString());
            }
        };

        View.OnClickListener getResult = new View.OnClickListener() {
            public void onClick(View v) {
                Button btn = (Button) v;
                _listener.TransportResultToActivity(btn.getText().toString());
            }
        };

        _binding.changeBase2.setOnClickListener(changBase);
        _binding.changeBase8.setOnClickListener(changBase);
        _binding.changeBase10.setOnClickListener(changBase);
        _binding.changeBase16.setOnClickListener(changBase);

        _binding.addSymbolPoint.setOnClickListener(addSymbol);
        _binding.addSymbol0.setOnClickListener(addSymbol);
        _binding.addSymbol1.setOnClickListener(addSymbol);
        _binding.addSymbol2.setOnClickListener(addSymbol);
        _binding.addSymbol3.setOnClickListener(addSymbol);
        _binding.addSymbol4.setOnClickListener(addSymbol);
        _binding.addSymbol5.setOnClickListener(addSymbol);
        _binding.addSymbol6.setOnClickListener(addSymbol);
        _binding.addSymbol7.setOnClickListener(addSymbol);
        _binding.addSymbol8.setOnClickListener(addSymbol);
        _binding.addSymbol9.setOnClickListener(addSymbol);
        _binding.addSymbolA.setOnClickListener(addSymbol);
        _binding.addSymbolB.setOnClickListener(addSymbol);
        _binding.addSymbolC.setOnClickListener(addSymbol);
        _binding.addSymbolD.setOnClickListener(addSymbol);
        _binding.addSymbolE.setOnClickListener(addSymbol);
        _binding.addSymbolF.setOnClickListener(addSymbol);

        _binding.result.setOnClickListener(getResult);

        _binding.clearText.setOnClickListener(clear);


        return _binding.getRoot();
    }
}