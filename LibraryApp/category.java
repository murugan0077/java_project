package com.zoho.LibraryApp;
class category{
private int id;
private String categoryname;

category(int id,String categoryname){

this.id=id;
this.categoryname=categoryname;
}
public int getId(){
return id;
}
public String getcategoryname(){
return categoryname;
}

public void setId(){
this.id=id;
}
public void setcategoryname(){
this.categoryname=categoryname;
}

}
