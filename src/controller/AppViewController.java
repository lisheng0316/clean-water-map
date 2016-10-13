package controller;

import fxapp.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.text.SimpleDateFormat;
import java.util.*;

import javafx.scene.layout.AnchorPane;
import model.Account;
import model.Database;
import model.WaterCondition;
import model.WaterSourceReport;
import model.WaterType;

import java.net.URL;


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
    private TitledPane reportCollapse;

    @FXML
    private ComboBox<WaterType> waterType;

    @FXML
    private ComboBox<WaterCondition> waterCondition;

    @FXML
    private ComboBox<String> reportType;

    @FXML
    private ListView<WaterSourceReport> reportListView;

    private ObservableList<WaterSourceReport> waterReportList;

    private List<WaterSourceReport> dataArrayList = null;

    private final ObservableList<WaterSourceReport> sourceReportList
            = FXCollections.observableArrayList(WaterSourceReport.getInstance());
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
        pullReport();
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        System.out.println("jklhlkhj");
    }

    private void focusItem() {
        int index = waterReportList.size() - 1;
        reportListView.scrollTo(index);
        reportListView.getFocusModel().focus(index);
        reportListView.getSelectionModel().select(index);
    }
    private void pullReport() {
        waterReportList = FXCollections.observableArrayList();
        dataArrayList = Database.getWaterSourceReports();
        for (WaterSourceReport e : dataArrayList) {
            waterReportList.add(e);
        }
        reportListView.setItems(waterReportList);
        focusItem();
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
            resetForm();
        }
    }

    private void resetForm() {
        reportType.setValue("Report Type");
        waterCondition.setValue(null);
        contaminantPPM.clear();
        virusPPM.clear();
        longitude.clear();
        latitude.clear();
    }

    @FXML
    private void submitFormPressed() {

        alert.setTitle("Form Submission");
        alert.setHeaderText("You are about to submit the current report");
        alert.setContentText("Proceed your submission?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            WaterSourceReport report = new WaterSourceReport();
            Account loggedAccount = application.getLoggedAccount();

            Date tempDate = new Date();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yy");
            SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
            String date = timeFormatter.format(tempDate) + " on " +dateFormatter.format(tempDate);

            Database.addWaterSourceReport(loggedAccount.getId(), longitude.getText()
                    , latitude.getText(),
                    waterType.getValue(),
                    waterCondition.getValue(),
                    date);
            pullReport();
            resetForm();
            formCollapse.setExpanded(false);
            reportCollapse.setExpanded(true);
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
