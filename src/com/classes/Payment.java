package com.classes;

import javafx.animation.Timeline;
import javafx.scene.control.DatePicker;

import java.sql.Timestamp;

public class Payment {
    private long id;
    private double montant;
    private Vente vente;
    private String date;
    private boolean payed;
    private String type;
    private double reste;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Payment(long id_payment, double montant, Vente vente, String date , String type, boolean payed) {
        this.id=id_payment;
        this.montant = montant;
        this.vente = vente;
        this.date = date;
        this.type = type;
        this.payed=payed;
        this.reste=vente.getTotal()-montant;
    }
    public Payment(double montant, Vente vente, String date, String type, boolean payed) {
        this.montant = montant;
        this.vente = vente;
        this.date = date;
        this.type = type;
        this.payed=payed;
        this.reste=vente.getTotal()-montant;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public double getReste() {
        return reste;
    }

    public void setReste(double reste) {
        this.reste = reste;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }
}
