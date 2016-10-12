package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sean on 10/11/2016.
 * A class to hold the Water Source Reports
 */
public class WaterSourceReportHolder {
    public static Map<Integer, WaterSourceReport> reports = new HashMap<>();

    /**
     * a method to place a report in the hashmap
     * @param reportNumber the number of the created report
     * @param report the report to be inserted into the hashmap
     */
    public static void addReport(Integer reportNumber, WaterSourceReport report) {
        reports.put(reportNumber, report);
        //WaterSourceReport.reportList.put(nameAndDate, report);
    }


}
