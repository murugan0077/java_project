 package com.zoho.LibraryApp;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Scanner;
class BookDeatils 
{
  private static Connection con=null;
  private static PreparedStatement ps=null;
  private static ResultSet rs=null;
  private static Scanner scanner = new Scanner(System.in);

 public static void addBook() throws SQLException
 {

    Statement st=new DbConnection().getConnenction().createStatement();
    System.out.println("Enter the book title:");
    String title = scanner.nextLine().trim();

    System.out.println("Enter the author:");
    String author = scanner.nextLine();

    System.out.println("Enter the category:");
    String category=scanner.nextLine();

    System.out.println("Enter the publication year:");
    int year = scanner.nextInt();

    System.out.println("Enter the count");
    int count = scanner.nextInt();
    
    int bookId=0,authorsId=0,categoryId=0;
    rs =st.executeQuery("SELECT id FROM books WHERE LOWER(bookname) = LOWER('"+title+"')  AND yearofpublication = "+year+";");
    if (rs.next())
    {
        bookId = rs.getInt("id");
        System.out.println("Book id already exists in the database. book ID: " + bookId);
    }
    else
    {
        ps = new DbConnection().getConnenction().prepareStatement("INSERT INTO books (bookname,yearofpublication) VALUES ( ?, ?)RETURNING id");
        ps.setString(1,title);
        ps.setInt(2,year);
        rs=ps.executeQuery();
        rs.next();
        bookId=rs.getInt("id");
        System.out.println("Book added successfully! " );
    }

    rs =  st.executeQuery("SELECT id FROM authors WHERE LOWER(name) = LOWER('"+author+"');");
    if (rs.next())
    {
        authorsId = rs.getInt("id");
        System.out.println("author id already exists in the database. authors ID: " + authorsId);
    }
    else
    {
        ps = new DbConnection().getConnenction().prepareStatement("INSERT INTO authors (name) VALUES ( ? ) RETURNING id");
        ps.setString(1,author);
        rs=ps.executeQuery();
        rs.next();
        authorsId=rs.getInt("id");
        System.out.println(authorsId);
        System.out.println("authors added successfully! " );
    }

    rs =  st.executeQuery("SELECT id FROM category  WHERE LOWER(name) = LOWER('"+category+"');");
    if (rs.next())
    {
        categoryId = rs.getInt("id");
        System.out.println("category already exists in the database. category ID: " + categoryId);
    }
    else
    {
        ps = new DbConnection().getConnenction().prepareStatement("INSERT INTO category (name) VALUES ( ? ) RETURNING id");
        ps.setString(1,category);
        rs=ps.executeQuery();
        rs.next();
        categoryId=rs.getInt("id");
        System.out.println("category added successfully! " );
    }



        ps = new DbConnection().getConnenction().prepareStatement("select id from book_details where book_id= ? and author_id= ? and category_id= ? ");
        ps.setInt(1,bookId);
        ps.setInt(2,authorsId);
        ps.setInt(3,categoryId);
        //int bid=book_Id;
       rs= ps.executeQuery();
 
        if(rs.next())
        {
            ps = new DbConnection().getConnenction().prepareStatement("update book_details set count=count+ ? where book_Id= ?;");
            ps.setInt(1,count);
            ps.setInt(2,bookId); 
            ps.executeUpdate();
            System.out.println("book_details UPDATE successfully! " );
        }
       else
       {    
            new DbConnection().getConnenction().createStatement().executeUpdate("INSERT INTO book_details (book_id,author_id,category_id,count) VALUES ("+bookId+","+authorsId+","+categoryId+","+count+" ); ");
            System.out.println("book_details added successfully!");
       }
}


    public void listBooks() throws SQLException
    {

        // Get all the books from the database
        //String sql = "SELECT * FROM books";
        //Statement ps = new DbConnection().getConnenction().createStatement();
        ResultSet rs = new DbConnection().getConnenction().createStatement().executeQuery("SELECT bd.book_id,b.bookname,b.yearofpublication,a.name,c.name,bd.count FROM books b join book_details bd on bd.book_id=b.id join authors a on bd.author_id=a.id join category c on c.id=bd.category_id  " );
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("\n\t%-5s|%-50s|%-20s|%-20s|%-20s|%-10s","id","title","yearofpublication","authorname","category","count"));
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        while (rs.next()) {
            int id = rs.getInt(1);
            String title = rs.getString(2);
            int yearofpublication = rs.getInt(3);
            String authorname=rs.getString(4);
            String category=rs.getString(5);
            int count=rs.getInt(6);
           // System.out.println( id + "   |   " + title + "   |   " + yearofpublication + "   |   " + authorname);
            System.out.println(String.format("\t%-5s %-50s %-20s %-20s %-20s %-10s ",id,title,yearofpublication,authorname,category,count));

        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");

    }


    
public void searchBook() throws SQLException {

        System.out.println("Enter the book name1:");
        String title = scanner.nextLine().toLowerCase();

        // Search for the book in the database
        //String sql = "SELECT * FROM books WHERE title = ?";
       //PreparedStatement ps = new DbConnection().getConnenction().prepareStatement(sql);
        //ps.setString(1, title);
        ResultSet rs =new DbConnection().getConnenction().createStatement().executeQuery("SELECT * FROM books WHERE lower(bookname) like '"+title+"%';");
        
        // Print the book to the console if it is found
        
        while (rs.next()) {
            int id = rs.getInt(1);
            String titles = rs.getString(2);
            int yearofpublication = rs.getInt(3);
    

            System.out.println(id + " | " + titles	 + " | " + yearofpublication );
        } 
        if(rs.next()) {
            System.out.println("Book not found!");
        }
    }
}
