package com.zoho.Mbank;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
public class AdminMenuView
{ 
    static Connection con=null;
    static PreparedStatement ps=null;
    static ResultSet rs=null;
  Scanner  scanner = new Scanner(System.in);


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

  public void adminAction() throws SQLException
  {
    
       int cont=1;
       while(cont==1)
       {
                System.out.println();
                System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");
                System.out.println("X\tWelcome to Mbank   ADMIN MENU           X");
                System.out.println("O\t1.    APPROVE  STAFF  REQUEST           O");
                System.out.println("X\t2.    HOLD STAFF                        X"); 
                System.out.println("X\t3.    SHOW ALL STAFF                    X"); 
                System.out.println("O\t4.    BACK                              O");
                System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");

               
                int choice = scanner.nextInt();
                    
                switch (choice)
                {

                    case 1:
                            approvesstaff();
                            break;
                    case 2:
                          holdstaff(); 
                           break;
                    case 3:
                            showtaff();
                            break;  
                    case 4: cont=0;
                             break;                                                 
                    default:
                    System.out.println("ENTER A VALID NUMBER ");                
                }
  }
}





public void approvesstaff()
{
    
        int cont=1;
       while(cont==1)
       {
                System.out.println();
                System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");
                System.out.println("X\tWelcome to Mbank   ADMIN MENU 2.0       X");
                System.out.println("O\t1.    APPROVE  STAFF                    O");
                System.out.println("O\t2.    BACK                              O");
                System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");

               
                int choice = scanner.nextInt();
                switch (choice)
                {

                	case 1: 
		       approveStaff();
		       break; 
		 
		case 2:
			  cont=0;
			  break;
		default:
			 System.out.println("ENTER A VALID NUMBER ");                
                }
                    
  }

    
        }
        
         public void approveStaff()
         {
          try
                {
                    ArrayList<UserModel> al=getter();
                                  
                    if(al.size()!=0)
                    {
                        for(int i=0;i<al.size();i++)
                        {
                        if(al.get(i).getstatus().equals("Registered"))
                        {
                        System.out.println("--------------------------------------------------------------------------------------");
                        System.out.println(String.format("\n\t%-50s|%-20s|%-20s","NAME","MOBILE","STATUS"));
                        System.out.println("--------------------------------------------------------------------------------------");
                    
                        System.out.println(String.format("\n\t%-50s|%-20s|%-20s",al.get(i).getname(),al.get(i).getmobile(),al.get(i).getstatus()));
                        System.out.println("---------------------------------------------------------------------------------------");
                           
                        System.out.println("1. Approve\n2. Reject");
                        byte aprove=scanner.nextByte();
                           while(aprove!=1&& aprove!=2)
                            {
                                 System.out.println("Enter the valid number 1 or 2");                   
                                aprove=scanner.nextByte();
                            }
                           if(aprove==1)
                           {
                              st.executeUpdate("update users set status='Approved' where mobile_no="+al.get(i).getmobile()); 
                             System.out.println("APPROVED SUCCESSFULLY"); 
   
                           }
                            else if(aprove==2)
                            {
                                 st.executeUpdate("update users set status='Rejected' where mobile_no="+al.get(i).getmobile()); 
                                 System.out.println(" NOT !!!!! APPROVED SUCCESSFULLY"); 
                            }
                            }
                            else
                   	{
                        System.out.println("NO APPROVEL avaliable");
          		}
                        }
                    }
                    
               
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();

                    }  
                }         
                              
                            
                        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
                public void holdstaff()
                {
                    try{

                    ArrayList<UserModel> al=getter();
                    if(al.size()>0)
                    {
                        for(int i=0;i<al.size();i++)
                        {
                        if(al.get(i).getstatus().equals("Approved"))
                        {
                        System.out.println("--------------------------------------------------------------------------------------");
                        System.out.println(String.format("\n\t%-50s|%-20s|%-20s","NAME","MOBILE","STATUS"));
                        System.out.println("--------------------------------------------------------------------------------------");
                    
                        System.out.println(String.format("\n\t%-50s|%-20s|%-20s",al.get(i).getname(),al.get(i).getmobile(),al.get(i).getstatus()));
                        System.out.println("---------------------------------------------------------------------------------------");
                           
                           System.out.println("1. REMOVE \n2. HOLD");
                           byte aprove=scanner.nextByte();
                           while(aprove!=1&& aprove!=2)
                            {
                                 System.out.println("Enter the valid number 1 or 2");                   
                                aprove=scanner.nextByte();
                            }
                           if(aprove==1)
                           {
                             st.executeUpdate("update users set status='Removed' where mobile_no="+al.get(i).getmobile()); 
                             System.out.println("APPROVED SUCCESSFULLY"); 
   
                           }
                            else if(aprove==2)
                            {
                                 st.executeUpdate("update users set status='Hold' where mobile_no="+al.get(i).getmobile()); 
                                 System.out.println(" HOLD SUCCESSFULLY"); 
                            }
                            }
                        }
                    }
                    else{
                        System.out.println("NO HOLD avaliable");
                    }
               
                    }
                
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                }







	public void showtaff()
	{
	    try
	    {
		
		  rs=Database.getInstance().getConnection().createStatement().executeQuery("SELECT name, mobile_no,status FROM users WHERE role_id = 3;");
	        while(rs.next())
            {
	        
	        {
	        System.out.println("---------------------------------------------------------------------------------------------");
	        System.out.println(String.format("\n\t%-50s|%-20s|%-20s","NAME","MOBILE","status"));
	        System.out.println("---------------------------------------------------------------------------------------------");
	      
	        System.out.println(String.format("\n\t%-50s|%-20s|%-20s",rs.getString(1),rs.getLong(2),rs.getString(3)));
	        System.out.println("---------------------------------------------------------------------------------------------");

	        }
	        }
		   
	    }
	    catch(Exception e)
	    {
	        System.out.println("STAFF SHOWING PROBLEM ");

	    }
	}



 	public ArrayList<UserModel> getter() 
          {
            ArrayList<UserModel> al=new ArrayList<>();
            try
            {
                ps=con.prepareStatement("SELECT name,mobile_no,status FROM users WHERE role_id = 3 ;");
                rs=ps.executeQuery();
                while(rs.next())
                {
                    al.add(new UserModel(rs.getString(1),rs.getLong(2),rs.getString(3)));
                }
            }
            catch(SQLException e)
            {
            System.out.println("getter problem"+e);
            }
            return al;
          }



}

