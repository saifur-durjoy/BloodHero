package com.example.bloodhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the Sent Email notification and shows that who sent the mail
 * CSE327 project documentations
 * @author Arfana Rahman_1831172042
 * @since 2022
 */

public class SentEmailActivity extends AppCompatActivity {
    /**
     * One Toolbar instance named toolbar, to display toolbar above the displayed tab
     */
    private Toolbar toolbar;

    /**
     * One RecyclerView instance named recyclerView, for displaying in list
     */
    private RecyclerView recyclerView;

    List<String> idList;
    List<User> userList;
    UserAdapter userAdapter;

    /**
     * This method is used for making connection between front end and back end.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_email);
        /**
         * connecting the instances declared above with the front end
         */
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("people sent Emails");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        recyclerView = findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        userList = new ArrayList<>();
        userAdapter = new UserAdapter(SentEmailActivity.this, userList);
        recyclerView.setAdapter(userAdapter);

        idList = new ArrayList<>();
        getIdOfUsers();

    }

    private void getIdOfUsers() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("emails")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()
        );

      reference.addValueEventListener(new ValueEventListener() {

          /**
           * Firebase class built in method to query for data and updates
           * @param snapshot
           */
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
              idList.clear();
              for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                  idList.add(dataSnapshot.getKey());
              }
              showUsers();
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
      });
    }

    private void showUsers() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("user");

        reference.addValueEventListener(new ValueEventListener() {
            /**
             * Firebase class built in method to query for data
             * @param snapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);

                    for (String id : idList){

                        if(user.getId().equals(id)){
                            userList.add(user);
                        }
                    }
                 }

                userAdapter.notifyDataSetChange();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}