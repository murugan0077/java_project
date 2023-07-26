package com.zoho.Mbank;
import java.util.Scanner;
import java.sql.SQLException;

public class StaffMenuView
{ 
    
    Scanner  scanner = new Scanner(System.in);
    StaffController sc=new StaffController();

	   public void staffAction(int uid) throws SQLException
	   {
       	    boolean flag=true;
	       while(flag==true)
	       {
		       System.out.println();
		       System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");
		       System.out.println("X\tWelcome to Mbank   STAFF MENU           X");
		       System.out.println("O\t1.    APPROVE  COSTOMER  REQUEST        O");
		       System.out.println("X\t2.    HOLD COSTOMER                     X"); 
		       System.out.println("X\t3.    SHOW ALL COSTOMER                 X"); 
			   System.out.println("X\t4.    WITHDRAW AMOUNT                   X"); 
               System.out.println("X\t5.    DEPOSITE AMOUNT                   X"); 
		       System.out.println("O\t6.    LOG-OUT                           O");
		       System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");


		       int choice = scanner.nextInt();
	  
			       switch (choice)
			       {

				  case 1:
					 sc.accessCustomer();
					 break;
				  case 2:
				        sc.holdcustomer(); 
					break;
				  case 3:
					 sc.showcustomer().toString();
					 break;  
				 case 4:
					 sc.withdrawAmount();
					 break;
				 case 5:
					 sc.depositAmount();
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
