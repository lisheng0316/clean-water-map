package model;

/**
 * Created by Sheng on 9/27/16.
 * Admin to implement the required functionality
 */
public class Admin extends Account{


    /**
     * Create new Admin
     * @param id the id
     * @param fname  first name
     * @param lname last name
     * @param email email
     * @param type account type
     *
     */
    public Admin(String id, String fname, String lname, String email, AccountType type) {
        super(id, fname, lname, email, AccountType.Admin);
    }

}
