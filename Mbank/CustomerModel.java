package com.zoho.Mbank;
import java.sql.Date;

public class CustomerModel
{
    private int uid;
    private String aadhar;
    private int schem_id;
    private int catagry_id;
    private String account_no;
    private float balance;
    private int branch_id;
    private String password;
    private int age;
    private int id;
 

      CustomerModel( int uid, String aadhar, int schem_id,int catagry_id,String account_no ,float balance,int branch_id,String password,int age,int id)
    {

        this.uid=uid;
        this.aadhar=aadhar;
        this.schem_id=schem_id;
        this.catagry_id=catagry_id;
        this.account_no=account_no;
        this.balance=balance;
        this.branch_id=branch_id;
        this.password=password;
        this.age=age;
        this.id=id;
        

    }	

    int getuid()
    {
        return uid;
    }
    
     String getaadhar()
    {
        return aadhar;
    }

    int getschem_id()
    {
    return schem_id;
    }
    
    int getcatagry_id()
    {
    return catagry_id;
    }
    
     String getaccount_no()
    {
        return account_no;
    }

    float getbalance()
    {
        return balance;
    }

     int getbranch_id()
    {
    return branch_id;
    }
    
    String getpassword()
    {
        return password;
    }
    int getage()
    {
        return age;
    }
    int getid()
    {
        return id;
    }
}
