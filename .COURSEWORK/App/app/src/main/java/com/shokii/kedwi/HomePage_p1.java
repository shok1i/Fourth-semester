package com.shokii.kedwi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.shokii.kedwi.databinding.FragmentHomePageP1Binding;

import java.util.ArrayList;


// TODO:
//   Сделать прогрузку для элементов


public class HomePage_p1 extends Fragment {
    private FragmentHomePageP1Binding _binding;
    private GridViewAdapter adapter;
    private ArrayList<GameItem> gameItems = new ArrayList<>();

    public HomePage_p1 () {
        super(R.layout.fragment_home_page_p1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _binding = FragmentHomePageP1Binding.inflate(getLayoutInflater());

        for (int i = 0; i < 30; i++)
            gameItems.add(new GameItem(1, "123", "пройдено", "Cyberpunk 2077"));

        adapter = new GridViewAdapter(getContext(), gameItems);
        _binding.Announcements.setAdapter(adapter);

        _binding.Announcements.setOnItemClickListener(this::clickOnGame);

        _binding.Announcements.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {  }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount >= totalItemCount) {
                    for (int i = 0; i < 30; i++)
                        gameItems.add(new GameItem(1, "123", "пройдено", "Cyberpunk 2077"));

                    adapter = new GridViewAdapter(getContext(), gameItems);
                    _binding.Announcements.setAdapter(adapter);
                }
            }
        });


        return _binding.getRoot();
    }

    private void clickOnGame(AdapterView<?> adapterView, View view, int i, long l) {
        // TODO: Переход на внутренюю информацию о нашей игре
    }

}