package controller;

import fxapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.WaterCondition;
import model.WaterType;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Sheng on 9/19/16.
 * A controller for the app view
 */
public class WSRViewController implements Initializable {

    @FXML
    private Label welcome;
    @FXML
    private Label username;
    @FXML
    private AnchorPane reportForm;

    @FXML
    private Label reportNumber;
    @FXML
    private Label reporter;
    @FXML
    private Label longitude;
    @FXML
    private Label latitude;
    @FXML
    private Label waterType;
    @FXML
    private Label waterCondition;
    @FXML
    private Label dateCreated;


    private Main application;
    private boolean reportExpand = true;
    private static boolean isSourceReport = false;
    private static Alert alert;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Locale.setDefault(Locale.US);

        alert = new Alert(Alert.AlertType.CONFIRMATION);
    }

    /**
     * Sets up the up view
     * @param application the main application of th app view
     */
    public void setApp(Main application) {
        this.application = application;
        // username.setText("" + application.getLoggedAccount());
    }

    /**
     * Setter of report number
     * @param number report number to set
     */
    public void setReportNumber(int number) {
        reportNumber.setText("" + number);
    }

    /**
     * Setter of reporter.
     * @param name name to set
     */
    public void setReporter(String name) {
        reporter.setText(name);
    }

    /**
     * Setter of date created.
     * @param date date to set
     */
    public void setDateCreated(String date) {
        dateCreated.setText(date);
    }

    /**
     * Setter of report longitude location.
     * @param longi longitude to set
     */
    public void setLongitude(double longi) {
        longitude.setText("" + longi);
    }


    /**
     * Setter of latitude location.
     * @param lati latitude to set
     */
    public void setLatitude(double lati) {
        latitude.setText("" + lati);
    }

    /**
     * Setter of water type in report.
     * @param type type to set
     */
    public void setWaterType(WaterType type) {
        waterType.setText("" + type);
    }

    /**
     * Setter of report water condition in report.
     * @param condition condition to set
     */
    public void setWaterCondition(WaterCondition condition) {
        waterCondition.setText("" + condition);
    }


    /**
     * Go to application when pressed.s
     */
    @FXML
    private void backToAppPressed() {
        application.gotoApp();
    }


}
