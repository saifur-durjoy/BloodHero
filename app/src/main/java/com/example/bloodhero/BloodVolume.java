package com.example.bloodhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class BloodVolume extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolBar;
    private TextView title;
    private Button maleButton, femaleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_volume);

        toolBar = findViewById(R.id.toolBar);
        drawerLayout = findViewById(R.id.drawerLayout);
        title = findViewById(R.id.title);
        maleButton = findViewById(R.id.maleButton);
        maleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBloodVolumeMen();
            }
        });
        femaleButton = findViewById(R.id.femaleButton);
        femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBloodVolumeWomen();
            }
        });
    }

    private void openBloodVolumeWomen() {
        Intent intent = new Intent(BloodVolume.this, BloodVolumeWomen.class);
        startActivity(intent);
    }

    private void openBloodVolumeMen() {
        Intent intent = new Intent(BloodVolume.this, BloodVolumeMen.class);
        startActivity(intent);
    }
}
