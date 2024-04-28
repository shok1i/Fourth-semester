package com.example.a4;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a4.databinding.ActivityMainBinding;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding _binding;
    private final static String FILE_NAME = "proba.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());
    }

    public void saveText (View view) {
        FileOutputStream fos = null;
        try {
            String text = _binding.editor.getText().toString();
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            throw new RuntimeException(ex);
        } finally {
            try {
                if (fos != null) fos.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void openText(View view) {
        FileInputStream fin = null;
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            _binding.text.setText(text);
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null) fin.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}