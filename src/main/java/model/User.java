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
//    public static User of(String id) {
//        User user = userList.get(id);
//        if (user == null) {
//            user = new User(id);
//            userList.put(id, user);
//        }
//
//        for (String key : Authenticator.ACCOUNTS.keySet()) {
//            System.out.println(key + " = " + Authenticator.ACCOUNTS.get(key));
//        }
//        return user;
//    }
//    private User(String id) {
//        this.id = id;
//    }
//
//    public Map<String, User> getUserList() {
//        return userList;
//    }
//    public String toString() {
//        return id;
//    }
//    public String getFname() {
//        return fname;
//    }
//    public String getLname() {
//        return lname;
//    }
//    public String getEmail() {
//        return email;
//    }
//    public String getRank() {
//        return rank;
//    }

}
