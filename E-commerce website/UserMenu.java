 package com.zoho.Mcart;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
public class UserMenu
{
     private static Connection con=null;
     private static ResultSet rs=null;
     private static PreparedStatement ps=null;
     private static Statement st=null;
     HashMap<Integer,Product> map=new HashMap<>();
     Scanner scanner=new Scanner(System.in);
  static
  {
	try{
	con=DbConnection.getInstance().getConnection();
	st=con.createStatement();
	}
	catch(SQLException e)
	{
	e.printStackTrace();
	}
  }
 public void userAction(int uid) throws SQLException
  {
    
       int cont=1;
       while(cont==1)
       {
                System.out.println();
                System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");
                System.out.println("X\tWelcome to Mcart   USER MENU            X");
                System.out.println("O\t1.    VIEW PRODECTS                     O");
                System.out.println("X\t2.    SEARCH PRODECT                    X"); 
                System.out.println("X\t3.    ORDER PRODECTS                    X"); 
                System.out.println("X\t4.    ORDER History                    X"); 
                System.out.println("O\t5.    BACK                              O");
                System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");

               
                int choice = scanner.nextInt();
                    
                switch (choice)
                {

                    case 1:
                            viewProducts();
                            break;
                    case 2:
                          searchProdect(); 
                           break;
                    case 3:try{
                            orderProdect(uid);
                    }
                    catch(SQLException e){}
                            break;  
                     case 4:try{
                            orderHistory(uid);
                     }
                     catch(SQLException e){}
                            break;
                     case 5: cont=0;
                             break;                                 
                
                    default:
                    System.out.println("ENTER A VALID NUMBER ");                
                }
  }
}

 public void viewProducts() throws SQLException  
 {
       
        rs=st.executeQuery("select pd.id,b.name,p.name,pd.count,pd.price from product_details pd join products p on p.id=pd.product_id join brand b on pd.brand_id=b.id");
        while(rs.next()){
            map.put(rs.getInt(1),new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDouble(5)));
        }
        System.out.println(String.format("\t\t%-5s %-15s  %-30s %-8s %-10s","id","brand","product","quantity","price"));
        System.out.println(String.format("\t\t%-5s %-15s  %-30s %-8s %-10s","--","-----","-------","--------","-----"));
        Set set=map.entrySet();
        Iterator itr=set.iterator();
        int c=1;
        while(c<=map.size()){
            int v=0;
            while(itr.hasNext() && v<3){
                Map.Entry m=(Map.Entry)itr.next();
                System.out.println(m.getValue());
                c++;
                v++;
            }
            System.out.println("\t1.go to next\t2.back");
            int num=scanner.nextInt();
            if(num==1) continue;
            else break;
        }
     System.out.println("Thanks");
 }
public void searchProdect() throws SQLException
 {
        System.out.println("Enter the Prodect name:");
        scanner.nextLine();
        String title = scanner.nextLine();

   rs= st.executeQuery("select pd.id,b.name,p.name,pd.count,pd.price from product_details pd join products p on p.id=pd.product_id join brand b on pd.brand_id=b.id where p.name like '%"+title+"%'");


        while (rs.next()) {
            System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getDouble(5) );
        } 
        if(!rs.next()) {
            System.out.println(" not found! ");
        }
 }

public void orderProdect(int uid) throws SQLException
{
    Double price=0d;
    viewProducts();
        outer:
        while(true)
        {         
         System.out.println("---------------------------------------------------------------------------------------------------");
	System.out.println("\n\t\t\t\t\tMcart\t\t\t\t\t"+"USER Order");
	System.out.println("---------------------------------------------------------------------------------------------------");
         System.out.println("\n\t\t\t\t\tpress 1 to ORDER ");
	System.out.println("---------------------------------------------------------------------------------------------------");
         System.out.println("Your Choice");
			int num=scanner.nextInt();
        String payment_type;
        int puthuid=0;
	if(num==1)
           {
              System.out.println("Enter the prodect id");
              int pid=scanner.nextInt(); 
               ps=con.prepareStatement("select price from product_details where product_id=?");
               ps.setInt(1,pid);
               rs=ps.executeQuery();
               if(rs.next())
               {
                   price=rs.getDouble(1);
               }
              System.out.println("Enter the number of prodect Do You Want?");
              int count=scanner.nextInt();              
              rs = st.executeQuery("SELECT count FROM product_details WHERE product_id = "+pid+";");
                       
              if(rs.next()) 
              {
                  puthuid=rs.getInt("count");
              }
              if(puthuid<=0)
              {
                 System.out.println("Prodect not AVALIABLE  !!!!!!!!!!!!!   ");
                 return;
              }
               price*=count;

	      System.out.println("Enter payment type");
	      System.out.println("1.COD\t 2.G-Pay \t 3.UPI");
	      int num1=scanner.nextInt();
	      if(num1==1)
	       payment_type="COD";
	      if(num1==2)
	       payment_type="G-Pay";
	      else
	       payment_type="UPI";              
        ps = con.prepareStatement("INSERT INTO order_details (user_id,product_id,count,amount,order_date,payment_type,payment_status,order_status,delivery_date) VALUES ( ?,?,?,?,?,?,?,?,? ) ");
        ps.setInt(1,uid);
        ps.setInt(2,pid);
        ps.setInt(3,count);
        ps.setDouble(4,price);
        ps.setDate(5,new java.sql.Date(new java.util.Date().getTime()));
        ps.setString(6,payment_type);
        ps.setBoolean(7,true);
        ps.setString(8,"ordered");
        ps.setDate(9,java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(5)));
        int count1=count;
        int p_id=pid;
       int m= ps.executeUpdate();
       System.out.println("ORDER DETAILS Insert successfully! ");
   //     rs.next();
       if(m>0)
        {    
         ps = con.prepareStatement("UPDATE product_details SET count  = count - ?  where product_id= ?;");            
         ps.setInt(1,count1);
         ps.setInt(2,p_id);
         ps.executeUpdate();
         System.out.println("ORDER DETAILS added successfully! " );
         break outer;
        }
       else{
       System.out.println("ORDER NOT AVALIABLE!!!!!!!");
       }
   }
 }
}

 public void orderHistory(int uid) throws SQLException
 {
     try
      {
           rs=st.executeQuery("SELECT amount,order_date, payment_type, order_status, delivery_date FROM order_details WHERE user_id = uid;");
        while(rs.next())
        
        {
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println(String.format("\n\t%-50s|%-20s|%-20s|%-20s|%-20s","amount","order_date","payment_type","order_status","delivery_date"));
        System.out.println("----------------------------------------------------------------------------------------------------");
      
        System.out.println(String.format("\n\t%-50s|%-20s|%-20s|%-20s|%-20s",rs.getDouble(1),rs.getDate(2),rs.getString(3),rs.getString(4),rs.getDate(5)));
        System.out.println("----------------------------------------------------------------------------------------------------");

        }      
    }
    catch(Exception e)
    {
        System.out.println("USER SHOWING PROBLEM ");

    }

 }
}

