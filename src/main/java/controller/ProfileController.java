package controller;

import fxapp.Main;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import java.net.URL;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Account;

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
        accountType.setText(loggedAccount.getType().name());
        user.setText(loggedAccount.getId());
        fname.setText(loggedAccount.getFname());
        lname.setText(loggedAccount.getLname());
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
     *
     */
    @FXML
    private void savePressed() {
        Account loggedAccount = application.getLoggedAccount();
        String emailRegex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)" +
                "*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (!email.getText().matches(emailRegex)) {
            message.setText("Invalid email address");
        } else if (!email.getText().matches(emailRegex)) {
            message.setText("Invalid email address");
        } else {
            loggedAccount.setEmail(email.getText());
            message.setText("profile updated");
        }
        loggedAccount.setFname(fname.getText());
        loggedAccount.setLname(lname.getText());

        loggedAccount.setPhone(phone.getText());
        loggedAccount.setAddress(address.getText());
        message.setVisible(true);
    }

    /**
     * Goes back to the previous page when the back button is pressed
     */
    @FXML
    private void backToRegPressed() {
        application.gotoApp();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void backKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            backToRegPressed();
        }
    }
    /**
     *
     * @param event
     */
    @FXML
    private void saveEntered(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            savePressed();
        }
    }

}
