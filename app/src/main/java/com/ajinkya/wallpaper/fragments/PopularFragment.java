package com.ajinkya.wallpaper.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.ajinkya.wallpaper.R;
import com.ajinkya.wallpaper.adapters.MainAdapter;
import com.ajinkya.wallpaper.models.wallpaper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.SCROLL_INDICATOR_BOTTOM;
import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {
FirebaseFirestore db;
List<wallpaper> wallpapers;
MainAdapter adapter;
QueryDocumentSnapshot lastvisible;
ProgressBar progressBar;
    public PopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView main_recyclerview = (RecyclerView) view.findViewById(R.id.popular_recycler_view);
        main_recyclerview.setHasFixedSize(true);
        main_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),3));
        wallpapers=new ArrayList<>();
        progressBar=view.findViewById(R.id.main_progress2);
        adapter = new MainAdapter(getActivity(),wallpapers);
        main_recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        db=FirebaseFirestore.getInstance();
        loadfirstwallpapers();
       main_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
           @Override
           public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
               super.onScrolled(recyclerView, dx, dy);
               if (!main_recyclerview.canScrollVertically(SCROLL_INDICATOR_BOTTOM)){
                   progressBar.setVisibility(View.VISIBLE);
                   loadmorewallpapers();
               }
           }
       });
    }
    public void loadfirstwallpapers(){
        db.collection("Wallpapers").whereEqualTo("Trending",true)
                .orderBy("Timestamp", Query.Direction.DESCENDING)
                .limit(18)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                wallpaper w= document.toObject(wallpaper.class);
                                wallpapers.add(w);
                                lastvisible=document;
                            }
                            adapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                        } else {
                            progressBar.setVisibility(View.GONE);
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void loadmorewallpapers(){
        progressBar.setVisibility(View.VISIBLE);

        db.collection("Wallpapers")
                .whereEqualTo("Trending",true)
                .orderBy("Timestamp", Query.Direction.DESCENDING)
                .limit(12)
                .startAfter(lastvisible)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                wallpaper w= document.toObject(wallpaper.class);
                                wallpapers.add(w);
                                lastvisible=document;
                            }
                            adapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                        } else {
                            progressBar.setVisibility(View.GONE);
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}
