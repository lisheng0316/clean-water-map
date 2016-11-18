package model;

/**
 * Water purity report class
 * Created by Bang on 10/25/16.
 */
public class WaterPurityReport extends WaterSourceReport {
    private final double virusPPM;
    private final double contaminantPPM;

    /**
     * Create water purity report
     *
     * @param reportNumber report number
     * @param user user
     * @param latitude latitude
     * @param longitude longitude
     * @param type water type
     * @param condition water condition
     * @param date create date
     * @param contaminantPPM contaminant PPM
     * @param virusPPM virus PPM
     */
    public WaterPurityReport(int reportNumber, String user,
                             double latitude, double longitude,
                             WaterType type,
                             WaterCondition condition,
                             String date,
                             double contaminantPPM,
                             double virusPPM) {
        super(reportNumber, user, latitude, longitude, type, condition, date);
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
    }


    /**
     * Get the virus PPM
     * @return the virus PPM
     */
    public double getVirusPPM() {
        return virusPPM;
    }
    /**
     * Get the contaminant PPM
     * @return the contaminant PPM
     */
    public double getContaminantPPM() {
        return contaminantPPM;
    }

    /**
     * a method to get string representation of report title
     * @return the report title
     */
    @Override
    public String toString() {
        return "" + this.getReportNumber();

    }
}