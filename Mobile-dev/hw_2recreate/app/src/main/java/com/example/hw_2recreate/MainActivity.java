package com.example.hw_2recreate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_2, result_8, result_10, result_16, solution_base, solution;
    MaterialButton  from_2, from_8, from_10, from_16;
    MaterialButton b_0, b_1, b_2, b_3, b_4, b_5, b_6, b_7, b_8, b_9, b_A, b_B, b_C, b_D, b_E, b_F;
    MaterialButton b_calculate, b_point, clear;

    int from = 10, to = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        solution = findViewById(R.id.solution);
        solution_base = findViewById(R.id.solution_base);

        result_2 = findViewById(R.id.result_base_2);
        result_8 = findViewById(R.id.result_base_8);
        result_10 = findViewById(R.id.result_base_10);
        result_16 = findViewById(R.id.result_base_16);


        LinkingButtons(from_2, R.id.button_from_2);
        LinkingButtons(from_8, R.id.button_from_8);
        LinkingButtons(from_10, R.id.button_from_10);
        LinkingButtons(from_16, R.id.button_from_16);


        LinkingButtons(clear, R.id.clear_text);
        LinkingButtons(b_point, R.id.NUMBER_point);
        LinkingButtons(b_calculate, R.id.NUMBER_res);

        LinkingButtons(b_0, R.id.NUMBER_0);
        LinkingButtons(b_1, R.id.NUMBER_1);
        LinkingButtons(b_2, R.id.NUMBER_2);
        LinkingButtons(b_3, R.id.NUMBER_3);
        LinkingButtons(b_4, R.id.NUMBER_4);
        LinkingButtons(b_5, R.id.NUMBER_5);
        LinkingButtons(b_6, R.id.NUMBER_6);
        LinkingButtons(b_7, R.id.NUMBER_7);
        LinkingButtons(b_8, R.id.NUMBER_8);
        LinkingButtons(b_9, R.id.NUMBER_9);
        LinkingButtons(b_A, R.id.NUMBER_A);
        LinkingButtons(b_B, R.id.NUMBER_B);
        LinkingButtons(b_C, R.id.NUMBER_C);
        LinkingButtons(b_D, R.id.NUMBER_D);
        LinkingButtons(b_E, R.id.NUMBER_E);
        LinkingButtons(b_F, R.id.NUMBER_F);
    }

    void LinkingButtons (MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        if (solution.getText().toString().equals("0")) {
            solution.setText("");
        }

        if (solution.getText().toString().equals("Ошибка")) {
            solution.setText("");
        }

//        FROM
        if (buttonText.equals("F2")){
            from = 2;
            solution_base.setText("2");
            return;
        }
        if (buttonText.equals("F8")){
            from = 8;
            solution_base.setText("8");
            return;
        }
        if (buttonText.equals("F10")){
            from = 10;
            solution_base.setText("10");
            return;
        }
        if (buttonText.equals("F16")){
            from = 16;
            solution_base.setText("16");
            return;
        }

        if (buttonText.equals("AC")) {
            solution.setText("0");
            solution.setText("0");
            result_2.setText("0");
            result_8.setText("0");
            result_10.setText("0");
            result_16.setText("0");

            return;
        }
        if (buttonText.equals("=")) {
            if (stringCheck( solution.getText().toString(), from )) {
                result_2.setText(converter(solution.getText().toString(), from, 2));
                result_8.setText(converter(solution.getText().toString(), from, 8));
                result_10.setText(converter(solution.getText().toString(), from, 10));
                result_16.setText(converter(solution.getText().toString(), from, 16));
            }


            return;
        }

        solution.append(buttonText);

    }

    String converter(String number, int input_base, int out_base) {
        String[] parts = number.split("\\.");
        StringBuilder integer_part = new StringBuilder(parts[0]);

        StringBuilder temp = new StringBuilder();
        String dictionary = "0123456789ABCDEF";

        // ====> Целая часть
        // Перевод из любой сс в 10
        int first_half = 0;
        integer_part.reverse();
        for (int i = 0; i < integer_part.length(); i++) {
            int j = 0;
            while (integer_part.charAt(i) != dictionary.charAt(j)) j++;

            first_half += (int) (j * Math.pow(input_base, i));
        }

        // Перевод в любую сс из 10
        do {
            temp.append(dictionary.charAt(first_half % out_base));

            first_half = (int)(first_half / out_base);
        } while ((first_half / out_base) != 0);
        if (first_half != 0) temp.append(first_half);

        // ====> Результат целой части
        String integer_result = temp.reverse().toString();

        // ====> Дробная часть
        try {
            StringBuilder fractional_part = new StringBuilder(parts[1]);

            // Перевод из любой сс в 10
            double second_part = 0;
            for (int i = 0; i < fractional_part.length(); i++) {
                int j = 0;
                while (fractional_part.charAt(i) != dictionary.charAt(j)) j++;

                second_part += j * Math.pow(input_base,  (i + 1) * -1 );
            }


            int digit = 0;
            temp = new StringBuilder();
            // Перевод в любую сс из 10
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


    boolean stringCheck (String number, int input_base) {
        String dictionary = "0123456789ABCDEF";

        int temp = 0;
        for (int i = 0; i < number.length(); i++){
            int j = 0;
            if (number.charAt(i) == '.') {
                temp++;
                continue;
            }
            while (number.charAt(i) != dictionary.charAt(j)) j++;


            if (j >= input_base) {
                solution.setText("Ошибка");
                result_2.setText("Err");
                result_8.setText("Err");
                result_10.setText("Err");
                result_16.setText("Err");
                return false;
            }
        }
        if (temp > 1) {
            solution.setText("Ошибка");
            result_2.setText("Err");
            result_8.setText("Err");
            result_10.setText("Err");
            result_16.setText("Err");
            return false;
        }

        return true;
    }
}