package com.zoho.LibraryApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
	
public class DbConnection
{
	public Connection getConnenction() throws SQLException
		{			
			String url="jdbc:postgresql://localhost:5432/librarymanagementsystem";
			String username="postgres";
			String password="zoho";
			Connection con=DriverManager.getConnection(url,username,password);
			
			return con;
		}
}
