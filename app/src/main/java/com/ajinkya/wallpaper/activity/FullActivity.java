package com.ajinkya.wallpaper.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.ajinkya.wallpaper.R;
import com.ajinkya.wallpaper.adapters.FullPageAdapter;
import com.ajinkya.wallpaper.models.wallpaper;

import java.util.ArrayList;
import java.util.List;

public class FullActivity extends AppCompatActivity {
List<wallpaper> wallpapers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        wallpapers=new ArrayList<>();

        ViewPager viewPager = findViewById(R.id.full_view_pager);
        final int position=getIntent().getIntExtra("position",0);
        wallpapers= (List<wallpaper>) getIntent().getSerializableExtra("list");
        Log.e("walllist",wallpapers.get(position).getImage());
        FullPageAdapter PagerAdapter = new FullPageAdapter(this, getSupportFragmentManager(),wallpapers);
        PagerAdapter.notifyDataSetChanged();
        viewPager.setAdapter(PagerAdapter);
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int pos) {
                if (pos==wallpapers.size()-1){
                    Toast.makeText(FullActivity.this, "Reaching final", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
