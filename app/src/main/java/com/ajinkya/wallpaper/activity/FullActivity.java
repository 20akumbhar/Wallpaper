package com.ajinkya.wallpaper.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.ajinkya.wallpaper.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FullActivity extends AppCompatActivity implements View.OnClickListener{
    BottomSheetDialog bottomSheetDialog;
    String Image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Image=getIntent().getStringExtra("Image");
        ImageView imageView=findViewById(R.id.full_image);
        imageView.setOnClickListener(this);
        Glide.with(FullActivity.this)
                .load(Image)
                .into(imageView)
       ;


    }
    private void createbottomsheetdialog(){
        if (bottomSheetDialog==null){
            View view= LayoutInflater.from(FullActivity.this).inflate(R.layout.bottom_shit,null);
            view.findViewById(R.id.set_layout).setOnClickListener(FullActivity.this);
            view.findViewById(R.id.share_layout).setOnClickListener(FullActivity.this);
            view.findViewById(R.id.download_layout).setOnClickListener(FullActivity.this);
            bottomSheetDialog=new BottomSheetDialog(FullActivity.this);
            bottomSheetDialog.setContentView(view);

        }
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.full_image:
                createbottomsheetdialog();
                bottomSheetDialog.show();
                break;
            case R.id.set_layout:
                setwallpaper(Image);
                break;
            case R.id.share_layout:
                sharewallpaper(Image);
                break;
            case R.id.download_layout:
                downloadwallpaper(Image);
                break;
            default:
                Toast.makeText(FullActivity.this, "Invalid Move", Toast.LENGTH_SHORT).show();
        }
    }


    private void setwallpaper(String image) {


        Glide.with(FullActivity.this)
                .asBitmap()
                .load(image)
                .into(new SimpleTarget<Bitmap>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        WallpaperManager mywallpapermanager=WallpaperManager.getInstance(FullActivity.this);
                        try{
                            Intent intent=new Intent(Intent.ACTION_ATTACH_DATA);
                            intent.setDataAndType(getbitmap(resource),"image/jpg");
                            intent.putExtra("mimiType","image/jpg");
                            startActivityForResult(Intent.createChooser(intent,"set as"),200);

                            mywallpapermanager.setBitmap(resource);
                            mywallpapermanager.setWallpaperOffsetSteps(0,0);
                            mywallpapermanager.getCropAndSetWallpaperIntent(savewallpaperAndGeturi(resource, System.currentTimeMillis()+""));
                            Toast.makeText(FullActivity.this, "wallpaper changed !!!", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            Toast.makeText(FullActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    public void sharewallpaper(String image){
        Glide.with(FullActivity.this)
                .asBitmap()
                .load(image)
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
            File file=new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"wallpaper_"+System.currentTimeMillis()+".jpg");
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
        if (ContextCompat.checkSelfPermission(FullActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){


            if (ActivityCompat.shouldShowRequestPermissionRationale(FullActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                Intent intent=new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri=Uri.fromParts("package",FullActivity.this.getPackageName(),"this");
                intent.setData(uri);
                startActivity(intent);
            }else {
                ActivityCompat.requestPermissions(FullActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
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
            Toast.makeText(FullActivity.this, "File not found"+e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(FullActivity.this, "IOException"+e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return null;

    }

    public void downloadwallpaper(String image){
        Glide.with(FullActivity.this)
                .asBitmap()
                .load(image)
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
                            Toast.makeText(FullActivity.this, "null uri", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
