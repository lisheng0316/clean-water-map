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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Database;
import model.WaterPurityReport;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Historical Report Controller
 * Created by Bang on 11/5/16.
 */
public class HistoricalReportController implements Initializable {
    private Main application;
    private WaterPurityReport wpr;
    @FXML
    LineChart<String, Double> lineChart;
    @FXML
    private CategoryAxis x = new CategoryAxis();

    @FXML
    private NumberAxis y = new NumberAxis();

    @FXML
    private TextField yearBox;
    @FXML
    private TextField reportBox;
    @FXML
    private Label latitude;
    @FXML
    private Label longitude;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    private List<WaterPurityReport> totalList = Database.getWaterPurityReports();
    private List<WaterPurityReport> waterPurityReportList;

    private XYChart.Series<String, Double> virusSeries;

    private XYChart.Series<String, Double> contaminantSeries;

    /**
     /**
     * Setup the main application link so we can call methods there
     * @param application the reference to the FX Application instance
     *
     * @param wpr the water purity report to show
     */
    public void setApp(Main application, WaterPurityReport wpr){
        this.application = application;
        this.wpr = wpr;
        latitude.setText(wpr.getLatitude()+ "");
        longitude.setText(wpr.getLongitude()+ "");
        reportBox.setText(wpr.getReportNumber() + "");
        yearBox.setText(getYear(wpr));
        graph(wpr);
    }
    /**
     * Draw the historical graph
     *
     * @param report the water purity report to show
     */
    private void graph(WaterPurityReport report) {
        System.out.println(report.getLatitude() + " This is in Graph()");
        setWaterPurityData(report);
        virusPPM();
        contaminantPPM();
        reportBox.setText(report.getReportNumber() + "");
        latitude.setText(report.getLatitude()+ "");
        longitude.setText(report.getLongitude()+ "");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        x.setLabel("Month");
        y.setLabel("PPM");
        //String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        String[] months = new String[12];
        for (int i = 0; i < 12; i++) {
            months[i] = (i + 1) + "";
        }
        monthNames.addAll(Arrays.asList(months));
        x.setCategories(monthNames);
    }
    /**
     * Create the monthly data for chart
     * @param monthCounter month counter to be
     */
    private XYChart.Series<String, Double> createMonthDataSeries(double[] monthCounter) {
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        for (int i = 0; i < monthCounter.length; i++) {
            XYChart.Data<String, Double> monthData
                    = new XYChart.Data<>(monthNames.get(i), monthCounter[i]);
            series.getData().add(monthData);
        }

        return series;
    }

    /**
     * Get all reports with the same location and add to instance waterPurityReportList;
     * @param report the water purity report to get data from
     */
    private void setWaterPurityData(WaterPurityReport report) {
        float EPSILON = 0.00000000000001f;
        waterPurityReportList = new ArrayList<>();
        System.out.println("Report " + report.getReportNumber()
                + " " + report.getContaminantPPM());
        for (WaterPurityReport e : totalList) {
            if (Math.abs(report.getLatitude()
                    - e.getLatitude()) < EPSILON
                    && Math.abs(report.getLongitude() - e.getLongitude()) < EPSILON) {
                waterPurityReportList.add(e);
            }
        }
        System.out.println("Number of records: " + waterPurityReportList.size());
    }

    /**
     * Show the line graph base on virus PPM
     *
     */
    private void virusPPM() {
            double[] monthCounter = new double[12];
            int[] eachMonthTotalReports = new int[12];
            for (WaterPurityReport p : waterPurityReportList) {
                if (yearBox.getText().equals(getYear(p))) {
                    int month = getMonth(p) - 1;
                    monthCounter[month] += p.getVirusPPM();
                    eachMonthTotalReports[month]++;
                }
            }

            double[] averages = new double[12];
            for (int i = 0; i < 12; i++) {
                if (eachMonthTotalReports[i] != 0) {
                    averages[i] = monthCounter[i] / eachMonthTotalReports[i];
                }
            }

            virusSeries = createMonthDataSeries(averages);
            virusSeries.setName("Virus PPM");
            lineChart.getData().add(virusSeries);
    }
    /**
     * Show the line graph base on contaminant PPM
     *
     */
    private void contaminantPPM() {
            double[] monthCounter = new double[12];
            int[] eachMonthTotalReports = new int[12];
            for (WaterPurityReport p : waterPurityReportList) {
                if (yearBox.getText().equals(getYear(p))) {
                    int month = getMonth(p) - 1;
                    monthCounter[month] += p.getContaminantPPM();
                    eachMonthTotalReports[month]++;
                }
            }
            double[] averages = new double[12];
            for (int i = 0; i < 12; i++) {
                if (eachMonthTotalReports[i] != 0) {
                    averages[i] = monthCounter[i] / eachMonthTotalReports[i];
                }
            }
            contaminantSeries = createMonthDataSeries(averages);
            contaminantSeries.setName("Contaminant PPM");
            lineChart.getData().add(contaminantSeries);
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

    /**
     * Get the year of the report date
     * @param report - the particular purity report
     * @return year of the report date
     */
    private String getYear(WaterPurityReport report) {
        String[] dateAndTime = report.getDate().split("/");
        String year = dateAndTime[2];
        return year;
    }
    /**
     * increment the year
     *
     */

    @FXML
    private void yearIncrement() {
        int temp = Integer.parseInt(yearBox.getText());
        temp++;
        yearBox.setText("" + temp);
        refreshChart();
    }
    /**
     * decrement the year
     *
     */
    @FXML
    private void yearDecrement() {
        int temp = Integer.parseInt(yearBox.getText());
        temp--;
        yearBox.setText("" + temp);
        refreshChart();
    }
    /**
     * increment the report number
     *
     */
    @FXML
    private void reportIncrement() {
        int currentReportNumber = Integer.parseInt(reportBox.getText());
        if (currentReportNumber < totalList.size()) {
            int temp = Integer.parseInt(reportBox.getText());
            temp++;
            reportBox.setText("" + temp);
            refreshChart();
        }
    }
    /**
     * decrement the report number
     *
     */
    @FXML
    private void reportDecrement() {
        int currentReportNumber = Integer.parseInt(reportBox.getText());
        if (currentReportNumber > 1) {
            int temp = Integer.parseInt(reportBox.getText());
            temp--;
            reportBox.setText("" + temp);
            refreshChart();
        }
    }

    /**
     * clear the line chart
     */
    private void clearChart() {
        lineChart.getData().remove(virusSeries);
        lineChart.getData().remove(contaminantSeries);
    }


    /**
     * redraw the line chart
     */
    private void refreshChart() {
        clearChart();
        int currentReportNumber = Integer.parseInt(reportBox.getText());
        graph(getReport(currentReportNumber));
    }
    /**
     * called when the user closes the app
     */
    @FXML
    private void handleCloseMenu() {
        System.exit(0);
    }


    /**
     *  go back to Manager app view.
     */
    @FXML
    private void backButtonPressed() {
        lineChart.getData().clear();
        application.gotoManagerApp();
    }

    /**
     * Get the purity report base on report number
     * @param reportNumber the report number in which the purity report to get
     */
    private WaterPurityReport getReport(int reportNumber) {
        WaterPurityReport report = null;
        for (WaterPurityReport element : totalList) {
            if (element.getReportNumber() == reportNumber) {
                report = element;
            }
        }
        return report;
    }
}
