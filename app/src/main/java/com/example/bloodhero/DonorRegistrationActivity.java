package com.example.bloodhero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DonorRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_registration);
        TextView backButton;
        backButton = findViewById(R.id.backButton);
        backButton.setOnEditorActionListener((textView, i, keyEvent) -> {
                    Intent intent = new Intent(DonorRegistrationActivity.this, LoginActivity.class);
                    DonorRegistrationActivity.this.startActivity(intent);

                    return false;
                }


        );
    }
}