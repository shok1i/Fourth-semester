package com.example.hw_3_part_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.Switch);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//          Не явный вызов
                Intent intent = new Intent("android.intent.action.SecondActivity");
                startActivity(intent);
            }
        });

        toastShow();
    }

    private void toastShow() {
        Toast toast = Toast.makeText(this, "Юрий Сергеевич", Toast.LENGTH_LONG);

        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0,0);
        toast.show();
    }
}