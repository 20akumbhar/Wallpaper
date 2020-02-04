package com.ajinkya.wallpaper.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajinkya.wallpaper.R;
import com.ajinkya.wallpaper.activity.CatActivity;
import com.ajinkya.wallpaper.activity.FullActivity;
import com.ajinkya.wallpaper.models.wallpaper;
import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.ViewHolder> {
    Activity activity;
    List<wallpaper> wallpapers;
    public CatAdapter(Activity activity, List<wallpaper> wallpapers) {
        this.activity=activity;
        this.wallpapers=wallpapers;
    }

    @NonNull
    @Override
    public CatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CatAdapter.ViewHolder holder, final int position) {
        Glide.with(activity)
                .load(wallpapers.get(position).getThumbnail())
                .placeholder(R.drawable.background)
                .centerCrop()
                .into(holder.mainimage);
        holder.mainimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,FullActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("list", (Serializable) wallpapers);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wallpapers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mainimage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mainimage=itemView.findViewById(R.id.main_recycler_view_image);

        }
    }
}
