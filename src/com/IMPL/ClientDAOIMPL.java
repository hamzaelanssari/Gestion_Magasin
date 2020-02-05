package com.IMPL;



import com.DAO_Database.*;
import com.classes.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOIMPL implements DAO<Client>{
    Connection connection=null;
    Statement statement=null;

    public ClientDAOIMPL() {
        DataConnection data;
        data = new DataConnection();
        connection=data.getConnection();
    }

    @Override
    public boolean create(Client client) {

        // TODO Auto-generated method stub
        String sql = "insert into client(nom,prenom,email,telephone,adresse) values ('"+client.getNom()+"','"+client.getPrenom()+"','"+client.getEmail()+"','"+client.getTele()+"','"+client.getAdresse()+"')";

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
    public boolean delete(Client client) {
        // TODO Auto-generated method stub
        String sql = "delete from client where id_client="+client.getId_client();
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
    public boolean update(Client client) {
        // TODO Auto-generated method stub
        String sql = "update client set  nom ='"+client.getNom()+"', prenom='"+client.getPrenom()+"', email='"+client.getEmail()+"',telephone='"+client.getTele()+"',adresse='"+client.getAdresse()+"' where id_client="+client.getId_client();
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
    public Client find(long id) {
        // TODO Auto-generated method stub
        String sql="select * from client where id_client="+ id;
        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            ResultSet resultSet=statement.executeQuery(sql);
            if(resultSet.next()){
                return new Client(resultSet.getLong("id_client"), resultSet.getNString("nom"),resultSet.getNString("prenom"),resultSet.getNString("email"),resultSet.getNString("telephone"), resultSet.getNString("adresse"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Client> findAll(String key) {
        // TODO Auto-generated method stub
        List<Client> list=new ArrayList<Client>();
        String sql="select * from client where  id_client like '%"+key+"%' or nom like '%"+key+"%' or  prenom like '%"+key+"%' or  email like '%"+key+"%' or telephone like '%"+key+"%' or adresse like '%"+key+"%'";
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
                list.add(new Client(resultSet.getLong("id_client"), resultSet.getNString("nom"), resultSet.getNString("prenom"), resultSet.getNString("email"), resultSet.getNString("telephone"), resultSet.getNString("adresse")));
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
    public List<Payment> getPayements(Client p) {
        return null;
    }

    @Override
    public List<Commande> getCommandes(Client p) {
        return null;
    }

    @Override
    public List<Payment> getPayements(Vente v) {
        return null;
    }


    @Override
    public List<Client> findAll() {
        // TODO Auto-generated method stub
        List<Client> list=new ArrayList<Client>();
        String sql="select * from client";
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
                list.add(new Client(resultSet.getLong("id_client"), resultSet.getNString("nom"), resultSet.getNString("prenom"), resultSet.getNString("email"), resultSet.getNString("telephone"), resultSet.getNString("adresse")));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(list.size()!=0)
            return list;
        return null;
    }


}
