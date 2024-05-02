package com.shokii.kedwi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shokii.kedwi.databinding.FragmentEnterAccountBinding;

// TODO:
//  Добавить отработку пустых полей и ошибку ввода
//  Добавить поднятие интерфейса вверх при открытие клавиатуры


// Patterns.EMAIL_ADDRESS.matcher(email).matches()
// Данный метод проверяет корректность ввода почты



public class EnterAccount extends Fragment {
    private FragmentEnterAccountBinding _binding;
    private Bundle _bundle = new Bundle();

    private FirebaseAuth _mAuth;
    private FirebaseDatabase _dataBase;
    private DatabaseReference _userRefs;

    private EnterPassword nextFragment = new EnterPassword();

    public EnterAccount() {
        super (R.layout.fragment_enter_account);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _mAuth = FirebaseAuth.getInstance();
        _dataBase = FirebaseDatabase.getInstance();
        _userRefs = _dataBase.getReference("users");


        _binding = FragmentEnterAccountBinding.inflate(getLayoutInflater());


        _binding.loginContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email = _binding.loginText.getText().toString();

                _userRefs.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean isEXIST = false;

                        for (DataSnapshot userUID :snapshot.getChildren()) {
                            String databaseEmail = userUID.child("email").getValue().toString();
                            if(databaseEmail.equals(email)) {
                                isEXIST = true;
                                Log.d("my", "Email: " + email + " Firebase: " + databaseEmail);
                            }
                        }

                        _bundle.putString("EMAIL", email);
                        _bundle.putBoolean("isEXIST", isEXIST);
                        nextFragment.setArguments(_bundle);

                        if (getFragmentManager() != null)
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container_view, nextFragment).commit();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) { }
                });
            }

        });

        _binding.loginVk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Данная функция находиться в разработке", Toast.LENGTH_SHORT).show();
            }
        });

        _binding.loginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Данная функция находиться в разработке", Toast.LENGTH_SHORT).show();
            }
        });

        return _binding.getRoot();
    }
}