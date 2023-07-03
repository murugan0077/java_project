package com.zoho.LibraryApp;
class Books{
private int id;
private String btitle;
private int year;
public void Books(int id,String btitle,int year){
this.year=year;
this.id=id;
this.btitle=btitle;
}
public int getId(){
return id;
}
public String getTitle(){
return btitle;
}
public int getYear(){
return year;
}


public void setId(){
this.id=id;
}
public void setTitle(){
this.btitle=btitle;
}
public void setYear(){
this.year=year;
}
}
