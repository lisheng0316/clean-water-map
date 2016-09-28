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
    private String email;
    private String type;
    private final StringProperty _id = new SimpleStringProperty();
    private final StringProperty _fname = new SimpleStringProperty();
    private final StringProperty _lname = new SimpleStringProperty();
    private final StringProperty _email = new SimpleStringProperty();
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
//        this.id = id;
//        this.fname = fname;
//        this.lname = lname;
//        this.email = email;
//        this.type = type;
        _id.set(id);
        _fname.set(fname);
        _lname.set(lname);
        _email.set(email);
        _type.set(type);


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
//    public String getFname() {
//        return fname;
//    }
//    public String getLname() {
//        return lname;
//    }
//    public String getEmail() {
//        return email;
//    }
//    public String getType() {
//        return type;
//    }


//    public AccountType getAccountType() { return }

}
