package com.ajinkya.wallpaper.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ajinkya.wallpaper.R;
import com.ajinkya.wallpaper.activity.FullActivity;
import com.ajinkya.wallpaper.models.wallpaper;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.VIewHolder> {
    Context context;
    List<wallpaper> wallpapers= new ArrayList<>();

    public MainAdapter(FragmentActivity activity, List<wallpaper> wallpapers) {
        this.context=activity;
        this.wallpapers=wallpapers;
    }

    @NonNull
    @Override
    public MainAdapter.VIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_item,parent,false);
        return new VIewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.VIewHolder holder, int position) {
        Glide.with(context)
                .load(wallpapers.get(position).getThumbnail())
                .placeholder(R.drawable.background)
                .centerCrop()
                .into(holder.mainimage);

        holder.mainimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, FullActivity.class));
            }
        });
       /* Glide.with(context)
                .load(R.drawable.loader)

                .into(holder.mainimage);*/

    }

    @Override
    public int getItemCount() {
        return wallpapers.size();
    }

    public class VIewHolder extends RecyclerView.ViewHolder {
        ImageView mainimage;
        public VIewHolder(@NonNull View itemView) {
            super(itemView);
            mainimage=itemView.findViewById(R.id.main_recycler_view_image);

        }
    }
}
