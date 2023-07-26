package com.zoho.Mbank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileReader;
import java.io.IOException;

final class Database 
{
    private FileReader fr = null;
    private Connection con = null;
    private static Database db = null;

	    private Database() 
	    {
	        try
	        {
		   this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mbank","postgres","zoho");
	        }
		        catch (SQLException e) 
		        {
			   System.out.println("Database Connection Creation Failed : " + e.getMessage());
		        }
	        
	    }
	    
	    public static Database getInstance() throws SQLException
	    {
		    if(db==null || db.getConnection().isClosed())
		    {
		    	db=new Database();
		    }
	    return db;
	    }

	     Connection getConnection() 
	    {
	        return con;
	    }

	     void closeDB() throws SQLException 
	    {
	        if (con != null) {
		   con.close();
		   con = null;
	        }
	    }
}
