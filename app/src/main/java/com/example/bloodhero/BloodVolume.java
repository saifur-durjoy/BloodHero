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


/**
 * This Class represents the task of opening the tab for blood volume calculation.
 * It shows two buttons to navigate users to male or female calculation.
 * @author Saifur Rahman Durjoy (Saifur-Durjoy)
 * @since 2022
 */

public class BloodVolume extends AppCompatActivity
{
    /**
     * Two Button instances named maleButton and femaleButton to navigate to calculation on click for male and female respecttively
     */

    private Button maleButton, femaleButton;

    /**
     * Auto generated Java Class Built in method.
     * This method is used for establishing the connection between front end and back end.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_volume);
        /**
         * connecting the instances declared above by with the front end (i.e. from layout file)
         */
        maleButton = findViewById(R.id.maleButton);
        femaleButton = findViewById(R.id.femaleButton);

        /**
         * on clicking male button to start generating a new activity for opening Blood volume men calculation
         */
        maleButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openBloodVolumeMen();
            }
        });

        /**
         * on clicking female button to start generating a new activity for opening Blood volume women calculation
         */
        femaleButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openBloodVolumeWomen();
            }
        });
    }
    /**
     * This method is responsible for opening to blood volume calculation men tab
     */
    private void openBloodVolumeWomen()
    {
        Intent intent = new Intent(BloodVolume.this, BloodVolumeWomen.class);
        startActivity(intent);
    }

    /**
     * This method is responsible for opening to blood volume calculation women tab
     */
    private void openBloodVolumeMen()
    {
        Intent intent = new Intent(BloodVolume.this, BloodVolumeMen.class);
        startActivity(intent);
    }
}
