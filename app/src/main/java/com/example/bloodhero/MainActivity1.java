package com.example.bloodhero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity1 extends AppCompatActivity {

    private Button button;
    /**
     * Logout Button
     * Login Activity to Menu
     * @author Abrar Karim
     * @version 0.1
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity1.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}