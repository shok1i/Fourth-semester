package com.shokii.kedwi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.shokii.kedwi.databinding.GameItemBinding;

public class GameInfo extends Fragment {
    private GameItemBinding binding;

    public GameInfo() {
        super(R.layout.fragment_game_info);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = GameItemBinding.inflate(getLayoutInflater());

        String GameName = new Bundle().getString("name");
        Log.d("my", GameName);

        return binding.getRoot();
    }


}