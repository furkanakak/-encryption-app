package com.example.encryptionapp.model;

public class Phone {
  public    String phoneName;
  public    String phoneSurname;
  public    String phoneNo;

    public Phone(String phoneName, String phoneSurname, String phoneNo) {
        this.phoneName = phoneName;
        this.phoneSurname = phoneSurname;
        this.phoneNo = phoneNo;
    }


    public String getPhoneName() {
        return phoneName;
    }

    public String getPhoneSurname() {
        return phoneSurname;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}
