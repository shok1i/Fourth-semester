package com.example.a2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2.databinding.FragmentBlankBinding;
import com.google.android.material.button.MaterialButton;

public class BlankFragment extends Fragment {
    FragmentBlankBinding binding;

    public BlankFragment () {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding = FragmentBlankBinding.inflate(getLayoutInflater());
        View temp = binding.getRoot();
        super.onViewCreated(temp, savedInstanceState);

    }

    public void changBase (View view) {
        MaterialButton btn = (MaterialButton) view;
        String btnText = btn.getText().toString();
        binding.solutionBase.setText(btnText);
    }

    public void addSymbol (View view) {
        MaterialButton btn = (MaterialButton) view;
        String btnText = btn.getText().toString();
        binding.solution.append(btnText);
    }

    public void clear (View view) {
        binding.solution.setText("");
    }

    public void getResult (View view) {
        int from = Integer.parseInt(binding.solutionBase.getText().toString());
        binding.res2.setText(converter(binding.solution.toString(),  from, 2 ));
        binding.res8.setText(converter(binding.solution.toString(),  from, 8 ));
        binding.res10.setText(converter(binding.solution.toString(), from, 10));
        binding.res16.setText(converter(binding.solution.toString(), from, 16));
    }

    private String converter(String number, int input_base, int out_base) {
        String[] parts = number.split("\\.");
        StringBuilder integer_part = new StringBuilder(parts[0]);

        StringBuilder temp = new StringBuilder();
        String dictionary = "0123456789ABCDEF";

        int first_half = 0;
        integer_part.reverse();
        for (int i = 0; i < integer_part.length(); i++) {
            int j = 0;
            while (integer_part.charAt(i) != dictionary.charAt(j)) j++;

            first_half += (int) (j * Math.pow(input_base, i));
        }

        do {
            temp.append(dictionary.charAt(first_half % out_base));

            first_half = (int)(first_half / out_base);
        } while ((first_half / out_base) != 0);
        if (first_half != 0) temp.append(first_half);

        String integer_result = temp.reverse().toString();

        try {
            StringBuilder fractional_part = new StringBuilder(parts[1]);

            double second_part = 0;
            for (int i = 0; i < fractional_part.length(); i++) {
                int j = 0;
                while (fractional_part.charAt(i) != dictionary.charAt(j)) j++;

                second_part += j * Math.pow(input_base,  (i + 1) * -1 );
            }


            int digit = 0;
            temp = new StringBuilder();
            do {
                temp.append(dictionary.charAt((int)(second_part * out_base)));
                second_part = (second_part * out_base) % 1;
                digit++;
            } while (second_part * out_base != 0 || digit == 10);

            String fractional_result = "." + temp.toString();

            String FinalResult = integer_result + fractional_result;
            return FinalResult;
        }
        catch (ArrayIndexOutOfBoundsException E) {  }

        return integer_result;
    }
}