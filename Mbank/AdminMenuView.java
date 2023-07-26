package com.zoho.Mbank;
import java.util.Scanner;
import java.sql.SQLException;
public class AdminMenuView
{ 
    
    Scanner  scanner = new Scanner(System.in);
    AdminController ac=new AdminController();

  public void adminAction() throws SQLException
  {
       boolean flag=true;
       while(flag==true)
       {
                System.out.println();
                System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");
                System.out.println("X\tWelcome to Mbank   ADMIN MENU           X");
                System.out.println("O\t1.    APPROVE  STAFF  REQUEST           O");
                System.out.println("X\t2.    HOLD STAFF                        X"); 
                System.out.println("X\t3.    SHOW ALL STAFF                    X"); 
                System.out.println("O\t4.    LOG_OUT                           O");
                System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");


                int choice = scanner.nextInt();
                    
                switch (choice)
                {

                    case 1:
                            ac.approveStaff();
                            break;
                    case 2:
                          ac.holdstaff(); 
                           break;
                    case 3:
                            ac.showstaff();
                            break;  
                    case 4: 
                            flag=false;
                             break;                                                 
                    default:
                    System.out.println("ENTER A VALID NUMBER ");                
                }
  }
}






 }
