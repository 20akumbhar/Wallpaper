package com.ajinkya.wallpaper.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ajinkya.wallpaper.R;
import com.ajinkya.wallpaper.activity.CatActivity;
import com.ajinkya.wallpaper.models.category;
import com.bumptech.glide.Glide;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CatViewHolder> {
    Context context;
    List<category> categories;
    public CategoryAdapter(FragmentActivity activity, List<category> categories) {
        this.context=activity;
        this.categories=categories;
    }

    @NonNull
    @Override
    public CategoryAdapter.CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_recycler_item,parent,false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CatViewHolder holder, final int position) {
        Glide.with(context)
                .load(categories.get(position).getThumbnail())
                .placeholder(R.drawable.background)
                .centerCrop()
                .into(holder.category);
        holder.category_name.setText(categories.get(position).getName());
        holder.category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,CatActivity.class);
                intent.putExtra("catname",categories.get(position).getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder {
        ImageView category;
        TextView category_name;
        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            category=itemView.findViewById(R.id.category_recycler_view_image);
            category_name=itemView.findViewById(R.id.category_text_view);

        }
    }
}
