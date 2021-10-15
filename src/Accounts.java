
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author infancy voice
 */
public class Accounts {

    int ID = 0;
    int cid=0;
    String clientSSN;
    float balance = 0.0f;
    String type;
    String currency;
    String cdate;

    public Accounts() {

    }

    public Accounts(String SSN) {
        this.cid = getAccountCID(SSN);
    }
    
    public Accounts(int id) 
    {
        this.ID=id;
    }
    
    public Accounts(int id, String SSN, String currency, String atype) {
        this.cid = getAccountCID(SSN);
        this.currency=currency;
        this.type=atype;
        this.ID = getAccountID(cid, currency, type);
    }

    public void addAccount(String SSN, float balance, String type, String currency) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "noon");
            Statement stmt = con.createStatement();
            int id = getAccountCID(SSN);
            int cid = 0;
            String cc = null;
            String tt = null;

            if (id != 0) {
                ResultSet rs = stmt.executeQuery("select cid, currency, atype from accounts where cid =" + id + ";");
                while (rs.next()) {
                    cid = rs.getInt(1);
                    cc = rs.getString(2);
                    tt = rs.getString(3);
                }
                if (cid == 0 && cc == null && tt == null) {
                    stmt.executeUpdate(" insert into accounts (cid, cdate, atype, currency, balance)"
                            + " values ('" + id + "',now(), '" + type + "', '" + currency + "', '" + balance + "');");
                    
                    System.out.println("Account is created successfully..!");
                } else {
                    if (cc.equals(currency) && tt.equals(type)) {
                        System.out.println("You already have an account..!\nCreation Failed.");
                    } else {
                        stmt.executeUpdate(" insert into accounts (cid, cdate, atype, currency, balance)"
                                + " values ('" + id + "',now(), '" + type + "', '" + currency + "', '" + balance + "');");                        
                        System.out.println("Account is created successfully..!");
                    }

                }
            } else {
                System.out.println("You Don\'t have an account..!\nCreation Failed.");
            }
            con.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            System.err.println(e);
            e.printStackTrace();

        }
    }

    public void printAllAccountsInfo() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "noon");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from accounts;");
            if ((rs == null)) {
                System.out.println("System is corrupted..!");
            }
            System.out.println("ID | ClientID | Creation Date | Type | Currency | Balance");

            while (rs.next()) {
                System.out.println(rs.getInt(1) + "   | " + rs.getInt(2) + "        | " + rs.getString(3)
                        + " | " + rs.getString(4) + "     | " + rs.getString(5)
                        + " | " + rs.getString(6));

            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    public float getBalance(String SSN, String currency, String type) {
        return getBalance(getAccountCID(SSN), currency, type);
    }

    public float getBalance(int id, String currency, String type) {
        float b = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "noon");
            Statement stmt = con.createStatement();
            if (id > 0) {
                ResultSet rs = stmt.executeQuery("select balance from accounts where cid ="
                        + id + " and currency like '" + currency + "' and atype like '" + type + "';");
                if (rs == null) {
                    System.out.println("client is not added..!");
                }
                while (rs.next()) {
                    b = rs.getFloat(1);
                }
            } else {
                System.out.println("The account you are looking for is not found..!");
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);

        }
        return b;
    }
    
    public float getBalance(int id){
        float b = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "noon");            
            Statement stmt = con.createStatement();            
            if (id > 0) {
                ResultSet rs = stmt.executeQuery("select balance, currency, atype from accounts where id ="+ id + ";");
                if (rs == null) {
                    System.out.println("Account is not added..!");
                }
                while (rs.next()) {
                    b = rs.getFloat(1);
                    balance = rs.getFloat(1);
                    currency=rs.getString(2);
                    type=rs.getString(3);
                }
            } else {
                System.out.println("The account you are looking for is not found..!");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }        
        return b;
    }

    public String getCurrency(String SSN) {
        return getCurrency(getAccountCID(SSN));
    }

    public String getCurrency(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "noon");
            Statement stmt = con.createStatement();
            if (id > 0) {
                ResultSet rs = stmt.executeQuery("select currency from accounts where cid = " + id);
                if (rs == null) {
                    System.out.println("client is not added..!");
                }
                String currency = null;
                while (rs.next()) {
                    currency = rs.getString(1);
                }
            } else {
                System.out.println("The SSN you provided is not available. Setting Balance faild..!");
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);

        }
        return currency;
    }

    public void setBalance(String SSN, float balance, String currency, String type) {
        setBalance(getAccountCID(SSN), balance, currency, type);
    }

    public void setBalance(int id, float balance, String currency, String type) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "noon");            
            Statement stmt = con.createStatement();            
            System.out.println("id is " + id);
            if (id > 0) {                
                if (currency.equals(getCurrency(id))) {
                    stmt.executeUpdate(" update accounts set balance = " + balance + " where cid ="
                            + id + " and currency like '" + currency + "' and atype like '" + type + "';");
                    System.out.println("Set successfully..!");
                } else {
                    System.out.println("The currency is not match!");
                    System.out.println("Set failed..!");
                }
            } else {
                System.out.println("The SSN you provided is not available. Setting Balance faild..!");
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);

        }
    }

    public void setBalance(int id, float balance){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "noon");
            Statement stmt = con.createStatement();
            System.out.println("id is " + id);
            if (id > 0) {
                ResultSet rs = stmt.executeQuery("select currency, atype from accounts where id = " + id);
                if (rs == null) {
                    System.out.println("client is not added..!");
                }
                while (rs.next()) {
                    currency = rs.getString(1);
                    type = rs.getString(2);
                }
                if (currency.equals(getCurrency(id))) {
                    stmt.executeUpdate(" update accounts set balance = " + balance + " where id ="+ id );
                    System.out.println("Set successfully..!");
                } else {
                    System.out.println("The currency is not match!");
                    
                    System.out.println("Set failed..!");
                }
            } else {
                System.out.println("The SSN you provided is not available. Setting Balance faild..!");
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);

        }
    }

    private int getAccountCID(String SSN) {
        int id = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "noon");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select id from client where SSN like '" + SSN + "'");
            if (/*rs.next()==false ||*/(rs == null)) {
                System.out.println("client is not added..!");
            }

            while (rs.next()) {
                id = rs.getInt(1);
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);

        }
        return id;
    }
    
    private int getAccountID(int cid, String currency, String atype) {
        int id = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "noon");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select id from accounts where cid =" + cid +"and currency like '"+currency+"' and atype like '"+atype+"'" );
            if (/*rs.next()==false ||*/(rs == null)) {
                System.out.println("client is not added..!");
            }

            while (rs.next()) {
                id = rs.getInt(1);
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);

        }
        return id;
    }

    public float deposit(String SSN, float amount, String currency, String type) {        
        return deposit(getAccountCID(SSN), amount, currency, type);
    }

    public float deposit(int id, float amount, String currency, String type) { 
        float newBalance = getBalance(id, currency, type);
        newBalance += amount;
        setBalance(id, newBalance, currency, type);
        return newBalance;
    }

    public float withdraw(String SSN, float amount, String currency, String type) {       
        return withdraw(getAccountCID(SSN), amount, currency, type);
    }

    public float withdraw(int id, float amount, String currency, String type) {  
        float newBalance = getBalance(id, currency, type);
        if (newBalance >= amount) {
            newBalance -= amount;
            setBalance(id, newBalance, currency, type);
            return newBalance;
        } else {
            return 0;
        }
    }

    public void searchAccount(String SSN) {
        searchAccount(getAccountCID(SSN));
    }

    public void searchAccount(int id) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "noon");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from accounts where cid =" + id);
            System.out.println("ID | ClientID | Creation Date | Type | Currency | Balance");
            int s = 0;
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "   | " + rs.getInt(2) + "        | " + rs.getString(3)
                        + " | " + rs.getString(4) + "     | " + rs.getString(5)
                        + " | " + rs.getString(6));
                s++;
            }
            if (s == 0) {
                System.out.println("client is not added..!");
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);

        }
    }

}
