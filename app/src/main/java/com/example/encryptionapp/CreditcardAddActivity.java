package com.example.encryptionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class CreditcardAddActivity extends NavigationDrawer {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LayoutInflater inflater = LayoutInflater.from(this);
        View myview = inflater.inflate(R.layout.activity_creditcard_add,null,false);
        drawerLayout.addView(myview,0);

        toolbar = findViewById(R.id.toolbarCreditcardAdd);
        toolbar.setTitle("Creditcard Add Activity");
        setSupportActionBar(toolbar);

    }
}