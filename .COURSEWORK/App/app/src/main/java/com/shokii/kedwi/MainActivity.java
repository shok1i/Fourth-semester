package com.shokii.kedwi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.shokii.kedwi.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding _binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(_binding.getRoot());

        _binding.bottomNav.setOnNavigationItemSelectedListener(this);
        _binding.bottomNav.setSelectedItemId(R.id.home);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, new HomePage()).commit();
    }


    @Override
    public boolean
    onNavigationItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.home:
                Log.d("my", "Home clk");
                return true;

            case R.id.calendar:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, new HomePage()).commit();
                Log.d("my", "Calendar clk");
                return true;

            case R.id.bookmarks:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, new HomePage()).commit();
                Log.d("my", "Bookmarks clk");
                return true;

            case R.id.user:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, new HomePage()).commit();
                Log.d("my", "User clk");
                return true;
        }
        return false;
    }
}