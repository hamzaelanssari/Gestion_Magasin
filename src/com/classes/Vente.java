package com.classes;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vente {
    private long id;
    private Client client;
    private List<Commande> commandes;
    private double total;
    private String date;

    public Vente(long id) {
        this.id=id;
    }

    public Vente(long id, Client client, List<Commande> commandes, String date) {
        this.id = id;
        this.client = client;
        this.commandes = commandes;
        this.date = date;
        if(commandes == null) this.commandes = new ArrayList<>();
        else         for(Commande c:commandes) System.out.println(c);

        updateTotal();
    }
    public void updateTotal(){
        total = 0;
        for(Commande c:commandes)
            total+=c.getQuantite() * c.getProduit().getPrix();


    }

    @Override
    public String toString() {
        return ""+id;
    }

    public String getDate() {
        return date;
    }
    public String getDateStr(){
        return date.toString();
    }
    public void setDate(String date) {
        this.date = date;
    }

    public long getIdClient(){
        return client.getId_client();
    }
    public void addCommande(Commande c){
        commandes.add(c);
    }
    public void addCommande(List<Commande> c){
        commandes.addAll(c);
    }
    public void addCommande(Commande[] c){
        commandes.addAll(Arrays.asList(c));
    }
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Commande> getCommandes(Vente p2) {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }
}
