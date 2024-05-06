package com.shokii.kedwi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.shokii.kedwi.databinding.FragmentHomePageBinding;
import com.shokii.kedwi.databinding.FragmentHomePageBlankBinding;

public class HomePage extends Fragment {
    FragmentHomePageBlankBinding _binding;
    public HomePage () {
        super(R.layout.fragment_home_page);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentHomePageBinding _binding = FragmentHomePageBinding.inflate(getLayoutInflater());

        FragmentStateAdapter pageAdapter = new ViewPagerAdapter(getActivity());
        _binding.pager.setAdapter(pageAdapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                _binding.tabLayout, _binding.pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0)
                    tab.setText("Вышедшие");
                else
                    tab.setText("Анонсы");
            }
        });
        tabLayoutMediator.attach();

        return _binding.getRoot();
    }

}