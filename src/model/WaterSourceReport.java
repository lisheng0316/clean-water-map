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
public class WaterSourceReport extends WaterReport {
    /**
     * Create water source report
     *
     * @param reportNumber report number
     * @param user user
     * @param latitude latitude
     * @param longitude longitude
     * @param type water type
     * @param condition water condition
     * @param date create date\
     */
    public WaterSourceReport(int reportNumber, String user, double latitude, double longitude,  WaterType type,
                             WaterCondition condition, String date) {
        super(reportNumber, user, latitude, longitude, type, condition, date);
    }

}
