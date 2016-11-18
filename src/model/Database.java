package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


/**
 * Implement Database class
 * Created by Bang on 10/6/16.
 */
public class Database {
    //declare connection, statement and resultSet objects
    private static Connection connection = null;
    private static PreparedStatement stmt = null;
    private static ResultSet rs = null;

    private static final Database INSTANCE = new Database();

    /**
     * Get the instance of database.
     * @return database
     */
    public static Database getDatabase() {
        return INSTANCE;
    }

    /**
     * Update information of an account to database.
     * @param fname first name of user.
     * @param lname last name of user.
     * @param email email address of user.
     * @param phone phone number of user.
     * @param address address of user.
     * @param username username of user.
     * @param password password of user.
     */
    public static void updateAccount(String fname, String lname,
                                     String email, String phone,
                                     String address, String username,
                                     String password) {
        PreparedStatement stmt = null;
        try {
            String sql = "UPDATE user SET fname = ?, lname = ?, email = ? , " 
                    + "phone = ?, address = ?, password = ? where username = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, fname);
            stmt.setString(2, lname);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, address);
            stmt.setString(6, password);
            stmt.setString(7, username);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
            }
        }
    }

    /**
     * Obtain user Account from database
     * @param username of user Account
     * @return account of that username.
     */
    public static Account getAccount(String username) {
        Account account = null;

        try {
            String sql = "SELECT * FROM user WHERE username= ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String userName = rs.getString("username");
                String firstName = rs.getString("fname");
                String lastName = rs.getString("lname");
                String email = rs.getString("email");
                String type = rs.getString("type");
                String phone = rs.getString("phone") + "";
                String address = rs.getString("address");

                account = new Account(username, firstName, lastName, email,
                        AccountType.valueOf(type), phone, address);
            }
            stmt.close();

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }

        return account;
    }

    /**
     * Obtain user Account from database
     * @param username of user Account
     * @return account of that username.
     */
    public static String getPassword(String username) {
        String password = null;
        try {
            String sql = "SELECT * FROM user WHERE username= ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            while (rs.next()) {
                password = rs.getString("password");
            }
            stmt.close();

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }

        return password;
    }

    /**
     * Check if user name matches password in database.
     * @param username to log in
     * @param password of that user.
     * @return if user name matches password in database
     */
    public static boolean login(String username, String password) {

        try {
            if (username != null && password != null) {
                String sql = "SELECT * FROM user WHERE username= ? AND "
                        + "password= ?";
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return false;
    }

    /**
     * Validate if username is available to register.
     * @param username to validate
     * @return if username is available.
     */
    public static boolean validateUsername(String username) {

        try {
            String sql = "SELECT `username` FROM `cwc`.user WHERE "
                    + "username = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            if (!rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
            }
        }
        return false;
    }



    /**
     * Added a user to user table in data base
     * @param username of user
     * @param password of user
     * @param fname first name.
     * @param lname last name.
     * @param email email address.
     * @param type type of user.
     */
    public static void addUser(String username, String password, String fname,
                               String lname, String email, AccountType type) {

        try {
            String query = "INSERT INTO `cwc`.`user` (`id`, `username`, "
                    + "`password`" + ", `fname`, `lname`, `email`, `type`)"
                    + " VALUES (null, ?, ?, ?, ?, ?, ?)";

            stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, fname);
            stmt.setString(4, lname);
            stmt.setString(5, email);
            stmt.setString(6, type.toString());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
            }
        }
    }

    /**
     * Added water source report to database.
     * @param username user who submit the report.
     * @param latitude of location report
     * @param longitude of location report
     * @param waterType type of water
     * @param waterCondition condition of water
     * @param date date of report
     */
    public static void addWaterSourceReport(String username,
                                            String latitude,
                                            String longitude,
                                            WaterType waterType,
                                            WaterCondition waterCondition,
                                            String date) {

        try {
            String query = "INSERT INTO watersourcereport "
                    + "(`ReportNumber`, `Username`, `Latitude`"
                    + ", `Longitude`, `WaterType`, `WaterCondition`, `Date`)"
                    + " VALUES (null, ?, ?, ?, ?, ?, ?)";

            stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, latitude);
            stmt.setString(3, longitude);
            stmt.setString(4, waterType.toString());
            stmt.setString(5, waterCondition.toString());
            stmt.setString(6, date);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
            }
        }
    }

    /**
     * Retrieve water source report from database.
     * @return list of water source report
     */
    public static List<WaterSourceReport> getWaterSourceReports() {
        String query = "SELECT * FROM watersourcereport";
        List<WaterSourceReport> sourceReportList = new ArrayList<>();

        try {
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int reportNumber = rs.getInt("ReportNumber");
                String username = rs.getString("Username");
                Double latitude = rs.getDouble("Latitude");
                Double longitude = rs.getDouble("Longitude");
                String type = rs.getString("WaterType");
                String condition = rs.getString("WaterCondition");
                String date = rs.getString("Date");
                WaterSourceReport report;
                report = new WaterSourceReport(reportNumber,
                        username, latitude, longitude, WaterType.valueOf(type),
                        WaterCondition.valueOf(condition), date);
                sourceReportList.add(report);
            }
            stmt.close();

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }

        return sourceReportList;
    }


    /**
     * Added water purity report to database.
     * @param username user who submit the report.
     * @param latitude of location report
     * @param longitude of location report
     * @param waterType type of water
     * @param waterCondition condition of water
     * @param date date of report
     * @param contaminant contaminantPPM of the water
     * @param virus virusPPM of the water
     */

    public static void addWaterPurityReport(String username,
                                            String latitude,
                                            String longitude,
                                            WaterType waterType,
                                            WaterCondition waterCondition,
                                            String date,
                                            String contaminant, String virus) {
        try {
            String query = "INSERT INTO waterpurityreport "
                    + "(`ReportNumber`, `Username`, `Latitude`"
                    + ", `Longitude`, `WaterType`, `WaterCondition`, `Date`, "
                    + "`ContaminantPPM`, `VirusPPM`)"
                    + " VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?)";

            stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, latitude);
            stmt.setString(3, longitude);
            stmt.setString(4, waterType.toString());
            stmt.setString(5, waterCondition.toString());
            stmt.setString(6, date);
            stmt.setString(7, contaminant);
            stmt.setString(8, virus);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
            }
        }
    }

    /**
     * Retrieve water source report from database.
     * @return list of water source report
     */
    public static List<WaterPurityReport> getWaterPurityReports() {
        String query = "SELECT * FROM waterpurityreport";
        List<WaterPurityReport> purityReportList = new ArrayList<>();

        try {
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int reportNumber = rs.getInt("ReportNumber");
                String username = rs.getString("Username");
                Double latitude = rs.getDouble("Latitude");
                Double longitude = rs.getDouble("Longitude");
                String type = rs.getString("WaterType");
                String condition = rs.getString("WaterCondition");
                String date = rs.getString("Date");
                Double contaminantPPM = rs.getDouble("ContaminantPPM");
                Double virusPPM = rs.getDouble("VirusPPM");
                WaterPurityReport report;
                report = new WaterPurityReport(reportNumber,
                        username, latitude, longitude,
                        WaterType.valueOf(type),
                        WaterCondition.valueOf(condition),
                        date, contaminantPPM, virusPPM);
                purityReportList.add(report);
            }
            stmt.close();

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }

        return purityReportList;
    }

    /**
     * Connect to database.
     * @return null
     */
    public static Database connectToDatabase() {

        //load jdbc driver for mysql database
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Unable to load Driver");
        }

        //Establish connection using DriverManager
        try {
            connection = DriverManager.getConnection("jdbc:mysql://cleanwatermap.ci13wa0hgqap.us-west-1.rds.amazonaws.com/cwc", "master", "mypassword");
        } catch (SQLException e) {
            System.out.println("Unable to connect to database");
        }

        return null;
    }
}
