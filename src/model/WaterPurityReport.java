package model;

/**
 * Created by Bang on 10/25/16.
 */
public class WaterPurityReport extends WaterReport {
    private String virusPPM;
    private String contaminantPPM;
    public WaterPurityReport(int reportNumber, String user,
                             double latitude, double longitude,
                             WaterType type,
                             WaterCondition condition,
                             String date,
                             String contaminantPPM,
                             String virusPPM ) {

        super(reportNumber, user, latitude, longitude, type, condition, date);
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
    }
    public String getVirusPPM() {
        return virusPPM;
    }
    public String getContaminantPPM() {
        return contaminantPPM;
    }
}