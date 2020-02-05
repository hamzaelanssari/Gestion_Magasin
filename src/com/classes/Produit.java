package com.classes;

public class Produit {
    private long id;
    private String designation;
    private double prix;
    private Categorie   categorie;

    public Produit(long id, String designation, double prix,Categorie categorie) {
        this.id = id;
        this.designation = designation;
        this.prix = prix;
        this.categorie = categorie;
    }

    public Produit(String designation, double prix, Categorie categorie) {
        this.designation = designation;
        this.prix = prix;
        this.categorie = categorie;
    }

    public Produit(String designation) {
        this.designation = designation;
    }
    @Override
    public String toString(){
        return this.designation;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setcategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    public String getTitle(){
        return categorie.getTitle();
    }
}
