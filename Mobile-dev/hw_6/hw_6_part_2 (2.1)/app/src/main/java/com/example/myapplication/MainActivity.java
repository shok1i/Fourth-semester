package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding _binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());

        // Realisation of TabLayout
        TabLayout tabLayout = _binding.tabLayout;
        ViewPager viewPager = _binding.viewPager;

        TabsFragmentAdapter adapter = new TabsFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new TabOneFragment(), "О программе");
        adapter.addFragment(new TabTwoFragment(), "Об авторе");

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
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