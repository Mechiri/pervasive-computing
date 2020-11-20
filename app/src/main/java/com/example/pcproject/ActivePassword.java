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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivePassword extends AppCompatActivity {

    //TAG
    private static final String TAG = "ActivePassword";


    //Next Button
    ImageView buttonUserName;
    //Password Text
    EditText textInputPassword;
    //Database
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_password);

        //Initialization
        buttonUserName = findViewById(R.id.buttonUserName);
        textInputPassword = findViewById(R.id.textInputPassword);
        mAuth = FirebaseAuth.getInstance();

        buttonUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }

    protected void createUser()
    {
        String password = textInputPassword.getText().toString();
        Log.d(TAG,"Input Password: "+password);
        if(password.length()<6 || password.length()>15)
        {
            textInputPassword.setError("Password length should be 6 - 15 ");
            textInputPassword.requestFocus();
            Log.d(TAG,"Password length is less than 6 - 15");
        }
        else
        {
            Intent iCurrentIntent = getIntent();
            final String email = iCurrentIntent.getStringExtra("email");
            Log.d(TAG,"Email: "+email);
            Log.d(TAG,"Password: "+password);

            if( !email.isEmpty() && !password.isEmpty() ) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "User created Successfully");
                                    //Toast.makeText(ActivePassword.this, "Creation Successful!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ActivePassword.this, ActiveUserName.class);
                                    startActivity(intent);
                                } else {
                                    Log.d(TAG, "User creation UnSuccessful - Please connect to Internet");
                                    Toast.makeText(ActivePassword.this, "User creation UnSuccessful. Check Internet connection", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    }
}