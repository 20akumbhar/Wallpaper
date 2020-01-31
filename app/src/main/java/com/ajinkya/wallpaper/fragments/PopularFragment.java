package com.ajinkya.wallpaper.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajinkya.wallpaper.R;
import com.ajinkya.wallpaper.adapters.MainAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {


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
        RecyclerView main_recyclerview = (RecyclerView) view.findViewById(R.id.popular_recycler_view);
        main_recyclerview.setHasFixedSize(true);
        main_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),3));
        MainAdapter adapter = new MainAdapter(getActivity());
        main_recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
