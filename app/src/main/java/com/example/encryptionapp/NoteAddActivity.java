package com.example.encryptionapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

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