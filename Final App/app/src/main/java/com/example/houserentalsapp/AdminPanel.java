package com.example.houserentalsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminPanel extends AppCompatActivity {
    Button btnPanel, btnpanel1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        btnPanel =  findViewById(R.id.btnPanel3);
        btnpanel1 = findViewById(R.id.btnPanel2);
        btnPanel.setOnClickListener(view -> {
            Intent intent = new Intent(AdminPanel.this, CustomerPanel.class);
            startActivity(intent);
        });
        btnpanel1.setOnClickListener(view -> {
            Intent Detailintent = new Intent(AdminPanel.this, TakeDetails.class);
            startActivity(Detailintent);
        });
    }
}