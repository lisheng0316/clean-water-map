package controller;

import fxapp.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.time.LocalDate;
import java.util.Locale;

import javafx.scene.layout.AnchorPane;
import model.WaterCondition;
import model.WaterType;

//import javax.jnlp.UnavailableServiceException;
import javax.xml.stream.XMLReporter;
import javax.xml.transform.Source;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Sheng on 9/19/16.
 * A controller for the app view
 */
public class AppViewController implements Initializable {
    @FXML
    private WebView webView;
    @FXML
    private Label welcome;
    @FXML
    private Label username;
    @FXML
    private AnchorPane reportForm;
    @FXML
    private VBox form;

    @FXML
    private TextField virusPPM;

    @FXML
    private TextField contaminantPPM;

    @FXML
    private TextField longitude;

    @FXML
    private TextField latitude;

    @FXML
    private TitledPane formCollapse;

    @FXML
    private ComboBox<WaterType> waterType;

    @FXML
    private ComboBox<WaterCondition> waterCondition;

    @FXML
    private ComboBox<String> reportType;


    private final ObservableList<WaterType> waterTypeList
            = FXCollections.observableArrayList(WaterType.values());

    private final ObservableList<WaterCondition> waterConditionList
            = FXCollections.observableArrayList(WaterCondition.values());

    private final ObservableList<String> reportTypeList
            = FXCollections.observableArrayList("Source report", "Purity report");

    private DatePicker reportDate;
    private WebEngine engine;
    private Main application;
    private boolean reportExpand = true;
    private static boolean isSourceReport = false;
    private static Alert alert;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Locale.setDefault(Locale.US);
        waterType.setItems(waterTypeList);
        waterCondition.setItems(waterConditionList);
        reportType.setItems(reportTypeList);
        alert = new Alert(Alert.AlertType.CONFIRMATION);
    }

    /**
     * Sets up the up view
     * @param application the main application of th app view
     */
    public void setApp(Main application){
        this.application = application;
        engine = webView.getEngine();
        engine.load("https://maps.google.com");
        username.setText("" + application.getLoggedAccount());
    }

    /**
     * Activates the username is pressed
     */
    @FXML
    private void usernamePressed() {
        application.gotoProfile();
    };

    @FXML
    private void cancelFormPressed() {
        alert.setTitle("Form Cancellation");
        alert.setHeaderText("Are you sure to cancel the current report?");
        alert.setContentText("Hit OK to void your submission");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            reportType.setValue("Report Type");
            waterCondition.setValue(null);
            contaminantPPM.clear();
            virusPPM.clear();
            longitude.clear();
            latitude.clear();
        }
    }

    @FXML
    private void submitFormPressed() {

        alert.setTitle("Form Submission");
        alert.setHeaderText("You are about to submit the current report");
        alert.setContentText("Proceed your submission?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
        }
    }



    @FXML
    private void menuPressed(){
        toggleMenu();
    }

    @FXML
    private void accountSettingPressed() {
        application.gotoProfile();
    }

    @FXML
    private void signoutPressed() {
        application.accountLogout();
    }


    private void toggleMenu() {
        if (!reportExpand) {
            webView.setMinWidth(800);
            webView.setMaxWidth(800);
            reportForm.setMinWidth(0);
            reportForm.setMaxWidth(0);
            reportExpand = true;
        }
        else {
            webView.setMinWidth(600);
            webView.setMaxWidth(600);
            reportForm.setMinWidth(200);
            reportForm.setMaxWidth(200);
            reportExpand = false;
        }
    }

    @FXML
    private void reportTypeSelected() {

        if (reportType.getValue().equals("Source report")) {
            isSourceReport = true;
        } else if (reportType.getValue().equals("Purity report")) {
            isSourceReport = false;
        }
        virusPPM.setDisable(isSourceReport);
        contaminantPPM.setDisable(isSourceReport);
        waterType.setDisable(!isSourceReport);
    }

}
