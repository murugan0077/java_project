package com.zoho.Mcart;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DbConnection
 {
    private static DbConnection instance;
    public Connection connection;

    private DbConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/mcart";
        String username = "postgres";
        String password = "zoho";
        if(connection==null)
	        connection = DriverManager.getConnection(url, username, password);
    }

    public static DbConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
}
