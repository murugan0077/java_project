package com.zoho.Mbank;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.Scanner;
public class Login

{ 
    static Connection con=null;
    static PreparedStatement ps=null;
    static ResultSet rs=null;
    static Statement st=null;

    Scanner scanner = new Scanner(System.in);
     static
  	{
  		try{
  		con=Database.getInstance().getConnection();
  		st=con.createStatement();
  		}
  		catch(SQLException e)
  		{
  		e.printStackTrace();
  		}
  	}
    
    public UserModel elogin() throws SQLException
    {
        UserModel user=null;

        System.out.println("Enter The User phone-number");
         long mobile=scanner.nextLong();


        System.out.println("Enter The Password");
        scanner.nextLine();
        String password=scanner.nextLine();
           
        try
            { 
                ps=con.prepareStatement("select u.id,u.role_id,u.name,u.mobile_no,u.gender,a.password,ua.door_no,ua.street_name,ua.city,ua.district,ua.pincode from users u JOIN accounts a on u.id=a.user_id JOIN user_address ua on u.id=ua.user_id where u.mobile_no= (?) and a.password= (?) and  (u.status='Approved' or u.status='Admin')");
                ps.setLong(1,mobile);
                ps.setString(2,password);
                rs=ps.executeQuery();
                if(rs.next())
                {
                   user=new UserModel(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getLong(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getInt(11));
                  System.out.println("LOGIN SUCCESSFULL ");
                }
                
                
             }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        return user;
    }
}