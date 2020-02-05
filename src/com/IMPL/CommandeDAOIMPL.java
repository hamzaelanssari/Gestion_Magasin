package com.IMPL;

import com.DAO_Database.DAO;
import com.DAO_Database.DataConnection;
import com.classes.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAOIMPL implements DAO<Commande>{
    Connection connection=null;
    Statement statement=null;

    public CommandeDAOIMPL() {
        DataConnection data;
        data = new DataConnection();
        connection=data.getConnection();
    }

    @Override
    public boolean create(Commande p) {
        String sql = "insert into commande(id_produit,quantite,id_vente) values("+p.getProduit().getId()+","+p.getQuantite()+","+p.getVente_id()+")";
        try {
            statement=connection.createStatement();
            return statement.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Commande find(long id) {
        var tmp = Get("select * from commande where id_commande="+ id);
        return tmp == null ? null : tmp.get(0);
    }

    @Override
    public List<Commande> findAll() {
        return Get("SELECT *  from commande");
    }

    @Override
    public boolean delete(Commande p) {
        // TODO Auto-generated method stub
        String sql = "delete from commande where id_commande="+p.getId();
        try {
            statement=connection.createStatement();
            return statement.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Commande p) {
        // TODO Auto-generated method stub
        String sql = "update commande set  id_produit ="+p.getProduit().getId()+", quantite="+p.getQuantite()+",id_vente="+p.getVente_id()+" where id_commande="+p.getId();
        try {
            statement=connection.createStatement();
            return statement.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Commande> findAll(String key) {
        return Get("select * from commande where  id_commande like '%"+key+"%' or id_produit like '%"+key+"%'  or  quantite like '%"+key+"%' or id_vente like '%"+key+"'");
    }

    @Override
    public List<Payment> getPayements(Commande p) {
        return null;
    }

    @Override
    public List<Commande> getCommandes(Commande p) {
        return null;
    }

    @Override
    public List<Payment> getPayements(Vente v) {
        return null;
    }


    public List<Commande> Get(String req){
        List<Commande> list=new ArrayList<Commande>();
        try{
            statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery(req);
            ProduitDAOIMPL pdi = new ProduitDAOIMPL();
            while(resultSet.next()){
                Produit tp = pdi.find(resultSet.getLong("id_produit"));
                list.add(new Commande(resultSet.getLong("id_commande"),tp,resultSet.getInt("quantite"),resultSet.getLong("id_vente")));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return list.size()>0?list:null;
    }
}
