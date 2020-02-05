package com.classes;

public class Commande{
    private long id;
    private Produit produit;
    private double quantite;
    private double sousTotal;
    private long vente_id ;

    public long getVente_id() {
        return vente_id;
    }

    public void setVente_id(long vente_id) {
        this.vente_id = vente_id;
    }

    public Commande(long id, Produit produit, double quantite, long vente_id) {
        this.id = id;
        this.produit = produit;
        this.quantite = quantite;
        this.vente_id=vente_id;
        this.sousTotal=quantite*produit.getPrix();
    }

    public String toString(){
        return  produit.toString();
    }

    public double getSousTotal() {
        return sousTotal;
    }

    public void setSousTotal(double sousTotal) {
        this.sousTotal = sousTotal;
    }

    public void updateSousTotal(){
        this.sousTotal = quantite * produit.getPrix();
    }
    public long getId() {
        return id;
    }
    public Long getIdProduit(){
        if(produit!=null) return produit.getId();
        return null;
    }
    public Categorie getCategorie(){
        if(produit!=null) return produit.getCategorie();
        return null;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(long quantite) {
        this.quantite = quantite;
    }
}
