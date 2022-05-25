package com.example.houserentalsapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText userId,password;
    Button signUp_btn, signIn_btnLog;
    TextView adminLink;
    private FirebaseAuth mAuth;

    private void loginUser(){
        String email = userId.getText().toString();
        String passwordText = password.getText().toString();

        if (TextUtils.isEmpty(email)){
            userId.setError("Email cannot be empty");
            userId.requestFocus();
        }else if (TextUtils.isEmpty(passwordText)){
            userId.setError("Password cannot be empty");
            password.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, CustomerPanel.class));
                    }else{
                        Toast.makeText(MainActivity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            startActivity(new Intent(MainActivity.this, CustomerPanel.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userId = (EditText) findViewById(R.id.btnUsernameSgnIn);
        password = (EditText)findViewById(R.id.btnPasswordSgIn);
        signIn_btnLog = (Button)findViewById(R.id.btnSignIn);
        signUp_btn = (Button) findViewById(R.id.btnSignUpSgn);
        adminLink = findViewById(R.id.adminPaneLink);


        mAuth = FirebaseAuth.getInstance();



        signUp_btn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SignUp.class);
            startActivity(intent);
        });
        signIn_btnLog.setOnClickListener(view -> {
            String userIdText =userId.getText().toString();
            String passwordText =password.getText().toString();

            if (userIdText.isEmpty() || passwordText.isEmpty()){
                Toast.makeText(getApplicationContext(), "Fill all the Fields", Toast.LENGTH_SHORT).show();
            }
            else {
                //perform query
//                TenantDatabase tenantDatabase = TenantDatabase.getTenantDatabase(getApplicationContext());
//                final TenantDao tenantDao = tenantDatabase.tenantDao();
//                new Thread(() -> {
//                    TenantEntity tenantEntity = tenantDao.btnSignIn(userIdText,passwordText);
//
//                    if (tenantEntity == null){
//                        runOnUiThread(() ->
//                                Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show());
//                    }else {
//                        Intent intent = new Intent(MainActivity.this, CustomerPanel.class);
//                        startActivity(intent);
//
//                    }
//                }).start();
                loginUser();
            }
        });

        adminLink.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),LoginAdminPanel.class);
             startActivity(intent);
         });




    }
}