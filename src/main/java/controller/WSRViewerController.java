package controller;

import fxapp.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.WaterSourceReport;

import java.net.URL;
import java.util.*;

import static model.WaterSourceReportHolder.reports;

/**
 * Created by Sean on 10/11/2016.
 * A controller class for the Water Source Report Viewer page
 */
public class WSRViewerController extends AnchorPane implements Initializable {
    private Main application;
    @FXML
    private ScrollPane WSRList;
    @FXML
    private Button goBack;

    private ObservableList<WaterSourceReport> waterSourceReports = FXCollections.observableArrayList();

    /**
     * sets the Water Source Report Viewer page
     * @param application the main application of the WSRViewer page
     */
    public void setApp(Main application) {
        this.application = application;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VBox theVBox = new VBox();
        for(Integer key : reports.keySet()) {
            theVBox.getChildren().addAll(new HBox(new Label(reports.get(key).toString())));
        }
        WSRList.setContent(theVBox);

    }

    /**
     * Goes to the previous page when the button clicked
     */
    @FXML
    private void goBackButtonPressed() {
        application.gotoApp();
    }

}
