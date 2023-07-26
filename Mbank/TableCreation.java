package com.zoho.Mbank;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
class TableCreation
{    static Connection con=null;
     public static Statement st=null;
     static
  	{
  		try{
  		con=Database.getInstance().getConnection();
  		st=con.createStatement();
  		}
  		catch(SQLException e)
  		{
  		e.printStackTrace();
  		}
  	}

public void tableOrder()
{
       
	
         branchTable();
         catagryTable();
         rolesTable();
         schemeTable();
         userTable();
         userAddress();
         accountTable();
         customerTable();
         trancationTable();
     }
     private void userAddress()
    {
        try
        {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS user_address(id SERIAL PRIMARY KEY,user_id INT NOT NULL,door_no INT NOT NULL,street_name VARCHAR(30) NOT NULL,city VARCHAR(30) NOT NULL,district VARCHAR(30) NOT NULL,pincode INT NOT NULL,CONSTRAINT fk_userid FOREIGN KEY(user_id) REFERENCES users(id) on delete cascade);");                                
        }    
        catch(SQLException e)
        {
            System.out.println("userAddress Table Not Created"+e);
        } 
    }
     private void branchTable()
     {
           try
             {
                   st.executeUpdate("create table if not exists branch(id serial primary key,name varchar not null)");                                
             }
             
         catch(SQLException e)
             {
                   System.out.println("brands Table Not Created"+e);
             } 
     }
     private void catagryTable()
     {
           try
             {
                   st.executeUpdate("create table if not exists catagry(id serial primary key,name varchar not null)");                                
             }
             
         catch(SQLException e)
             {
                   System.out.println("catagry Table Not Created"+e);
             } 
     }
     private void rolesTable()
     {
           try
             {
                   st.executeUpdate("create table if not exists roles(id serial primary key,name varchar not null)");                                
             }
             
         catch(SQLException e)
             {
                   System.out.println("roles Table Not Created"+e);
             } 
     }
     
      private void schemeTable()
     {
           try
             {
                   st.executeUpdate("create table if not exists scheme(id serial primary key,name varchar not null)");                                
             }
             
         catch(SQLException e)
             {
                   System.out.println("scheme Table Not Created"+e);
             } 
     }
     
      private void userTable()
     {
           try
             {
                   st.executeUpdate("create table if not exists users( id serial primary key,role_id int,name varchar(50) not null,mobile_no bigint not null,password varchar(20) not null,DOB date,gender varchar(20) not null ,status varchar(20) ,CONSTRAINT fk_role_id foreign key(role_id) references roles(id) on delete cascade )");                                
             }
             
         catch(SQLException e)
             {
                   System.out.println("users Table Not Created"+e);
             } 
     }
     
     private void customerTable()
     {
           try
             {
                   st.executeUpdate("create table if not exists customer(id serial primary key,user_id int,aadhar bigint ,schem_id int not null,catagry_id int not null,account_no bigint not null,balance DECIMAL NOT NULL,branch_id int not null,opening_date date,address varchar,password varchar(20),CONSTRAINT fk_userid foreign key(user_id) references users(id) on delete cascade,CONSTRAINT fk_schem_id foreign key(schem_id) references scheme(id) on delete cascade,CONSTRAINT fk_catagry_id foreign key(catagry_id) references catagry(id) on delete cascade,CONSTRAINT fk_branch_id foreign key(branch_id) references branch(id) on delete cascade)");                                
             }
             
         catch(SQLException e)
             {
                   System.out.println("customerTable Table Not Created"+e);
             } 
     }

     
     private void trancationTable()
     {
           try
             {
                   st.executeUpdate("create table if not exists trancation(id serial primary key,from_id varchar(20) not null,to_id varchar(20) not null,amount DECIMAL NOT NULL,date date ,uid int not null,CONSTRAINT fk_uid FOREIGN KEY(uid) REFERENCES users(id) on delete cascade)");                                
             }
             
         catch(SQLException e)
             {
                   System.out.println("trancation Table Not Created"+e);
             } 
     }
     
      private void accountTable()
    {
        try
        {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS accounts(id SERIAL PRIMARY KEY,user_id INT NOT NULL,password VARCHAR(50) NOT NULL,CONSTRAINT fk_userid FOREIGN KEY(user_id) REFERENCES users(id) on delete cascade);");                                
        }
        catch(SQLException e)
        {
            System.out.println("accountTable Table Not Created"+e);
        } 
     }
     
   
     
    
}
