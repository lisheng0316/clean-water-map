package model;

import java.util.Date;

/**
 * Created by Sean on 10/6/2016.
 * A class to hold the information for the water source report
 */
public class WaterSourceReport {
    private double longitude;
    private double latitude;
    private WaterSourceType type;
    private String user;
    private WaterSourceCondition condition;
    private Date date;
    private int reportNumber;
    private static int totalReports;

    public WaterSourceReport() {
        date = new Date();
        totalReports++;
        reportNumber = totalReports;
    }

    /**
     * a method to get the date of the report
     * @return the date of the report
     */
    public Date getDate() {
        return date;
    }

    /**
     * a method to get the report number
     * @return the report number
     */
    public int getReportNumber() {
        return reportNumber;
    }

    /**
     * a method to get the longitude of the report
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * a method to ge the latitude of the report
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * a method to get the water source type
     * @return the water source type
     */
    public WaterSourceType getType() {
        return type;
    }

    /**
     * a method to get the condition of the water
     * @return the condition of the water
     */
    public WaterSourceCondition getCondition() {
        return condition;
    }
}
