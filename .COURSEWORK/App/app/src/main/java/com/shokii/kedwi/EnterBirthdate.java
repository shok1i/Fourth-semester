package com.shokii.kedwi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shokii.kedwi.databinding.FragmentEnterBirthdateBinding;



public class EnterBirthdate extends Fragment {
    private FirebaseAuth _mAuth;
    private FirebaseDatabase _dataBase;
    private DatabaseReference _usersRefs, _curentUser;

    private FragmentEnterBirthdateBinding _binding;


    public EnterBirthdate() { super   (R.layout.fragment_enter_birthdate); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _mAuth = FirebaseAuth.getInstance();
        _dataBase = FirebaseDatabase.getInstance();
        _usersRefs = _dataBase.getReference("users");
        _curentUser = _usersRefs.child(_mAuth.getUid());


        _binding = FragmentEnterBirthdateBinding.inflate(getLayoutInflater());

        _curentUser.child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                _binding.textHighlight.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {  }
        });


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