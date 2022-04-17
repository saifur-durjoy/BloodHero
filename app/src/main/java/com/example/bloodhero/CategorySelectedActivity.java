package com.example.bloodhero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;

import androidx.appcompat.widget.Toolbar;

import com.example.bloodhero.Adapter.UserAdapter;
import com.example.bloodhero.Model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CategorySelectedActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private List<User> userList;
    private Adapter userAdapter;

    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selected);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

        if(getIntent().getExtras() != null){
            title = getIntent().getStringExtra("group");
            getSupportActionBar().setTitle("Blood group" + title);

            readUsers();
        }

    }

    private void readUsers() {

    }

}