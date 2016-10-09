package controller;
import fxapp.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.AccountType;
import model.Account;

import model.Database;

/**
 * Created by Sheng on 9/19/16.
 * Controls the registration display functionality
 */
public class RegistrationController extends AnchorPane implements Initializable {
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
    @FXML
    private ComboBox<AccountType> accountTypeBox;

    private final ObservableList<AccountType> accountTypeList
            = FXCollections.observableArrayList(AccountType.User,  AccountType.Worker, AccountType.Manager);

    /**
     * Setup the main application link so we can call methods there
     * @param application the reference to the FX Application instance
     */
    public void setApp(Main application) {
        this.application = application;

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountTypeBox.setItems(accountTypeList);
    }


    /**
     * Validates the requirements to register for the user
     * @return true or false if the password and the username meets all the
     * requirements
     */
    private boolean validator() {

        String emailRegex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)" +
                "*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (id.getText() == null || id.getText().length() < 2) {
            errorMessage.setText("Username has to be at least 2 characters");
        } else if (accountTypeBox.getValue() == null) {
            errorMessage.setText("Please select an account type");
        } else if (!firstname.getText().matches("[a-zA-Z]+")
                || !lastname.getText().matches("[a-zA-Z]+")
                || firstname.getText().length() < 1
                || lastname.getText().length() < 1) {
            errorMessage.setText("Name fields are required and is restricted to letters only");
        } else if (!email.getText().matches(emailRegex)) {
            errorMessage.setText("Invalid email address");
        } else if (!email.getText().matches(emailRegex)) {
            errorMessage.setText("Invalid email address");
        } else if (!email.getText().equals(emailConfirm.getText())) {
            errorMessage.setText("Email confirmation does not match your email address");
        } else if (password.getText().length() < 6
                || !password.getText().matches("[a-zA-Z0-9]*")) {
            errorMessage.setText("Password must be more than 6 alphanumeric characters");
        } else if (!password.getText().equals(passwordConfirm.getText())) {
            errorMessage.setText("Password confirmation does not match your password");
        } else {
            return true;
        }
        System.out.println("bangdeptrai");
        return false;
    }

    /**
     * Becomes active when the registration is presses and it creates a new
     * account
     */
    @FXML
    private void registerPressed() {
        if (validator() && Database.validateUsername(id.getText())) {
            Database.addUser(id.getText(),
                    password.getText(),
                    firstname.getText(),
                    lastname.getText(),
                    email.getText(),
                    accountTypeBox.getValue());
            application.registrationLogging(id.getText());

        } else {
            errorMessage.setVisible(true);
        }
    }

    @FXML
    public void enterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            registerPressed();
        }
    }

    /**
     * Goes to the previous page when the button clicked
     */
    @FXML
    private void backPressed() {
        application.accountLogout();
    }

    /**
     * Closes when the registration display is closed
     */
    @FXML
    private void handleCloseMenu() {
        System.exit(0);
    }

}