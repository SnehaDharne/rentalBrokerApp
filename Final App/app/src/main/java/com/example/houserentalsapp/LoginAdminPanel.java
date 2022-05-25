package com.example.houserentalsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginAdminPanel extends AppCompatActivity {
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin_panel);

        loginBtn = (Button) findViewById(R.id.btnSignIn2);

        loginBtn.setOnClickListener(view -> {
            Intent intent = new Intent(LoginAdminPanel.this, AdminPanel.class);
           startActivity(intent);
        });

    }
}