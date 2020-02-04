package com.ajinkya.wallpaper.fragments;


import android.Manifest;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ajinkya.wallpaper.R;
import com.ajinkya.wallpaper.activity.FullActivity;
import com.ajinkya.wallpaper.models.wallpaper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * A simple {@link Fragment} subclass.
 */
public class FullFragment extends Fragment implements View.OnClickListener{
ImageView image;
BottomSheetDialog bottomSheetDialog;
FullActivity context;
int position;
wallpaper wallpaper;
    public FullFragment(FullActivity fullActivity, int position, wallpaper wallpaper) {
        // Required empty public constructor
        this.position=position;
        this.wallpaper=wallpaper;
        this.context=fullActivity;
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
        Glide.with(context)
                .load(wallpaper.getImage())
                .placeholder(R.drawable.background)
                .centerCrop()
                .into(image);
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
                setwallpaper();
                break;
            case R.id.share_layout:
                sharewallpaper(position);
                break;
            case R.id.download_layout:
                downloadwallpaper();
                break;
            default:
                Toast.makeText(getActivity(), "Invalid Move", Toast.LENGTH_SHORT).show();
        }
    }

    private void setwallpaper() {


        Glide.with(context)
                .asBitmap()
                .load(wallpaper.getImage())
                .into(new SimpleTarget<Bitmap>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        WallpaperManager mywallpapermanager=WallpaperManager.getInstance(context);
                        try{
                            Intent intent=new Intent(Intent.ACTION_ATTACH_DATA);
                            intent.setDataAndType(getbitmap(resource),"image/jpg");
                            intent.putExtra("mimiType","image/jpg");
                            startActivityForResult(Intent.createChooser(intent,"set as"),200);

                            mywallpapermanager.setBitmap(resource);
                            mywallpapermanager.setWallpaperOffsetSteps(0,0);
                            mywallpapermanager.getCropAndSetWallpaperIntent(savewallpaperAndGeturi(resource, System.currentTimeMillis()+""));
                            Toast.makeText(context, "wallpaper changed !!!", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    public void sharewallpaper(int position){
        Glide.with(context)
                .asBitmap()
                .load(wallpaper.getImage())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Intent intent=new Intent(Intent.ACTION_SEND);
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_STREAM,getbitmap(resource));
                        intent.putExtra(Intent.EXTRA_TEXT,"hey install this app : link will be here");
                        startActivity(Intent.createChooser(intent,"Send Wallpaper"));
                    }
                });
    }

    private Uri getbitmap(Bitmap bmp) {
        Uri bmpuri=null;
        try {
            File file=new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),"wallpaper_"+System.currentTimeMillis()+".jpg");
            FileOutputStream out=new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG,100,out);
            out.close();
            bmpuri=Uri.fromFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpuri;
    }

    private Uri savewallpaperAndGeturi(Bitmap bitmap,String id){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){


            if (ActivityCompat.shouldShowRequestPermissionRationale(context,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                Intent intent=new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri=Uri.fromParts("package",context.getPackageName(),"this");
                intent.setData(uri);
                startActivity(intent);
            }else {
                ActivityCompat.requestPermissions(context,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
            }
            return  null;
        }


        File folder=new File(Environment.getExternalStorageDirectory().toString()+"/Wallpapers");
        folder.mkdirs();
        File file=new File(folder,id+".jpeg");
        try {
            FileOutputStream out=new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,out);
            out.flush();
            out.close();
            return Uri.fromFile(file);
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "File not found"+e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(context, "IOException"+e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return null;

    }

    public void downloadwallpaper(){
        Glide.with(context)
                .asBitmap()
                .load(wallpaper.getImage())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        //((Activity)mctx).findViewById(R.id.progress2).setVisibility(View.GONE);
                        Intent intent=new Intent(Intent.ACTION_VIEW);
                        Uri uri=savewallpaperAndGeturi(resource,System.currentTimeMillis()+"");
                        if (uri!=null){
                            intent.setDataAndType(uri,"image/*");
                            startActivity(intent);

                        }else {
                            Toast.makeText(context, "null uri", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

