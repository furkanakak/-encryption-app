package com.example.encryptionapp.viewactivity;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.encryptionapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreditcardAddActivity extends NavigationDrawer {
    Toolbar toolbar;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LayoutInflater inflater = LayoutInflater.from(this);
        View myview = inflater.inflate(R.layout.activity_creditcard_add,null,false);
        drawerLayout.addView(myview,0);

        toolbar = findViewById(R.id.toolbarCreditcardAdd);
        toolbar.setTitle("Creditcard Add Activity");
        fab = findViewById(R.id.fab_creditcard_add);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreditcardAddActivity.this, CredicardDesignAddActivity.class);
                startActivity(i);
            }
        });



    }


}