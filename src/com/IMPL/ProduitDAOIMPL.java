package com.IMPL;



import com.classes.*;
import com.DAO_Database.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAOIMPL implements DAO<Produit>{
    Connection connection=null;
    Statement statement=null;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    public ProduitDAOIMPL(){

        DataConnection data;
        data = new DataConnection();
        connection=data.getConnection();
    }

    @Override
    public boolean create(Produit produit) {
        String sql = "INSERT INTO PRODUIT(designation,prix,categorie) values('"+produit.getDesignation()+"',"+produit.getPrix()+","+produit.getCategorie().getId()+")";
        System.out.println(produit);
        try {
            preparedStatement = connection.prepareStatement(sql);

            System.out.println(preparedStatement.executeUpdate());
            return true;
        }
        catch (SQLException e){
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Produit find(long id) {
            String sql = "SELECT PRODUIT.*,CATEGORIE.title as categorie_t from PRODUIT,CATEGORIE WHERE PRODUIT.categorie=CATEGORIE.id_categorie AND PRODUIT.id_produit="+id;
            try {
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    return new Produit(resultSet.getLong("id_produit"),resultSet.getString("designation"),resultSet.getDouble("prix"),new Categorie(resultSet.getLong("categorie"),resultSet.getString("categorie_t")));
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            return null;


    }

    @Override
    public List<Produit> findAll() {
        List<Produit> list = new ArrayList<Produit>();

        String sql = "SELECT *  from PRODUIT,CATEGORIE WHERE PRODUIT.categorie=CATEGORIE.id_categorie";
        try{
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                list.add(new Produit(resultSet.getLong("id_produit"),resultSet.getString("designation"),resultSet.getDouble("prix"),new Categorie(resultSet.getLong("id_categorie"),resultSet.getString("title"))));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(list.size() == 0) return null;
        return list;
    }

    @Override
    public boolean delete(Produit p) {
        String sql = "DELETE FROM PRODUIT where id_produit ="+p.getId();
        try{
            preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Produit prd) {
        String sql = "update produit set designation ='"+prd.getDesignation()+"', prix ="+prd.getPrix()+", categorie ="+prd.getCategorie().getId()+"  where id_produit ="+prd.getId();
        System.out.println(prd);
        try {

            preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return false;
    }

    @Override
    public List<Produit> findAll(String key) {
        List<Produit> list = new ArrayList<Produit>();
        String sql = "SELECT * from PRODUIT,CATEGORIE where PRODUIT.categorie=CATEGORIE.id_categorie AND designation LIKE '%"+key+"%' OR id_produit like '%" + key + "%' OR prix like '%"+key+"%'";
        try{
            statement=connection.createStatement();}
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }try{
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                list.add(new Produit(resultSet.getLong("id_produit"),resultSet.getString("designation"),resultSet.getDouble("prix"),new Categorie(resultSet.getLong("id_categorie"),resultSet.getString("title"))));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(list.size() == 0) return null;
        return list;
    }

    @Override
    public List<Payment> getPayements(Produit p) {
        return null;
    }

    @Override
    public List<Commande> getCommandes(Produit p) {
        return null;
    }

    @Override
    public List<Payment> getPayements(Vente v) {
        return null;
    }


    public Long getLastInsert(){
        try{
            preparedStatement = connection.prepareStatement("SELECT LAST_INSERT_ID() as ?");
            preparedStatement.setString(1,"index");
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) return resultSet.getLong("index");

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
