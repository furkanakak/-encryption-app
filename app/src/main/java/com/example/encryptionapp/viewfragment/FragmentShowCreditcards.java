package com.example.encryptionapp.viewfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.encryptionapp.R;
import com.example.encryptionapp.adapter.CreditcardShowRVAdapter;
import com.example.encryptionapp.model.Creditcard;
import com.example.encryptionapp.viewactivity.CredicardDesignAddActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FragmentShowCreditcards extends Fragment {
    FirebaseFirestore mfirestore;
    FirebaseAuth auth;
    DatabaseReference mReference;
    RecyclerView recyclerView;
    List<DocumentSnapshot> doclist;
    ArrayList<Creditcard> creditcardsList = new ArrayList<Creditcard>();
    CollectionReference collectionReference;
    CreditcardShowRVAdapter adapter;
    FloatingActionButton fab_creditcardadd_show;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_creditcards,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mfirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser mUser = auth.getCurrentUser();



        creditcarddataget(mUser.getUid());



        recyclerView = view.findViewById(R.id.recyclerView_creditcardShow);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new CreditcardShowRVAdapter(getActivity(),creditcardsList);



                recyclerView.setAdapter(adapter);


        fab_creditcardadd_show =getView().findViewById(R.id.fab_creditcardadd_show);
        fab_creditcardadd_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creditcarddataget(mUser.getUid());
                Intent i = new Intent(getActivity().getApplication(), CredicardDesignAddActivity.class);
                startActivity(i);
            }
        });

    }


    public void creditcarddataget(String uid) {
        collectionReference = mfirestore.collection("Kullanıcılar").document(uid).collection("Kartlar");
        collectionReference.get()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        doclist = new ArrayList<DocumentSnapshot>();
                        doclist =queryDocumentSnapshots.getDocuments();
                        creditcardsList.clear();
                        for (int i=0; i<doclist.size(); i++) {
                            if(doclist.get(i).exists())
                            {
                                String banka = (String) doclist.get(i).get("BankaAdi");
                                String no = (String) doclist.get(i).get("KartNo");
                                String iban = (String) doclist.get(i).get("KartIban");
                                String sifre = (String) doclist.get(i).get("KartSifresi");
                                String cvv = (String) doclist.get(i).get("KartCvv");

                                Creditcard card = new Creditcard(banka,no,iban,sifre,cvv);
                                creditcardsList.add(card);
                            }
                            adapter.notifyDataSetChanged();

                        }
                    }
                }); }











}
