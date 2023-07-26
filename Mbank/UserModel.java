package com.zoho.Mbank;
import java.sql.Date;

public class UserModel
{
    private String status;
    private String gender;

    private int uid;
    private String name;
    private long mobile;
    private String password;
    private int role_id;
    private String dob;
    AddressModel adr=null;
 
	
	  UserModel( String name,long mobile,String status)
	    {

	        this.name=name;
	        this.mobile=mobile;
	        this.status=status;

	    }		
	 
 
 

      UserModel( String name, long mobile, String dob,String status,int role_id ,String password)
    {

        this.name=name;
        this.mobile=mobile;
        this.dob=dob;
        this.status=status;
        this.role_id=role_id;
        this.password=password;

    }	



     UserModel(int uid,int role_id,String name,long mobile, String gender, String password,int door_no, String street_name,String city,String district,int pincode)
    {
         adr=new AddressModel(door_no,street_name,city,district,pincode);
	    this.uid=uid;
	    this.name=name;
	    this.role_id=role_id;
        this.mobile=mobile;
        this.gender=gender;
        this.password=password;
        
    }
    
    

    int getrole_id()
    {
    return role_id;
    }
    String getdob()
    {
    return dob;
    }
    String getstatus()
    {
        return status;
    }

    String getgender()
    {
        return gender;
    }
    int getuid()
    {
        return uid;
    }
    String getname()
    {
        return name;
    }
    long getmobile()
    {
        return mobile;
    }
    
    String getpassword()
    {
        return password;
    }
    public String toString()
    {
        return 	name+"  "+mobile+"  "+status;

    }
    
}
