package controller;

import fxapp.Main;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.net.URL;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.Account;
import model.Database;

/**
 * Created by Sheng on 9/29/16.
 * A controller for the profile page
 */
public class ProfileController extends AnchorPane implements Initializable{

    @FXML
    private TextField accountType;
    @FXML
    private TextField user;
    @FXML
    private TextField password;
    @FXML
    private TextField passwordConfirm;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextArea address;
    @FXML
    private Label message;

    private Main application;

    /**
     * sets the Registration page
     * @param application the main application of welcome page
     */
    public void setApp(Main application){
        this.application = application;
        Account loggedAccount = application.getLoggedAccount();
        accountType.setText(loggedAccount.getType().toString());
        user.setText(loggedAccount.getId());
        fname.setText(loggedAccount.getFName());
        lname.setText(loggedAccount.getLName());
        email.setText(loggedAccount.getEmail());
        phone.setText(loggedAccount.getPhone());
        if (loggedAccount.getAddress() != null) {
            address.setText(loggedAccount.getAddress());
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Saves the account and name when save is pressed in the profile display
     * @param event the instance to be saved
     */
    @FXML
    private void savePressed(ActionEvent event) {
        Account loggedAccount = application.getLoggedAccount();
        String emailRegex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)" +
                "*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (!email.getText().matches(emailRegex)) {
            message.setText("Invalid email address");
            //message.setVisible(true);
        } else if (!email.getText().matches(emailRegex)) {
            message.setText("Invalid email address");
            //message.setVisible(true);
        } else if (!phone.getText().matches("[0-9]+")) {
            message.setText("Please provide digit only for phone number");
            //message.setVisible(true);
        } else {
            loggedAccount.setEmail(email.getText());
            message.setText("profile updated");
            Database.updateAccount(fname.getText(),
            lname.getText(), email.getText(),
            phone.getText(), address.getText(), loggedAccount.getId());
            //message.setVisible(true);
        }
        message.setVisible(true);
    }

    /**
     * Goes back to the previous page when the back button is pressed
     */
    @FXML
    private void backToRegPressed() {
        application.accountLogging(application.getLoggedAccount().getId());
    }

}
