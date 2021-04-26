package com.example.encryptionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button signup , login;
    TextInputEditText email , password;
    Toolbar toolbar;
    FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

   toolbar = findViewById(R.id.toolbarEnter);
   signup = findViewById(R.id.signup);
   login = findViewById(R.id.login);
   email = findViewById(R.id.email);
   password = findViewById(R.id.password);

   toolbar.setTitle("LOGIN");
   setSupportActionBar(toolbar);

   signup.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Intent i = new Intent(MainActivity.this,NoteAddActivity.class);
           startActivity(i);

       }
   });


   login.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           String UserEmail = email.getText().toString();
           String UserPassword = password.getText().toString();
           signinFirebase(UserEmail,UserPassword);

       }
   });


    }

    public void signinFirebase(String userMail , String userPassword){

        auth.signInWithEmailAndPassword(userMail,userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(MainActivity.this,EnterActivity.class);
                            startActivity(i);
                        }
                        else
                        {
                                Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if(user !=null)
        {
            Intent i = new Intent(MainActivity.this,EnterActivity.class);
            startActivity(i);
            finish();
        }
    }
}