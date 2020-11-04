package com.example.pcproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class ActiveSignUp extends AppCompatActivity {

    //TAG
    private static final String TAG = "ActiveSignUp";

    //Next Button
    ImageView buttonNext;
    //Email Text
    TextInputLayout textInputEmail;
    //Database
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_sign_up);

        //Initialization
        buttonNext = findViewById(R.id.buttonNext);
        textInputEmail = findViewById(R.id.textInputEmail);
        mAuth = mAuth = FirebaseAuth.getInstance();

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidEmail();
            }
        });
    }

    protected  void checkValidEmail()
    {
        //Get String from user
        final String email = textInputEmail.getEditText().getText().toString();
        Log.d(TAG,"Entered Email is:" + email);
        //Regex for Email
        String regex = "[A-Za-z0-9.+_%-]+@[A-Za-z0-9-]+\\.[A-Za-z]{2,}";
        boolean regexStatus = false;

        if(email.isEmpty())
        {
            textInputEmail.setError("Please Enter Email-Id");
            Toast.makeText(ActiveSignUp.this, "Email should not be empty", Toast.LENGTH_SHORT).show();
            Log.d(TAG,"Email is Empty");
        }
        else if(!email.matches(regex))
        {
            textInputEmail.setError("Please enter valid Email-Id");
            Toast.makeText(ActiveSignUp.this, "Enter valid Email-id", Toast.LENGTH_SHORT).show();
            Log.d(TAG,"Email is not matching regex");
        }
        else
        {
            textInputEmail.setError(null);
            textInputEmail.setErrorEnabled(false);

            mAuth.fetchSignInMethodsForEmail(email)
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if(task.isSuccessful())
                        {
                            boolean check = !task.getResult().getSignInMethods().isEmpty();
                            if(check)
                            {
                                textInputEmail.requestFocus();
                                Toast.makeText(ActiveSignUp.this, "Email-Id exists choose another", Toast.LENGTH_SHORT).show();
                                Log.d(TAG,"email exists in database");
                            }
                            else
                            {
                                Log.d(TAG,"email not exists: "+email+"");
                                Intent intent = new Intent(ActiveSignUp.this, ActivePassword.class);
                                intent.putExtra("email", email);
                                startActivity(intent);
                            }
                        }
                        else {
                            Log.d(TAG, "Please connect to Internet");
                            Toast.makeText(ActiveSignUp.this, "Check Internet connection", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }
    }
}