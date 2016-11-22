package test;//import org.junit.After;
//import org.junit.Assert.*;
import model.AccountType;
import model.WaterCondition;
import model.WaterType;
import org.junit.Before;
import org.junit.Test;
//import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;





/**
 * Database test class
 * Created by Sheng on 11/10/16.
 */
public class DBmethodTest {
    private String id = null;
    private String password = null;
    private String fname = null;
    private String lname = null;
    private String email = null;
    private final String phone = null;
    private final String address = null;
    private AccountType accountType = null;

    private String latitude;
    private String longitude;
    private WaterType waterType;
    private WaterCondition waterCondition;
    private String date;


//    @Before
//    public void setUp() {
//
//        Database db = Database.getDatabase();
//        Database.connectToDatabase();
//        id = "sli471";
//        password = "123456";
//        fname = "Sheng";
//        lname = "Li";
//        email = "sli@gmail.com";
//        accountType = AccountType.User;
//        Account account = new Account(id, fname, lname, email, accountType,
//                phone, address);
//
//    }
//    /*
//    public static void updateAccount(String fname, String lname,
//                                     String email, String phone,
//                                     String address, String username,
//                                     String password) {
//    */
//    @Test
//    public void testUpdateAccount() {
//        setUp();
//        Database.updateAccount("Sean", "Mc", "sm@hotmail.com", "8005990232",
//                "North Ave Dinning", id, "654321");
//        Account newAccount = Database.getAccount(id);
//        /*
//        id = "sli471";
//        password = "123456";
//        fname = "Sheng";
//        lname = "Li";
//        email = "sli@gmail.com";
//        */
//        assertEquals(newAccount.getFName(), "Sean");
//        assertEquals(newAccount.getLName(), "Mc");
//        assertEquals(newAccount.getEmail(), "sm@hotmail.com");
//        assertEquals(newAccount.getPhone(), "8005990232");
//        assertEquals(newAccount.getAddress(), "North Ave Dinning");
//
//
//    }
//    /*
//    public static void addUser(String username, String password, String fname,
//       String lname, String email, AccountType type) {
//    */
//    @Test
//    public void testAddUser() {
//        setUp();
//        id = "little";
//        password = "qweryu";
//        fname = "Bob";
//        lname = "Hanson";
//        email = "bh@gmail.com";
//        accountType = AccountType.Manager;
//        /*
//        Account newAcount = new Account(id, fname, lname, email, accountType,
//        phone, address);
//        */
//        assertNull(Database.getAccount("panda"));
//
//        Database.addUser(id, password, fname, lname, email, accountType);
//        assertEquals(Database.getAccount(id).getId(), "little");
//        assertEquals(Database.getAccount(id).getFName(), "Bob");
//        assertEquals(Database.getAccount(id).getLName(), "Hanson");
//        assertEquals(Database.getAccount(id).getEmail(), "bh@gmail.com");
//
//    }
//
//
//
//
//    //addWaterSourceReport(String username, String latitude,
//    // String longitude, WaterType waterType,
//    // WaterCondition waterCondition, String date) {
//
//    @Test
//    public void testAddWaterSourceReport() {
//        setUp();
//        id = "little";
//        latitude = "23.1023";
//        longitude = "-83.2341";
//        waterType = WaterType.Lake;
//        waterCondition = WaterCondition.Treatable;
//
//
//        int i = Database.getWaterSourceReports().size();
//        assertFalse(Database.getWaterSourceReports().isEmpty());
//
//        Database.addWaterSourceReport(id, latitude, longitude, waterType,
//                waterCondition, "testDate");
//        assertEquals(Database.getWaterSourceReports().size(), i + 1);
//
//    }
//
//
//
//    @Test
//    public void testGetWaterSourceReports() {
//        setUp();
//        id = "little";
//        latitude = "23.1023";
//        longitude = "-83.2341";
//        waterType = WaterType.Lake;
//        waterCondition = WaterCondition.Treatable;
//
//        int i = Database.getWaterSourceReports().size();
//        assertEquals(Database.getWaterSourceReports().size(), i);
//
//        Database.addWaterSourceReport(id, latitude, longitude, waterType,
//                waterCondition, date);
//        assertEquals(Database.getWaterSourceReports().size(), i++);
//
//    }
//    /*
//    public static void addWaterPurityReport(String username , String latitude
//                                            , String longitude
//                                            , WaterType waterType
//                                            , WaterCondition waterCondition
//                                            , String date, String contaminant
//                                            , String virus) {
//    */
//    @Test
//    public void testAddWaterPurityReport() {
//        setUp();
//        id = "little";
//        latitude = "23.1023";
//        longitude = "-83.2341";
//        waterType = WaterType.Lake;
//        waterCondition = WaterCondition.Treatable;
//        date = "9999-12-31";
//        String contaminant = "999.99";
//        String virus = "999.99";
//
//        int i = Database.getWaterPurityReports().size();
//        assertFalse(Database.getWaterPurityReports().isEmpty());
//
//        Database.addWaterPurityReport(id, latitude, longitude, waterType,
//                waterCondition, date, contaminant, virus);
//        assertEquals(Database.getWaterPurityReports().size(), i + 1);
//
//    }

}
