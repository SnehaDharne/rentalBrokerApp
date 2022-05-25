package com.example.houserentalsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class TakeDetails extends AppCompatActivity {
    TextView Location,Price,Description,Contact,Deposit;
    Button post;
    public ImageView ProfilePic;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    public String randomKey;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_details);

        Location = findViewById(R.id.textDetail1);
        Price = findViewById(R.id.textDetail2);
        Description = findViewById(R.id.textDetail3);
        Contact = findViewById(R.id.textDetail4);
        Deposit = findViewById(R.id.textDetail5);
        post = findViewById(R.id.button);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Firebase","Button pressed!");
                rootNode = FirebaseDatabase.getInstance("https://house-rentals-app-default-rtdb.firebaseio.com/");
                reference = rootNode.getReference("Havelis");


                //Get all the values
                String location = Location.getText().toString();
                String description = Description.getText().toString();
                String contact = Contact.getText().toString();
                String price = Price.getText().toString();
                String deposit = Deposit.getText().toString();


                DataHelper helperClass = new DataHelper(location,price,contact,description,deposit,randomKey);
                reference.child(location).setValue(helperClass);
                Toast.makeText(TakeDetails.this, "House Ad Posted", Toast.LENGTH_SHORT).show();
                Intent calIntent = new Intent(TakeDetails.this, CustomerPanel.class);
                startActivity(calIntent);
            }

        });

        ProfilePic = findViewById(R.id.Profilepic);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        ProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });

    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            ProfilePic.setImageURI(imageUri);
            uploadPicture();
        }

    }

    public void uploadPicture() {
        // Create a reference to "mountains.jpg"

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();
        randomKey = UUID.randomUUID().toString();
        StorageReference mountainsRef = storageReference.child("images/" + randomKey);
        mountainsRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pd.dismiss();
                Snackbar.make(findViewById(android.R.id.content),"Image Uploaded.",Snackbar.LENGTH_LONG).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(),"Failed to Upload",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progressPercent = (100.00 * snapshot.getBytesTransferred()/ snapshot.getTotalByteCount());
                        pd.setMessage("Uploading " + (int)progressPercent + "%");
                    }
                });

//// Create a reference to 'images/mountains.jpg'
//        StorageReference mountainImagesRef = storageReference.child("images/mountains.jpg");
//
//// While the file names are the same, the references point to different files
//        mountainsRef.getName().equals(mountainImagesRef.getName());    // true
//        mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false
    }
}