package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.InfoWindow;

import fxapp.Main;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


import java.text.SimpleDateFormat;
//import java.util.*;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Date;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
//import model.*;
import model.WaterType;
import model.WaterCondition;
import model.WaterSourceReport;
import model.Database;
import model.Account;

import netscape.javascript.JSObject;

import java.net.URL;


/**
 * Created by Sheng on 9/19/16.
 * A controller for the app view
 */
public class UserAppController implements Initializable,
        MapComponentInitializedListener  {
    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    @FXML
    private Label welcome;
    @FXML
    private Label username;
    @FXML
    private AnchorPane reportPane;

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
    private ListView<WaterSourceReport> sourceReportListView;

    private ObservableList<WaterSourceReport> waterReportList;

    private List<WaterSourceReport> dataArrayList;


    private final ObservableList<WaterType> waterTypeList
            = FXCollections.observableArrayList(WaterType.values());

    private final ObservableList<WaterCondition> waterConditionList
            = FXCollections.observableArrayList(WaterCondition.values());

    private final ObservableList<String> reportTypeList
            = FXCollections.observableArrayList("Source report",
            "Purity report");

    private DatePicker reportDate;

    //    private WebEngine engine;
    private Main application;
    private boolean reportExpand = true;
    private static boolean isSourceReport = false;
    private static Alert alert;

    //Main List View

    //Main report instances
    @FXML
    private VBox mainListViewPane;
    @FXML
    private Label mainReportNumber;
    @FXML
    private Label mainReporter;
    @FXML
    private Label mainLongitude;
    @FXML
    private Label mainLatitude;
    @FXML
    private Label mainWaterType;
    @FXML
    private Label mainWaterCondition;
    @FXML
    private Label mainContaminant;
    @FXML
    private Label mainVirus;
    @FXML
    private Label mainDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        mapView.addMapInializedListener(this);

        Locale.setDefault(Locale.US);
        waterType.setItems(waterTypeList);
        waterCondition.setItems(waterConditionList);
        reportType.setItems(reportTypeList);
        pullReport();

        //listener for Listview, check if item selected;
        sourceReportListView.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends WaterSourceReport> ov,
                              WaterSourceReport oldSelectedItem,
                              WaterSourceReport selectedItem) -> {
                    if (selectedItem != null) {
                        if (mapView.isDisable()) {
                            mainReportNumber.setText(selectedItem
                                    .getReportNumber() + "");
                            mainReporter.setText(selectedItem.getUser());
                            mainLongitude.setText(selectedItem.getLongitude()
                                    + "");
                            mainLatitude.setText(selectedItem.getLatitude()
                                    + "");
                            mainWaterType.setText(selectedItem.getType() + "");
                            mainWaterCondition.setText(selectedItem
                                    .getCondition() + "");
                            mainContaminant.setText("N/A (Purity report only)");
                            mainVirus.setText("N/A (Purity report only)");
                            mainDate.setText(selectedItem.getDate());
                        } else {
                            displayPinFromList(selectedItem);
                        }
                    }
                });

        alert = new Alert(Alert.AlertType.CONFIRMATION);
    }


    @Override
    public void mapInitialized() {
        Database db = Database.getDatabase();
        dataArrayList = Database.getWaterSourceReports();
        WaterSourceReport wsr = dataArrayList.get(0);
        double longitude = wsr.getLongitude();
        double latitude = wsr.getLatitude();

        MapOptions options = new MapOptions();
        //set up the center location for the map
        LatLong center = new LatLong(latitude, longitude);
        options.center(center)
                .zoom(10)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);

        map = mapView.createMap(options);
        displayPins();

    }

    /**
     * Helper method to display pin on map.
     * If a pin is clicked, an info window with location information will pop
     */
    private void displayPins() {
        for (WaterSourceReport w: dataArrayList) {
            MarkerOptions markerOptions = new MarkerOptions();
            LatLong loc = new LatLong(w.getLatitude(), w.getLongitude());

            markerOptions.position(loc)
                    .visible(Boolean.TRUE)
                    .title(w.toString());

            Marker marker = new Marker(markerOptions);

            map.addUIEventHandler(marker, UIEventType.click, (JSObject obj) -> {
                InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                infoWindowOptions.content("<h2> Source report #"
                        + w.getReportNumber() + "</h2>" + "Reporter: "
                        + w.getUser() + "<br>Location: " + w.getLatitude()
                        + ", " + w.getLongitude() + "<br>Type: " + w.getType()
                        + "<br>Condition: " + w.getCondition() + "<br>Date: "
                        + w.getDate());

                InfoWindow window = new InfoWindow(infoWindowOptions);
                window.open(map, marker);
            });

            map.addMarker(marker);

        }
    }

    /**
     * Helper method to display pin on Map.
     * When user select an item in report list, it will display on map.
     * @param report report to be pinned on
     */
    private void displayPinFromList(WaterSourceReport report) {
        MarkerOptions markerOption = new MarkerOptions();
        LatLong location = new LatLong(report.getLatitude(),
                report.getLongitude());

        markerOption.position(location)
                .visible(Boolean.TRUE)
                .title(report.toString());
        Marker marker = new Marker(markerOption);

        InfoWindowOptions infoWindow = new InfoWindowOptions();
        infoWindow.content("<h2>Source report #" + report.getReportNumber()
                + "</h2>"
                + "Reporter: " + report.getUser()
                + "<br>Location: " + report.getLatitude()
                + ", " + report.getLongitude()
                + "<br>Type: " + report.getType()
                + "<br>Condition: " + report.getCondition()
                + "<br>Date: " + report.getDate());

        InfoWindow itemWindow = new InfoWindow(infoWindow);
        itemWindow.setPosition(location);
        itemWindow.open(map, marker);
        map.addMarker(marker);
    }

    /**
     * Helper method to auto focus on last added item on report list.
     */
    private void focusItem() {
        int index = waterReportList.size() - 1;
        sourceReportListView.scrollTo(index);
        sourceReportListView.getFocusModel().focus(index);
        sourceReportListView.getSelectionModel().select(index);
    }

    /**
     * Helper method to added report from database to report List.
     */
    private void pullReport() {
        waterReportList = FXCollections.observableArrayList();
        dataArrayList = Database.getWaterSourceReports();
        waterReportList.addAll(dataArrayList);
        sourceReportListView.setItems(waterReportList);
        focusItem();
    }
    /**
     * Sets up the up view
     * @param application the main application of th app view
     */
    public void setApp(Main application) {
        this.application = application;
        username.setText("" + application.getLoggedAccount());
    }

    /**
     * Go to user's profile page.
     */
    @FXML
    private void usernamePressed() {
        application.gotoProfile();
    }


    /**
     * Cancel the submission of the report
     */

    @FXML
    private void cancelFormPressed() {
        alert.setTitle("Form Cancellation");
        alert.setHeaderText("Are you sure to cancel the current report?");
        alert.setContentText("Hit OK to void your submission");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            resetForm();
        }
    }

    /**
     * Reset form. Removed any filled text area.
     */
    private void resetForm() {
        waterCondition.setValue(null);
        waterType.setValue(null);
        longitude.clear();
        latitude.clear();
    }

    /**
     * Submit a source report form.
     * Submit report to database.
     */
    @FXML
    private void submitFormPressed() {
        if (latitude.getText() == null
                || longitude.getText() == null
                || waterType.getValue() == null
                || waterCondition.getValue() == null) {

            alert.setTitle("Form Submission");
            alert.setHeaderText("Report is has missing information");
            alert.setContentText("Please fill out all required field "
                    + "before submitting");
            Optional<ButtonType> result = alert.showAndWait();

        } else {
            alert.setTitle("Form Submission");
            alert.setHeaderText("You are about to submit the current report");
            alert.setContentText("Proceed your submission?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                Account loggedAccount = application.getLoggedAccount();

                Date tempDate = new Date();
                SimpleDateFormat dateFormatter = new
                        SimpleDateFormat("MM/dd/yyyy");
                SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
                String date = timeFormatter.format(tempDate) + " on "
                        + dateFormatter.format(tempDate);

                Database.addWaterSourceReport(loggedAccount.getId(),
                        latitude.getText(),
                        longitude.getText(),
                        waterType.getValue(),
                        waterCondition.getValue(),
                        date);
                pullReport();
                resetForm();
                formCollapse.setExpanded(false);
                reportCollapse.setExpanded(true);
                displayPins();
            }
        }
    }


    /**
     * toggle menu when pressed.
     */
    @FXML
    private void menuPressed() {
        toggleMenu();
    }

    /**
     * Go to user's profile page when settings is pressed
     */
    @FXML
    private void accountSettingPressed() {
        application.gotoProfile();
    }

    /**
     * Log current user out of application when pressed.
     */
    @FXML
    private void signoutPressed() {
        application.accountLogout();
    }

    /**
     * Expand and Collapse menu when clicked.
     */
    private void toggleMenu() {
        if (!reportExpand) {
            mapView.setMinWidth(800);
            mapView.setMaxWidth(800);
            reportPane.setMinWidth(0);
            reportPane.setMaxWidth(0);
            reportExpand = true;
        } else {
            mapView.setMinWidth(600);
            mapView.setMaxWidth(600);
            reportPane.setMinWidth(200);
            reportPane.setMaxWidth(200);
            reportExpand = false;
        }
    }

    /**
     * Disable options between source and purity report when one of both
     * is clicked.
     */
    @FXML
    private void reportTypeSelected() {
        if (reportType.getValue().equals("Source report")) {
            isSourceReport = true;
        } else if (reportType.getValue().equals("Purity report")) {
            isSourceReport = false;
        }
        waterType.setDisable(!isSourceReport);
    }
    /**
     * Collapse other expandable when this tap is pressed.
     */
    @FXML
    private void newReportPressed() {
        reportCollapse.setExpanded(false);
    }
    /**
     * Collapse other expandable when this tap is pressed.
     */
    @FXML
    private void viewReportPressed() {
        formCollapse.setExpanded(false);
    }


    /**
     * Go to worker's water source report page.
     */
    @FXML
    private void viewPressed() {

        if (!mainListViewPane.isVisible()) {
            mapView.setVisible(false);
            mapView.setDisable(true);
            mainListViewPane.setVisible(true);
            mainListViewPane.setDisable(false);
            sourceReportListView.getSelectionModel().select(0);
        } else {
            mapView.setVisible(true);
            mapView.setDisable(false);
            mainListViewPane.setVisible(false);
            mainListViewPane.setDisable(true);
        }
    }
}