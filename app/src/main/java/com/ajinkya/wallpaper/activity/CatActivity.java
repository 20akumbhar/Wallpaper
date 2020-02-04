package com.ajinkya.wallpaper.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.ajinkya.wallpaper.R;
import com.ajinkya.wallpaper.adapters.CatAdapter;
import com.ajinkya.wallpaper.adapters.MainAdapter;
import com.ajinkya.wallpaper.models.wallpaper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CatActivity extends AppCompatActivity {
RecyclerView recyclerView;
CatAdapter adapter;
List<wallpaper> wallpapers;
FirebaseFirestore db;
String category="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
        recyclerView = findViewById(R.id.cat_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);
        wallpapers=new ArrayList<>();
        adapter = new CatAdapter(this,wallpapers);
        recyclerView.setAdapter(adapter);
        category=getIntent().getStringExtra("catname");
        db=FirebaseFirestore.getInstance();
        db.collection("Wallpapers")
                .whereEqualTo("Category",category)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                wallpaper w= document.toObject(wallpaper.class);
                                wallpapers.add(w);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }
}
