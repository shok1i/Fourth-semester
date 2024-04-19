package com.example.a2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a2.databinding.FragmentButtonsBinding;
import com.google.android.material.button.MaterialButton;

public class Buttons extends Fragment {
    private FragmentButtonsBinding _binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _binding = FragmentButtonsBinding.inflate(inflater, container, false);
        View.OnClickListener changBase = new View.OnClickListener() {
            public void onClick(View v) {
                Button btn = (Button) v;
                String btnText = btn.getText().toString();
                _binding.solutionBase.setText(btnText);
            }
        };

        View.OnClickListener addSymbol = new View.OnClickListener() {
            public void onClick(View v) {
                Button btn = (Button) v;
                String btnText = btn.getText().toString();
                _binding.solution.append(btnText);
            }
        };

        View.OnClickListener clear = new View.OnClickListener() {
            public void onClick(View v) {
                _binding.solution.setText("");
            }
        };

        View.OnClickListener getResult = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("my", "Base => " + _binding.solutionBase.getText().toString() + " Solution => " +  _binding.solution.getText().toString());
                SentData(_binding.solutionBase.getText().toString(), _binding.solution.getText().toString());
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

    private void SentData (String base, String solution) {
        Bundle bundle = new Bundle();
        bundle.putString("BASE", base);
        bundle.putString("SOLUTION", solution);

        Result SecondFragment = new Result();
        SecondFragment.setArguments(bundle);

        assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, SecondFragment).commit();
    }

}