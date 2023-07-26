package com.zoho.Mbank;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class StaffController extends UserController implements StaffInterface
{

         public void accessCustomer()
         {
                try
                {
                    ArrayList<UserModel> al=getterstaff();
                                  
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
                                return;
          		            }
                            
                        }
                       
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();

                }  
            }         
                              
        
        
                public void holdcustomer()
                {
                    try{

                    ArrayList<UserModel> al=getterstaff();
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



	public UserModel showcustomer()
	{
	    try
	    {
		
		  rs=st.executeQuery("SELECT name, mobile_no,status FROM users WHERE role_id = 2;");
	        while(rs.next())
	        {
                return new UserModel(rs.getString(1),rs.getLong(2),rs.getString(3));
	       
	        }
	    }
	    catch(Exception e)
	    {
	        System.out.println("STAFF SHOWING PROBLEM ");
	    }
        return null;
	}


/*

	public void showcustomer()
	{
	    try
	    {
		
		  rs=st.executeQuery("SELECT name, mobile_no,status FROM users WHERE role_id = 2;");
	        while(rs.next())
	        {
	        System.out.println("---------------------------------------------------------------------------------------------");
	        System.out.println(String.format("\n\t%-50s|%-20s|%-20s","NAME","MOBILE","status"));
	        System.out.println("---------------------------------------------------------------------------------------------");
	      
	        System.out.println(String.format("\n\t%-50s|%-20s|%-20s",rs.getString(1),rs.getLong(2),rs.getString(3)));
	        System.out.println("---------------------------------------------------------------------------------------------");
	        }
	    }
	    catch(Exception e)
	    {
	        System.out.println("STAFF SHOWING PROBLEM ");
	    }
	}*/





            public void withdrawAmount()
            {
                 try
                    {
                          
                        System.out.println("ENTER A ACCOUNT NUMBER correctly");
                        String accno=scanner.nextLine();

                        System.out.println("ENTER A AMOUNT TO WITHDRAW");
                        float withdraw_amound=scanner.nextFloat();
                        scanner.nextLine();

                        ps =con.prepareStatement("select balance from customer where account_no=(?)");  
                        ps.setString(1,accno);
                        rs=ps.executeQuery();
                        if(rs.next())
                        {
                            if(rs.getInt(1)>=withdraw_amound)
                            {
                                ps =con.prepareStatement("UPDATE customer SET balance  =  balance - ? WHERE account_no=(?) ");
                                ps.setFloat(1,withdraw_amound);
                                ps.setString(2,accno);
                                ps.executeUpdate();
                                System.out.println("WITHDRAW added successfully!");
                            }
                            else
                            {
                                System.out.println("insufficient balance");
                            }
                                
                        }

                    }
                
                catch(Exception e)
                {
                        System.out.println("WITHDRAW PROBLEM! "+e );
                }
            }








       public void depositAmount()
        {
                try
                {
                        
                    System.out.println("ENTER A ACCOUNT NUMBER correctly");
                    String accno=scanner.nextLine();
                    
                    ps =con.prepareStatement("select account_no from customer where account_no= ? ");
                    ps.setString(1,accno);
                    rs=ps.executeQuery();

                    System.out.println("ENTER A AMOUNT TO DEPOSIT");
                    float deposit_amound=scanner.nextFloat();
                    scanner.nextLine();

                                    
                    ps =con.prepareStatement("UPDATE customer SET balance  =  balance + ? WHERE account_no=(?) ");
                    ps.setFloat(1,deposit_amound);
                    ps.setString(2,accno);
                    ps.executeUpdate();
                    System.out.println("DEPOSIT added successfully!");
                }
            
            catch(Exception e)
            {
                    System.out.println("DEPOSIT PROBLEM! "+e );
            }
        }




 	      ArrayList<UserModel> getterstaff() 
          {
            ArrayList<UserModel> al=new ArrayList<>();
            try
            {
                ps=con.prepareStatement("SELECT name,mobile_no,status FROM users WHERE role_id = 2 ;");
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