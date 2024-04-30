package com.example.kedwi;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kedwi.databinding.FragmentCreateAccountBinding;

public class CreateAccount extends Fragment {
    private FragmentCreateAccountBinding _binding;

    public CreateAccount() {
        super (R.layout.fragment_create_account);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void Continue (View v) {
        LocalUserData localUserData = LocalUserDataJSON.FromJSON(getContext());
        String emailVal = _binding.LogInEmail.getText().toString();
        if (emailVal != null)
            localUserData.set_login(_binding.LogInEmail.getText().toString());
//        else Отобразить что допущена ошибка при вводе
    }
}