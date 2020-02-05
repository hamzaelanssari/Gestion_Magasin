package com.DAO_Database;

import com.classes.Commande;
import com.classes.Payment;
import com.classes.Vente;

import java.util.List;

public interface DAO<T> {
    public boolean create(T p);//void
    public T find(long id);
    public List<T> findAll();
    public boolean delete(T p);//void
    public boolean update(T p);
    public List<T> findAll(String key);
    List<Payment> getPayements(T p);
    List<Commande> getCommandes(T p);

    List<Payment> getPayements(Vente v);

  /*  T getPayement(T v);*/
}
