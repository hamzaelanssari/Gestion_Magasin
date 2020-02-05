package com.IMPL;



import com.classes.Categorie;
import com.DAO_Database.*;
import com.classes.Commande;
import com.classes.Payment;
import com.classes.Vente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAOIMPL implements DAO<Categorie>{
    Connection connection=null;
    Statement statement=null;

    public CategorieDAOIMPL() {
        DataConnection data;
        data = new DataConnection();
        connection=data.getConnection();
    }

    @Override
    public boolean create(Categorie p) {
        // TODO Auto-generated catch block
        String sql = "INSERT INTO CATEGORIE(title) VALUES ('"+p.getTitle()+"')";
        try {
            statement=connection.createStatement();
            return statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Categorie find(long id) {

        String sql="SELECT * FROM CATEORIE where id_categorie=?";
        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            ResultSet resultSet=statement.executeQuery(sql);
            if(resultSet.next()){
                return new Categorie(resultSet.getLong("id_categorie"), resultSet.getNString("title"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Categorie> findAll() {
        // TODO Auto-generated method stub
        List<Categorie> list=new ArrayList<Categorie>();
        String sql="SELECT * FROM CATEGORIE";
        try
        {
            statement=connection.createStatement();
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            ResultSet resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                list.add(new Categorie(resultSet.getLong("id_categorie"), resultSet.getNString("title")));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(list.size()!=0)
            return list;
        return null;
    }

    @Override
    public boolean delete(Categorie p) {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM CATEGORIE WHERE id_categorie="+p.getId();
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
    public boolean update(Categorie p) {
        // TODO Auto-generated method stub
        String sql = "UPDATE CATEGORIE SET title ='"+p.getTitle()+"' WHERE id_categorie="+p.getId();
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
    public List<Categorie> findAll(String key) {
        // TODO Auto-generated method stub
        List<Categorie> list=new ArrayList<Categorie>();
        String sql="SELECT * FROM CATEGORIE WHERE  id_categorie LIKE '%"+key+"%' OR title LIKE '%"+key+"%'";
        try
        {
            statement=connection.createStatement();
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            ResultSet resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                list.add(new Categorie(resultSet.getLong("id_categorie"), resultSet.getNString("title")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(list.size()!=0)
            return list;
        return null;
    }

    @Override
    public List<Payment> getPayements(Categorie p) {
        return null;
    }

    @Override
    public List<Commande> getCommandes(Categorie p) {
        return null;
    }

    @Override
    public List<Payment> getPayements(Vente v) {
        return null;
    }

}
