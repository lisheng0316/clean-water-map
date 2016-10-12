package model;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;


/**
 * Created by Bang on 10/6/16.
 */
public class Database {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/schema";
    private static final String USER = "root";
    private static final String PASSWORD = "pass";

    //declare connection, statement and resultSet objects
    private static Connection connection = null;
    private static PreparedStatement stmt = null;
    private static ResultSet rs = null;

    public static void updateAccount(String fname,
                                     String lname,
                                     String email,
                                     String phone, String address, String username) {

        try {
            String SQL = "UPDATE user SET fname = ?, lname = ?, email = ? , phone = ?, address = ? where username = ?";
            stmt = connection.prepareStatement(SQL);
            stmt.setString (1, fname);
            stmt.setString (2, lname);
            stmt.setString (3, email);
            stmt.setString (4, phone);
            stmt.setString (5, address);
            stmt.setString (6, username);
            stmt.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally {
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

    public static Account getAccount(String username) {
        String query = "SELECT * FROM user";
        Account account = null;

        try {
            String SQL = "SELECT * FROM user WHERE username= ?";
            stmt = connection.prepareStatement(SQL);
            stmt.setString(1,username);
            rs = stmt.executeQuery();

            while (rs.next())
            {
                String username_ = rs.getString("username");
                String firstName = rs.getString("fname");
                String lastName = rs.getString("lname");
                String email = rs.getString("email");
                String type = rs.getString("type");
                String phone = rs.getString("phone") + "";
                String address = rs.getString("address");

                account = new Account(username, firstName, lastName, email, AccountType.valueOf(type), phone, address);
            }
            stmt.close();

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }

        return account;
    }

    public static boolean login(String username, String password) {

        try {
            if (username != null && password != null) {
                String SQL = "SELECT * FROM user WHERE username= ? AND password= ?";
                stmt = connection.prepareStatement(SQL);
                stmt.setString(1,username);
                stmt.setString(2,password);
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


    public static boolean validateUsername(String username) {

        try {
            String SQL = "SELECT `username` FROM `schema`.user WHERE username = ?";
            stmt = connection.prepareStatement(SQL);
            stmt.setString(1,username);
            rs = stmt.executeQuery();

            if (!rs.next()) {
                return true;
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getErrorCode());
        }
        finally {
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


    public static void addUser(String username, String password, String fname
            , String lname, String email, AccountType type) {

        try {
            String query = "INSERT INTO `schema`.`user` (`id`, `username`, `password`" +
                    ", `fname`, `lname`, `email`, `type`)"
                    + " VALUES (null, ?, ?, ?, ?, ?, ?)";

            stmt = connection.prepareStatement(query);
            stmt.setString (1, username);
            stmt.setString (2, password);
            stmt.setString (3, fname);
            stmt.setString (4, lname);
            stmt.setString (5, email);
            stmt.setString (6, type.toString());
            stmt.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally {
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

    public static void addWaterSourceReport(int reportNumber, String fname ,String lname, String longitude
            , String latitude, WaterSourceType waterType, WaterSourceCondition waterCondition, Timestamp date) {

        try {
            String query = "INSERT INTO `schema`.`WaterSourceReport` (`ReportNumber`, `Fname`,  `Lname`, `Longitude`" +
                    ", `Latitude`, `WaterType`, `WaterCondition`, `CreatedDate`)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            stmt = connection.prepareStatement(query);
            stmt.setInt (1, reportNumber);
            stmt.setString (2, fname);
            stmt.setString (3, lname);
            stmt.setString (4, longitude);
            stmt.setString (5, latitude);
            stmt.setString (6, waterType.toString());
            stmt.setString (7, waterCondition.toString());
            stmt.setString (8, date.toString());
            stmt.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally {
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



    public static Database connectToDatabase(){

        //load jdbc driver for mysql database
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e) {
            System.out.println("Unable to load Driver");
        }

        //Establish connection using DriverManager
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/schema?" +
                    "user=root&password=pass");
        } catch (SQLException e) {
            System.out.println("Unable to connect to database");
        }
        return null;
    }
}
