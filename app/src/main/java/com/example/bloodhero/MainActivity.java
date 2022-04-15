package com.example.bloodhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {

     private DrawerLayout drawerLayout;
     private Toolbar toolbar;
     private NavigationView nav_view;

     private CircleImageView nav_profile_image;
     private TextView nav_fullname, nav_email, nav_bloodgroup, nav_type;

     private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Blood Hero");

        drawerLayout = findViewById(R.id.drawerLayout);
        nav_view = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav_profile_image = nav_view.getHeaderView(0).findViewById(R.id.nav_user_image);
        nav_fullname = nav_view.getHeaderView(0).findViewById(R.id.nav_user_fullname);
        nav_email = nav_view.getHeaderView(0).findViewById(R.id.nav_user_email);
        nav_bloodgroup = nav_view.getHeaderView(0).findViewById(R.id.nav_user_bloodgroup);
        nav_type = nav_view.getHeaderView(0).findViewById(R.id.nav_user_type);

        userRef = FirebaseDatabase.getInstance().getReference().child("users").child(
                FirebaseAuth.getInstance().getCurrentUser().getUid()
        );

        userRef.addValueEventListener(new ValueEventListener(){
            @override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                if(snapshot.exist()){
                    String name = snapshot.child("name").getValue.toString();
                    nav_fullname.setText(name);

                    String email = snapshot.child("email").getValue.toString();
                    nav_email.setText(email);

                    String bloodgroup = snapshot.child("bloodgroup").getValue.toString();
                    nav_bloodgroup.setText(bloodgroup);

                    String type = snapshot.child("type").getValue.toString();
                    nav_type.setText(type);

                    String imageUrl = snapshot.child("profilepictureurl").getValue.toString();
                    Glide.with(getApplicationContext()).load(imageUrl).into(nav_profile_image);
                    
                }
            }

            @override
            public void onCancelled(@NonNull DatabaseError error){

            }
        });


    }
}