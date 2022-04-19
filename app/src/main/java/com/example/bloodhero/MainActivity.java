package com.example.bloodhero;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

import com.bumptech.glide.Glide;
import com.example.bloodhero.Adapter.UserAdapter;
import com.example.bloodhero.Model.User;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private DrawerLayout drawerLayout;
    private Toolbar toolBar;
    private NavigationView navView;

    private CircleImageView navProfileImage;
    private TextView navFullName, navEmail, navBloodGroup, navType;

    private DatabaseReference userRef;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private List<User> userList;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Blood Hero");

        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout,
                toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progressbar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        userList = new ArrayList<>();
        userAdapter = new UserAdapter(MainActivity.this, userList);

        recyclerView.setAdapter(userAdapter);
/*
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        ref.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                String type = snapshot.child("type").getValue().toString();
                if(type.equals("donor")){
                    readRecipients();
                }else readDonors();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
 */
/*
        navProfileImage = navView.getHeaderView(0).findViewById(R.id.nav_user_image);
        navFullName = navView.getHeaderView(0).findViewById(R.id.nav_user_fullname);
        navEmail = navView.getHeaderView(0).findViewById(R.id.nav_user_email);
        navBloodGroup = navView.getHeaderView(0).findViewById(R.id.nav_user_bloodgroup);
        navType = navView.getHeaderView(0).findViewById(R.id.nav_user_type);

       userRef = FirebaseDatabase.getInstance().getReference().child("users").child(
               FirebaseAuth.getInstance().getCurrentUser().getUid());

        userRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (snapshot.exists()) {
                    String name = snapshot.child("name").getValue().toString();
                    navFullName.setText(name);

                    String email = snapshot.child("email").getValue().toString();
                    navEmail.setText(email);

                    String bloodGroup = snapshot.child("bloodgroup").getValue().toString();
                    navBloodGroup.setText(bloodgroup);

                    String type = snapshot.child("type").getValue().toString();
                    navType.setText(type);

                    if(snapshot.hasChild("profilepictureurl"))
                    {
                        String imageUrl = snapshot.child("imageurl").getValue().toString();
                        Glide.with(getApplicationContext()).load(imageurl).into(navProfileImage);
                    }
                    else
                    {
                        navProfileImage.setImageResource(R.drawable.profile_image);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
 */
    }
/*
    private void readRecipients()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
        Query query = reference.orderByChild("type").equalTo("recipient");
        query.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                userList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    User user = snapshot.getValue(User.class);
                    userList.add(user);
                }
                progressBar.setVisibility(View.GONE);

                if(userList.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "No Recipients Found",
                            Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }

    private void readDonors()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
        Query query = reference.orderByChild("type").equalTo("donor");
        query.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                userList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    User user = snapshot.getValue(User.class);
                    userList.add(user);
                }
                userAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

                if(userList.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "No Donors Found",
                            Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });

     }
 */
        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.aplus:
                    Intent intent1 = new Intent(MainActivity.this, CategorySelectedActivity.class);
                    intent1.putExtra("group", "A+");
                    startActivity(intent1);
                    break;

                case R.id.aminus:
                    Intent intent2 = new Intent(MainActivity.this, CategorySelectedActivity.class);
                    intent2.putExtra("group", "A-");
                    startActivity(intent2);
                    break;

                case R.id.bplus:
                    Intent intent3 = new Intent(MainActivity.this, CategorySelectedActivity.class);
                    intent3.putExtra("group", "B+");
                    startActivity(intent3);
                    break;

                case R.id.bminus:
                    Intent intent4 = new Intent(MainActivity.this, CategorySelectedActivity.class);
                    intent4.putExtra("group", "B-");
                    startActivity(intent4);
                    break;

                case R.id.abplus:
                    Intent intent5 = new Intent(MainActivity.this, CategorySelectedActivity.class);
                    intent5.putExtra("group", "AB+");
                    startActivity(intent5);
                    break;

                case R.id.abnegative:
                    Intent intent6 = new Intent(MainActivity.this, CategorySelectedActivity.class);
                    intent6.putExtra("group", "AB-");
                    startActivity(intent6);
                    break;

                case R.id.oplus:
                    Intent intent7 = new Intent(MainActivity.this, CategorySelectedActivity.class);
                    intent7.putExtra("group", "O+");
                    startActivity(intent7);
                    break;

                case R.id.onegative:
                    Intent intent8 = new Intent(MainActivity.this, CategorySelectedActivity.class);
                    intent8.putExtra("group", "O-");
                    startActivity(intent8);
                    break;

                case R.id.compatible:
                    Intent intent11 = new Intent(MainActivity.this, CategorySelectedActivity.class);
                    intent11.putExtra("group", "Compatible with me");
                    startActivity(intent11);
                    break;

                case R.id.bloodvolume:
                    Intent intent12 = new Intent(MainActivity.this, BloodVolume.class);
                    startActivity(intent12);
                    break;

                case R.id.donationCapacity:
                    Intent intent13 = new Intent(MainActivity.this, DonationCapacityActivity.class);
                    startActivity(intent13);
                    break;

                case R.id.profile:
                    //Intent intent9 = new Intent(MainActivity.this, ProfileActivity.class);
                    //startActivity(intent9);
                    break;

                case R.id.loggout:
                    //Intent intent10 = new Intent(MainActivity.this, LoginActivity.class);
                    //startActivity(intent10);
                    break;

            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
}


