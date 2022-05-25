package com.example.houserentalsapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    EditText userId,password,name, confPass;
    Button sgnIn_btn, AlreadySgnUp_btn;
    private FirebaseAuth mAuth;

    private void createUser(){
        String email = userId.getText().toString();
        String password1 = password.getText().toString();

        if (TextUtils.isEmpty(email)){
            userId.setError("Email cannot be empty");
            userId.requestFocus();
        }else if (TextUtils.isEmpty(password1)){
            password.setError("Password cannot be empty");
            password.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(SignUp.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUp.this, MainActivity.class));
                    }else{
                        Toast.makeText(SignUp.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        userId = findViewById(R.id.RegUsername);
        password = findViewById(R.id.RegPassword);
        confPass = findViewById(R.id.RegRePassword);
        name = findViewById(R.id.RegRePassword);

        sgnIn_btn = findViewById(R.id.btnSignUp);
        AlreadySgnUp_btn = findViewById(R.id.btnSignInReg);

        sgnIn_btn.setOnClickListener(view -> {
            //creating Tenant Entity
            final TenantEntity tenantEntity = new TenantEntity();
            tenantEntity.setUserId(userId.getText().toString());
            tenantEntity.setPassword(password.getText().toString());

            tenantEntity.setName(name.getText().toString());

            if (confPass.getText().toString().equals(password.getText().toString())){

                // do insert operation
//                TenantDatabase tenantDatabase = TenantDatabase.getTenantDatabase(getApplicationContext());
//                TenantDao tenantDao = tenantDatabase.tenantDao();
//
//                new Thread(() -> {
//                    //register tenant
//                    tenantDao.registerTenant(tenantEntity);
//
//                    runOnUiThread(() ->
//                            Toast.makeText(getApplicationContext(), "You are Registered", Toast.LENGTH_SHORT).show());
//                }).start();
                mAuth.createUserWithEmailAndPassword(userId.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "User registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(), "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
            else{
                Toast.makeText(getApplicationContext(), "Fill all the fields", Toast.LENGTH_SHORT).show();
            }
        });
        AlreadySgnUp_btn.setOnClickListener(view -> {
            Intent intent = new Intent(SignUp.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
    private Boolean validateInput(TenantEntity tenantEntity){
        return !tenantEntity.getUserId().isEmpty() && !tenantEntity.getPassword().isEmpty() &&
                !tenantEntity.getName().isEmpty() && tenantEntity.getPassword().equals(tenantEntity.getPassword());
    }
}