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
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActiveLogin extends AppCompatActivity {

    private static final String TAG = "ActiveLogin";

    private TextInputLayout textLayoutUsername;
    private TextInputLayout textLayoutPassword;
    private Button buttonLogin;
    private TextView textViewSignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_login);

        textLayoutUsername = findViewById(R.id.textLayoutUsername);
        textLayoutPassword = findViewById(R.id.textLayoutPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewSignUp = findViewById(R.id.textViewSignUp);
        mAuth = FirebaseAuth.getInstance();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();
            }
        });

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActiveLogin.this, ActiveSignUp.class);
                startActivity(intent);
            }
        });
    }

    protected void SignIn()
    {
        //get the credentials
        String email = textLayoutUsername.getEditText().getText().toString();
        String password = textLayoutPassword.getEditText().getText().toString();
        String regex = "[A-Za-z0-9.+_%-]+@[A-Za-z0-9-]+\\.[A-Za-z]{2,}";

        if(email.isEmpty())
        {
            textLayoutUsername.setError("Email should not be empty");
            Toast.makeText(ActiveLogin.this, "Email should not be empty", Toast.LENGTH_SHORT).show();
            Log.d(TAG,"Email is Empty");
        }
        else if(!email.matches(regex))
        {
            textLayoutUsername.setError("Enter valid Email-id");
            Toast.makeText(ActiveLogin.this, "Enter valid Email-id", Toast.LENGTH_SHORT).show();
            Log.d(TAG,"Email is not matching regex");
        }
        else if(password.length()<6 || password.length()>15)
        {
            textLayoutUsername.setError(null);
            textLayoutUsername.setErrorEnabled(false);

            textLayoutPassword.setError("Password length should be than 6 - 15");
            Toast.makeText(ActiveLogin.this, "Password length should be than 6 - 15", Toast.LENGTH_SHORT).show();
            Log.d(TAG,"Password length should be than 6 - 15");
        }
        else
        {
            textLayoutPassword.setError(null);
            textLayoutPassword.setErrorEnabled(false);

            //check login
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Log.d(TAG, "Login Successful");
                                Toast.makeText(ActiveLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                //0.30 Activity
                                //Intent intent = new Intent(ActiveUserName.this,<write 0.30 class name>);
                                //startActivity();
                            }
                            else
                            {
                                Log.w(TAG,"Login Unsuccessful", task.getException());
                                Toast.makeText(ActiveLogin.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


}