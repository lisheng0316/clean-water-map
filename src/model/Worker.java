package model;
/**
 * Created by Sheng on 9/27/16.
 * Worker to implement the required functionality
 */
class Worker extends User {

    /**
     * Create new worker
     * @param id the id
     * @param fname  first name
     * @param lname last name
     * @param email email
     * @param type account type
     *
     */
    Worker(String id, String fname, String lname, String email,
           AccountType type) {
        super(id, fname, lname, email, type);
    }
}
