package com.zoho.Mbank;
import java.io.Console;   
import java.sql.SQLException;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class SignupUser
{ 
	    static Connection con=null;
	    static PreparedStatement ps=null;
	    static ResultSet rs=null;	
		static Statement st=null;
	    Scanner scanner=new Scanner(System.in);
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

	    public void usersignup() throws Exception
	    {
	    System.out.println("Enter the Name:");
	    String name = scanner.nextLine();

	    System.out.println("Enter the Mobile Number:");
	    long mobile =new Validation().isMobile (scanner.nextLine());
	     
	     ArrayList<UserModel> al=getter();
	     for(int i=0;i<al.size();i++)
	     {
	        if(al.get(i).getmobile()==mobile)
	        {
		   System.out.println("this mobile number is already exist");
		   return;
	        }
	     }
		     
		System.out.println("Enter the Password:");
		  // scanner.nextLine();
		    String password1=String.valueOf(System.console().readPassword());
		       boolean password = new Validation().passwordChecker(password1);
		    while(!password)
		    {
		        System.out.println("Enter the valid Password like *** captial and small letters special charectors and numbers ");
		        password1=scanner.nextLine();
		        password = new Validation().passwordChecker(password1);   
		    }	 
		   
	        System.out.print("Enter your date of birth (dd/mm/yyyy): ");
	        String dobInput = scanner.nextLine();

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        LocalDate dateOfBirth = LocalDate.parse(dobInput, formatter);

		System.out.println("Enter the Gender:");
		String genter=scanner.nextLine();


        try
        {
            
            rs=st.executeQuery("select * from roles");
            System.out.println("Enter the user role");
            while(rs.next())
            {
                System.out.println(rs.getInt(1)+" for "+rs.getString(2));

            }
              int role_id=scanner.nextInt();
                    
          
		  int u_id=0;
		  ps = con.prepareStatement("INSERT INTO users (role_id,name,mobile_no,password,Dob,gender,status) VALUES ( ?,?,?,?,?,?,?) returning id");
		  ps.setInt(1,role_id);
		  ps.setString(2,name);
		  ps.setLong(3,mobile);
		  ps.setString(4,password1);
		  ps.setDate(5,Date.valueOf(dateOfBirth));
		  ps.setString(6,genter);

		
		  if(role_id==2)
		  {
		  ps.setString(7,"Registered");//Customer
		  }
		  else if(role_id==3)
		  ps.setString(7,"Registered");//staff
		  else if(role_id==4)
		  ps.setString(7,"Registered");//Customer
		  else
		  ps.setString(7,"Admin");


		  rs=ps.executeQuery();
		  if(rs.next())
		  {
		  u_id=rs.getInt(1);
		  System.out.println("user_details added successfully! " );
		  
		  }
	   
	   


                System.out.println("Enter the ADDRESS");
                System.out.println("Enter the door no");
                int door_no=scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter the steet name");
                String street_name=scanner.nextLine();
                System.out.println("Enter the city name");
                String city_name=scanner.nextLine();
                System.out.println("Enter the district name");
                String district_name=scanner.nextLine();
                System.out.println("Enter the Pinncode");
                int pin_cod=scanner.nextInt();
                

                ps = con.prepareStatement("INSERT INTO user_address ( user_id,door_no,street_name,city,district,pincode) VALUES (?,?,?,?,?,?)");
                ps.setInt(1,u_id);
                ps.setInt(2,door_no);
                ps.setString(3,street_name);
                ps.setString(4,city_name);
                ps.setString(5,district_name);
                ps.setInt(6,pin_cod);
                ps.executeUpdate();
                System.out.println("user_address  added successfully! " );
    


                ps = con.prepareStatement("INSERT INTO accounts ( user_id,password) VALUES (?,?)");
                ps.setInt(1,u_id);
                ps.setString(2,password1.trim());
                ps.executeUpdate();
                System.out.println("USER_ACCOUNTS added successfully! " );

        }
	        catch(SQLException e)
	        {
		   e.printStackTrace();
	        }


          }

          
          
          
         ArrayList<UserModel> getter() throws SQLException
          {
            ArrayList<UserModel> al=new ArrayList<>();
            try

            {   
                rs=st.executeQuery("select name,mobile_no,dob,status,role_id,password from  users");            
                while(rs.next())
                {
                    al.add(new UserModel(rs.getString(1),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6)));
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
                 return al;
          }

}
