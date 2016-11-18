package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//import java.util.HashMap;
//import java.util.Map;

/**
 * Created by Sheng on 9/27/16.
 * Represents a single account in the system
 *
 * Information Holder
 *
 */
public class Account {

    private final StringProperty strId = new SimpleStringProperty();
    private final StringProperty strFname = new SimpleStringProperty();
    private final StringProperty strLname = new SimpleStringProperty();
    private final StringProperty strEmail = new SimpleStringProperty();
    private final StringProperty strPhone = new SimpleStringProperty();
    private final StringProperty strAddress = new SimpleStringProperty();
    private final ObjectProperty<AccountType> strType =
            new SimpleObjectProperty<>();

    // --Commented out by Inspection (11/16/16, 12:07 AM):private static final
    // Map<String, Account> accountList = new HashMap<>();


    /**
     * a method to get the ID of the user
     * @return the ID of the user
     */
    public String getId() { 
        return strId.get(); }

    // --Commented out by Inspection START (11/16/16, 12:04 AM):
    //    /**
    //     * a method to set the user ID
    //     * @param id the String to be set as the ID
    //     */
    //    public void setId(String id) {
    //    strId.set(id); }
    // --Commented out by Inspection STOP (11/16/16, 12:04 AM)
    /**
     * a method to get the first name of the user
     * @return the first name of the user
     */
    public String getFName() { 
        return strFname.get(); }

    // --Commented out by Inspection START (11/16/16, 12:04 AM):
    //    /**
    //     * a method to set the first name of the user
    //     * @param fName the first name of the user
    //     */
    //    public void setFName(String fName) {
    //    strFname.set(fName); }
    // --Commented out by Inspection STOP (11/16/16, 12:04 AM)

    /**
     * a method to get the last name of the User
     * @return the last name of the user
     */
    public String getLName() { 
        return strLname.get(); }

    // --Commented out by Inspection START (11/16/16, 12:05 AM):
    //    /**
    //     * a method to set the last name of the User
    //     * @param lName the last name of the user
    //     */
    //    public void setLName(String lName) {
    //    strLname.set(lName); }
    // --Commented out by Inspection STOP (11/16/16, 12:05 AM)

    /**
     * a method to get the e-mail address of the user
     * @return the user's e-mail address
     */
    public String getEmail() { 
        return strEmail.get(); }

    /**
     * a method to set the e-mail address of the User
     * @param email the e-mail to be set
     */
    public void setEmail(String email) { 
        strEmail.set(email); }


    /**
     * a method to get the type of the User
     * types include: User, Worker, Manager, Admin
     * @return the type of User
     */
    public AccountType getType() { 
        return strType.get(); }

    // --Commented out by Inspection START (11/16/16, 12:05 AM):
    //    /**
    //     * a method to set the user account type
    //     * @param type the Enum of the account type
    //     */
    //    public void setType(AccountType type) {
    //    strType.set(type); }
    // --Commented out by Inspection STOP (11/16/16, 12:05 AM)

    /**
     * @return the address
     */
    public String getAddress() {
        return strAddress.get();
    }

    /**
     * @return the phone number
     */
    public String getPhone() {
        return strPhone.get();
    }
    // --Commented out by Inspection START (11/16/16, 12:05 AM):
    //    /**
    //     * a method to we haven't used yet
    //     * @return the type from the combo box in M3
    //     */
    //    public ObjectProperty getAccountTypeProperty() {
    //      return strType; }
    // --Commented out by Inspection STOP (11/16/16, 12:05 AM)

    /**
     * A  the default constructor of the user
     * @param id the id
     * @param fName  first name
     * @param lName last name
     * @param email email
     * @param type account type
     *
     */
    Account(String id, String fName, String lName, String email, 
            AccountType type) {

        this(id, fName, lName, email, type, "", "");

    }
    /**
     * the constructor of user if the user included their phone number
     * and address
     *
     *
     * @param id the id
     * @param fName  first name
     * @param lName last name
     * @param email email
     * @param type account type
     * @param phone phone
     * @param address address
     *
     */
    public Account(String id, String fName, String lName, String email,
                   AccountType type, String phone, String address) {

        strId.set(id);
        strFname.set(fName);
        strLname.set(lName);
        strEmail.set(email);
        strType.set(type);
        strPhone.set(phone);
        strAddress.set(address);
    }

    // --Commented out by Inspection START (11/16/16, 12:05 AM):
    //    /**
    //     * a method to create the account of the user
    //     * @param id the ID of the user
    //     * @return the newly created account
    //     */
    //    public static Account of(String id) {
    //        Account account = accountList.get(id);
    //        if (account == null) {
    //            account = new Account(id);
    //            accountList.put(id, account);
    //        }
    //        return account;
    //    }
    // --Commented out by Inspection STOP (11/16/16, 12:05 AM)

    // --Commented out by Inspection START (11/16/16, 12:07 AM):
    //    /**
    //     * a method to set the ID of the account
    //     * @param id the new ID of the user
    //     */
    //    private Account(String id) {
    //        strId.set(id);
    //    }
    // --Commented out by Inspection STOP (11/16/16, 12:07 AM)

    // --Commented out by Inspection START (11/16/16, 12:05 AM):
    //    /**
    //     * a method to get the map that holds the users
    //     * @return the map of the users
    //     */
    //    public Map<String, Account> getaccountList() {
    //        return accountList;
    //    }
    // --Commented out by Inspection STOP (11/16/16, 12:05 AM)

    /**
     * a method to easily toString the account
     * @return the String created from the account
     */
    public String toString() {
        return strId.get();
    }

    /*
    /**
     * a method to set phone number of the account
     * @param phone the phone to set

    public void setPhone(String phone) {
    }


    /**
     * a method to set address of the account
     * @param address the address to set

    public void setAddress(String address) {
    }
    */

}
