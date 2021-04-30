package com.example.encryptionapp.viewactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.encryptionapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CredicardDesignAddActivity extends AppCompatActivity {
    FirebaseFirestore mfirestore;
    FirebaseAuth auth;
    DatabaseReference mReference;
    Toolbar toolbar;
    Spinner spinner;
    Button saveButton;
    Button cancelButton;
    TextInputEditText cardNo , cardIban , cardPassword , cardCvv;
    ArrayList<String> banks = new ArrayList<>();
    HashMap<String,Object> creditcardDate;
    ArrayAdapter<String> banksAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mfirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser mUser =auth.getCurrentUser();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credicard_design_add);
        toolbar = findViewById(R.id.toolbarNoteDesignAdd);
        toolbar.setTitle("Creditcard Add");
        setSupportActionBar(toolbar);

        spinner = findViewById(R.id.spinner_creditcard);
        cardNo = findViewById(R.id.addCreditcard_cardno);
        cardIban = findViewById(R.id.addCreditcard_cardiban);
        cardPassword = findViewById(R.id.addCreditcard_cardpassword);
        cardCvv = findViewById(R.id.addCreditcard_cardcvv);
        saveButton = findViewById(R.id.saveButtonCreditcardone);
        cancelButton = findViewById(R.id.cancelButtonCreditcard);

        banks.add("ZiraatBank");
        banks.add("AkBank");
        banks.add("İsBank");
        banks.add("HalkBank");
        banks.add("FinansBank");
        banks.add("VakıfBank");
        banks.add("GarantiBank");

        banksAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,banks);
        spinner.setAdapter(banksAdapter);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kartNo = cardNo.getText().toString();
                String kartIban = cardIban.getText().toString();
                String kartSifresi = cardPassword.getText().toString();
                String kartCvv = cardCvv.getText().toString();

                String spinnerSelect = banks.get(spinner.getSelectedItemPosition());

                creditcardDate = new HashMap<>();
                creditcardDate.put("BankaAdi",spinnerSelect);
                creditcardDate.put("KartNo",kartNo);
                creditcardDate.put("KartIban",kartIban);
                creditcardDate.put("KartSifresi",kartSifresi);
                creditcardDate.put("KartCvv",kartCvv);
                UUID uuid = UUID.randomUUID();

                mfirestore.collection("Kullanıcılar").document(mUser.getUid()).collection("Kartlar").document(uuid.toString())
                        .set(creditcardDate)
                        .addOnCompleteListener(CredicardDesignAddActivity.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();

                                }
                                else {

                                    Toast.makeText(getApplicationContext(),"not isSuccessful",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }


    public void onBackPressed() {
        finish();
    }








}