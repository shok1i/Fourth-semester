package com.example.a2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    String[] name = { "Луна", "Оливер", "Белла", "Макс", "Кира", "Томас", "Лола", "Саймон" };
    String[] breed = { "Персидская", "Сиамская", "Мейн-кун", "Британская короткошерстная",
            "Сфинкс", "Шотландская вислоухая", "Бенгальская", "Норвежская лесная" };
    int[] age = { 3, 7, 4, 9, 2, 8, 5, 1 };

    int[] colors = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colors[0] = Color.parseColor("#808080");
        colors[1] = Color.parseColor("#D3D3D3");

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        LayoutInflater ltinflater = getLayoutInflater();

        for (int i = 0; i < name.length; i++) {
            View item = ltinflater.inflate(R.layout.item, linLayout, false);
            TextView animalName = (TextView) item.findViewById(R.id.animalName);
            animalName.setText(name[i]);

            TextView animalBreed = (TextView) item.findViewById(R.id.animalBreed);
            animalBreed.setText("Порода: " + breed[i]);

            TextView animalAge = (TextView) item.findViewById(R.id.animalAge);
            animalAge.setText("Возраст: " + age[i]);

            item.getLayoutParams().width = LayoutParams.MATCH_PARENT;
            item.setBackgroundColor(colors[i % 2]);
            linLayout.addView(item);
        }
    }
}

