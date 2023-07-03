package com.zoho.LibraryApp;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Scanner;
class User 
{
    private static Connection con=null;
  private static PreparedStatement ps=null;
  private static ResultSet rs=null;
  private static Scanner scanner = new Scanner(System.in);

 public static void addUser() throws SQLException
 {
    Statement st=new DbConnection().getConnenction().createStatement();
    System.out.println("Enter the user name:");
    String name = scanner.nextLine();

    System.out.println("Enter the address:");
    String address = scanner.nextLine();


    System.out.println("Enter the mobile:");
    long mobile = scanner.nextLong();
    

rs =st.executeQuery("SELECT id FROM user_details WHERE LOWER(name) = LOWER('"+name+"') ;");
    if (rs.next())
    {
        System.out.println("names id already exists in the database. user name " + name);
    }
else{
        new DbConnection().getConnenction().createStatement().executeUpdate("INSERT INTO user_details (name,address,mobile) VALUES ('"+ name+"','"+address+"',"+mobile+" ); ");
        // ps.setString(1,name);
        // ps.setString(2,address);
        // ps.setLong(3,mobile);
        // rs=ps.executeQuery();
       // rs.next();
        System.out.println("User added successfully! " );
    }
   }
}