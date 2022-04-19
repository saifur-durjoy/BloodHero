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

/**
 * This Class represents the task of filtering out the Specific Blood Group's user list
 * @author Saifur Rahman Durjoy (Saifur-Durjoy)
 * @since 2022
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    /**
     * Drawer layout instance named drawer layout, to acts as a top-level container for window content
     */
    private DrawerLayout drawerLayout;
    /**
     * Toolbar instance named toolbar, to display toolbar above the displayed tab
     */
    private Toolbar toolBar;
    /**
     * NavigationView instance named navView, to generate side bar
     */
    private NavigationView navView;
    /**
     * CircleImageview instance named navProfileImage, to hold profile image
     */
    private CircleImageView navProfileImage;
    /**
     * Texteview instances to work with text of names
     */
    private TextView navFullName, navEmail, navBloodGroup, navType;
    /**
     * Instance of firebase database reference
     */
    private DatabaseReference userRef;
    /**
     * One List instance named userList, for storing user information
     */
    private RecyclerView recyclerView;
    /**
     * progress bar instance to show display of the working in mode when a activity gets called
     */
    private ProgressBar progressBar;
    private List<User> userList;
    private UserAdapter userAdapter;

    /**
     * Auto generated Java Class Built in method.
     * This method is used for establishing the connection between front end and back end.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        /**
         * connecting the instances declared above by with the front end (i.e. from layout file)
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Blood Hero");

        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.nav_view);

        /**
         * Action bar instance used to open close drawer of side bar
         */
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

        /**
         * Declaring instance to fetch info from the firebase database used
         */
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        ref.addValueEventListener(new ValueEventListener()
        {
            /**
             * Firebase class built in method to query for data
             * @param snapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                /**
                 * if type is donor then open recipient info
                 */
                String type = snapshot.child("type").getValue().toString();
                if(type.equals("donor")){
                    readRecipients();
                }
                /**
                 * if type is recipient then open donor info
                 */
                else readDonors();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });

        /**
         * getching user profileimage, full name etc to display over the side bar
         */
        navProfileImage = navView.getHeaderView(0).findViewById(R.id.nav_user_image);
        navFullName = navView.getHeaderView(0).findViewById(R.id.nav_user_fullname);
        navEmail = navView.getHeaderView(0).findViewById(R.id.nav_user_email);
        navBloodGroup = navView.getHeaderView(0).findViewById(R.id.nav_user_bloodgroup);
        navType = navView.getHeaderView(0).findViewById(R.id.nav_user_type);

        /**
         * fetch info from the firebase database used
         */
       userRef = FirebaseDatabase.getInstance().getReference().child("users").child(
               FirebaseAuth.getInstance().getCurrentUser().getUid());

        userRef.addValueEventListener(new ValueEventListener()
        {
            /**
             * Firebase class built in method to query for data
             * @param snapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (snapshot.exists()) {
                    /**
                     * if quried for user Full name then set the full name
                     */
                    String name = snapshot.child("name").getValue().toString();
                    navFullName.setText(name);
                    /**
                     * if quried for user email then set the email
                     */
                    String email = snapshot.child("email").getValue().toString();
                    navEmail.setText(email);
                    /**
                     * if quried for user blood group then set the blood group
                     */
                    String bloodGroup = snapshot.child("bloodgroup").getValue().toString();
                    navBloodGroup.setText(bloodGroup);

                    String type = snapshot.child("type").getValue().toString();
                    navType.setText(type);

                    /**
                     * if quried for profile image then fetch the image url from database and set
                     * the profile picture with the value of url
                     */
                    if(snapshot.hasChild("profilepictureurl"))
                    {
                        String imageUrl = snapshot.child("imageurl").getValue().toString();
                        Glide.with(getApplicationContext()).load(imageUrl).into(navProfileImage);
                    }
                    else
                    /**
                     * if quried takes longer to load then set user profile image with
                     * image from drawer layout
                     */
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

    }

    /**
     * method to fetch the info of the recipients if you are loggen in as a donor
     */
    private void readRecipients()
    {
        /**
         * Declaring instance to fetch info from the firebase database used
         */
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
        Query query = reference.orderByChild("type").equalTo("recipient");
        query.addValueEventListener(new ValueEventListener()
        {
            /**
             * Firebase class built in method to query for data
             * @param snapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                /**
                 * clear the list of data fetched before new data added
                 */
                userList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    User user = snapshot.getValue(User.class);
                    userList.add(user);
                }
                progressBar.setVisibility(View.GONE);
                /**
                 * if user list is empty promt a message
                 */
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
    /**
     * method to fetch the info of the donors if you are logged in as a recipients
     */
    private void readDonors()
    {
        /**
         * Declaring instance to fetch info from the firebase database used
         */
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
        Query query = reference.orderByChild("type").equalTo("donor");
        query.addValueEventListener(new ValueEventListener()
        {
            /**
             * Firebase class built in method to query for data
             * @param snapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                /**
                 * clear the list of data fetched before new data added
                 */
                userList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    User user = snapshot.getValue(User.class);
                    userList.add(user);
                }
                userAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                /**
                 * if user list is empty promt a message
                 */
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

    /**
     * This is the method which works when any activity gets clicked from the sidebar
     * and makes the connection with that activity and switches to that activity
     * @param item
     */
        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                /**
                 * redirects to A+ tab if it gets clicked
                 */
                case R.id.aplus:
                    Intent intent1 = new Intent(MainActivity.this, CategorySelectedActivity.class);
                    intent1.putExtra("group", "A+");
                    startActivity(intent1);
                    break;
                /**
                 * redirects to A- tab if it gets clicked
                 */

                case R.id.aminus:
                    Intent intent2 = new Intent(MainActivity.this, CategorySelectedActivity.class);
                    intent2.putExtra("group", "A-");
                    startActivity(intent2);
                    break;
                /**
                 * redirects to B+ tab if it gets clicked
                 */

                case R.id.bplus:
                    Intent intent3 = new Intent(MainActivity.this, CategorySelectedActivity.class);
                    intent3.putExtra("group", "B+");
                    startActivity(intent3);
                    break;
                /**
                 * redirects to B- tab if it gets clicked
                 */

                case R.id.bminus:
                    Intent intent4 = new Intent(MainActivity.this, CategorySelectedActivity.class);
                    intent4.putExtra("group", "B-");
                    startActivity(intent4);
                    break;
                /**
                 * redirects to AB+ tab if it gets clicked
                 */

                case R.id.abplus:
                    Intent intent5 = new Intent(MainActivity.this, CategorySelectedActivity.class);
                    intent5.putExtra("group", "AB+");
                    startActivity(intent5);
                    break;
                /**
                 * redirects to AB- tab if it gets clicked
                 */

                case R.id.abnegative:
                    Intent intent6 = new Intent(MainActivity.this, CategorySelectedActivity.class);
                    intent6.putExtra("group", "AB-");
                    startActivity(intent6);
                    break;
                /**
                 * redirects to O+ tab if it gets clicked
                 */

                case R.id.oplus:
                    Intent intent7 = new Intent(MainActivity.this, CategorySelectedActivity.class);
                    intent7.putExtra("group", "O+");
                    startActivity(intent7);
                    break;
                /**
                 * redirects to O- tab if it gets clicked
                 */

                case R.id.onegative:
                    Intent intent8 = new Intent(MainActivity.this, CategorySelectedActivity.class);
                    intent8.putExtra("group", "O-");
                    startActivity(intent8);
                    break;
                /**
                 * redirects to Compatible users tab if it gets clicked
                 */

                case R.id.compatible:
                    Intent intent11 = new Intent(MainActivity.this, CategorySelectedActivity.class);
                    intent11.putExtra("group", "Compatible with me");
                    startActivity(intent11);
                    break;
                /**
                 * redirects to blood volume calculation tab if it gets clicked
                 */

                case R.id.bloodvolume:
                    Intent intent12 = new Intent(MainActivity.this, BloodVolume.class);
                    startActivity(intent12);
                    break;
                /**
                 * redirects to donation capacity tab if it gets clicked
                 */

                case R.id.donationCapacity:
                    //Intent intent13 = new Intent(MainActivity.this, DonationCapacityActivity.class);
                    //startActivity(intent13);
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
}


