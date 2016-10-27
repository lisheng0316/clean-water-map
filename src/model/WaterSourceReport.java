package model;

import java.io.Serializable;

/**
 * Created by Bang on 10/25/16.
 */
public class WaterSourceReport implements Serializable {

    private double longitude;
    private double latitude;
    private WaterType type;
    private String user;
    private WaterCondition condition;
    private String date;
    private int reportNumber;
    /**
     * Create water report with type and condition
     *
     * @param reportNumber report number
     * @param user user
     * @param latitude latitude
     * @param longitude longitude
     * @param type water type
     * @param condition water condition
     * @param date create date
     */
    public WaterSourceReport(int reportNumber, String user, double latitude, double longitude, WaterType type,
                             WaterCondition condition, String date) {

        this(reportNumber, user, latitude, longitude, date);
        this.type = type;
        this.condition = condition;

    }

    /**
     * Create water report
     *
     * @param reportNumber report number
     * @param user user
     * @param latitude latitude
     * @param longitude longitude
     * @param date create date
     */
    public WaterSourceReport(int reportNumber, String user, double latitude, double longitude, String date) {

        this.reportNumber = reportNumber;
        this.user = user;
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
    }


    /**
     * a method to get the report number
     * @return the report number
     */
    public int getReportNumber() {
        return reportNumber;
    }

    /**
     * a method to get the user who made the report
     * @return the creator of the report
     */
    public String getUser() {return user;}


    /**
     * a method to get the date of the report
     * @return the date of the report
     */
    public String getDate() {
        return date;
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
    public WaterType getType() {
        return type;
    }

    /**
     * a method to get the condition of the water
     * @return the condition of the water
     */
    public WaterCondition getCondition() {
        return condition;
    }


    /**
     * a method to get string representation of report title
     * @return the report title
     */
    public String toString(){
        return "[" + this.reportNumber + "] " + this.user + " " + this.date;

    };
}