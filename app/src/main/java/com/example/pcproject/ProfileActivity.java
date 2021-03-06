package com.example.pcproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    //TAG
    private static final String TAG = "ProfileActivity";

    //Variables
    private TextView tvUser;
    private Button bLogout;
    private Button bProfileOutput;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvUser = findViewById(R.id.textViewUser);
        bLogout = findViewById(R.id.buttonLogout);
        bProfileOutput = findViewById(R.id.buttonOutput);
        mAuth = FirebaseAuth.getInstance();


        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.addButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, EventForm.class));
            }
        });


        bProfileOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, SampleProfileActivity.class));
            }
        });
        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null)
            startActivity(new Intent(this, MainActivity.class));
        else
        {
            String email = currentUser.getEmail();
            tvUser.setText("Hello: "+ email);
        }
    }

    protected void logout()
    {
        mAuth.signOut();
        startActivity(new Intent(this, MainActivity.class));
    }
}