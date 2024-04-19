package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding _binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            Toast.makeText(this, "Перевод из одних систем счисления в другие", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.action_author) {
            Toast.makeText(this, "ИКБО-33-22 Шило Юрий", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void changBase (View view) {
        MaterialButton btn = (MaterialButton) view;
        String btnText = btn.getText().toString();
        _binding.solutionBase.setText(btnText);
    }

    public void addSymbol (View view) {
        MaterialButton btn = (MaterialButton) view;
        String btnText = btn.getText().toString();
        _binding.solution.append(btnText);
    }

    public void clear (View view) {
        _binding.solution.setText("");
    }

    public void getResult (View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("SOLUTION", _binding.solution.getText().toString());
        intent.putExtra("SOLUTION_BASE", _binding.solutionBase.getText().toString());
        startActivity(intent);
    }
}