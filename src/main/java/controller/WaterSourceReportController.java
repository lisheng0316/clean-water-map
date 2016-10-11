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
    private Label location;
    @FXML
    private Label number;
    @FXML
    private ComboBox type;
    @FXML
    private ComboBox condition;
    @FXML
    private Button back;
    @FXML
    private Button submitReport;

    private final ObservableList<WaterSourceCondition> waterSourceConditions = FXCollections.observableArrayList(WaterSourceCondition.values());
    private final ObservableList<WaterSourceType> waterSourceTypes = FXCollections.observableArrayList(WaterSourceType.values());

    public void setApp(Main application) {
        this.application = application;
        Account loggedAccount = application.getLoggedAccount();
        SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("H:mm a");
        Date theDate = new Date();
        date.setText(dateformatter.format(theDate));
        time.setText(timeFormatter.format(theDate));
        username.setText(loggedAccount.getFname());


    }

    public void initalize (URL location, ResourceBundle resources) {
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
                time.getText(), location.getText(), location.getText(),
                type.getValue(), condition.getValue());
    }
}
