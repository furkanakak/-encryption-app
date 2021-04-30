package com.example.encryptionapp.viewfragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.encryptionapp.R;
import com.example.encryptionapp.adapter.phoneShowRVAdapter;
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
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FragmentShowPhones extends Fragment {
    FirebaseFirestore mfirestore;
    FirebaseAuth auth;
    DatabaseReference mReference;
    FloatingActionButton phonefab;
    HashMap<String,Object> phoneDate;
    RecyclerView recyclerView;
    List<DocumentSnapshot> doclist;
    ArrayList<Phone> phoneList = new ArrayList<Phone>();
    CollectionReference collectionReference;
    phoneShowRVAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_phones,container,false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mfirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser mUser =auth.getCurrentUser();

        phonefab = getView().findViewById(R.id.fab_phoneshow);

        phonedataget(mUser.getUid());
        recyclerView = view.findViewById(R.id.recyclerView_PhoneShow);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new phoneShowRVAdapter(getActivity(),phoneList);
        recyclerView.setAdapter(adapter);

        phonefab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View design  = getLayoutInflater().inflate(R.layout.alert_design_phone,null);
                TextInputEditText name = design.findViewById(R.id.alert_phone_name);
                TextInputEditText surname = design.findViewById(R.id.alert_phone_surname);
                TextInputEditText number = design.findViewById(R.id.alert_note_context);

                AlertDialog.Builder ad = new AlertDialog.Builder(v.getContext());
                ad.setMessage("Phone Add");
                ad.setView(design);

                ad.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String isim = name.getText().toString();
                        String soyisim = surname.getText().toString();
                        String numara = number.getText().toString();

                        phoneDate = new HashMap<>();
                        phoneDate.put("isim",isim);
                        phoneDate.put("soyisim",soyisim);
                        phoneDate.put("numara",numara);
                        UUID uuid = UUID.randomUUID();
                       mfirestore.collection("Kullanıcılar").document(mUser.getUid()).collection("Telefonlar").document(uuid.toString())
                               .set(phoneDate)
                               .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                                   @Override
                                   public void onComplete(@NonNull Task<Void> task) {
                                       if (task.isSuccessful())
                                       {
                                           Toast.makeText(getActivity(),"Successful",Toast.LENGTH_SHORT).show();
                                       }
                                       else
                                       {
                                           Toast.makeText(getActivity(),"not isSuccessful",Toast.LENGTH_SHORT).show();
                                       }
                                   }
                               });
                        phonedataget(mUser.getUid());
                    }
                });


                ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity().getApplicationContext(),"Cancel",Toast.LENGTH_SHORT).show();
                    }
                });   ad.create().show();

            }
        });


    }
    public void phonedataget(String uid) {
        collectionReference = mfirestore.collection("Kullanıcılar").document(uid).collection("Telefonlar");
        collectionReference.get()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        doclist = new ArrayList<DocumentSnapshot>();
                        doclist =queryDocumentSnapshots.getDocuments();
                        phoneList.clear();
                        for (int i=0; i<doclist.size(); i++) {
                            if(doclist.get(i).exists())
                            {
                                String name = (String) doclist.get(i).get("isim");
                                String surname = (String) doclist.get(i).get("soyisim");
                                String number = (String) doclist.get(i).get("numara");
                                Phone phone = new Phone(name,surname,number);
                                phoneList.add(phone);
                            }
                            adapter.notifyDataSetChanged();

                        }
                    }
                }); }
}
