package com.ajinkya.wallpaper.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ajinkya.wallpaper.activity.FullActivity;
import com.ajinkya.wallpaper.fragments.FullFragment;

public class FullPageAdapter extends FragmentStatePagerAdapter {
    public FullPageAdapter(FullActivity fullActivity, FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new FullFragment(position);
    }

    @Override
    public int getCount() {
        return 5;
    }
}
