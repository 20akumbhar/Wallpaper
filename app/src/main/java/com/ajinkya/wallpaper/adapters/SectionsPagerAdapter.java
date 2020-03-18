package com.ajinkya.wallpaper.adapters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ajinkya.wallpaper.fragments.Category_Fragment;
import com.ajinkya.wallpaper.fragments.MainFragment;
import com.ajinkya.wallpaper.fragments.PopularFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    Context context;
    public SectionsPagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return new MainFragment();
        }else if (position==2){
            return new Category_Fragment();
        }else if (position==1){
            return new PopularFragment();
        }
        return new MainFragment();
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
            title="Home";
        }else  if (position==1){
            title="Popular";
        }else  if (position==2){
            title="Categories";
        }
        return  title;
    }


}
