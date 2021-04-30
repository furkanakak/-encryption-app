package com.example.encryptionapp.viewactivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.example.encryptionapp.R;

public class PhoneAddActivity extends NavigationDrawer {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LayoutInflater inflater = LayoutInflater.from(this);
        View myview = inflater.inflate(R.layout.activity_phone_add,null,false);
        drawerLayout.addView(myview,0);

        toolbar = findViewById(R.id.toolbarPhoneAdd);
        toolbar.setTitle("Phone Add Activity");
        setSupportActionBar(toolbar);










    }
}