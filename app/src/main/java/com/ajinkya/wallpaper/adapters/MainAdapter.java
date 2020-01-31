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
import com.bumptech.glide.Glide;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.VIewHolder> {
    Context context;

    public MainAdapter(FragmentActivity activity) {
        this.context=activity;
    }

    @NonNull
    @Override
    public MainAdapter.VIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_item,parent,false);
        return new VIewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.VIewHolder holder, int position) {
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
        return 20;
    }

    public class VIewHolder extends RecyclerView.ViewHolder {
        ImageView mainimage;
        public VIewHolder(@NonNull View itemView) {
            super(itemView);
            mainimage=itemView.findViewById(R.id.main_recycler_view_image);

        }
    }
}
