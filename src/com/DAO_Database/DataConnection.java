package com.DAO_Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataConnection {
    Connection connection=null;
    Statement statement=null;
    public DataConnection() {
        String url = "jdbc:mysql://localhost:3306/bdcc";

        try {
            connection = DriverManager.getConnection(url, "root", "");
            System.out.println("Server Connected ...");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Problem connection...");
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return connection;
    }

}
