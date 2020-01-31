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
import com.ajinkya.wallpaper.activity.CatActivity;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CatViewHolder> {
    Context context;
    public CategoryAdapter(FragmentActivity activity) {
        this.context=activity;
    }

    @NonNull
    @Override
    public CategoryAdapter.CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_recycler_item,parent,false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CatViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class CatViewHolder extends RecyclerView.ViewHolder {
        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageView category=itemView.findViewById(R.id.category_recycler_view_image);
            category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, CatActivity.class));
                }
            });
        }
    }
}
