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

    private String id;
    private String fname;
    private String lname;
    private String email = "";
    private String address = "";
    private String phone = "";
    private final StringProperty _id = new SimpleStringProperty();
    private final StringProperty _fname = new SimpleStringProperty();
    private final StringProperty _lname = new SimpleStringProperty();
    private final StringProperty _email = new SimpleStringProperty();
    private final StringProperty _phone = new SimpleStringProperty();
    private final StringProperty _address = new SimpleStringProperty();
    private final ObjectProperty<AccountType> _type = new SimpleObjectProperty<>();




    public String getId() {return _id.get();}
    public void setId(String id) { _id.set(id); }
    public String getFname() {return _fname.get();}
    public void setFname(String fname) { _fname.set(fname); }
    public String getLname() {return _lname.get(); }
    public void setLname(String lname) { _lname.set(lname); }
    public String getEmail() {return _email.get();}
    public void setEmail(String email) { _email.set(email); }



    public AccountType getType() {return _type.get();}

    public void setType(AccountType type) { _type.set(type); }

    public ObjectProperty getAccountTypeProperty() { return _type; }

    public static final Map<String, Account> accountList = new HashMap<String, Account>();

    public Account(String id, String fname, String lname, String email, AccountType type) {

        this(id, fname, lname, email, type, "", "");


    }

    public Account(String id, String fname, String lname, String email, AccountType type, String phone, String address) {

        _id.set(id);
        _fname.set(fname);
        _lname.set(lname);
        _email.set(email);
        _type.set(type);
        _phone.set(phone);
        _address.set(address);

    }


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

    private Account(String id) {
        _id.set(id);
    }

    public Map<String, Account> getAccountList() {
        return accountList;
    }
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
