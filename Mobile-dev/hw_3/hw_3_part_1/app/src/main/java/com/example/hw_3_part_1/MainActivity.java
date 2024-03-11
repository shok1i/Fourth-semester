package com.example.hw_3_part_1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hw_3_part_1.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    ActivityResultLauncher<Intent> startSecondActivityForResult = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(),
             new ActivityResultCallback<ActivityResult>(){
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                Intent intent = result.getData();
                                if (intent != null) {
                                    String name = intent.getStringExtra("Name");
                                    binding.textView.setText(name);
                                }
                            }
                            else {
                                String textError = "Error";
                                binding.textView.setText(textError);
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = new Intent(this, second.class);
        binding.Switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSecondActivityForResult.launch(intent);
            }
        });
        loadText();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }

    SharedPreferences sPref;
    final String keyName = "#key";

    private void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(keyName, binding.textView.getText().toString());
        editor.apply();
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }

    private void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(keyName, "");
        binding.textView.setText(savedText);
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }
    /*
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
            }
        });

        toastShow();
    }

    private void toastShow() {
        Toast toast = Toast.makeText(this, "Юрий Сергеевич", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0,0);
        toast.show();
    }
*/
}