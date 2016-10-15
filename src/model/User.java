package model;

/**
 * Created by Sheng on 9/13/16.
 * User to implement the required functionality
 */
public class User extends Account {


//    public static final Map<String, User> userList = new HashMap<String, User>();
//
//    public User (String id, String fname, String lname, String email, String rank) {
//        this.id = id;
//        this.fname = fname;
//        this.lname = lname;
//        this.email = email;
//        this.rank = rank;
//    }
    public User(String id, String fname, String lname, String email, AccountType type) {
        super(id, fname, lname, email, type);
    }







}
