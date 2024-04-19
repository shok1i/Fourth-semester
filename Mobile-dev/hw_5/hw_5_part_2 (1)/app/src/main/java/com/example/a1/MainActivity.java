package com.example.a1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.a1.databinding.ActivityMainBinding;
import com.example.a1.onBtnClick;

public class MainActivity extends AppCompatActivity implements onBtnClick {

    private ActivityMainBinding _binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());
    }

    @Override
    public void ChangeBaseOnActivity(String text) {
        _binding.solutionBase.setText(text);
    }

    @Override
    public void ChangeSolutionOnActivity(String text) {
        _binding.solutionOnActivity.append(text);
    }

    @Override
    public void ClearSolutionOnActivity(String text) {
        _binding.solutionOnActivity.setText("");
    }

    @Override
    public void TransportResultToActivity(String text) {
        int from = Integer.parseInt(_binding.solutionBase.getText().toString());
        _binding.res2.setText(converter(_binding.solutionOnActivity.getText().toString(),  from, 2 ));
        _binding.res8.setText(converter(_binding.solutionOnActivity.getText().toString(),  from, 8 ));
        _binding.res10.setText(converter(_binding.solutionOnActivity.getText().toString(), from, 10));
        _binding.res16.setText(converter(_binding.solutionOnActivity.getText().toString(), from, 16));
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