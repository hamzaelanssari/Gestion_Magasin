package com.IMPL;

import com.DAO_Database.DAO;
import com.DAO_Database.DataConnection;
import com.classes.Client;
import com.classes.Commande;
import com.classes.Payment;
import com.classes.Vente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOIMPL implements DAO<Payment> {
    Connection connection=null;
    Statement statement=null;
    public PaymentDAOIMPL(){
        DataConnection data;
        data = new DataConnection();
        connection=data.getConnection();
    }
    @Override
    public  boolean create(Payment p) {
        String sql = "INSERT INTO payment(type,price,id_vente,payed,date) VALUES ('"+p.getType()+"',"+p.getMontant()+","+p.getVente().getId()+","+p.isPayed()+",'"+p.getDate()+"')";
        try{
            statement = connection.prepareStatement(sql);
            return statement.execute(sql);
            } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return  false;
    }

    @Override
    public Payment find(long id) {
        return null;
    }

    @Override
    public List<Payment> findAll() {
        String sql ="SELECT * FROM PAYMENT";
        List<Payment> list = new ArrayList<>();
        Payment p;
        try{
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                p = new Payment(resultSet.getLong("id_payment"),resultSet.getDouble("price"),new Vente(resultSet.getLong("id_vente")),resultSet.getString("date"),resultSet.getString("type"),resultSet.getBoolean("payed"));
                list.add(p);
            }
            if(list.size()!=0) return list;
        }catch(SQLException e){
            e.printStackTrace();
        }
        if (list!=null)
            return list;
        return null;

    }

    @Override
    public boolean delete(Payment p) {
        String sql = "DELETE FROM PAYMENT where id_payment ="+p.getId();
        try{
            statement =  connection.prepareStatement(sql);
            return statement.executeUpdate(sql) > 0 ? true:false;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean update(Payment p) {
        String sql = "UPDATE PAYMENT SET price="+p.getMontant()+",type='"+p.getType()+"',payed="+p.isPayed()+",date='"+p.getDate()+"' WHERE id_payment="+p.getId();
        try{
            statement =  connection.prepareStatement(sql);
            return statement.executeUpdate(sql) > 0 ? true:false;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Payment> findAll(String key) {
        return null;
    }

    @Override
    public List<Payment> getPayements(Payment p) {
        return null;
    }

    @Override
    public List<Commande> getCommandes(Payment p) {
        return null;
    }
    /*public Payment getPayment(Vente v){
        String sql ="SELECT * FROM PAYMENT WHERE id_vente="+v.getId();
        try{
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
               Payment p = new Payment(resultSet.getLong("id_payment"),resultSet.getDouble("price"),v,resultSet.getString("date"),resultSet.getString("type"),resultSet.getBoolean("payed"));
               return p;
            }}catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }*/

    @Override
    public List<Payment> getPayements(Vente v){
        String sql ="SELECT * FROM PAYMENT WHERE id_vente="+v.getId();
        List<Payment> list = new ArrayList<>();
        Payment p;
        try{
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                    p = new Payment(resultSet.getLong("id_payment"),resultSet.getDouble("price"),v,resultSet.getString("date"),resultSet.getString("type"),resultSet.getBoolean("payed"));
                list.add(p);
            }
            if(list.size()!=0) return list;
        }catch(SQLException e){
            e.printStackTrace();
        }
        if (list!=null)
            return list;
        return null;
    }
}
