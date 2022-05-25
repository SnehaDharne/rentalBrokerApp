package com.example.houserentalsapp;

import android.content.Intent;
import android.os.Bundle;
//import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class CustomerPanel extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    ShowDetailAdapter showDetailAdapter;
    ArrayList<ShowDetails> list;


    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    //String[] s1;
   // String[] s2;
    //int[] images = {R.drawable.img1,R.drawable.img2, R.drawable.img3,R.drawable.img1,R.drawable.img5,
           // R.drawable.img1,R.drawable.img3,R.drawable.img5,R.drawable.img5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        recyclerView = findViewById(R.id.recyclerView);
        database = FirebaseDatabase.getInstance().getReference("Havelis");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        list = new ArrayList<>();
        showDetailAdapter = new ShowDetailAdapter(this,list);
        recyclerView.setAdapter(showDetailAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ShowDetails showDetails = dataSnapshot.getValue(ShowDetails.class);
                    list.add(showDetails);
                }
                showDetailAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





//        s1 = getResources().getStringArray(R.array.house_list);
//        s2 = getResources().getStringArray(R.array.description_id);
//
//        HouseAdapter houseAdapter = new HouseAdapter(this, s1, s2, images);
//        recyclerView.setAdapter(houseAdapter);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener((new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.nav_home){
                    Toast.makeText(CustomerPanel.this, "home", Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId() == R.id.nav_calculator)
                {
                    Intent calIntent = new Intent(CustomerPanel.this, calculator.class);
                    startActivity(calIntent);
                }
                if(item.getItemId() == R.id.nav_about_us)
                {
                    Intent calIntent = new Intent(CustomerPanel.this, AboutUs.class);
                    startActivity(calIntent);
                }
                if(item.getItemId() == R.id.links){
                    Intent calIntent = new Intent(CustomerPanel.this, usefulLinks.class);
                    startActivity(calIntent);
                }
                if(item.getItemId() == R.id.logOut) {
                    mAuth.signOut();
                    startActivity(new Intent(CustomerPanel.this, MainActivity.class));
                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        }));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CustomerPanel.this, "Adding house on rent", Toast.LENGTH_SHORT).show();
                Intent calIntent = new Intent(CustomerPanel.this, TakeDetails.class);
                startActivity(calIntent);

            }
        });

        updateNavHeader();


    }
    public void updateNavHeader(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUser = headerView.findViewById(R.id.hi_user);
        String mail = currentUser.getEmail();
        int index = mail.indexOf('@');
        char first = mail.charAt(0);
        first -= 32;
        String user = "Hi, " +  first + mail.substring(1,index);
        navUser.setText(user + "!");
    }
}