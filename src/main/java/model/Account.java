package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sheng on 9/27/16.
 * Represents a single account in the system
 *
 * Information Holder
 *
 */
public class Account {

    private String id; //the id of the user
    private String fname; //the first name of the user
    private String lname; //the last name of the user
    private String email = ""; //the e-mail address of the user
    private String address = ""; //the address of the user
    private String phone = ""; //the phone number of the user
    private final StringProperty _id = new SimpleStringProperty();
    private final StringProperty _fname = new SimpleStringProperty();
    private final StringProperty _lname = new SimpleStringProperty();
    private final StringProperty _email = new SimpleStringProperty();
    private final StringProperty _phone = new SimpleStringProperty();
    private final StringProperty _address = new SimpleStringProperty();
    private final ObjectProperty<AccountType> _type = new SimpleObjectProperty<>();


    /**
     * a method to get the ID of the user
     * @return the ID of the user
     */
    public String getId() {return _id.get();}

    /**
     * a method to set the user ID
     * @param id the String to be set as the ID
     */
    public void setId(String id) { _id.set(id); }

    /**
     * a method to get the first name of the user
     * @return the first name of the user
     */
    public String getFname() {return _fname.get();}

    /**
     * a method to set the first name of the user
     * @param fname the first name of the user
     */
    public void setFname(String fname) { _fname.set(fname); }

    /**
     * a method to get the last name of the User
     * @return the last name of the user
     */
    public String getLname() {return _lname.get(); }

    /**
     * a method to set the last name of the User
     * @param lname the last name of the user
     */
    public void setLname(String lname) { _lname.set(lname); }

    /**
     * a method to get the e-mail address of the user
     * @return the user's e-mail address
     */
    public String getEmail() {return _email.get();}

    /**
     * a method to set the e-mail address of the User
     * @param email the e-mail to be set
     */
    public void setEmail(String email) { _email.set(email); }


    /**
     * a method to get the type of the User
     * types include: User, Worker, Manager, Admin
     * @return the type of User
     */
    public AccountType getType() {return _type.get();}

    /**
     * a method to set the user account type
     * @param type the Enum of the account type
     */
    public void setType(AccountType type) { _type.set(type); }

    /**
     * a method to we haven't used yet
     * @return the type from the combo box in M3
     */
    public ObjectProperty getAccountTypeProperty() { return _type; }


    /**
     * A map to hold all of the accounts
     */
    public static final Map<String, Account> accountList = new HashMap<String, Account>();
    /**
     * A  the default constructor of the user
     */
    public Account(String id, String fname, String lname, String email, AccountType type) {

     this(id, fname, lname, email, type, "", "");

    }
    /**
     * the constructor of user if the user included their phone number
     * and address
     */
    public Account(String id, String fname, String lname, String email, AccountType type, String phone, String address) {

        _id.set(id);
        _fname.set(fname);
        _lname.set(lname);
        _email.set(email);
        _type.set(type);
        _phone.set(phone);
        _address.set(address);



    }

    /**
     * a method to create the account of the user
     * @param id the ID of the user
     * @return the newly created account
     */
    public static Account of(String id) {
        Account account = accountList.get(id);
        if (account == null) {
            account = new Account(id);
            accountList.put(id, account);
        }

        for (String key : Authenticator.ACCOUNTS.keySet()) {
            System.out.println(key + " = " + Authenticator.ACCOUNTS.get(key));
        }
        return account;
    }

    /**
     * a method to set the ID of the account
     * @param id the new ID of the user
     */
    private Account(String id) {
        _id.set(id);
    }

    /**
     * a method to get the map that holds the users
     * @return the map of the users
     */
    public Map<String, Account> getAccountList() {
        return accountList;
    }

    /**
     * a method to easily toString the account
     * @return the String created from the account
     */
    public String toString() {
        return _id.get();
    }
    /**
     * @return the email
     */
//    public String getEmail() {
//        return email;
//    }

    /**
     * @param email the email to set
     */
//    public void setEmail(String email) {
//        this.email = email;
//    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }



    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the account type
     */
//    public AccountType getAccountType() { return getType(); }

}
