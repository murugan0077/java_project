 package com.zoho.LibraryApp;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class LibraryManagementSystem {

    private static Connection connection;
    private static Scanner scanner;
    public static void main(String[] args) throws Exception 
    {
    	new CreateTable().tableOrder();
            
	   scanner = new Scanner(System.in);
       int cont=1;
       while(cont==1)
       {
                System.out.println();
                System.out.println("********************************************************************")  ;
                System.out.println("*\tWelcome to MURUGAN Library                                 *");
                System.out.println("*\tWhat would you like to do?                                 *");
                System.out.println("*\t1. Add a book                                              *");
                System.out.println("*\t2. List all books                                          *");
                System.out.println("*\t3. Search for a book                                       *");
                System.out.println("*\t4. reservation                                             *");
                System.out.println("*\t5. Borrow a book                                           *");
                System.out.println("*\t6. Return book                                             *");
                System.out.println("*\t7. Borrowing history a book                                *");
                System.out.println("*\t8. Add User                                                *");
                System.out.println("*\t9.  Exit                                                   *");
                System.out.println("********************************************************************")    ;

               
                int choice = scanner.nextInt();
                //scanner.nextLine();
                BookDeatils bd=new BookDeatils();
                    
                switch (choice)
                {

                    case 1:
                        bd.addBook();
                        break;
                    case 2:
                        bd.listBooks();
                        break;
                    case 3:
                        bd.searchBook();
                        break;
                    case 4:
                        Reservation.reserve_book();
                        break;
                    case 5:
                            Borrowbooks.borrowBook();
                        break;
                    case 6:
                        Return.returnBook();
                        break;
                    case 7:
                        Borrow_History.history();
                        break;
                    case 8:
                        new User().addUser();
                        break;
                    case 9:
                        System.out.println("Goodbye!");
                        return;
                    default:
                    	System.out.println("Enter number 1-9");
                
                }
                System.out.println();
        System.out.println("press 1 to continue"); 
	    cont=scanner.nextInt();
       }
    }

}
