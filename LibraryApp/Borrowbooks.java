 package com.zoho.LibraryApp;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

class Borrowbooks 
{
  private static Connection con=null;
  private static PreparedStatement ps=null;
  private static ResultSet rs=null;
  private static Scanner scanner = new Scanner(System.in);


public static void borrowBook() throws SQLException {

        System.out.println("Enter the book ID:");
        int book_Id = scanner.nextInt();
int puthuid=0;

ResultSet rs =new DbConnection().getConnenction().createStatement().executeQuery("SELECT count FROM book_details WHERE book_id = "+book_Id+";");
        
        if(rs.next()) 
        {
            puthuid=rs.getInt("count");
        }
        if(puthuid<=0)
        {
                      System.out.println("book  not AVALIABLE  !!!!!!!!!!!!!   PLEASE RESERVE THE BOOK");
                      return;

        }






        rs =new DbConnection().getConnenction().createStatement().executeQuery("SELECT id FROM books WHERE Id = "+book_Id+";");
        
        if(rs.next()) 
        {
            puthuid=rs.getInt("id");
        }
        if(puthuid==0)
        {
                      System.out.println("book_Id not found!");
                      return;

        }


         System.out.println("Enter the user ID:");
        int user_id = scanner.nextInt();

        rs =new DbConnection().getConnenction().createStatement().executeQuery("SELECT id FROM user_details WHERE id ="+user_id+";");
        
        if(rs.next()) 
        {
          puthuid=rs.getInt("id");
        }
        else
        {
           System.out.println("user_id not found!");
           return;

        }
        
        ps = new DbConnection().getConnenction().prepareStatement("INSERT INTO barrow_details (user_id,book_id,barrow_date,returning_date,return_status) VALUES ( ?,?,?,?,?) ");
        ps.setInt(1,user_id);
        ps.setInt(2,book_Id);
        ps.setDate(3,new java.sql.Date(new java.util.Date().getTime()));
        ps.setDate(4,java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(20)));
        ps.setBoolean(5,false);
        int bid=book_Id;
       int m= ps.executeUpdate();
   //     rs.next();
        if(m>0)
        {
        new DbConnection().getConnenction().createStatement().executeUpdate("update book_details set count=count-1 where book_id= "+bid);
                  ps = new DbConnection().getConnenction().prepareStatement("UPDATE reservation SET status  =  'SUCCESSFULY BARROW' WHERE user_id=(?) ");
                  ps.setInt(1,user_id);
                  ps.executeUpdate();
        System.out.println("Borrow_Details added successfully! " );

        }
       else
       System.out.println("CURRENTLY BOOK NOT AVALIABLE!!!!!!!");
    
}

}