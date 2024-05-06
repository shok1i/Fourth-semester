package com.shokii.kedwi;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter (FragmentActivity fragmentActivity) { super (fragmentActivity); }
    @NonNull
    @Override
    public Fragment createFragment (int position) { return (HomePage_Blank.newInstance(position)); }

    @Override
    public int getItemCount() { return 2; }
}
