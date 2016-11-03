package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import fxapp.Main;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import model.Account;
import model.Database;
import model.WaterCondition;
import model.WaterPurityReport;
import model.WaterSourceReport;
import model.WaterType;
import netscape.javascript.JSObject;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * Created by Sheng on 9/19/16.
 * A controller for the app view
 */
public class ManagerAppController extends UserAppController implements Initializable, MapComponentInitializedListener  {
    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

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
    private ListView<WaterSourceReport> sourceReportListView;
    @FXML
    private Tab srTab;
    @FXML
    private Tab prTab;
    @FXML
    private ListView<WaterPurityReport> purityReportListView;

    private ObservableList<WaterSourceReport> waterSourceReportList;
    private ObservableList<WaterPurityReport> waterPurityReportList;
    private List<WaterSourceReport> wsrArrayList;
    private List<WaterPurityReport> wprArrayList;

    private final ObservableList<WaterType> waterTypeList
            = FXCollections.observableArrayList(WaterType.values());

    private final ObservableList<WaterCondition> waterConditionList
            = FXCollections.observableArrayList(WaterCondition.values());

    private final ObservableList<String> reportTypeList
            = FXCollections.observableArrayList("Source report", "Purity report");

    private DatePicker reportDate;

    //    private WebEngine engine;
    private Main application;
    private boolean reportExpand = true;
    private static boolean isSourceReport = false;
    private static Alert alert;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapView.addMapInializedListener(this);

        Locale.setDefault(Locale.US);
        waterType.setItems(waterTypeList);
        waterCondition.setItems(waterConditionList);
        reportType.setItems(reportTypeList);
        pullReport();

        //listener for Listview, check if item selected;
        sourceReportListView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends WaterSourceReport> ov, WaterSourceReport oldSelectedItem,
                 WaterSourceReport selectedItem) -> {
                    if (selectedItem != null) {
                        displayPinFromList(selectedItem);
                    }
                });


        purityReportListView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends WaterPurityReport> ov, WaterPurityReport oldSelectedItem,
                 WaterPurityReport selectedItem) -> {
                    if (selectedItem != null) {
                        displayPinFromList(selectedItem);
                    }
                });
        alert = new Alert(Alert.AlertType.CONFIRMATION);
    }


    @Override
    public void mapInitialized() {
        Database db = Database.getDatabase();
        wsrArrayList = db.getWaterSourceReports();
        wprArrayList = db.getWaterPurityReports();
        System.out.println("wsrlist :" + wsrArrayList.toString());
        System.out.println("wprlist :" + wprArrayList.toString());

        WaterSourceReport wsr = wsrArrayList.get(0);
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
     * If a pin is clicked, an info window with location information will pop up.
     */
    private void displayPins() {
        for (WaterSourceReport w: wsrArrayList) {
            MarkerOptions markerOptions = new MarkerOptions();
            LatLong loc = new LatLong(w.getLatitude(), w.getLongitude());

            markerOptions.position(loc)
                    .visible(Boolean.TRUE)
                    .title(w.toString());

            Marker marker = new Marker(markerOptions);

            map.addUIEventHandler(marker,
                    UIEventType.click,
                    (JSObject obj) -> {
                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        infoWindowOptions.content("<h2>" + w.toString() + "</h2>"
                                + "Reporter: " + w.getUser()
                                + "<br>Location: " + w.getLatitude() + ", " + w.getLongitude()
                                + "<br>Type: " + w.getType()
                                + "<br>Condition: " + w.getCondition()
                                + "<br>Date: " + w.getDate());

                        InfoWindow window = new InfoWindow(infoWindowOptions);
                        window.open(map, marker);
                        latitude.setText("" + w.getLatitude());
                        longitude.setText("" + w.getLongitude());
                        waterType.setValue(w.getType());
                        waterCondition.setValue(w.getCondition());

                    });

            map.addMarker(marker);

        }

        for (WaterPurityReport wpr: wprArrayList) {
            MarkerOptions markerOptions = new MarkerOptions();
            LatLong loc = new LatLong(wpr.getLatitude(), wpr.getLongitude());

            markerOptions.position(loc)
                    .visible(Boolean.TRUE)
                    .title(wpr.toString());

            Marker marker = new Marker(markerOptions);

            map.addUIEventHandler(marker,
                    UIEventType.click,
                    (JSObject obj) -> {
                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        infoWindowOptions.content("<h2>" + wpr.toString() + "</h2>"
                                + "Reporter: " + wpr.getUser()
                                + "<br>Location: " + wpr.getLatitude() + ", " + wpr.getLongitude()
                                + "<br>Type: " + wpr.getType()
                                + "<br>Condition: " + wpr.getCondition()
                                + "<br>Date: " + wpr.getDate()
                                + "<br>VirusPPM: " + wpr.getVirusPPM()
                                + "<br>ContaminantPPM: " + wpr.getContaminantPPM());

                        InfoWindow window = new InfoWindow(infoWindowOptions);
                        window.open(map, marker);
                        latitude.setText("" + wpr.getLatitude());
                        longitude.setText("" + wpr.getLongitude());
                        waterType.setValue(wpr.getType());
                        waterCondition.setValue(wpr.getCondition());



                    });

            map.addMarker(marker);

        }


    }


    /**
     * Helper method to display pin on Map.
     * When user select an item in source report list, it will display on map.
     * @param report report to be pinned on
     */
    private void displayPinFromList(WaterSourceReport report) {
        MarkerOptions markerOption = new MarkerOptions();
        LatLong location = new LatLong(report.getLatitude(), report.getLongitude());

        markerOption.position(location)
                .visible(Boolean.TRUE)
                .title(report.toString());
        Marker marker = new Marker(markerOption);

        InfoWindowOptions infoWindow = new InfoWindowOptions();
        infoWindow.content("<h2>" + report.toString() + "</h2>"
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
     * Helper method to display pin on Map.
     * When user select an item in purity report list, it will display on map.
     * @param report report to be pinned on
     */
    private void displayPinFromList(WaterPurityReport report) {
        MarkerOptions markerOption = new MarkerOptions();
        LatLong location = new LatLong(report.getLatitude(), report.getLongitude());

        markerOption.position(location)
                .visible(Boolean.TRUE)
                .title(report.toString());
        Marker marker = new Marker(markerOption);

        InfoWindowOptions infoWindow = new InfoWindowOptions();
        infoWindow.content("<h2>" + report.toString() + "</h2>"
                + "Reporter: " + report.getUser()
                + "<br>Location: " + report.getLatitude()
                + ", " + report.getLongitude()
                + "<br>Type: " + report.getType()
                + "<br>Condition: " + report.getCondition()
                + "<br>Date: " + report.getDate()
                + "<br>VirusPPM: " + report.getVirusPPM()
                + "<br>ContaminantPPM: " + report.getContaminantPPM());

        InfoWindow itemWindow = new InfoWindow(infoWindow);
        itemWindow.setPosition(location);
        itemWindow.open(map, marker);
        map.addMarker(marker);
    }

    /**
     * Helper method to auto focus on last added item on report list.
     */
    private void focusItem() {
        int indexWSR = waterSourceReportList.size() - 1;
        sourceReportListView.scrollTo(indexWSR);
        sourceReportListView.getFocusModel().focus(indexWSR);
        sourceReportListView.getSelectionModel().select(indexWSR);


        int indexWPR = waterPurityReportList.size() - 1;
        purityReportListView.scrollTo(indexWPR);
        purityReportListView.getFocusModel().focus(indexWPR);
        purityReportListView.getSelectionModel().select(indexWPR);
    }

    /**
     * Helper method to added report from database to report List.
     */
    private void pullReport() {
        waterSourceReportList = FXCollections.observableArrayList();
        wsrArrayList = Database.getWaterSourceReports();
        for (WaterSourceReport e : wsrArrayList) {
            waterSourceReportList.add(e);
        }

        sourceReportListView.setItems(waterSourceReportList);

        waterPurityReportList = FXCollections.observableArrayList();
        wprArrayList = Database.getWaterPurityReports();
        for (WaterPurityReport e : wprArrayList) {
            waterPurityReportList.add(e);
        }

        purityReportListView.setItems(waterPurityReportList);
        focusItem();
    }
    /**
     * Sets up the up view
     * @param application the main application of th app view
     */
    public void setApp(Main application){
        this.application = application;
        username.setText("" + application.getLoggedAccount());
    }

    /**
     * Activates the username is pressed
     */
    @FXML
    private void usernamePressed() {
        application.gotoProfile();
    };


    /**
     * Cancel the submission of the report
     */
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

    /**
     * Helper method to reset form when cancel  is clicked.
     */
    private void resetForm() {
        reportType.setValue("Report Type");
        waterCondition.setValue(null);
        contaminantPPM.clear();
        virusPPM.clear();
        longitude.clear();
        latitude.clear();
    }

    /**
     * Disable options between source and purity report when one of both is clicked.
     */
    @FXML
    private void reportTypeSelected() {
        if (reportType.getValue().equals("Source report")) {
            isSourceReport = true;
        } else if (reportType.getValue().equals("Purity report")) {
            isSourceReport = false;
        }
        virusPPM.setDisable(isSourceReport);
        contaminantPPM.setDisable(isSourceReport);
    }


    /**
     * Submit a source report form.
     * Submit report to database.
     */
    @FXML
    private void submitFormPressed() {
        if (reportType.getValue() == null
                || latitude.getText() == null
                || longitude.getText() == null
                || waterType.getValue() == null
                || waterCondition.getValue() == null) {

            alert.setTitle("Form Submission");
            alert.setHeaderText("Report is has missing information");
            alert.setContentText("Please fill out all required field before submitting");
            Optional<ButtonType> result = alert.showAndWait();

        } else {
            alert.setTitle("Form Submission");
            alert.setHeaderText("You are about to submit the current report");
            alert.setContentText("Proceed your submission?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                Account loggedAccount = application.getLoggedAccount();

                Date tempDate = new Date();
                SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yy");
                SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
                String date = timeFormatter.format(tempDate) + " on " + dateFormatter.format(tempDate);

                if (reportType.getValue().equals("Source report")) {
                    Database.addWaterSourceReport(loggedAccount.getId(),
                            latitude.getText(),
                            longitude.getText(),
                            waterType.getValue(),
                            waterCondition.getValue(),
                            date);
                } else {
                    Database.addWaterPurityReport(loggedAccount.getId(),
                            latitude.getText(),
                            longitude.getText(),
                            waterType.getValue(),
                            waterCondition.getValue(),
                            date, contaminantPPM.getText(), virusPPM.getText());
                }
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
    private void menuPressed(){
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
            reportForm.setMinWidth(0);
            reportForm.setMaxWidth(0);
            reportExpand = true;
        }
        else {
            mapView.setMinWidth(600);
            mapView.setMaxWidth(600);
            reportForm.setMinWidth(200);
            reportForm.setMaxWidth(200);
            reportExpand = false;
        }
    }


    /**
     * Go to worker's water source report page.
     */
    @FXML
    private void viewPressed() {
        WaterSourceReport wsr
                = sourceReportListView.getSelectionModel().getSelectedItem();
        WaterPurityReport wpr
                = purityReportListView.getSelectionModel().getSelectedItem();

        if (wsr == null) {
            alert.setTitle("ERROR");
            alert.setContentText(
                    "Please select the report to view!");
            alert.showAndWait();
        } else if (srTab.isSelected()) {
            application.gotoWSR(wsr);
        } else if (prTab.isSelected()) {
            application.gotoWPR(wpr);
        }



    }


}