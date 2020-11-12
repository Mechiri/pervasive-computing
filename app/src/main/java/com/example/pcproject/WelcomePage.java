package com.example.pcproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class WelcomePage extends AppCompatActivity {

    private static final String TAG = "WelcomePage";
    private Button surveyB;
    private TextView name;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        surveyB = findViewById(R.id.toSurveyB);
        name = findViewById(R.id.nameTextView);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        String userId = mAuth.getCurrentUser().getEmail();
        Log.d(TAG, "OnCreate ...... coming 1");
        db.collection(userId)
                .document("myData")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {
                            userName = documentSnapshot.getString("userName");
                            name.setText("Hi, " + userName);
                            Log.d(TAG, "Username: "+userName+" End");
                            Toast.makeText(WelcomePage.this, "got username", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Log.d(TAG, "Not a exists in the document Snapshot");
                            Toast.makeText(WelcomePage.this, "Something went wrong", Toast.LENGTH_LONG).show();
                        }
                    }
                });


        Log.d(TAG, "OnCreate ...... coming 2");
        //name.setText("Hi, " + userName);

        surveyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomePage.this, initialSurvey.class));
            }
        });
    }
}