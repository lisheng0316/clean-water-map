package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 */
public class Facade {

    private static final Facade instance = new Facade();
    public static Facade getInstance() { return instance; }

    Database db = Database.getDatabase();
    //this is our simple model
    private List<WaterSourceReport> locations = db.getWaterSourceReports();



    private Facade() {
//        //make up some dummy data
        for (WaterSourceReport w: locations) {
//            WaterSourceReport pin = new WaterSourceReport(w.getReportNumber(), w.getUser(),
//                w.getLongitude(),
//                w.getLatitude(),
//                 w.getType(),
//                    w.getCondition(),
//                    w.getDate());
            locations.add(w);
        }
    }

    public List<WaterSourceReport> getLocations() { return locations; }

//    public void saveModelToText(File file) {
//        PersistenceManager pm = new PersistenceManager(locations);
//        pm.saveToText(file);
//    }
//
//    public void loadModelFromText(File file) {
//        PersistenceManager pm = new PersistenceManager(locations);
//        pm.loadFromText(file);
//    }
//
//    public void saveModelToBinary(File file) {
//        PersistenceManager pm = new PersistenceManager(locations);
//        pm.saveToBinary(file);
//    }
//
//    public void loadModelFromBinary(File file) {
//        PersistenceManager pm = new PersistenceManager(locations);
//        pm.loadFromBinary(file);
//    }
//
//    public void loadModelFromJson(File file) {
//        PersistenceManager pm = new PersistenceManager(locations);
//        pm.loadFromJsonfile(file);
//    }
//
//    public void saveModelToJson(File file) {
//        PersistenceManager pm = new PersistenceManager(locations);
//        pm.saveToJson(file);
//    }

//    public void addLocations() {
//        locations.add(new Location(34.043, -88.043, "New Marker", "Some new data"));
//    }
}
