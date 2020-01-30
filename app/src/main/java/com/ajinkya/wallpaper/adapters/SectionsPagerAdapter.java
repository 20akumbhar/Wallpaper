package com.ajinkya.wallpaper.adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ajinkya.wallpaper.fragments.HomeFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    Context context;
    public SectionsPagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        return new HomeFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title=null;
        if(position==0){
            title="One";
        }else  if (position==1){
            title="two";
        }else  if (position==2){
            title="three";
        }
        return  title;
    }
}
