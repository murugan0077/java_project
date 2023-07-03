package com.zoho.LibraryApp;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Scanner;
class Return 
{
    private static Connection con=null;
  private static PreparedStatement ps=null;
  private static ResultSet rs=null;
  private static Scanner scanner = new Scanner(System.in);    
    
    public static void returnBook() throws SQLException 
    {

       Borrow_History.history();

        System.out.println("Enter the book ID:");
        int book_Id = scanner.nextInt();
        int puthuid=0;
        System.out.println("Enter the user ID:");
        int user_id = scanner.nextInt();

        rs =new DbConnection().getConnenction().createStatement().executeQuery("SELECT id FROM barrow_details WHERE user_id ="+user_id+" and book_id="+book_Id+" and return_status=false;");    
        if(rs.next()) 
        {
            puthuid=rs.getInt("id");
            System.out.println(puthuid);
            ps = new DbConnection().getConnenction().prepareStatement("UPDATE book_details SET count = count+1 WHERE id=(?) ");
            ps.setInt(1,book_Id);
            int m = ps.executeUpdate();
            if(m>0)
            {
                ps = new DbConnection().getConnenction().prepareStatement("UPDATE barrow_details SET return_status  = true , returning_date = (?) WHERE id=(?) ");
                ps.setDate(1,new java.sql.Date(new java.util.Date().getTime()));
                ps.setInt(2,puthuid);
                ps.executeUpdate();
                  
                  ps = new DbConnection().getConnenction().prepareStatement("UPDATE reservation SET status  =  'SUCCESSFULY RETURN' WHERE user_id=(?) ");
                  ps.setInt(1,user_id);
                  ps.executeUpdate();

                System.out.println("Book returned successfully! " );
            }
            else
            System.out.println("Book returned UNSUCCESSFUL!!!!!!!");

        }
        else
        {
           System.out.println("RETURN NOT AVAILABLE!");
           return;

        }
    }
}