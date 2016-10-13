package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sean on 10/6/2016.
 * A class to hold the information for the water source report
 */
public class WaterSourceReport {
    private String longitude;
    private String latitude;
    private WaterSourceType type;
    private String user;
    private WaterSourceCondition condition;
    private String date;
    private String time;
    private int reportNumber;
    private static int totalReports;
    public static Map<String, WaterSourceReport> reportList = new HashMap<>();

    public WaterSourceReport(String theUser, String theDate,
                             String theTime, String longi, String lati,
                             WaterSourceType theType, WaterSourceCondition condi) {
        user = theUser;
        date = theDate;
        time = theTime;
        totalReports++;
        reportNumber = totalReports;
        condition = condi;
        type = theType;
        longitude = longi;
        latitude = lati;
    }

    /**
     * getter for the date of the report
     * @return the date of the report
     */
    public String getDate() {
        return date;
    }

    /**
     * getter for the time of the report
     * @return the time the report was placed.
     */
    public String getTime() {
        return time;
    }

    /**
     * getter for the report number
     * @return the report number
     */
    public int getReportNumber() {
        return reportNumber;
    }

    /**
     * getter for the longitude of the report
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * getter for the latitude of the report
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * getter for the water source type
     * @return the water source type
     */
    public WaterSourceType getType() {
        return type;
    }

    /**
     * getter for the condition of the water
     * @return the condition of the water
     */
    public WaterSourceCondition getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        String temp;
        temp = "Date: " + date
                + " Time: " + time
                + " User: " + user
                + " Type: " + type
                + " Condition: " + condition
                + " Report Number: " + reportNumber
                + " Longitude: " + longitude
                + " Latitude: " + latitude;
        return temp;
    }

    /**
     * getter for the backing map
     * @return the backing map
     */
    public Map<String, WaterSourceReport> getReportList() {
        return reportList;
    }
}
