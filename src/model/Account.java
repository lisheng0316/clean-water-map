package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sheng on 9/27/16.
 */
public class Account {

    private String id;
    private String fname;
    private String lname;
    private String email;
    private String rank;

    public static final Map<String, Account> accountList = new HashMap<String, Account>();

    public Account(String id, String fname, String lname, String email, String rank) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.rank = rank;
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
        this.id = id;
    }

    public Map<String, Account> getAccountList() {
        return accountList;
    }
    public String toString() {
        return id;
    }
    public String getFname() {
        return fname;
    }
    public String getLname() {
        return lname;
    }
    public String getEmail() {
        return email;
    }
    public String getRank() {
        return rank;
    }
}
