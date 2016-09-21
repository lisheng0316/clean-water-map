package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import fxapp.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.AnchorPane;

/**
 * Created by Sheng on 9/19/16.
 */
public class WelcomeController extends AnchorPane implements Initializable {


    private Main application;

    public void setApp(Main application){ this.application = application;
    }


    public void initialize(URL location, ResourceBundle resources) {
        System.out.print("call initialize from WelcomeController");
    }

    @FXML
    private void loginPressed() {
        boolean okClicked = application.showLoginDialog();
    }

    @FXML
    private void registrationPressed() {
        //switch scene to registration
    }

    @FXML
    private void handleCloseMenu() {
        System.exit(0);
    }

}