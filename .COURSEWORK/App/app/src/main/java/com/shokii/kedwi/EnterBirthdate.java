package com.shokii.kedwi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shokii.kedwi.databinding.FragmentEnterBirthdateBinding;



public class EnterBirthdate extends Fragment {
    private FirebaseAuth _mAuth;
    private FirebaseDatabase _dataBase;
    private DatabaseReference _userRefs;

    private FragmentEnterBirthdateBinding _binding;


    public EnterBirthdate() { super   (R.layout.fragment_enter_birthdate); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _mAuth = FirebaseAuth.getInstance();
        _dataBase = FirebaseDatabase.getInstance();
        _userRefs = _dataBase.getReference("users").child(_mAuth.getUid());


        _binding = FragmentEnterBirthdateBinding.inflate(getLayoutInflater());

        _binding.textHighlight.setText(
                _userRefs.child("name").toString()
        );

        _binding.birthdateContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null)
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new EnterGender()).commit();
            }
        });

        return _binding.getRoot();
    }
}