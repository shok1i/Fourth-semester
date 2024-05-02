package com.shokii.kedwi;

import android.os.Bundle;
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
import com.shokii.kedwi.databinding.FragmentRegistrationContinueBinding;


// TODO:
//   ? Переброс на фрагмент кооторый не был заполнен



public class RegistrationContinue extends Fragment {
    public RegistrationContinue () {super (R.layout.fragment_registration_continue);}
    private FragmentRegistrationContinueBinding _binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _binding = FragmentRegistrationContinueBinding.inflate(getLayoutInflater());

        _binding.registrationContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null)
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new EnterName()).commit();
            }
        });

        return _binding.getRoot();
    }
}