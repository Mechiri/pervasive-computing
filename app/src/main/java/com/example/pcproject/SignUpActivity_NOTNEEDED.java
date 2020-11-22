package com.example.pcproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/*
    Initial Temporary Sign Up activity
 */

public class SignUpActivity_NOTNEEDED extends AppCompatActivity {

    //TAG
    private static final String TAG = "SignUpActivity";

    //Layout Variables
    private EditText etEmail;
    private EditText etPassword;
    private EditText etPassword2;
    private Button bSignUp;
    private TextView tvLogin;

    //Database Variables
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //set All Variables
        etEmail = findViewById(R.id.editTextEmailSignUp);
        etPassword = findViewById(R.id.editTextPasswordSignUp);
        etPassword2 = findViewById(R.id.editTextPassword2Signup);
        tvLogin = findViewById(R.id.textViewLoginPage);
        bSignUp = findViewById(R.id.buttonCreateUser);

        mAuth = FirebaseAuth.getInstance();

        //call SignUp
        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        //login page
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity_NOTNEEDED.this, MainActivity_NOTNEEDED.class));
            }
        });
    }

    protected void signUp()
    {
        //Get String from user
        String email = etEmail.getText().toString();
        //Regex for Email
        String regex = "[A-Za-z0-9.+_%-]+@[A-Za-z0-9-]+\\.[A-Za-z]{2,}";
        boolean regexStatus = false;

        if(email.isEmpty())
        {
            etEmail.setError("Please Enter Email-Id");
            etEmail.requestFocus();
            Log.d(TAG,"Email is Empty");
        }
        if(!email.matches(regex))
        {
            etEmail.setError("Please enter valid Email-Id");
            etEmail.requestFocus();
            Log.d(TAG,"Email is not matching regex");
        }
        else
        {
            regexStatus = true;
        }

        String password = etPassword.getText().toString();
        if(password.length()<6)
        {
            etPassword.setError("Password length sgould be minimum 6");
            etPassword.requestFocus();
            Log.d(TAG,"Password length is less than 6");
        }
        String password2 = etPassword2.getText().toString();
        if(password2.length()<6)
        {
            etPassword2.setError("Password length sgould be minimum 6");
            etPassword2.requestFocus();
            Log.d(TAG,"Password length is less than 6");
        }
        if(!password.equals(password2))
        {
            etPassword2.setError("Passwords should equal");
            Log.d(TAG,"Passwords are not matching");
            etPassword2.requestFocus();
        }

        Log.d(TAG,"Email entered successfully");
        Log.d(TAG,"Passwords entered successfully");

        if( !email.isEmpty() && regexStatus && !password.isEmpty() )
        {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "User created Successfully");
                                Toast.makeText(SignUpActivity_NOTNEEDED.this, "Successful! Please go to login page for login", Toast.LENGTH_SHORT).show();
                                //mAuth.signOut();
                            } else {
                                Log.d(TAG, "User creation UnSuccessful");
                                Toast.makeText(SignUpActivity_NOTNEEDED.this, "User creation UnSuccessful, Please try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}