package com.example.bloodhero;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
/**
 * User will Select donor or recipient
 * Sign in button for those who are already signed up
 * @author Abrar Karim
 * @version 0.1
 */


public class SelectRegistrationActivity extends AppCompatActivity {
    private View decorView;

    public SelectRegistrationActivity() {
    }
    /**
     * Donor and recipient selection
     * Selection page to Registration page
     * @author Abrar Karim
     * @version 0.1
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_registration);

        Button donorButton = findViewById(R.id.donorButton);
        Button recipientButton = findViewById(R.id.recipientButton);
        View backButton = findViewById(R.id.backButton);

        donorButton.setOnClickListener(view -> {
            Intent intent = new Intent (SelectRegistrationActivity.this, DonorRegistrationActivity.class);
            startActivity(intent);
        });

        recipientButton.setOnClickListener(view -> {
            Intent intent = new Intent (SelectRegistrationActivity.this, RecipientRegistrationActivity.class);
            startActivity(intent);
        });

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent (SelectRegistrationActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(visibility -> {
            if (visibility == 0)
                decorView.setSystemUiVisibility(hideSystemBars());
        });

    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            decorView.setSystemUiVisibility(hideSystemBars());
        }

    }

    private int hideSystemBars(){
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;



    }

}