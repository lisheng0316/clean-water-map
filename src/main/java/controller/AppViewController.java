package controller;

import fxapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Sheng on 9/19/16.
 */
public class AppViewController implements Initializable {
    @FXML
    private WebView webView;
    @FXML
    private Label welcomeUser;

    private WebEngine engine;
    private Main application;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void setApp(Main application){
        this.application = application;
        engine = webView.getEngine();
        engine.load("https://maps.google.com");
        welcomeUser.setText("Welcome, " + application.getLoggedAccount());
    }

    @FXML
    private void logoutPressed() {
        application.accountLogout();
    }
}
