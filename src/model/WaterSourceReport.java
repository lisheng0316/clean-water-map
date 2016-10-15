package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;
import java.io.Serializable;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Sean on 10/6/2016.
 * A class to hold the information for the water source report
 */
public class WaterSourceReport implements  Serializable {
    private Location location;
    private double longitude;
    private double latitude;

    private WaterType type;
    private String user;
    private WaterCondition condition;
    private String date;
    private int reportNumber;



    public WaterSourceReport(int reportNumber, String user, double longitude, double latitude,  WaterType type,
                             WaterCondition condition, String date) {

        this.reportNumber = reportNumber;
        this.user = user;
        this.longitude = longitude;
        this.latitude = latitude;
        this.type = type;
        this.condition = condition;
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
        return "[" + this.reportNumber + "] " + date;
    };



}
