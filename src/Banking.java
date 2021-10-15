
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author nabdak
 */
public class Banking {

    public static void main_print()
    {
        System.out.println("****This is the main menu****");
        System.out.println("1. Create a new client");
        System.out.println("2. Search an existing client");
        System.out.println("3. Change client\'s information");
        System.out.println("4. Print All Clients.");
        System.out.println("5. Go to the accounts list.");
        System.out.println("0. Close the system");
    }
    
    public static void account_menu_print()
    {
        System.out.println("****This is the accounts menu****");
        System.out.println("1. Create a new Account for an existing client");
        System.out.println("2. Search an available Account for an existing client");
        System.out.println("3. Check balance");
        System.out.println("4. Withdraw");
        System.out.println("5. Diposite");
        System.out.println("6. Transfer to another account by clients id");
        System.out.println("7. Print all accounts");
        System.out.println("0. Go back to the previous menu");
    }
    
    public static void account_menu()
    {
        try{
            Scanner input=new Scanner(System.in);
        
        char c;
        while(true)
        {
           account_menu_print();
           c=input.next().charAt(0);
           Accounts a=new Accounts();
           switch(c)
           {
               case '1': 
               {   System.out.println("Please enter the new client information");
                   System.out.print("New account\'s owner SSN is: ");                   
                   a.clientSSN=input.next();
                   System.out.print("New account balance is: ");                   
                   a.balance=input.nextFloat();
                   System.out.print("New account type is: ");                   
                   a.type=input.next();
                   System.out.print("New account\'s currency: ");                   
                   a.currency=input.next();                   
                   a.addAccount( a.clientSSN, a.balance, a.type, a.currency);                  
                   break;
               }
                                
               case '2':
               {   
                   System.out.println("Please enter the account\'s owner\'s SSN");
                   a.clientSSN=input.next();
                   a.searchAccount( a.clientSSN);
                   break;
               }
               
               case '3':
               {   
                   System.out.println("Please enter the new client information");
                   System.out.print("New account\'s owner SSN is: ");                   
                   a.clientSSN=input.next();
                   System.out.print("the currency of this amount is: ");                   
                   a.currency=input.next();
                   System.out.print("and the type of this amount is: ");                   
                   a.type=input.next();                   
                   System.out.println(a.getBalance(a.clientSSN, a.currency,a.type));
                   break;
               }
               
               case '4':
               {   
                   System.out.println("Please enter the new client information");
                   System.out.print("New account\'s owner SSN is: ");                   
                   a.clientSSN=input.next();
                   float amount;
                   System.out.print("How much do you want to withdraw? ");                   
                   amount=input.nextFloat();
                   System.out.print("and the currency of this amount is: ");                   
                   a.currency=input.next(); 
                   System.out.print("and the type of this amount is: ");                   
                   a.type=input.next(); 
                   float result=a.withdraw(a.clientSSN,amount, a.currency, a.type);
                   if(result>=0)
                     System.out.println("Successful withdrawal, your new balance is "+result);
                   else
                       System.out.println("No amount was withdrawn, your balance is less than the amount");
                   break;
               }
               
               case '5':
               {   
                   System.out.println("Please enter the new client information");
                   System.out.print("New account\'s owner SSN is: ");                   
                   a.clientSSN=input.next();
                   float amount;
                   System.out.print("How much do you want to deposit? ");                   
                   amount=input.nextFloat();
                   System.out.print("and the currency of this amount is: ");                   
                   a.currency=input.next(); 
                   System.out.print("and the type of this amount is: ");                   
                   a.type=input.next(); 
                   float result=a.deposit(a.clientSSN,amount,a.currency, a.type);
                   System.out.println("Successful deposit, your new balance is "+result);
                   break;
               }
                              
               case '6':
               {    
                   Transfer t=new Transfer();
                   System.out.println("Please enter the id of the sender account");
                   int sender=input.nextInt();
                   System.out.println("Please enter the id of the receiver account");
                   int receiver=input.nextInt();
                   System.out.println("Please enter the amount to be transferred");
                   float amount=input.nextFloat();                   
                   t.transfer(sender, receiver, amount);                   
                   break;
               }
               
               case '7':
               {   
                   a.printAllAccountsInfo();
                   break;
               }
               
               case '0':
               {   
                   return;
               }
               
               default:
                   System.out.println("The data you entered is not correct, please try again..!");
           }
        }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
    public static void main(String[] args) {
        System.out.println("Welcome to our NAN Bank");
        Scanner input=new Scanner(System.in);
        char c;
        while(true)
        {
           main_print();
           c=input.next().charAt(0);
           Clients client=new Clients();
           switch(c)
           {
               case '1': 
               {   System.out.println("Please enter the new client information");
                   System.out.print("New Client SSN is: ");                   
                   client.SSN=input.next();
                   System.out.print("New Client name is: ");                   
                   client.name=input.next();
                   System.out.print("New Client phone is: ");                   
                   client.phone=input.next();
                   System.out.print("New Client address is: ");                   
                   client.address=input.next();
                   System.out.print("New Client email is: ");                   
                   client.email=input.next();
                   System.out.print("New Client gender is: ");                   
                   client.gender=input.next().charAt(0);
                   System.out.print("New Client birth date is: (in the format yyyy-MM-dd)");
                   client.bdate=input.next();
                   client.addClient( client.SSN,  client.name,  client.address,  client.phone,  client.email, client.gender, client.bdate);
                   System.out.println("The new client is created successfully..!");
                   break;
               }
                                
               case '2':
               {   
                   System.out.println("Please enter the client's SSN to search for");
                   String ssn=input.next();
                   Clients client2=new Clients();
                   client.SSN=ssn;
                   client2=client.search(client.SSN);
                   if(client2!=null)
                   {
                       System.out.println("The client SSN is:     "+client2.SSN); 
                       System.out.println("The client name is:    "+client2.name);                   
                       System.out.println("The client phone is:   "+client2.phone);                   
                       System.out.println("The client address is: "+client2.address);                   
                       System.out.println("The client email is:   "+client2.email);
                       System.out.println("The client birth date is: "+client2.bdate);                   
                       System.out.println("The client gender is:   "+client2.gender);
                   }
                   else
                       System.out.println("The client you search for is not found..!");
                   break;
               }
               
               case '3':
               {   
                   System.out.println("Please enter the client's SSN to search for");
                   String ssn=input.next();
                   client.SSN=ssn;
                   client=client.search(client.SSN);
                   System.out.println("You can update address, phone and email only!");
                   if(client!=null){
                       System.out.print("The previous phone number was: "+client.phone+". New Client phone is: ");                   
                       client.phone=input.next();
                       System.out.print("The previous address was: "+client.address+". New Client address is: ");                   
                       client.address=input.next();
                       System.out.print("The previous email was: "+client.email+". New Client email is: ");                   
                       client.email=input.next();
                       client.updateClient(client.address, client.phone, client.email, client.SSN);
                       System.out.println("Data changed successfully..!");
                   }
                   else
                       System.out.println("The client you attempt to change his data is not found..!");
                   break;
               }
               
               case '4':
               {   
                   client.printAllClientInfo();
                   break;
               }
               
               case '5':
               {   
                   account_menu();
                   break;
               }
               
               case '0':
               {   
                   return;
               }
                              
               default:
                   System.out.println("The data you entered is not correct, please try again..!");
           }
        }
        
    }
    
}
