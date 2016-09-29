package controller;

import fxapp.Main;
import javafx.fxml.Initializable;
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
 */
public class ProfileController extends AnchorPane implements Initializable{

    @FXML
    private TextField accountType;
    @FXML
    private TextField user;
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextArea address;

    @FXML
    private Button save;

    @FXML
    private Label success;

    private Main application;

    /**
     * Sets up the application for profile
     * @param application the main application of the profile
     */
    public void setApp(Main application){
        this.application = application;
        Account loggedAccount = application.getLoggedAccount();
        accountType.setText(loggedAccount.getType().name());
        user.setText(loggedAccount.getId());
        name.setText(loggedAccount.getFname() + " " + loggedAccount.getLname());
        email.setText(loggedAccount.getEmail());
        phone.setText(loggedAccount.getPhone());
        if (loggedAccount.getAddress() != null) {
            address.setText(loggedAccount.getAddress());
        }
//        subscribed.setSelected(loggedAccount.isSubscribed());
        success.setOpacity(0);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * It updates the validated information
     * @param event
     */
    @FXML
    private void savePressed(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            animateMessage();
            return;
        }
        Account loggedAccount = application.getLoggedAccount();
        loggedAccount.setEmail(email.getText());
        loggedAccount.setPhone(phone.getText());
        loggedAccount.setAddress(address.getText());

        animateMessage();
        application.accountLogging(loggedAccount.toString());
    }

    /**
     * Called when the register is pressed
     */
    @FXML
    private void backToRegPressed() {
        application.gotoRegistration();
    }

    /**
     *  Creates an animation effect
     */
    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), success);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }

}
