package com.example.a5;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyAdapter extends FragmentStateAdapter {
    public MyAdapter (FragmentActivity fragmentActivity) { super (fragmentActivity); }
    @NonNull
    @Override
    public Fragment createFragment (int position) { return (BlankFragment.newInstance(position)); }
    @Override
    public int getItemCount() { return 3; }
}

