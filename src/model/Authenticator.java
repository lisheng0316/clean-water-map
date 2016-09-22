package model;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by Sheng on 9/20/16.
 */
public class Authenticator {
    public static Map<String, String> USERS = new HashMap<>();
    static {
        User bangpham = new User("bang", "bang", "pham", "bangpham@gmail.com","rank");
        USERS.put(bangpham.toString(), "pass");
    }

    public static void addUser(User user, String password) {
        USERS.put(user.toString(), password);
        User.userList.put(user.toString(), user);
    }

    public static boolean validatePassword(String id, String password){
        String validUserPassword = USERS.get(id);
        System.out.println(validUserPassword != null && validUserPassword.equals(password));
        return validUserPassword != null && validUserPassword.equals(password);
    }

    public static boolean validateID(String id){
        if (User.userList.get(id) != null) {
            String validUserName = User.userList.get(id).toString();
            System.out.println(!validUserName.equals(id));
            return !validUserName.equals(id);
        }
        return true;
    }
}
