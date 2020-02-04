package com.ajinkya.wallpaper.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ajinkya.wallpaper.activity.FullActivity;
import com.ajinkya.wallpaper.fragments.FullFragment;
import com.ajinkya.wallpaper.models.wallpaper;

import java.util.List;

public class FullPageAdapter extends FragmentStatePagerAdapter {
    List<wallpaper> wallpapers;
    FullActivity fullActivity;
    public FullPageAdapter(FullActivity fullActivity, FragmentManager fm, List<wallpaper> wallpapers) {
        super(fm);
        this.wallpapers=wallpapers;
        this.fullActivity=fullActivity;
    }

    @Override
    public Fragment getItem(int position) {
        return new FullFragment(fullActivity,position,wallpapers.get(position));
    }

    @Override
    public int getCount() {
        return wallpapers.size();
    }
}
