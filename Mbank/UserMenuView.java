package com.zoho.Mbank;
import java.util.Scanner;
import java.sql.SQLException;

public class UserMenuView 
{ 
    
    Scanner  scanner = new Scanner(System.in);
    UserController uc=new UserController();

  public void userAction(int uid) throws SQLException
  {
       boolean flag=true;
       while(flag==true)
       {
                System.out.println();
                System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");
                System.out.println("X\tWelcome to Mbank   USER MENU            X");
                System.out.println("O\t1.   CREATE A NEW ACCOUNT               O");
                System.out.println("X\t2.    TRANSATATION                      X"); 
                System.out.println("X\t3.    CHECK BALANCE                     X"); 
                System.out.println("X\t4.    SHOW ACC HISTORY                  X");
                System.out.println("X\t5.    SHOW PROFILE                      X");                
                System.out.println("O\t6.    LOG-OUT                              O");
                System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");


                int choice = scanner.nextInt();
                    
                switch (choice)
                {
                    case 1:
                          uc.createAccount( uid);
                            break;
                    case 2:
                          uc.transferAmount(uid); 
                           break; 
                     case 3:
                           uc.checkBalance( uid);
                            break;   
                     case 4:
                            uc.showAccountHistory(uid);
                            break;
                     case 5:
                            uc.showProfile( uid);
                            break;      

                    case 6: 
                            flag=false;
                            break;                                                 
                    default:
                    System.out.println("ENTER A VALID NUMBER ");                
                }
            }
        }
 }








