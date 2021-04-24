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
    FloatingActionButton phonefab;
    TextView phonename,phonesurname,phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LayoutInflater inflater = LayoutInflater.from(this);
        View myview = inflater.inflate(R.layout.activity_phone_add,null,false);
        drawerLayout.addView(myview,0);

        toolbar = findViewById(R.id.toolbarPhoneAdd);
        toolbar.setTitle("Phone Add Activity");
        setSupportActionBar(toolbar);

        phonefab = findViewById(R.id.fab_phoneadd);
        phonename = findViewById(R.id.phonename);
        phonesurname = findViewById(R.id.phonesurname);
        phonenumber = findViewById(R.id.phonenumber);




        phonefab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View design  = getLayoutInflater().inflate(R.layout.alert_design_phone,null);
                TextInputEditText name = design.findViewById(R.id.alert_phone_name);
                TextInputEditText surname = design.findViewById(R.id.alert_phone_surname);
                TextInputEditText number = design.findViewById(R.id.alert_phone_number);

                AlertDialog.Builder ad = new AlertDialog.Builder(PhoneAddActivity.this);
                ad.setMessage("Phone Add");
                ad.setView(design);

                ad.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        phonename.setText(name.getText().toString());
                        phonesurname.setText(surname.getText().toString());
                        phonenumber.setText(number.getText().toString());
                        Toast.makeText(getApplicationContext(),"Save",Toast.LENGTH_SHORT).show();

                    }
                });


                ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_SHORT).show();
                    }
                });   ad.create().show();


            }
        });



    }
}