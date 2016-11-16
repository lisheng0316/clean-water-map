package model;

/**
 * Created by Sheng on 9/13/16.
 * User to implement the required functionality
 */
class User extends Account {

    /**
     * Create new user
     * @param id the id
     * @param fName  first name
     * @param lName last name
     * @param email email
     * @param type account type
     *
     */
    User(String id, String fName, String lName,
         String email, AccountType type) {
        super(id, fName, lName, email, type);
    }







}
