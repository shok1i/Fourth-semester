package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivitySecondBinding;


public class SecondActivity extends AppCompatActivity {
   private ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.solution.setText(getIntent().getStringExtra("SOLUTION"));
        binding.solutionBase.setText(getIntent().getStringExtra("SOLUTION_BASE"));

        int from = Integer.parseInt(binding.solutionBase.getText().toString());

        binding.res2.setText(converter(binding.solution.getText().toString(), from, 2));
        binding.res8.setText(converter(binding.solution.getText().toString(), from, 8));
        binding.res10.setText(converter(binding.solution.getText().toString(), from, 10));
        binding.res16.setText(converter(binding.solution.getText().toString(), from, 16));
    }

    public void back (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    String converter(String number, int input_base, int out_base) {
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