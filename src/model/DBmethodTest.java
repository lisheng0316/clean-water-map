package model;
import org.junit.After;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;





/**
 * Created by Sheng on 11/10/16.
 */
public class DBmethodTest {
    private Database db = null;
    private Account account = null;
    private String id = null;
    private String password = null;
    private String fname = null;
    private String lname = null;
    private String email = null;
    private String phone = null;
    private String address = null;
    private AccountType accountType= null;

    private String latitude;
    private String longitude;
    private WaterType waterType;
    private WaterCondition waterCondition;
    private String date;
    private String contaminant;

    private String virus;



    @Before
    public void setUp() {

        db = Database.getDatabase();
        db.connectToDatabase();
        id = "sli471";
        password = "123456";
        fname = "Sheng";
        lname = "Li";
        email = "sli@gmail.com";
        accountType = AccountType.User;
        account = new Account(id, fname, lname, email, accountType, phone, address);

    }

    @Test
    public void testUpdateAccount() {
        setUp();
        Database.updateAccount("Sean", "Mc", "sm@hotmail.com", "8005990232", "North Ave Dinning", id);
        Account newAccount = Database.getAccount(id);
//        id = "sli471";
//        password = "123456";
//        fname = "Sheng";
//        lname = "Li";
//        email = "sli@gmail.com";

        assertEquals(newAccount.getFname(), "Sean");
        assertEquals(newAccount.getLname(), "Mc");
        assertEquals(newAccount.getEmail(), "sm@hotmail.com");
        assertEquals(newAccount.getPhone(), "8005990232");
        assertEquals(newAccount.getAddress(), "North Ave Dinning");


    }

//    public static void addUser(String username, String password, String fname,
//                               String lname, String email, AccountType type) {
//
//
    @Test
    public void testAddUser() {
        setUp();
        id = "little";
        password = "qweryu";
        fname = "Bob";
        lname = "Hanson";
        email = "bh@gmail.com";
        accountType = AccountType.Manager;
//        Account newAcount = new Account(id, fname, lname, email, accountType, phone, address);

        assertNull(db.getAccount("panda"));

        Database.addUser(id, password, fname, lname, email, accountType);
        assertEquals(db.getAccount(id).getId(), "little");
        assertEquals(db.getAccount(id).getFName(), "Bob");
        assertEquals(db.getAccount(id).getLName(), "Hanson");
        assertEquals(db.getAccount(id).getEmail(), "bh@gmail.com");

    }




    //addWaterSourceReport(String username, String latitude
    //       , String longitude, WaterType waterType, WaterCondition waterCondition, String date) {

        @Test
        public void testAddWaterSourceReport() {
            setUp();
            id = "little";
            latitude = "23.1023";
            longitude = "-83.2341";
            waterType = WaterType.Lake;
            waterCondition = WaterCondition.Treatable;


            int i = db.getWaterSourceReports().size();
            assertFalse(db.getWaterSourceReports().isEmpty());

            db.addWaterSourceReport(id, latitude, longitude, waterType, waterCondition, "testDate");
            assertEquals(db.getWaterSourceReports().size(), i + 1);

        }



        @Test
        public void testGetWaterSourceReports() {
            setUp();
            id = "little";
            latitude = "23.1023";
            longitude = "-83.2341";
            waterType = WaterType.Lake;
            waterCondition = WaterCondition.Treatable;

            int i = db.getWaterSourceReports().size();
            assertEquals(db.getWaterSourceReports().size(), i);

            db.addWaterSourceReport(id, latitude, longitude, waterType, waterCondition, date);

            assertEquals(db.getWaterSourceReports().size(), i++);

    }

//    public static void addWaterPurityReport(String username , String latitude
//            , String longitude
//            , WaterType waterType
//            , WaterCondition waterCondition
//            , String date, String contaminant, String virus) {

    @Test
    public void testAddWaterPurityReport() {
        setUp();
        id = "little";
        latitude = "23.1023";
        longitude = "-83.2341";
        waterType = WaterType.Lake;
        waterCondition = WaterCondition.Treatable;
        date = "9999-12-31";
        contaminant = "999.99";
        virus = "999.99";

        int i = db.getWaterPurityReports().size();
        assertFalse(db.getWaterPurityReports().isEmpty());

        db.addWaterPurityReport(id, latitude, longitude, waterType, waterCondition, date, contaminant, virus);
        assertEquals(db.getWaterPurityReports().size(), i + 1);

    }

}
