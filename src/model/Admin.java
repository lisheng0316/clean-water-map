package model;

/**
 * Created by Sheng on 9/27/16.
 * Admin to implement the required functionality
 */
class Admin extends Account{


    /**
     * Create new Admin
     * @param id the id
     * @param fname  first name
     * @param lname last name
     * @param email email
     *
     */
    public Admin(String id, String fname, String lname, String email) {
        super(id, fname, lname, email, AccountType.Admin);
    }

}
