package com.example.bloodhero;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private View decorView;
    private TextInputEditText loginEmail, loginPassword;
    private TextView forgotPassword;
    private Button loginButton;
    private ProgressDialog loader;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if(user != null){
                    Intent intent = new Intent(LoginActivity.this, MainActivity1.class);
                    startActivity(intent);
                    finish();

                }
            }
        };

        TextView backButton = findViewById(R.id.backButton);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        forgotPassword = findViewById(R.id.forgotpassword);
        loginButton = findViewById(R.id.loginButton);
        loader = new ProgressDialog(this);


        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SelectRegistrationActivity.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = loginEmail.getText().toString().trim();
                final String password = loginPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    loginEmail.setError("Email is Required");
                }
                if (TextUtils.isEmpty(password)){
                    loginPassword.setError("Password is Required");
                }

                else{
                     loader.setMessage("Logging In");
                     loader.setCanceledOnTouchOutside(false);
                     loader.show();

                     mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if (task.isSuccessful()){
                                 Toast.makeText(LoginActivity.this, "Log in Successful", Toast.LENGTH_SHORT).show();
                                 Intent intent = new Intent(LoginActivity.this, MainActivity1.class);
                                 startActivity(intent);
                                 finish();
                             }
                             else {
                                 Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                             }

                             loader.dismiss();

                         }
                     });
                }
            }
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

    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override

    protected void onStop(){
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }

}