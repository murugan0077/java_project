 package com.zoho.LibraryApp;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;
class Reservation 
{
  private static Connection con=null;
  private static PreparedStatement ps=null;
  private static ResultSet rs=null;
  private static Scanner scanner = new Scanner(System.in);

  
public static void reserve_book() throws SQLException 
{
    
        System.out.println("Enter the book_id");
        int book_id = scanner.nextInt();

        ps = new DbConnection().getConnenction().prepareStatement("select id from book_details WHERE id= ? and count = 0 ");
        ps.setInt(1,book_id); 
        rs=ps.executeQuery();
    
        if(rs.next())
        {
           // isBookAvailable(book_id);
        }
         else
        {
        System.out.println(" BOOK  IS AVALIABLE .PLEASE BORROW BOOK ");
        return;
        }


        System.out.println("Enter the user ID:");
        int user_id = scanner.nextInt();


        ps = new DbConnection().getConnenction().prepareStatement("select id from user_details WHERE id= ?");
        ps.setInt(1,user_id); 
        rs=ps.executeQuery();
    
        if(rs.next())
        {   
                    // checkBorrowIdBorrowingBookExceeds5(user_id);
        ps = new DbConnection().getConnenction().prepareStatement("SELECT COUNT(*) AS num_books_borrowed FROM barrow_details WHERE user_id = ? ");
        ps.setInt(1, user_id);
        ResultSet resultSet= ps.executeQuery();

                if (resultSet.next() && resultSet.getInt("num_books_borrowed") <= 5) 
                {
                    System.out.println("BOOKS COUNT OK! ");
                }
                else
                {
                    System.out.println("BOOK BORROW LIMIT EXCEED  !!!!!!!!!");
                    return;
                }

        }
        else
        {
            System.out.println("ENTER THE VALID USER ID ");
            return;
        }


ps = new DbConnection().getConnenction().prepareStatement("INSERT INTO reservation (user_id, book_id, reservation_date, status) VALUES (?, ?, ?, ?)");
        ps.setInt(1,user_id);
        ps.setInt(2,book_id);
        ps.setDate(3,new java.sql.Date(new java.util.Date().getTime()));
        ps.setString(4,"pending");
       int m = ps.executeUpdate();

        if(m>0)
        {
             System.out.println("RESERVATION added successfully! " );

        }
       else
        System.out.println("RESERVATION PROBLEM!!!!!!!");



    }
}











    
    
