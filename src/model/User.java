package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Sheng on 9/13/16.
 */
public class User {

    private String id;


    private static final Map<String, User> USERS = new HashMap<String, User>();

    public static User of(String id) {
        User user = USERS.get(id);
        if (user == null) {
            user = new User(id);
            USERS.put(id, user);
        }
        return user;
    }
    private User(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
