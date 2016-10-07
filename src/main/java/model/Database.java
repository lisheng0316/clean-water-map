package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

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


            //display
            rs = stmt.executeQuery("SELECT * FROM user");
            while (rs.next())
            {
                String foo = rs.getString(1);
                System.out.println("id:"+ foo);
            }
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
