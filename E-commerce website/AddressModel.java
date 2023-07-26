package com.zoho.Mcart;

public class AddressModel
{
    private int role_id;
    private int door_no;
    private String street_name;
    private int pincode;
    private String city;
    private String district;
    AddressModel(int role_id,int door_no, String street_name,String city,String district,int pincode)
    {
        this.role_id=role_id;
        this.door_no=door_no;
        this.street_name=street_name;
        this.city=city;
        this.district=district;
        this.pincode=pincode;
    }
    int getrole_id()
    {
        return role_id;
    }
    int getdoor_no()
    {
        return door_no;
    }
    String getstreet_name()
    {
        return street_name;
    }
    long getpincode()
    {
        return pincode;
    }
    String getcity()
    {
        return city;
    }
    String getdistrict()
    {
        return district;
    }
}
