package com.example.bloodhero;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class RecipientRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient_registration);

        TextView backButton;
        backButton = findViewById(R.id.backButton);
        backButton.setOnEditorActionListener((textView, i, keyEvent) -> {
            Intent intent = new Intent(RecipientRegistrationActivity.this, LoginActivity.class);
            RecipientRegistrationActivity.this.startActivity(intent);

            return false;
        }


        );




    }
}