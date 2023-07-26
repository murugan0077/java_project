package com.zoho.Mbank;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AppMain 
{

  static Connection con=null;
  static PreparedStatement ps=null;
  static ResultSet rs=null;
  static Connection connection;
  static Scanner scanner;


public static void main(String[] args)throws SQLException
{
	   scanner = new Scanner(System.in);

	    try {
    		new TableCreation().tableOrder();
            }
            catch(Exception e)
            {
               e.printStackTrace();
            }
       boolean flag=true;
       while(flag==true)
       {
                System.out.println();
                System.out.println("--------------------------------------------");
                System.out.println("|\tWelcome to Mbank                   |");
                System.out.println("|\t1.  Login                          |");
                System.out.println("|\t2.  Sign-up                        |"); 
                System.out.println("|\t3.  Exit                           |");
                System.out.println("--------------------------------------------");

               
                int choice = scanner.nextInt();
                //scanner.nextLine();
                switch (choice)
                {

                    case 1:
                            UserModel user=new Login().elogin();
                            if(user==null)
                                System.out.println("Invalid user number or password");
                   else
                       {
                                int id=user.getrole_id();
                                int uid=user.getuid();
                                if(user.getrole_id()==1)
                                {   
                                System.out.println("\t WELCOME ADMIN");
                                try{
                                    new AdminMenuView().adminAction();
                                }
                                catch(SQLException e)
                                {
                                    e.printStackTrace();
                                }
                                }
		                       else if(user.getrole_id()==3)
		                        {
		                             System.out.println("\t WELCOME STAFF");
		                             new StaffMenuView().staffAction(uid);
		                        }
				               else 
				               {
				                   System.out.println("\t WELCOME CUSTOMER"); 
				                   new UserMenuView().userAction(uid);
				               }
                       }
                            break;
                            	
                    case 2:
                           	try
                            	{
                        	 	new SignupUser().usersignup();
                            	}
		                  catch(Exception e)
		                  {
		                      e.printStackTrace();
		                  }
		                  break;
                    case 3:
                        System.out.println("Goodbye!");
                        flag=false;
                        break ;
                }
       }
    }

}
