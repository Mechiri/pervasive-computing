package com.example.pcproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

/*
    A user's profile page that allows user to access information from initial survey,
    such as love languages and ideal relationship qualities, and allows user's to
    logout.
 */

public class userProfile extends AppCompatActivity {

    private Button backB;
    private CardView relationshipExp;
    private CardView loveLang;
    private Button logoutB;
    private TextView accountSetting;
    private TextView helpSupport;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        backB = findViewById(R.id.profileBackB);
        relationshipExp = findViewById(R.id.relationExp);
        loveLang = findViewById(R.id.loveLang);
        logoutB = findViewById(R.id.logoutB);
        accountSetting = findViewById(R.id.accountSetting);
        helpSupport = findViewById(R.id.helpSupport);
        mAuth = FirebaseAuth.getInstance();

        //Sends user back to dashboard landing page of app
        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(userProfile.this, LandingPage.class));
            }
        });

        //Sends user to a future development/under construction page of app
        relationshipExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(userProfile.this, futureDev.class));
            }
        });

        //Sends user to a future development/under construction page of app
        loveLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(userProfile.this, futureDev.class));
            }
        });

        //Button to call logout() function, allowing a user to sign out
        logoutB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        //Sends user to a future development/under construction page of app
        accountSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(userProfile.this, futureDev.class));
            }
        });

        //Sends user to a future development/under construction page of app
        helpSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(userProfile.this, futureDev.class));
            }
        });
    }

    //function to allow a current user to logout of the app
    protected void logout()
    {
        mAuth.signOut();
        startActivity(new Intent(this, ActiveLogin.class));
    }
}