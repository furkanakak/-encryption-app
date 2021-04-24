package com.example.encryptionapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class FragmentShowPhones extends Fragment {
    FloatingActionButton phonefab;
    TextView phonename;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_phones,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        phonefab = getView().findViewById(R.id.fab_phoneshow);
        phonename = getView().findViewById(R.id.show_name);

        phonefab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View design  = getLayoutInflater().inflate(R.layout.alert_design_phone,null);
                TextInputEditText name = design.findViewById(R.id.alert_phone_name);
                TextInputEditText surname = design.findViewById(R.id.alert_phone_surname);
                TextInputEditText number = design.findViewById(R.id.alert_phone_number);

                AlertDialog.Builder ad = new AlertDialog.Builder(v.getContext());
                ad.setMessage("Phone Add");
                ad.setView(design);

                ad.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        phonename.setText(name.getText().toString());
                        Toast.makeText(getActivity().getApplicationContext(),"Save",Toast.LENGTH_SHORT).show();

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
}
