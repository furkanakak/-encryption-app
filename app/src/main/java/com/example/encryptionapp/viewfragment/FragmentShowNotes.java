package com.example.encryptionapp.viewfragment;

import android.app.DatePickerDialog;
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
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.encryptionapp.R;
import com.example.encryptionapp.adapter.noteShowRVAdapter;
import com.example.encryptionapp.adapter.phoneShowRVAdapter;
import com.example.encryptionapp.model.Note;
import com.example.encryptionapp.model.Phone;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FragmentShowNotes extends Fragment {
    FirebaseFirestore mfirestore;
    FirebaseAuth auth;
    DatabaseReference mReference;
    FloatingActionButton fab_noteadd;
    HashMap<String, Object> noteDate;
    RecyclerView recyclerView;
    noteShowRVAdapter adapter;
    List<DocumentSnapshot> doclist;
    ArrayList<Note> noteList = new ArrayList<Note>();
    CollectionReference collectionReference;
    DatePickerDialog.OnDateSetListener setListener;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_notes, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mfirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser mUser = auth.getCurrentUser();
        notedataget(mUser.getUid());



        fab_noteadd = getView().findViewById(R.id.fab_noteadd_show);
        recyclerView = view.findViewById(R.id.recyclerView_noteShow);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        adapter = new noteShowRVAdapter(getContext(),noteList);
        recyclerView.setAdapter(adapter);





        fab_noteadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View design = getLayoutInflater().inflate(R.layout.alert_design_note, null);
                TextInputEditText headar = design.findViewById(R.id.alert_note_header);
                TextInputEditText content = design.findViewById(R.id.alert_note_context);
                TextView calenderShow = design.findViewById(R.id.alert_note_calender);


                Calendar calender = Calendar.getInstance();
                final int year = calender.get(Calendar.YEAR);
                final int month = calender.get(Calendar.MONTH);
                final int day = calender.get(Calendar.DAY_OF_MONTH);


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
                                month = month + 1;
                                String date = day + "/" + month + "/" + year;
                                calenderShow.setText(date);

                            }
                        }, year, month, day);
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
                        noteDate.put("notBaslik", baslik);
                        noteDate.put("notIcerik", icerik);
                        noteDate.put("notTarih", tarih);


                        UUID uuid = UUID.randomUUID();
                        mfirestore.collection("Kullanıcılar").document(mUser.getUid()).collection("Notlar").document(uuid.toString())
                                .set(noteDate)
                                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {

                                            Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();

                                        } else {

                                            Toast.makeText(getActivity(), "not isSuccessful", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                        notedataget(mUser.getUid());
                    }
                });


                ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();

                    }
                });   ad.create().show();

            }
        });


    }

    public void notedataget(String uid) {
        collectionReference = mfirestore.collection("Kullanıcılar").document(uid).collection("Notlar");
        collectionReference.get()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        doclist = new ArrayList<DocumentSnapshot>();
                        doclist =queryDocumentSnapshots.getDocuments();
                        noteList.clear();
                        for (int i=0; i<doclist.size(); i++) {
                            if(doclist.get(i).exists())
                            {
                                String header = (String) doclist.get(i).get("notBaslik");
                                String content = (String) doclist.get(i).get("notIcerik");
                                String date = (String) doclist.get(i).get("notTarih");
                                Note note = new Note(header,content,date);
                                noteList.add(note);
                            }
                            adapter.notifyDataSetChanged();

                        }
                    }
                }); }






    }




