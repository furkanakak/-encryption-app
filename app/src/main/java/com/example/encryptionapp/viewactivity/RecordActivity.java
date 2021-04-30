package com.example.encryptionapp.viewactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.encryptionapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RecordActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button sigin,complete;
    TextInputEditText email,password,repeatpassword;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        toolbar = findViewById(R.id.toolbarRecord);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repeatpassword = findViewById(R.id.repeatpassword);
        complete = findViewById(R.id.login);
        sigin = findViewById(R.id.sigin);

        toolbar.setTitle("RECORD");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecordActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserEmail = email.getText().toString();
                String UserPassword = password.getText().toString();
                String UserPasswordRepeat = repeatpassword.getText().toString();
                if(UserPassword.equals(UserPasswordRepeat))
                {
                    signUpFirebase(UserEmail,UserPassword);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"false",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void signUpFirebase(String userEmail , String userPassword)
    {
        auth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"account created",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(RecordActivity.this,MainActivity.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Couldn't create account",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}