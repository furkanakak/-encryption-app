package com.example.encryptionapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;

public class FragmentShowNotes extends Fragment {
    FirebaseFirestore mfirestore;
    FirebaseAuth auth;
    DatabaseReference mReference;
    FloatingActionButton fab_noteadd;
    TextView noteheader;
    HashMap<String,Object> noteDate;
    DatePickerDialog.OnDateSetListener setListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_notes,container,false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mfirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser mUser =auth.getCurrentUser();

        fab_noteadd =getView().findViewById(R.id.fab_noteadd_show);
        noteheader = getView().findViewById(R.id.header_show);

        fab_noteadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View design  = getLayoutInflater().inflate(R.layout.alert_design_note,null);
                TextInputEditText headar = design.findViewById(R.id.alert_note_header);
                TextInputEditText content = design.findViewById(R.id.alert_phone_number);
                TextView calenderShow = design.findViewById(R.id.alert_note_calender);


                Calendar calender = Calendar.getInstance();
                final int year = calender.get(Calendar.YEAR);
                final int month = calender.get(Calendar.MONTH);
                final  int day = calender.get(Calendar.DAY_OF_MONTH);


                AlertDialog.Builder ad = new AlertDialog.Builder(v.getContext());
                ad.setMessage("Note Add");
                ad.setView(design);


                calenderShow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                v.getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month = month+1;
                                String date = day+"/"+month+"/"+year;
                                calenderShow.setText(date);

                            }
                        },year,month,day);
                        datePickerDialog.show();
                    }
                });


                ad.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String baslik = headar.getText().toString();
                        String icerik = content.getText().toString();
                        String tarih = calenderShow.getText().toString();

                        noteDate = new HashMap<>();
                        noteDate.put("notBaslik","baslikssdsadsadsadsa");
                        noteDate.put("notIcerik","icerik");
                        noteDate.put("notTarih","tarih");




                        mfirestore.collection("Kullanıcılar").document(mUser.getUid())
                                .set(noteDate)
                                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful())
                                        {
                                            noteheader.setText("basarili");
                                            Toast.makeText(getActivity(),"ddd",Toast.LENGTH_SHORT).show();

                                        }
                                        else {
                                            noteheader.setText("basarisizz");
                                            Toast.makeText(getActivity(),"not isSuccessful",Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });

                    }
                });


                ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(),"Cancel",Toast.LENGTH_SHORT).show();
                        noteheader.setText("basarisizz");
                    }
                });    ad.create().show();



            }
        });










    }
}
