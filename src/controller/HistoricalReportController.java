package controller;

import fxapp.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.Database;
import model.WaterPurityReport;
import model.WaterSourceReport;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Bang on 11/5/16.
 */
public class HistoricalReportController implements Initializable {
    private Main application;
    private WaterPurityReport wpr;
    @FXML
    LineChart<String , Double> lineChart;
    @FXML
    private CategoryAxis x = new CategoryAxis();

    @FXML
    private NumberAxis y = new NumberAxis();

    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    private List<WaterPurityReport> waterPurityReportList = new ArrayList<>();

    private int virusPPMclick = 1;

    private int contaminantPPMclick = 1;

    private XYChart.Series<String, Double> virusSeries;

    private XYChart.Series<String, Double> contaminantSeries;

    /**
     * sets the welcome page
     * @param application the main application of welcome page
     */
    public void setApp(Main application, WaterPurityReport wpr){
        this.application = application;
        this.wpr = wpr;
        setWaterPurityData();
        virusPPM();
        contaminantPPM();
    }

    /**
     * Initialize the location and resources
     * @param location the relative location
     * @param resources the resources to be used
     */
    public void initialize(URL location, ResourceBundle resources) {
        x.setLabel("Month");
        y.setLabel("PPM");
        //String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        String[] months = new String[12];
        for(int i = 0; i < 12; i++) {
            months[i] = (i + 1) + "";
        }
        monthNames.addAll(Arrays.asList(months));
        x.setCategories(monthNames);
    }

    private XYChart.Series<String, Double> createMonthDataSeries(double[] monthCounter) {
        XYChart.Series<String,Double> series = new XYChart.Series<String,Double>();

        for (int i = 0; i < monthCounter.length; i++) {
            XYChart.Data<String, Double> monthData = new XYChart.Data<String,Double>(monthNames.get(i), monthCounter[i]);
            series.getData().add(monthData);
        }

        return series;
    }

    /**
     * get all reports with the same location and add to instance waterPurityReportList;
     */
    private void setWaterPurityData() {
        float EPSILON = 0.00000000000001f;
        List<WaterPurityReport> totalList = new ArrayList<>(Database.getWaterPurityReports());

        for (WaterPurityReport e : totalList) {
            if (Math.abs(wpr.getLatitude()
                    - e.getLatitude()) < EPSILON
                    && Math.abs(wpr.getLongitude() - e.getLongitude()) < EPSILON) {
                waterPurityReportList.add(e);
            }
        }
    }


    private void virusPPM() {
        if (virusPPMclick % 2 != 0) {
            double[] monthCounter = new double[12];
            int[] eachMonthTotalReports = new int[12];
            for (WaterPurityReport p : waterPurityReportList) {
                System.out.println(p.getDate());
                int month = getMonth(p) - 1;
                System.out.println(month);
                monthCounter[month] += p.getVirusPPM();
                eachMonthTotalReports[month]++;
            }

            double[] averages = new double[12];
            for (int i = 0; i < 12; i++) {
                if (eachMonthTotalReports[i] != 0) {
                    averages[i] = monthCounter[i] / eachMonthTotalReports[i];
                }
            }

            virusSeries = createMonthDataSeries(averages);
            virusSeries.setName("VirusPPM");
            lineChart.getData().add(virusSeries);
        } else {
            lineChart.getData().remove(virusSeries);
        }
        virusPPMclick++;
    }

    /**
     * Get the month of the report date
     * @param report - the particular purity report
     * @return month of the report date
     */
    private int getMonth(WaterPurityReport report) {
        String[] dateAndTime = report.getDate().split("/");
        String[] temp = dateAndTime[0].split(" ");
        int month = Integer.parseInt(temp[3]);
        return month;
    }

    private void contaminantPPM() {
        if (contaminantPPMclick % 2 != 0) {
            double[] monthCounter = new double[12];
            int[] eachMonthTotalReports = new int[12];
            for (WaterPurityReport p : waterPurityReportList) {
                int month = getMonth(p) - 1;
                monthCounter[month] += p.getContaminantPPM();
                eachMonthTotalReports[month]++;
            }

            double[] averages = new double[12];
            for (int i = 0; i < 12; i++) {
                if (eachMonthTotalReports[i] != 0) {
                    averages[i] = monthCounter[i] / eachMonthTotalReports[i];
                }
            }

            contaminantSeries = createMonthDataSeries(averages);
            contaminantSeries.setName("ContaminantPPM");

            lineChart.getData().add(contaminantSeries);
        } else {
            lineChart.getData().remove(contaminantSeries);
        }
        contaminantPPMclick++;
    }

    /**
     * called when the user closes
     */
    @FXML
    private void handleCloseMenu() {
        System.exit(0);
    }

    @FXML
    private void backButtonPressed() {
        lineChart.getData().clear();
        application.gotoManagerApp();
    }
}
