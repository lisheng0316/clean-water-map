package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import fxapp.Main;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.*;

/**
 * Created by Sean on 10/6/2016.
 * A Controller class to handle the Water Source Report
 */
public class WaterSourceReportController extends AnchorPane implements Initializable {
    private Main application;
    private WaterSourceReport report;
    @FXML
    private Label username;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private Label number;
    @FXML
    private ComboBox<WaterSourceType> type;
    @FXML
    private ComboBox<WaterSourceCondition> condition;
    @FXML
    private Button back;
    @FXML
    private Button submitReport;
    @FXML
    private TextField latitude; //initialize all of these field first, set them to "" or something. So if user doesnt fill out , it isnt null at
    @FXML
    private TextField longitude; //pre-type your registration fields, so you dont have to do it everytime. For testing purposes. put in password and shit
    //takes too much extra coding breh. ithats in Scene builder my scene builder doesn't work properly


    private final ObservableList<WaterSourceCondition> waterSourceConditions = FXCollections.observableArrayList(WaterSourceCondition.values());
    private final ObservableList<WaterSourceType> waterSourceTypes = FXCollections.observableArrayList(WaterSourceType.values());

    public void setApp(Main application) {
        this.application = application;
        Account loggedAccount = application.getLoggedAccount();
        SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("H:mm");
        Date theDate = new Date();
        date.setText(dateformatter.format(theDate));
        time.setText(timeFormatter.format(theDate));
        username.setText(loggedAccount.getId());
        latitude.setText("1");
        longitude.setText("2");


    }

    public void initialize(URL location, ResourceBundle resources) {
        type.setItems(waterSourceTypes);
        condition.setItems(waterSourceConditions);
    }

    /**
     * Goes to the previous page when the button clicked
     */
    @FXML
    private void backButtonPressed() {
        application.gotoApp();
    }

    @FXML
    private void submitReport () {
        report = new WaterSourceReport(username.getText(), date.getText(),
                time.getText(), longitude.getText(), latitude.getText(),
                type.getValue(), condition.getValue());
        WaterSourceReportHolder.addReport(report.getReportNumber(), report);
        application.gotoApp();
    }
}
