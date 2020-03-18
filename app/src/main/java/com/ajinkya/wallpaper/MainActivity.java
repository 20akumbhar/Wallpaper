package com.ajinkya.wallpaper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.ajinkya.wallpaper.activity.no_internet_connection;
import com.ajinkya.wallpaper.adapters.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {
private Toolbar toolbar;
Merlin merlin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        merlin = new Merlin.Builder().withConnectableCallbacks().withDisconnectableCallbacks().build(this);
        init();
        if (!connectivity()){
            Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, no_internet_connection.class));
            finish();
        }

        merlin.registerConnectable(new Connectable() {
            @Override
            public void onConnect() {
                Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_SHORT).show();
            }
        });

        merlin.registerDisconnectable(new Disconnectable() {
            @Override
            public void onDisconnect() {

                    Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, no_internet_connection.class));
                    finish();

            }
        });
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //init();

    }

    public void init(){
        //connectivity();
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.settings_menu){
            AboutUs();
            Toast.makeText(this, "Setting Clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void AboutUs(){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("App NAme");
        builder.setCancelable(true);
        builder.setMessage("Version 1.0.0");
        builder.create();
        builder.show();

    }

    public boolean connectivity(){
        boolean havewifi=false;
        boolean havemobile=false;
        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] activenetwork=connectivityManager.getAllNetworkInfo();
        for (NetworkInfo ni: activenetwork){
            if (ni.getTypeName().equalsIgnoreCase("WIFI")){
                if (ni.isConnected()){
                    havewifi=true;
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("MOBILE")){
                if (ni.isConnected()){
                    havemobile=true;
                }
            }
        }
        boolean isConnected = havemobile || havewifi;

        if(!isConnected){
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();

        }
        return isConnected;
    }

    @Override
    protected void onResume() {
        super.onResume();
        merlin.bind();
    }

    @Override
    protected void onPause() {
        super.onPause();
        merlin.unbind();
    }
}