package com.zoho.LibraryApp;
class AuthorModel{
private int id;
private String authorname;

public void AuthorModel(int id,String authorname){

this.id=id;
this.authorname=authorname;
}
public int getId(){
return id;
}
public String getAuthorname(){
return authorname;
}
public void setId(){
this.id=id;
}
public void setAuthorname(){
this.authorname=authorname;
}

}
