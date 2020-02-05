package com.IMPL;

import com.DAO_Database.DAO;
import com.DAO_Database.DataConnection;
import com.classes.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VenteDAOIMPL implements DAO<Vente> {
    Connection connection=null;
    Statement statement=null;
    public VenteDAOIMPL(){
        DataConnection data;
        data = new DataConnection();
        connection=data.getConnection();
    }
    @Override
    public boolean create(Vente p) {
        String sql = "INSERT INTO VENTE(id_client,date) VALUES("+p.getClient().getId_client()+",'"+p.getDate()+"')";
        try{
            statement = connection.prepareStatement(sql);
            if(statement.executeUpdate(sql) <= 0) return false;
            for (Commande c:p.getCommandes(p)){
                sql = "INSERT INTO COMMANDE(id_produit,id_vente,quantite) VALUES ("+c.getProduit().getId()+","+p.getId()+","+c.getQuantite()+")";
                statement = connection.prepareStatement(sql);
                if(statement.executeUpdate(sql) <=0) return false;
            }
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Vente find(long id) {
        String sql ="SELECT * FROM VENTE,CLIENT WHERE VENTE.id_vente="+id+" AND VENTE.id_client=CLIENT.id_client";
        try{
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                ClientDAOIMPL clt=new ClientDAOIMPL();
                Client c=clt.find(resultSet.getLong("id_client"));
                return new Vente(resultSet.getLong("Vente.id_vente"),c, null, resultSet.getString("date"));
            }}catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public List<Vente> findAll() {
        List<Vente> list = new ArrayList<Vente>();
        String sql="SELECT * from CLIENT,VENTE WHERE CLIENT.id_client=VENTE.id_client";
        try{
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                ClientDAOIMPL clt=new ClientDAOIMPL();
                Client c=clt.find(resultSet.getLong("id_client"));
                list.add(new Vente(resultSet.getLong("Vente.id_vente"),c,null, resultSet.getString("date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size() == 0) return null;
        return list;
    }

    @Override
    public boolean delete(Vente p) {
        String sql ="DELETE FROM VENTE WHERE id_vente="+p.getId();
        try{
            statement = connection.prepareStatement("DELETE FROM COMMANDE WHERE id_vente="+p.getId());
            if(statement.executeUpdate(sql) <=0) return false;
            statement = connection.prepareStatement(sql);
            if(statement.executeUpdate(sql) <=0) return false;
            return true;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Vente p) {
        String sql = "UPDATE VENTE SET id_client="+p.getClient().getId_client()+",date="+p.getDate();
        try{
            statement = (PreparedStatement) connection.prepareStatement(sql);
            if(statement.executeUpdate(sql) <=0) return false;
            statement = connection.prepareStatement("DELETE FROM COMMANDE WHERE id_vente="+p.getId());
            if(statement.executeUpdate(sql) <=0) return false;
            for (Commande c:p.getCommandes(p)){
                sql = "INSERT INTO COMMANDE(id_produit,id_vente,quantite) VALUES ("+c.getProduit().getId()+","+p.getId()+","+c.getQuantite()+")";
                statement = (PreparedStatement) connection.prepareStatement(sql);

                if(statement.executeUpdate(sql) <=0) return false;
            }
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Vente> findAll(String key) {
        return null;
    }

    @Override
    public List<Payment> getPayements(Vente p) {
        List<Payment> list = new ArrayList<Payment>();
        String sql="SELECT * from PAYMENT where id_vente = "+p.getId();
        try{
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                list.add(new Payment(resultSet.getLong("id_payment"),resultSet.getDouble("price"),p,resultSet.getString("date"),resultSet.getString("type"),resultSet.getBoolean("payed")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size() == 0) return null;
        return list;

    }

    @Override
    public List<Commande> getCommandes(Vente p){
        List<Commande> list = new ArrayList<Commande>();
        String sql="SELECT * from COMMANDE,PRODUIT,CATEGORIE where COMMANDE.id_produit=PRODUIT.id_produit AND PRODUIT.categorie=CATEGORIE.id_categorie and id_vente = "+p.getId();
        try{
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            ProduitDAOIMPL pd = new ProduitDAOIMPL();
            while(resultSet.next()){
                Produit tp = pd.find(resultSet.getLong("PRODUIT.id_produit"));
                list.add(new Commande(resultSet.getLong("id_commande"),tp,resultSet.getInt("quantite"),p.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size() == 0) return null;
        return list;
    }

}
