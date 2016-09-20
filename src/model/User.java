package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Sheng on 9/13/16.
 */
public class User {
    private final StringProperty _name = new SimpleStringProperty();
    private final StringProperty _password = new SimpleStringProperty();

    public String getName() { return _name.get(); }
    public void setName(String name) { _name.set(name); }

    public String getPassword() {return _password.get(); }
    public void setPassword(String password) { _password.set(password); }


}
