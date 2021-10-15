
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nabdak
 */
public class Clients {

    String SSN;
    String name;
    String address;
    String phone;
    String email;
    char gender;
    String bdate;

    public Clients() {
    }

    public Clients(String SSN, String name, String address, String phone, String email, char gender, String bdate) {
        this.SSN = SSN;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.bdate = bdate;
    }

    public Clients(String SSN, String name) {
        this.SSN = SSN;
        this.name = name;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getSSN() {
        return SSN;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public char getGender() {
        return gender;
    }

    public String getBdate() {
        return bdate;
    }

    public void addClient(String SSN, String name, String address, String phone, String email, char gender, String bdate) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "noon");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(" insert into client (ssn, name, address, phone, email, gender, bdate)"
                    + " values ('" + SSN + "','" + name + "', '" + address + "', '" + phone + "', '" + email + "', '" + gender + "', '" + bdate + "');");
            con.close();
        } 
        catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            System.err.println(e);
            e.printStackTrace();            
        }
    }
    public void updateClient(String address, String phone, String email, String ssn)
    {
       try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "noon");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(" update client set address = '" + address + "', phone='" + phone + "' , email='" + email + "' where ssn ='"+SSN+"';");
            con.close();
        } 
        catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            System.err.println(e);
            e.printStackTrace();
        } 
    }
    public Clients search(String SSN) throws NullPointerException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "noon");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from client where SSN like '"+SSN+"'");
            if((rs== null))
            {
                System.out.println("System is corrupted..!");
                return null;
            }
            Clients c=new Clients();
            while (rs.next()) {
            c.SSN=rs.getString(2);
            c.gender=rs.getString(3).charAt(0);
            c.bdate=rs.getString(4);
            c.name=rs.getString(5);
            c.address=rs.getString(6);
            c.phone=rs.getString(7);
            c.email=rs.getString(8);             
            }
            con.close();
            return c;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }      
    }
    public void printAllClientInfo()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "noon");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from client;");
            if((rs== null))
            {
                System.out.println("System is corrupted..!");                
            }
            System.out.println("ID  | name       | SSN        | gender | Birth date | Address     | Phone     | Email");
            while (rs.next()) {
                System.out.println(rs.getString(1) +"   | "+rs.getString(5)+ "        | "+rs.getString(2)
                        +" | "+rs.getString(3).charAt(0)+"      | "+rs.getString(4)
                        +" | "+rs.getString(6)+"       | "+rs.getString(7)+" | "+rs.getString(8));           
            }
            con.close();          
        } catch (Exception e) {
            System.out.println(e);
           
        }
    }
      
}
