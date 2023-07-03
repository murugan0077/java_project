package com.zoho.LibraryApp;
class barrow{
private int id;
private String name;
private String email;
private int count;
private long PhonNumber;
barrow(int id,String name,String email,long PhonNumber,int count){
this.id=id;
this.name=name;
this.email=email;
this.count=count;
this.PhonNumber=PhonNumber;
}
public int getId(){
return id;
}
public String getNmae(){
return name;
}
public String getEmail(){
return email;
}
public long PhonNumber(){
return PhonNumber;
}
public int getcount(){
return count;
}


public void setId(){
this.id=id;
}
public void setName(){
this.name=name;
}
public void setEmail(){
this.email=email;
}
public void setcount(){
this.count=count;
}
public void setPhonNumber(){
this.PhonNumber=PhonNumber;
}
}
