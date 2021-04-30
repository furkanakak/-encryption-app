package com.example.encryptionapp.viewactivity;

import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.encryptionapp.R;

public class NoteAddActivity extends NavigationDrawer {
    Toolbar toolbar;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = LayoutInflater.from(this);
        View myview = inflater.inflate(R.layout.activity_note_add,null,false);
        drawerLayout.addView(myview,0);

        toolbar = findViewById(R.id.toolbarNoteAdd);

        toolbar.setTitle("Note Add Activity");
        setSupportActionBar(toolbar);











    }
}