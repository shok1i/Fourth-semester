package com.example.a3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] names = { "Анна", "Максим", "Елена", "Александр", "Дарья", "Иван",
            "Ольга", "Никита", "Мария", "Денис", "София" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvMain = (ListView) findViewById(R.id.lvMain);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, R.layout.my_list_item, names);

        lvMain.setAdapter(adapter);
    }
}

