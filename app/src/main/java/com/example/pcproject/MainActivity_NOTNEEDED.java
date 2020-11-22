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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity_NOTNEEDED extends AppCompatActivity {

    //TAG
    private static final String TAG = "MainActivity";

    //Variables
    private EditText etEmail;
    private EditText etPass;
    private Button bLogin;
    private TextView tvSignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set Variables
        etEmail = findViewById(R.id.editTextPersonEmail);
        etPass = findViewById(R.id.editTextPassword);
        bLogin = findViewById(R.id.buttonlogin);
        tvSignUp = findViewById(R.id.textViewSignup);
        mAuth = FirebaseAuth.getInstance();

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity_NOTNEEDED.this, SignUpActivity_NOTNEEDED.class));
            }
        });
    }

    //onStart
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            startActivity(new Intent(this, ProfileActivity_NOTNEEDED.class));
        }
    }

    //Login
    protected void login()
    {
        //get the credentials
        String email = etEmail.getText().toString();
        String password = etPass.getText().toString();

        //check login
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Log.d(TAG, "Login Successful");
                            startActivity(new Intent(MainActivity_NOTNEEDED.this, ProfileActivity_NOTNEEDED.class));
                        }
                        else
                        {
                            Log.w(TAG,"Login Unsuccessful", task.getException());
                            Toast.makeText(MainActivity_NOTNEEDED.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}