package com.shokii.kedwi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shokii.kedwi.databinding.FragmentEnterGenderBinding;



public class EnterGender extends Fragment {
    private FirebaseAuth _mAuth;
    private FirebaseDatabase _dataBase;
    private DatabaseReference _userRefs;



    private FragmentEnterGenderBinding _binding;

    public EnterGender() {
        super (R.layout.fragment_enter_gender);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _mAuth = FirebaseAuth.getInstance();
        _dataBase = FirebaseDatabase.getInstance();
        _userRefs = _dataBase.getReference("users");

        _binding = FragmentEnterGenderBinding.inflate(getLayoutInflater());


        _binding.maleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _userRefs.child(_mAuth.getUid().toString()).child("gender").setValue("Male");
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });

        _binding.femaleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _userRefs.child(_mAuth.getUid().toString()).child("gender").setValue("Female");
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });

        _binding.wontSayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _userRefs.child(_mAuth.getUid().toString()).child("gender").setValue("WontSayBtn");
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });

        return _binding.getRoot();
    }
}