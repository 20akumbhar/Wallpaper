package com.ajinkya.wallpaper.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.WindowManager;

import com.ajinkya.wallpaper.R;
import com.ajinkya.wallpaper.adapters.FullPageAdapter;

public class FullActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        FullPageAdapter PagerAdapter = new FullPageAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.full_view_pager);
        viewPager.setAdapter(PagerAdapter);
        //int pos=viewPager.getCurrentItem();
    }
}
