package com.zoho.Mbank;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class UserController implements CustomerMenuInterface
{

    static Connection con=null;
    static PreparedStatement ps=null;
    static ResultSet rs=null;
    static Statement st=null;
    Scanner  scanner = new Scanner(System.in);

     static
  	{
  		try
        {
  		con=Database.getInstance().getConnection();
  		st=con.createStatement();
  		}
  		catch(SQLException e)
  		{
  		e.printStackTrace();
  		}
  	}

 
         public void createAccount(int uid)
         {
           String account_no="";
                    // ArrayList<UserModel> al=getter();
                                  
                    // if(al.size()!=0)


	    System.out.println("Enter the aadhar 12 Digit number:");
	    String aadhar = scanner.nextLine();

         System.out.println("Enter the age:");
	    int age = scanner.nextInt();



       int scheme_id=0;
         try
        {
            rs=st.executeQuery("select * from scheme");
            System.out.println("Enter the scheme id");
            while(rs.next())
            {
                System.out.println(rs.getInt(1)+" for "+rs.getString(2));

            }
               scheme_id=scanner.nextInt();
        }
         catch(Exception e)
        {
            e.printStackTrace();

        }  
int branch_id=0;
         try
        {
            rs=st.executeQuery("select * from branch");
            System.out.println("Enter the branch id");
            while(rs.next())
            {
                System.out.println(rs.getInt(1)+" for "+rs.getString(2));

            }
             branch_id=scanner.nextInt();
             account_no=new Validation().getAccNo("Bid",branch_id);
             account_no+=new Validation().getAccNo("Uid",uid);

        }
         catch(Exception e)
                    {
                        e.printStackTrace();

                    }
        int id=0;
	    System.out.println("Enter the Mobile Number:");
	    long mobile = scanner.nextLong();
	    try{
            ps = con.prepareStatement("select id from users where mobile_no= ? ");
            ps.setLong(1,mobile);
            rs=ps.executeQuery();
            rs.next();
            id=rs.getInt(1);
            if(id==uid)
            {
                System.out.println("mobile ok");
            }
        }
        catch(Exception e)
                    {
                         System.out.println("mobile number not register"+e);

                    }  
		String password=""; 
        System.out.println("Enter the Password:");
        String password1=scanner.nextLine();
        try
        {
            
            ps = con.prepareStatement("select password from users where id= ? ");
            ps.setInt(1,uid);
            rs=ps.executeQuery();
            if(rs.next()){
            password=rs.getString(1);
            }
            if(password.equals(password1))
            {
                System.out.println("password ok");
            }
            else
            {
              System.out.println("no sutable password ");
              return;

            }
        }
		catch(Exception e)
        {
            System.out.println("PASSWORD PROBLEM"+e);
        }
        try
        {
          
		  //int c_id=0;
		  ps = con.prepareStatement("INSERT INTO customer ( user_id, aadhar,schem_id,catagry_id,account_no,balance,branch_id,opening_date,status,password,age) VALUES ( ?,?,?,?,?,?,?,?,?,?,?) returning id");
		  ps.setInt(1,uid);
          ps.setString(2,aadhar);
		  ps.setInt(3,scheme_id);
          ps.setString(5,account_no);
          ps.setInt(7,branch_id);
          ps.setDate(8,new java.sql.Date(new java.util.Date().getTime()));
          ps.setString(9,"ACC_CREATE");
          ps.setString(10,password1);
          ps.setInt(11,age);
        
        if(age<=10)
		  ps.setInt(4,1);
           else if(age>10 && age<18)
		        ps.setInt(4,2);
                    else if(age>=18 && age<60)
		                 ps.setInt(4,3);
                            else if(age>=60)
		                         ps.setInt(4,4);

        if(scheme_id==1)
		  ps.setFloat(6,500);
            else if(scheme_id==2)
		        ps.setFloat(6,0);
                else if(scheme_id==3)
		            ps.setFloat(6,1000);
                    else if(scheme_id==2)
		                ps.setFloat(6,0);

		  rs=ps.executeQuery();
		  if(rs.next())
		  {
		
		  System.out.println("customer_details added successfully! " );
		  
		  }
        }
        catch(Exception e)
        {
           System.out.println("customer_details NOT SHOWING" +e);
        }  

}                             
        
        
            public void transferAmount(int uid)
            {
                   try
                    {
                          
                        System.out.println("ENTER A BENIFISHER ACCOUND NUMBER correctly");
                        String ben_account_no=scanner.nextLine();
                        
                        ps =con.prepareStatement("select account_no from customer where  account_no= ? ");
                        
                        ps.setString(1,ben_account_no);
                        rs=ps.executeQuery();

                        System.out.println("HOW MUCH MONEY YOU WANT TO SENT");
                        int send_amound=scanner.nextInt();
                        scanner.nextLine();

                        String acc_no;
                        ps =con.prepareStatement("select account_no,balance from customer where user_id=(?)");  
                        ps.setInt(1,uid);
                        rs=ps.executeQuery();

                        if(rs.next())
                        {
                           acc_no=rs.getString(1);

                            if(rs.getInt(2)>=send_amound)
                            {
                                ps =con.prepareStatement("UPDATE customer SET balance  =  balance - ? WHERE user_id=? ");
                                ps.setInt(1,send_amound);
                                ps.setInt(2,uid);
                                ps.executeUpdate();

                                ps =con.prepareStatement("UPDATE customer SET balance  =  balance + ? WHERE account_no=? ");
                                ps.setInt(1,send_amound);
                                ps.setString(2,ben_account_no);
                                ps.executeUpdate();

                                ps =con.prepareStatement("insert into trancation (from_id,to_id,amount,date,uid) values (?,?,?,?,?)");
                                ps.setString(1,acc_no);
                                ps.setString(2,ben_account_no);
                                ps.setInt(3,send_amound);
                                ps.setDate(4,new java.sql.Date(new java.util.Date().getTime()));
                                ps.setInt(5,uid);
                                ps.executeUpdate();

                                System.out.println("TRANSACTION added successfully!");
                            }
                            else
                            {
                                System.out.println("insufficient balance");
                            }
                                
                        }

                    }
                
                catch(Exception e)
                {
                     System.out.println("transatation not showing"+e);
                    
                }
            }
            public void checkBalance(int uid)
            {
                try{
                    System.out.println("ENTER A ACCOUNT NUMBER correctly");
                    String accno=scanner.nextLine();
                        
                        ps =con.prepareStatement("select balance,account_no from customer where user_id = ? and account_no= ? ");
                        ps.setInt(1,uid);
                        ps.setString(2,accno);
                        rs=ps.executeQuery();
                        rs.next();
                        System.out.println("THE CURRENT BALANCE IS : "+rs.getInt(1));
                }
                catch(Exception e)
                {
                    System.out.println("ENTER YOUR ACCOUNT NUMBER PLZ! "+e );
                } 

            }
 

        public void showAccountHistory(int uid)
        {
            try
            {
            
            rs=st.executeQuery("SELECT from_id, to_id,amount,date FROM trancation WHERE uid = "+uid+";");
                while(rs.next())
                {
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println(String.format("\n\t%-50s|%-20s|%-20s|%-20s","FROM_ID","TO_ID","AMOUNT","DATE"));
                System.out.println("-----------------------------------------------------------------------------------------------------");
            
                System.out.println(String.format("\n\t%-50s|%-20s|%-20s|%-20s",rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
                System.out.println("-------------------------------------------------------------------------------------------------------");
                }
            }
            catch(Exception e)
            {
                System.out.println("ACC HISTORY SHOWING PROBLEM "+e);
            }
        }

        public void showProfile(int uid)
        {
             try
	    {
		
		  rs=st.executeQuery("SELECT name, mobile_no,status FROM users WHERE id = "+uid+";");
	        while(rs.next())
	        {
	        System.out.println("---------------------------------------------------------------------------------------------");
	        System.out.println(String.format("\n\t%-50s|%-20s|%-20s","NAME","MOBILE","status"));
	        System.out.println("---------------------------------------------------------------------------------------------");
	      
	        System.out.println(String.format("\n\t%-50s|%-20s|%-20s",rs.getString(1),rs.getLong(2),rs.getString(3)));
	        System.out.println("---------------------------------------------------------------------------------------------");
	        }
	    }
	    catch(Exception e)
	    {
	        System.out.println("STAFF SHOWING PROBLEM ");
	    }
        }


            




 	public ArrayList<CustomerModel> getter() 
          {
            ArrayList<CustomerModel> al=new ArrayList<>();
            try
            {
                ps=con.prepareStatement("SELECT uid,aadhar,schem_id,catagry_id,account_no,balance,branch_id,password,age,id FROM customer WHERE status = 'approve' ;");
                rs=ps.executeQuery();
                while(rs.next())
                {
                    al.add(new CustomerModel(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getFloat(6),rs.getInt(7),rs.getString(8),rs.getInt(9),rs.getInt(10)));
                }
            }
            catch(SQLException e)
            {
               System.out.println("getter problem"+e);
            }
              return al;
          }



}

