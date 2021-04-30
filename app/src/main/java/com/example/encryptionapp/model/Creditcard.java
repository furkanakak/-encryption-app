package com.example.encryptionapp.model;

public class Creditcard {
  public   String cardBanka;
  public   String cardNo;
  public   String cardIban;
  public   String cardSifre;
  public   String cardCvv;

    public Creditcard(String cardBanka, String cardNo, String cardIban, String cardSifre, String cardCvv) {
        this.cardBanka = cardBanka;
        this.cardNo = cardNo;
        this.cardIban = cardIban;
        this.cardSifre = cardSifre;
        this.cardCvv = cardCvv;
    }

    public String getCardBanka() {
        return cardBanka;
    }

    public String getCardNo() {
        return cardNo;
    }

    public String getCardIban() {
        return cardIban;
    }

    public String getCardSifre() {
        return cardSifre;
    }

    public String getCardCvv() {
        return cardCvv;
    }
}
