package com.shokii.kedwi;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapterBookmarks extends FragmentPagerAdapter {
    private static final int NUM_TABS = 6; // Количество вкладок

    public ViewPagerAdapterBookmarks(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // Избранное
                return new BookmarksPageBlank(0);
            case 1:
                // Прохожу
                return new BookmarksPageBlank(1);
            case 2:
                // В планах
                return new BookmarksPageBlank(2);
            case 3:
                // Пройдено
                return new BookmarksPageBlank(3);
            case 4:
                // Отложено
                return new BookmarksPageBlank(4);
            case 5:
                // Брошено
                return new BookmarksPageBlank(5);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Избранное";
            case 1:
                return "Прохожу";
            case 2:
                return "В планах";
            case 3:
                return "Пройдено";
            case 4:
                return "Отложено";
            case 5:
                return "Брошено";
            default:
                return "null";
        }
    }
}
