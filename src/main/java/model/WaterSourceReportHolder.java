package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sean on 10/11/2016.
 * A class to hold the Water Source Reports
 */
public class WaterSourceReportHolder {
    public static Map<String, WaterSourceReport> reports = new HashMap<>();

    /**
     * a method to place a report in the hashmap
     * @param nameAndDate the name and date of the user that created the report
     * @param report the report to be inserted into the hashmap
     */
    public void addReport(String nameAndDate, WaterSourceReport report) {
        reports.put(nameAndDate, report);
    }
}
