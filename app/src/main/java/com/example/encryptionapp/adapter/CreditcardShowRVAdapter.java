package com.example.encryptionapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.encryptionapp.R;
import com.example.encryptionapp.model.Creditcard;

import java.util.ArrayList;

public class CreditcardShowRVAdapter extends RecyclerView.Adapter<CreditcardShowRVAdapter.CreditcardShowViewHolder>{

    private Context mContext;
    private ArrayList<Creditcard> cardList;

    public CreditcardShowRVAdapter(Context mContext, ArrayList<Creditcard> cardList) {
        this.mContext = mContext;
        this.cardList = cardList;
    }


    public class CreditcardShowViewHolder extends RecyclerView.ViewHolder{
        public TextView cardBanka,cardNo,cardIban,cardSifre,cardCvv;
        public CardView creditcardCardView;

        public CreditcardShowViewHolder(@NonNull View itemView) {
            super(itemView);
            cardBanka = itemView.findViewById(R.id.card_design_creditcard_bank);
            cardNo = itemView.findViewById(R.id.card_design_creditcard_number);
            cardIban = itemView.findViewById(R.id.card_design_creditcard_iban);
            cardSifre = itemView.findViewById(R.id.card_design_creditcard_password);
            cardCvv = itemView.findViewById(R.id.card_design_creditcard_cvv);

        }
    }

    @NonNull
    @Override
    public CreditcardShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design_creditcard,parent,false);
        return new CreditcardShowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CreditcardShowViewHolder holder, int position) {
        Creditcard card = cardList.get(position);
        holder.cardBanka.setText(card.cardBanka);
        holder.cardNo.setText(card.cardNo);
        holder.cardIban.setText(card.cardIban);
        holder.cardSifre.setText(card.cardSifre);
        holder.cardCvv.setText(card.cardCvv);
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }



}
