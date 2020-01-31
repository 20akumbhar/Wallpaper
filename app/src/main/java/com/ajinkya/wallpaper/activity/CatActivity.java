package com.ajinkya.wallpaper.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ajinkya.wallpaper.R;
import com.ajinkya.wallpaper.adapters.CatAdapter;
import com.ajinkya.wallpaper.adapters.MainAdapter;

public class CatActivity extends AppCompatActivity {
RecyclerView recyclerView;
CatAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
        recyclerView=findViewById(R.id.cat_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setHasFixedSize(true);
        adapter=new CatAdapter(this);
        recyclerView.setAdapter(adapter);
    }
}
