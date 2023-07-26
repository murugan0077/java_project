package com.zoho.Mcart;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
public class AdminMenu
{ 
    static Connection con=null;
    static PreparedStatement ps=null;
    static ResultSet rs=null;
    static Statement st=null;
  Scanner  scanner = new Scanner(System.in);
  	static
  	{
  		try{
  		con=DbConnection.getInstance().getConnection();
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
                System.out.println("X\tWelcome to Mcart   ADMIN MENU           X");
                System.out.println("O\t1.    APPROVE  VENTOR  REQUEST          O");
                System.out.println("X\t2.    HOLD VENTOR                       X"); 
                System.out.println("X\t3.    SHOW ALL VENTORS                  X"); 
                System.out.println("O\t4.    BACK                              O");
                System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");

               
                int choice = scanner.nextInt();
                    
                switch (choice)
                {

                    case 1:
                            approveventor();
                            break;
                    case 2:
                          holdventor(); 
                           break;
                    case 3:
                            showventor();
                            break;  
                     case 4: cont=0;
                             break;                                                     default:
                    System.out.println("ENTER A VALID NUMBER ");                
                }
  }
}





public void approveventor()
{
    
        int cont=1;
       while(cont==1)
       {
                System.out.println();
                System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");
                System.out.println("X\tWelcome to Mcart   ADMIN MENU 2.0       X");
                System.out.println("O\t1.    APPROVE  VENTOR                   O");
                System.out.println("O\t2.    BACK                              O");
                System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");

               
                int choice = scanner.nextInt();
                switch (choice)
                {

                case 1: 
                try
                {
                    ArrayList<UserModel> al=getter();
                    if(al.size()>0)
                    {
                        for(int i=0;i<al.size();i++)
                        {
                        if(al.get(i).getstatus().equals("Registered"))
                        {
                        System.out.println("--------------------------------------------------------------------------------------");
                        System.out.println(String.format("\n\t%-50s|%-20s|%-20s|%-20s","NAME","MOBILE","G-MAIL","STATUS"));
                        System.out.println("--------------------------------------------------------------------------------------");
                    
                        System.out.println(String.format("\n\t%-50s|%-20s|%-20s|%-20s",al.get(i).getname(),al.get(i).getmobile(),al.get(i).getgmail(),al.get(i).getstatus()));
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
                             DbConnection.getInstance().getConnection().createStatement().executeUpdate("update user_details set status='Approved' where mobile="+al.get(i).getmobile()); 
                             System.out.println("APPROVED SUCCESSFULLY"); 
   
                           }
                            else if(aprove==2)
                            {
                                 st.executeUpdate("update user_details set status='Rejected' where mobile="+al.get(i).getmobile()); 
                                 System.out.println(" NOT !!!!! APPROVED SUCCESSFULLY"); 
                            }
                            }
                        }
                    }
                    else{
                        System.out.println("no approvel avaliable");
                    }
               
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();

                    }           
                            break;     
                    case 2:
                    cont=0;
                    break;
                    default:
                    System.out.println("ENTER A VALID NUMBER ");                
                }
                    
  }

    
        }
        
                public void holdventor()
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
                        System.out.println(String.format("\n\t%-50s|%-20s|%-20s|%-20s","NAME","MOBILE","G-MAIL","STATUS"));
                        System.out.println("--------------------------------------------------------------------------------------");
                    
                        System.out.println(String.format("\n\t%-50s|%-20s|%-20s|%-20s",al.get(i).getname(),al.get(i).getmobile(),al.get(i).getgmail(),al.get(i).getstatus()));
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
                             st.executeUpdate("update user_details set status='Removed' where mobile="+al.get(i).getmobile()); 
                             System.out.println("APPROVED SUCCESSFULLY"); 
   
                           }
                            else if(aprove==2)
                            {
                                st.executeUpdate("update user_details set status='Hold' where mobile="+al.get(i).getmobile()); 
                                 System.out.println(" HOLD SUCCESSFULLY"); 
                            }
                            }
                        }
                    }
                    else{
                        System.out.println("no HOLD avaliable");
                    }
               
                    }
                
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                }







public void showventor()
{
    try
    {
         
           rs=st.executeQuery("SELECT name, mobile, gmail, status FROM user_details WHERE role_id = 2;");
        while(rs.next()){
        
        {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println(String.format("\n\t%-50s|%-20s|%-20s|%-20s","NAME","MOBILE","G-MAIL","status"));
        System.out.println("---------------------------------------------------------------------------------------------");
      
        System.out.println(String.format("\n\t%-50s|%-20s|%-20s|%-20s",rs.getString(1),rs.getLong(2),rs.getString(3),rs.getString(4)));
        System.out.println("---------------------------------------------------------------------------------------------");

        }
        }
            
    }
    catch(Exception e)
    {
        System.out.println("VENTOR SHOWING PROBLEM ");

    }
}









 public ArrayList<UserModel> getter() 
          {
            ArrayList<UserModel> al=new ArrayList<>();
            try
            {
                ps=con.prepareStatement("SELECT name,mobile,gmail,status FROM user_details WHERE role_id = 2 ;");
                rs=ps.executeQuery();
                while(rs.next())
                {
                    al.add(new UserModel(rs.getString(1),rs.getLong(2),rs.getString(3),rs.getString(4)));
                }
            }
            catch(SQLException e)
            {
               System.out.println("getter problem");
            }
                 return al;
          }



}

