package model;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by Sheng on 9/20/16.
 * Authenticator to verify the username and password
 */
public class Authenticator {
    public static Map<String, String> ACCOUNTS = new HashMap<>();
//    static {
//        Account bangpham = new Account("bang", "bang", "pham", "bangpham@gmail.com", AccountType.Admin);
//        ACCOUNTS.put(bangpham.toString(), "pass");
//    }

    /**
     * a method to add an account to the backing data structure
     * @param account the account of the user
     * @param password the password of the user
     */
    public static void addAccount(Account account, String password) {
        ACCOUNTS.put(account.toString(), password);
        Account.accountList.put(account.toString(), account);
    }

    /**
     * a method to validate the password upon trying to login
     * @param id the ID of the user
     * @param password the password of the user to be compared
     * @return whether or not the password was validated
     */
    public static boolean validatePassword(String id, String password){
        String validAccountPassword = ACCOUNTS.get(id);
        System.out.println(validAccountPassword != null && validAccountPassword.equals(password));
        return validAccountPassword != null && validAccountPassword.equals(password);
    }

    /**
     * a method to validate the ID of the user which will tell whether or not
     * the ID the current user is trying to pass has already been used in the system
     * @param id the ID of the user to check
     * @return whether or not the ID was not taken
     */
    public static boolean validateID(String id){
        if (Account.accountList.get(id) != null) {
            String validAccountName = Account.accountList.get(id).toString();
            System.out.println(!validAccountName.equals(id));
            return !validAccountName.equals(id);
        }
        return true;
    }
}
