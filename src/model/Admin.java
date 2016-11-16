package model;

/**
 * Created by Sheng on 9/27/16.
 * Admin to implement the required functionality
 */
class Admin extends Account {


    /**
     * Create new Admin
     * @param id the id
     * @param fName  first name
     * @param lName last name
     * @param email email
     *
     */
    public Admin(String id, String fName, String lName, String email) {
        super(id, fName, lName, email, AccountType.Admin);
    }

}
