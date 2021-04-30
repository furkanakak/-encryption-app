package com.example.encryptionapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.encryptionapp.R;
import com.example.encryptionapp.model.Phone;

import java.util.ArrayList;

public class phoneShowRVAdapter extends RecyclerView.Adapter<phoneShowRVAdapter.PhoneShowViewHolder> {
    private Context mContext;
    private ArrayList<Phone> phoneList;
    public boolean click;

    public phoneShowRVAdapter(Context mContext, ArrayList<Phone> phoneList) {
        this.mContext = mContext;
        this.phoneList =phoneList;
    }

    public class PhoneShowViewHolder extends RecyclerView.ViewHolder{
      public TextView phoneName,phoneSurname,phoneNumber;
      public CardView phoneCardView;


      public PhoneShowViewHolder(@NonNull View itemView) {
          super(itemView);
          phoneName = itemView.findViewById(R.id.card_design_phone_name);
          phoneSurname = itemView.findViewById(R.id.card_design_phone_surname);
          phoneNumber = itemView.findViewById(R.id.card_design_phone_no);
          phoneCardView = itemView.findViewById(R.id.card_design_phone_card);
      }
  }

    @NonNull
    @Override
    public PhoneShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design_phone,parent,false);
        return new PhoneShowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneShowViewHolder holder, int position) {
        Phone phone = phoneList.get(position);
        holder.phoneName.setText(phone.phoneName);
        holder.phoneSurname.setText(phone.phoneSurname);
        holder.phoneNumber.setText(phone.phoneNo);
        holder.phoneCardView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {
                    holder.phoneCardView.setBackgroundColor(Color.GREEN);
                    v.setOnFocusChangeListener(null);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return phoneList.size();
    }








}
