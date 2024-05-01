package com.shokii.kedwi;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shokii.kedwi.databinding.FragmentRegistrationContinueBinding;

// TODO:
//   Переброс на фрагмент кооторый не был заполнен

public class RegistrationContinue extends Fragment {
    private FragmentRegistrationContinueBinding _binding;

    private FirebaseAuth _mAuth;
    private FirebaseDatabase _dataBase;
    private DatabaseReference _userRefs;



    public RegistrationContinue () {super (R.layout.fragment_registration_continue);}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _mAuth = FirebaseAuth.getInstance();
        _dataBase = FirebaseDatabase.getInstance();
        _userRefs = _dataBase.getReference("users");

        _binding = FragmentRegistrationContinueBinding.inflate(getLayoutInflater());

        _binding.registrationContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference currentUserRef = _userRefs.child(_mAuth.getUid());

                currentUserRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        switch ((int) (snapshot.getChildrenCount())) {
                            case 2:
                                getFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new EnterName()).commit();
                                break;
                            case 3:
                                // Фрагмент с датой рождения
                                break;
                            case 4:
                                // Фрагмент с полом
                                break;
                            default:
                                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {  }
                });
            }
        });

        return _binding.getRoot();
    }
}