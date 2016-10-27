package model;

/**
 * Created by Bang on 10/25/16.
 */
public class WaterPurityReport extends WaterSourceReport {
    private String virusPPM;
    private String contaminantPPM;
    private OverallCondition overallCondition;

    /**
     * Create water purity report
     *
     * @param reportNumber report number
     * @param user user
     * @param latitude latitude
     * @param longitude longitude
     * @param date create date
     * @param contaminantPPM contaminant PPM
     * @param virusPPM virus PPM
     */
    public WaterPurityReport(int reportNumber, String user,
                                   double latitude, double longitude,
                                   String date,
                                   OverallCondition overallCondition,
                                   String contaminantPPM,
                                   String virusPPM ) {

        super(reportNumber, user, latitude, longitude, date);
        this.overallCondition = overallCondition;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;

    }


    /**
     * Get the overall condition of water
     * @return overall condition type
     */
    public OverallCondition getOverallCondition() {
        return overallCondition;
    }
    /**
     * Get the virus PPM
     * @return the virus PPM
     */
    public String getVirusPPM() {
        return virusPPM;
    }
    /**
     * Get the contaminant PPM
     * @return the contaminant PPM
     */
    public String getContaminantPPM() {
        return contaminantPPM;
    }
}