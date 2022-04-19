package com.example.bloodhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Adapter;

import androidx.appcompat.widget.Toolbar;

import com.example.bloodhero.Adapter.UserAdapter;
import com.example.bloodhero.Model.User;
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
public class CategorySelectedActivity extends AppCompatActivity
{
    /**
     * One Toolbar instance named toolbar, to display toolbar above the displayed tab
     */
    private Toolbar toolBar;
    /**
     * One RecyclerView instance named recyclerView, for displaying in list
     */
    private RecyclerView recyclerView;
    /**
     * One List instance named userList, for storing user information
     */
    private List<User> userList;
    /**
     * One List Adapter instance named userAdapter, for fetching user info
     */
    private Adapter userAdapter;
    /**
     * One String instance named title, for fetching containing string data
     */
    private String title = "";

    /**
     * Auto generated Java Class Built in method.
     * This method is used for establishing the connection between front end and back end.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selected);

        /**
         * connecting the instances declared above with the front end (i.e. from layout file)
         */
        toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        userList = new ArrayList<>();
        userAdapter = (Adapter) new UserAdapter(CategorySelectedActivity.this, userList);
        recyclerView.setAdapter((RecyclerView.Adapter) userAdapter);

        /**
         * setting compatible tab's title
         */

        if(getIntent().getExtras() != null)
        {
            title = getIntent().getStringExtra("group");
            getSupportActionBar().setTitle("Blood group" + title);

            /**
             * if the string value then shows the compatible users
             */
            if(title.equals("Compatible with me"))
            {
               // getCompatibleUsers();
                getSupportActionBar().setTitle("Compatible with me");
            }else
            {
               // readUsers();
            }
        }
    }

    /**
     * This method filters out and displays the users compatible with the currently loggen in user
     * by fetching info from the database
     */
    private void getCompatibleUsers()
    {
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
                String result;
                String type = snapshot.child("type").getValue().toString();
                /**
                 * if current user type is donor then only show the recipients
                 */
                if(type.equals("donor"))
                {
                    result = "recipient";
                }
                /**
                 * if current user type is recipient then only show the donors
                 */
                else
                {
                    result = "donor";
                }

                String bloodGroup = snapshot.child("bloodgroup").getValue().toString();

                DatabaseReference reference =FirebaseDatabase.getInstance().getReference().child("users");
                Query query = reference.orderByChild("search").equalTo(result+bloodGroup);
                query.addValueEventListener(new ValueEventListener()
                {
                    /**
                     * Firebase class built in method to query for data and updates
                     * @param snapshot
                     */
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        userList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            User user = dataSnapshot.getValue(User.class);
                            userList.add(user);
                        }
                        //userAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }
    /**
     * This method read the users data and only shows the users with filtering out by the blood group stored
     * in the database
     */
    private void readUsers()
    {
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
                String result;
                String type = snapshot.child("type").getValue().toString();
                /**
                 * if current user type is donor then only show the recipients
                 */
                if(type.equals("donor"))
                {
                    result = "recipient";
                }
                /**
                 * if current user type is recipient then only show the donors
                 */
                else
                {
                    result = "donor";
                }

                DatabaseReference reference =FirebaseDatabase.getInstance().getReference().child("users");
                Query query = reference.orderByChild("search").equalTo(result + title);
                query.addValueEventListener(new ValueEventListener()
                {
                    /**
                     * Firebase class built in method to query for data and updates
                     * @param snapshot
                     */
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        userList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            User user = dataSnapshot.getValue(User.class);
                            userList.add(user);
                        }
                        //userAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }
    /**
     * This method takes user to a new activity to home when clicked
     * @param item
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

