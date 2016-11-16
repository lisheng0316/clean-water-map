package model;

/**
 * Created by Sheng on 9/27/16.
 * Manager to implement the required functionality
 */
class Manager extends Worker {

    /**
     * Create new Manager
     * @param id the id
     * @param fname  first name
     * @param lname last name
     * @param email email
     * @param type account type
     *
     */
    public Manager(String id, String fname, String lname, String email,
                   AccountType type) {
        super(id, fname, lname, email, type);
    }
}
