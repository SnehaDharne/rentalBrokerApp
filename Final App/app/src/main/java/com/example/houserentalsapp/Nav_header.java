package com.example.houserentalsapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Nav_header extends AppCompatActivity {
    private FirebaseAuth user;

    String uid = user.getUid();
    TextView t1 = (TextView)findViewById(R.id.hi_user);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_drawer);
        t1.setText("Hello Aniket");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String name = user.getDisplayName();
    }
}
