package com.zoho.Mbank;

public class AddressModel
{
    private int door_no;
    private String street_name;
    private int pincode;
    private String city;
    private String district;
    AddressModel(int door_no, String street_name,String city,String district,int pincode)
    {
        this.door_no=door_no;
        this.street_name=street_name;
        this.city=city;
        this.district=district;
        this.pincode=pincode;
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
