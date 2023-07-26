package com.zoho.Mcart;
import java.sql.Date;

public class UserModel
{
    private String status;
    private Date created_on;
    private String gender;
    private int uid;
    private String name;
    private long mobile;
    private String gmail;
    private String password;
    AddressModel adr=null;
    UserModel(int uid, String name, long mobile, String gmail,String password)
    {
        this.uid=uid;
        this.name=name;
        this.mobile=mobile;
        this.gmail=gmail;
        this.password=password;

    }

      UserModel( String name, long mobile, String gmail,String status)
    {

        this.name=name;
        this.mobile=mobile;
        this.gmail=gmail;
        this.status=status;

    }

    UserModel(int uid, String name, long mobile, String gmail,String gender,Date created_on,String password,int role_uid,int door_no, String street_name,String city,String district,int pincode)
    {
        adr=new AddressModel(role_uid,door_no,street_name,city,district,pincode); 
        this.uid=uid;
        this.name=name;
        this.mobile=mobile;
        this.gmail=gmail;
        this.gender=gender;
        this.created_on=created_on;
        this.password=password;
    }
    String getstatus()
    {
        return status;
    }
    Date getcreated_on()
    {
        return created_on;
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
    String getgmail()
    {
        return gmail;
    }
    String getpassword()
    {
        return password;
    }
}
