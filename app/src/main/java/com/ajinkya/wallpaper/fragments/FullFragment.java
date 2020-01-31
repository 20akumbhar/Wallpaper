package com.ajinkya.wallpaper.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ajinkya.wallpaper.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class FullFragment extends Fragment implements View.OnClickListener{
ImageView image;
BottomSheetDialog bottomSheetDialog;
int position;
    public FullFragment(int position) {
        // Required empty public constructor
        this.position=position;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_full, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        image=view.findViewById(R.id.full_image_view);
        image.setOnClickListener(this);

    }

    private void createbottomsheetdialog(){
        if (bottomSheetDialog==null){
            View view=LayoutInflater.from(getActivity()).inflate(R.layout.bottom_shit,null);
            view.findViewById(R.id.set_layout).setOnClickListener(this);
            view.findViewById(R.id.share_layout).setOnClickListener(this);
            view.findViewById(R.id.download_layout).setOnClickListener(this);
            bottomSheetDialog=new BottomSheetDialog(getActivity());
            bottomSheetDialog.setContentView(view);

        }
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.full_image_view:
                createbottomsheetdialog();
                bottomSheetDialog.show();
                break;
            case R.id.set_layout:
                Toast.makeText(getActivity(), "Set Wallpaper + "+position, Toast.LENGTH_SHORT).show();
                break;
            case R.id.share_layout:
                Toast.makeText(getActivity(), "Share Wallpaper", Toast.LENGTH_SHORT).show();
                break;
            case R.id.download_layout:
                Toast.makeText(getActivity(), "download Wallpaper", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getActivity(), "Invalid Move", Toast.LENGTH_SHORT).show();
        }
    }
}
