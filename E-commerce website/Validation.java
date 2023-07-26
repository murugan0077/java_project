package com.zoho.Mcart;
import java.util.regex.Pattern;
import java.util.Scanner;

class Validation {

    public long isMobile(String s) 
	{
		while(!Pattern.matches("[6-9][0-9]{9}",s))
		{
			System.out.print("\nEnter mobile number in Correct Format :");
			s=new Scanner(System.in).nextLine();
		}
		return Long.parseLong(s);
	}


    public boolean passwordChecker(String password) 
    {
        if(password.length() < 8){
        System.out.println("Must have 8 charectors ");
            return false;}
        
        boolean lowercase = false,uppercase = false,digit = false,special = false; 
        for(int i=0; i<password.length();i++)
        {
            char ch = password.charAt(i);
            
            if(Character.isLowerCase(ch))
                lowercase = true;
            
            else if(Character.isUpperCase(ch))
                uppercase = true;
            
            else if(ch > 47 && ch < 58)
                digit = true;
            
            else if(ch == '!' || ch == '@' || ch == '#' || ch == '$' || ch == '%' || ch == '^' || ch == '&' || ch == '*' || ch == '(' || ch == ')' || ch == '-' || ch == '+')
                special = true;
       
            }
            
            if(!lowercase )
            System.out.println("please contain one lowercase csk");
            if(!uppercase)
            System.out.println("please contain one uppercase  MSD");
            if(!digit)
            System.out.println("please contain one digit like 555 ");
            if(!special)
            System.out.println("please contain one special charector like !@#$% ");
            


        return (lowercase & uppercase & digit & special);
    }
}