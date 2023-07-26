package com.zoho.Mcart;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.Scanner;


 class Login
{ 
    static Connection con=null;
    static PreparedStatement ps=null;
    static ResultSet rs=null;
     static Statement st=null;
    
    private static Scanner scanner = new Scanner(System.in);
    
    static
  	{
  		try{
  		if(con==null || st==null){
  		con=DbConnection.getInstance().getConnection();
  		st=con.createStatement();
  		}
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
                ps=con.prepareStatement("select u.id,u.role_id,u.name,u.mobile,u.gmail,u.gender,u.created_on,a.password,ua.door_no,ua.street_name,ua.city,ua.district,ua.pincode from user_details u JOIN accounts a on u.id=a.user_id JOIN user_address ua on u.id=ua.user_id where u.mobile= (?) and a.password= (?) and (u.status='Approved' or u.status='Admin');");
                ps.setLong(1,mobile);
                ps.setString(2,password);
                rs=ps.executeQuery();
                if(rs.next())
                {
                   user=new UserModel(rs.getInt(1),rs.getString(3),rs.getLong(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getInt(2),rs.getInt(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getInt(13));
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
