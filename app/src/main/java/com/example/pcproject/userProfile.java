package com.example.pcproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class userProfile extends AppCompatActivity {

    private Button backB;
    private CardView relationshipExp;
    private CardView loveLang;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        backB = findViewById(R.id.profileBackB);
        relationshipExp = findViewById(R.id.relationExp);
        loveLang = findViewById(R.id.loveLang);
        mAuth = FirebaseAuth.getInstance();

        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(userProfile.this, LandingPage.class));
            }
        });

        relationshipExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(userProfile.this, futureDev.class));

                //We need to replace the below code in logout button
                logout();
            }
        });

        loveLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(userProfile.this, futureDev.class));
            }
        });
    }

    protected void logout()
    {
        mAuth.signOut();
        startActivity(new Intent(this, ActiveLogin.class));
    }
}