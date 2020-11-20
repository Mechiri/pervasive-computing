package com.example.pcproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ActiveUserName extends AppCompatActivity {

    //TAG
    private static final String TAG = "ActiveUserName";

    //the User
    AppUser appUser;

    //Username
    private EditText editTextUserName;
    //next Button
    private ImageView imageButton;

    //Database Variables
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_user_name);

        //Initialization
        editTextUserName = findViewById(R.id.editTextUserName);
        imageButton = findViewById(R.id.imageButton);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUserName();
                startActivity(new Intent(ActiveUserName.this, WelcomePage.class));
            }
        });
    }

    protected void checkUserName()
    {
        String userName = editTextUserName.getText().toString();

        if(!userName.isEmpty())
        {
            appUser = new AppUser();
            appUser.setUserName(userName);

            String userId = mAuth.getCurrentUser().getEmail();
            Map<String, Object> userData = new HashMap<>();
            userData.put("userName", userName);
            userData.put("timestamp", FieldValue.serverTimestamp());
            Log.d(TAG,"uploadUserName: coming!");
            db.collection(userId).document("myData").set(userData)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            //Toast.makeText(ActiveUserName.this, "Uploaded Username to database", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Database: Upload Username");

                            //0.30 Activity
                            //Intent intent = new Intent(ActiveUserName.this,<write 0.30 class name>);
                            //startActivity();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ActiveUserName.this, "Error in Uploading Data", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Database: Upload Failure");
                        }
                    });
        }
        else
        {
            Toast.makeText(ActiveUserName.this, "Username shouldn't be empty", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Username shouldn't be empty");
        }
    }
}