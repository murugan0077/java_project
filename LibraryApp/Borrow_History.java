package com.zoho.LibraryApp;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;
class Borrow_History 
{
  private static Connection con=null;
  private static PreparedStatement ps=null;
  private static ResultSet rs=null;
  private static Scanner scanner = new Scanner(System.in);


static void history() throws SQLException
{

        ResultSet rs = new DbConnection().getConnenction().createStatement().executeQuery("SELECT u.id,bd.book_id,bw.barrow_date,bw.returning_date,bw.return_status FROM books b join book_details bd on bd.book_id=b.id  join barrow_details bw on bw.book_id=bd.book_id join user_details u on bw.user_id=u.id " );
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("\n\t%-5s|%-50s|%-20s|%-20s|%-10s","user_id","book_id","barrow_date","returning_date","return_status "));
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        while (rs.next()) {
            int user_id = rs.getInt(1);
            String book_id=rs.getString(2);
            String barrow_date = rs.getString(3);
            String returning_date = rs.getString(4);
            boolean return_status=rs.getBoolean(5);
            
           // System.out.println( id + "   |   " + title + "   |   " + yearofpublication + "   |   " + authorname);
            System.out.println(String.format("\t%-5s %-50s %-20s %-20s %-10s ",user_id,book_id,barrow_date,returning_date,return_status));

        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");

    }          

}
