package com.zoho.Mcart;
import java.sql.SQLException;
import java.util.Scanner;
public class SignUP

{ 
    private static Scanner scanner = new Scanner(System.in);
    public void esignin() throws Exception
    {
       int cont=1;
       while(cont==1)
       {
                System.out.println();
                System.out.println("*************************************************")  ;
                System.out.println("*\tWelcome to Shopyfy                         x");
                System.out.println("*\t CHOOSE SignUP                              o");
                System.out.println("*\t1. USER                                    x");
                System.out.println("*\t2. VENTOR                                  o");
                System.out.println("*\t3. ADMIN                                   x");
                System.out.println("*\t4. BACK                                    O");
                System.out.println("************************************************")    ;

               
                int choice = scanner.nextInt();
                    
                switch (choice)
                {

                    case 1:
                        new SignupUser().usersignup();
                        break;
                    case 2:
                        //new SignupUser().ventorsignup();
                        break;
                    case 3:
                       // new SignupUser().adminsignup();
                        break;
                    case 4:
                        // AppMain().main();
                        return;
                
                }
                System.out.println();
        System.out.println("press 1 to continue"); 
	    cont=scanner.nextInt();
       }
    }
}
