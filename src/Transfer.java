
import static javax.swing.text.html.HTML.Attribute.ID;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nabdak
 */
public class Transfer {
    
    public void transfer(int senderAccount, int recieverAccount, float amount)
    {
        Accounts sender =new Accounts(senderAccount);
        Accounts reciever =new Accounts(recieverAccount);
        if(sender.ID!=reciever.ID)
        {   sender.balance=sender.getBalance(senderAccount);
            System.out.println(sender.balance);
            if(amount<=sender.balance)
            {   reciever.balance=reciever.getBalance(recieverAccount);
                reciever.balance+=amount;
                 sender.balance-=amount;
                 if(sender.currency.equals(reciever.currency)&&sender.type.equals(reciever.type))
                 {sender.setBalance(senderAccount, sender.balance);
                 reciever.setBalance(recieverAccount, reciever.balance);
                 System.out.println("Transfer done successfully");
                 }
                 else
                     System.out.println("The balance currency or account type are not compatible withe receiver\'s");
            }
            else
                System.out.println("ERROR! \nTransfer cannot be completed! \n"
                        + "sender balance is less than the amount to be trasnfered..!");
        }            
        else
            System.out.println("Either senderID or recieverID is not correct");
    }
    
}
