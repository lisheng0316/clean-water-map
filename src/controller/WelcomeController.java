package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import fxapp.Main;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Sheng on 9/19/16.
 */
public class WelcomeController extends AnchorPane implements Initializable {
    @FXML
    Button login;
    @FXML
    Button registration;


    private Main application;

    public void setApp(Main application){
        this.application = application;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void handleLoginPressed() {

    }
    @FXML
    private void handleRegPressed() {

    }


    @FXML
    private void handleCloseMenu() {
        System.exit(0);

    }


}
