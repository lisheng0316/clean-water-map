package controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import fxapp.Main;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Authenticator;
import model.User;

/**
 * Created by Sheng on 9/19/16.
 */
public class RegistrationController extends AnchorPane implements Initializable {
    private User user;
    private Main application;
    @FXML
    private TextField id;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private TextField emailConfirm;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField passwordConfirm;
    @FXML
    private Label errorMessage;

    public void setApp(Main application){ this.application = application;
    }

    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private boolean validateID() {
        return Authenticator.validateID(id.getText());
    }

    private boolean validator() {
        String emailRegex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)" +
                "*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (id.getText() == null || id.getText().length() < 2) {
            errorMessage.setText("Username has to be at least 2 characters");
        } else if (!firstname.getText().matches("[a-zA-Z]+")
                || !lastname.getText().matches("[a-zA-Z]+")
                || firstname.getText().length() < 1
                || lastname.getText().length() < 1) {
            errorMessage.setText("Name fields are required and is restricted to letters only");
        } else if (!validateID()) {
            errorMessage.setText("Username is already taken");
        } else if (!email.getText().matches(emailRegex)) {
            errorMessage.setText("Invalid email address");
        } else if (!email.getText().equals(emailConfirm.getText())) {
            errorMessage.setText("Email confirmation does not match your email address");
        } else if(!password.getText().equals(passwordConfirm.getText())) {
            errorMessage.setText("Password confirmation does not match your password");
        } else if (password.getText().length() < 6
                || !password.getText().matches("[a-zA-Z0-9]*")) {
            errorMessage.setText("Password must be more than 6 alphanumeric characters");
        } else {
            return true;
        }
        return false;
    }

    @FXML
    private void registerPressed() {
        if (validator()) {
            user = new User(id.getText(), firstname.getText(), lastname.getText(), email.getText(), "rank");
            Authenticator.addUser(user,password.getText());
            application.userLogging(user.toString());
        } else {
            errorMessage.setVisible(true);
            System.out.println("visible");
        }
    }


    @FXML
    private void handleCloseMenu() {
        System.exit(0);
    }

}