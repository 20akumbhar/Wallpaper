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

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.ViewHolder> {
    Activity activity;
    public CatAdapter(Activity activity) {
        this.activity=activity;
    }

    @NonNull
    @Override
    public CatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CatAdapter.ViewHolder holder, int position) {
        holder.mainimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, FullActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mainimage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mainimage=itemView.findViewById(R.id.main_recycler_view_image);

        }
    }
}
