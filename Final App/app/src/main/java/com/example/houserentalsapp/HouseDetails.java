package com.example.houserentalsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class HouseDetails extends AppCompatActivity {
    ImageView imageDetail;
    RatingBar rt;
    TextView location, price, deposit, description, contact;
    Button button1, button2;
    String data, data2,data3,data4,data5,data6;
    int mImage;
    String email = "snehadharne10@gmail.com";

    private FirebaseAuth mAuth;

    public void submitRating(View view){
        RatingBar rt = findViewById(R.id.ratingBar);
        float str = rt.getRating();
        Toast.makeText(this, "Rated " + str + " stars", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_second);
        mAuth = FirebaseAuth.getInstance();

        imageDetail = findViewById(R.id.imgDetail);
        location = findViewById(R.id.textDetail1);
        price = findViewById(R.id.textDetail2);
        deposit = findViewById(R.id.textDetail3);
        description = findViewById(R.id.textDetail4);
        contact = findViewById(R.id.textDetail5);
        button1 =  findViewById(R.id.button4);
        button2 = findViewById(R.id.button5);
        rt = findViewById(R.id.ratingBar);


        getData();
        setData();
        button1.setOnClickListener(view -> {
            Intent dial_intent = new Intent(Intent.ACTION_DIAL);
            dial_intent.setData(Uri.parse("tel:"+data5));
            startActivity(dial_intent);
        });
        button2.setOnClickListener(view -> {
            Intent email_intent = new Intent(Intent.ACTION_SENDTO,Uri.fromParts("mailto",email, null));
            startActivity(Intent.createChooser(email_intent,"Send Email"));

        });

        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener((new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    Toast.makeText(HouseDetails.this, "home", Toast.LENGTH_SHORT).show();
                    Intent calIntent = new Intent(HouseDetails.this, CustomerPanel.class);
                    startActivity(calIntent);
                }
                if (item.getItemId() == R.id.nav_calculator) {
                    Intent calIntent = new Intent(HouseDetails.this, calculator.class);
                    startActivity(calIntent);
                }
                if (item.getItemId() == R.id.nav_about_us) {
                    Intent calIntent = new Intent(HouseDetails.this, AboutUs.class);
                    startActivity(calIntent);
                }
                if (item.getItemId() == R.id.links) {
                    Intent calIntent = new Intent(HouseDetails.this, usefulLinks.class);
                    startActivity(calIntent);
                }
                if (item.getItemId() == R.id.logOut) {
                    mAuth.signOut();
                    startActivity(new Intent(HouseDetails.this, MainActivity.class));
                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        }));



    }
    public void getData(){
        if (getIntent().hasExtra("mImage") && getIntent().hasExtra("data") && getIntent().hasExtra("data2") && getIntent().hasExtra("data3") && getIntent().hasExtra("data4")&& getIntent().hasExtra("data5")){
            data = getIntent().getStringExtra("data");
            data2 = getIntent().getStringExtra("data2");
            data3 = getIntent().getStringExtra("data3");
            data4 = getIntent().getStringExtra("data4");
            data5 = getIntent().getStringExtra("data5");

           mImage = getIntent().getIntExtra("Image", 1);
            data6 = getIntent().getStringExtra("rating");


        }
        else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
    public void setData(){

        location.setText("Location : "+data);
        price.setText("Price : "+data2);
        description.setText("Particulars :"+data3);
        deposit.setText("Deposit :"+data4);
        //contact.setText(data5);
        Picasso.get().load(mImage).into(imageDetail);
        //imageDetail.setImageResource(mImage);
        rt.setRating(Float.parseFloat(data6));
    }



}