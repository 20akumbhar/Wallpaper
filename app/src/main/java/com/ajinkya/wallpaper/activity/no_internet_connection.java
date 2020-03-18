package com.ajinkya.wallpaper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ajinkya.wallpaper.MainActivity;
import com.ajinkya.wallpaper.R;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Merlin;

public class no_internet_connection extends AppCompatActivity {
Button btn;
    Merlin merlin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet_connection);
        btn=findViewById(R.id.retry_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(no_internet_connection.this, MainActivity.class));
                finish();
            }
        });
        merlin = new Merlin.Builder().withConnectableCallbacks().withDisconnectableCallbacks().build(this);

        merlin.registerConnectable(new Connectable() {
            @Override
            public void onConnect() {
                Toast.makeText(no_internet_connection.this, "Connected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(no_internet_connection.this, MainActivity.class));
                finish();
            }
        });

    }
}
