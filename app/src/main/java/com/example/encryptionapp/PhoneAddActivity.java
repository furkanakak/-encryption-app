package com.example.encryptionapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

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