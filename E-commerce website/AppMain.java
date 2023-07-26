package com.zoho.Mcart;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AppMain {

static Connection con=null;
  static PreparedStatement ps=null;
  static ResultSet rs=null;


 static Connection connection;
    static Scanner scanner;


    public static void main(String[] args) throws SQLException 
    {

    	new TableCreation().tableOrder();
            
	   scanner = new Scanner(System.in);
       int cont=1;
       outer:
       while(cont==1)
       {
                System.out.println();
                System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");
                System.out.println("X\tWelcome to Mcart                       |");
                System.out.println("O\t1.  Login                              |");
                System.out.println("X\t2.  Sigin-up                           |"); 
                System.out.println("O\t3.  Exit                               |");
                System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");

               
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
                                int id=user.getuid();
                                if(user.adr.getrole_id()==1)
                                {    new AdminMenu().adminAction();
                                }else if(user.adr.getrole_id()==2)
                                 {
                                      new VentarMenu().ventarActiion(id);
                                 }
                                 else 
                                 {
                                      new UserMenu().userAction(id);
                                 }
                                }
                            break;
                    case 2:
                            try{
                            new SignupUser().usersignup();
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                            break;
                    case 3:
                        System.out.println("Goodbye!");
                       
                        break outer;
                }
       }
       
          
    }

}
