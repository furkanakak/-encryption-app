package com.example.encryptionapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class noteShowRVAdapter extends RecyclerView.Adapter<noteShowRVAdapter.NoteShowViewHolder> {
    private Context mContext;
    private ArrayList<Note> noteList;

    public noteShowRVAdapter(Context mContext, ArrayList<Note> noteList) {
        this.mContext = mContext;
        this.noteList = noteList;
    }


    public class NoteShowViewHolder extends RecyclerView.ViewHolder{
        public TextView noteHeader,noteContent,noteDate;
        public CardView noteCardView;


        public NoteShowViewHolder(@NonNull View itemView) {
            super(itemView);
            noteHeader = itemView.findViewById(R.id.card_design_note_header);
            noteDate = itemView.findViewById(R.id.card_design_note_date);
            noteContent = itemView.findViewById(R.id.card_design_note_content);
        }
    }


    @NonNull
    @Override
    public NoteShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design_note,parent,false);
        return new NoteShowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteShowViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.noteHeader.setText(note.noteHeader);
        holder.noteContent.setText(note.noteContext);
        holder.noteDate.setText(note.noteDate);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }


}
