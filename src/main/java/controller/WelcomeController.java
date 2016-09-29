package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import fxapp.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.AnchorPane;

/**
 * Created by Sheng on 9/19/16.
 * A controller for the the welcome page
 */
public class WelcomeController extends AnchorPane implements Initializable {

    private Main application;

    /**
     * sets the welcome page
     * @param application the main application of welcome page
     */
    public void setApp(Main application){ this.application = application;
    }

    /**
     * Initialize the location and resources
     * @param location the relative location
     * @param resources the resources to be used
     */
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Called when the user presses login
     */
    @FXML
    private void loginPressed() {
        boolean okClicked = application.showLoginDialog();
    }

    /**
     * called when the registration is pressed
     */
    @FXML
    private void registrationPressed() {
        application.gotoRegistration();
    }

    /**
     * called when the user closes
     */
    @FXML
    private void handleCloseMenu() {
        System.exit(0);
    }

}