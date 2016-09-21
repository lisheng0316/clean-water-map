package controller;

import fxapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Sheng on 9/19/16.
 */
public class AppViewController implements Initializable {
    @FXML
    private WebView webView;
    @FXML
    private Button logout;

    private WebEngine engine;
    private Main application;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        engine = webView.getEngine();
    }
    public void setApp(Main application){
        this.application = application;
    }
//    public void btn1(ActionEvent e) {
//        engine.load("https://maps.google.com");
//    }
    @FXML
    private void logoutPressed() {
        application.userLogout();
    }

}
