package com.example.encryptionapp.model;

public class Note {
  public   String noteHeader;
  public   String noteContext;
  public   String noteDate;




    public Note(String noteHeader , String noteContext , String noteDate){
        this.noteContext = noteContext;
        this.noteHeader = noteHeader;
        this.noteDate = noteDate;

    }

    public String getNoteHeader() {
        return noteHeader;
    }

    public String getNoteContext() {
        return noteContext;
    }

    public String getNoteDate() {
        return noteDate;
    }
}
