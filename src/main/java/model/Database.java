package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet resultSet = null;


    public static void addUser(String id, String fname
            , String lname, String email, AccountType type
            , String phone, String address) {


        ResultSet rs = null;
        PreparedStatement stmt = null;

        try {
            String query = "INSERT INTO `schema`.`test` (`id`, `username`, `password`)"
                    + " VALUES (null, ?, ?)";

            stmt = connection.prepareStatement(query);
 //           String SQL = "INSERT INTO `schema`.`test` (`id`, `username`, `password`)" +
 //                   " VALUES (null, 'Chau', 'Phan');";
            //         String SQL = "DELETE FROM `schema`.`test` WHERE id = 3;";
            //       String SQL = "INSERT INTO `schema`.`test` (`id`, `username`, `password`) VALUES (null, 'Chau', 'Phan');";
            stmt.setString (1, id);
            stmt.setString   (2, email);
            stmt.executeUpdate();



            //display
            rs = stmt.executeQuery("SELECT * FROM test");
            while (rs.next())
            {
                String foo = rs.getString(1);
                System.out.println("id:"+ foo);
            }
            connection.close();
        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore

                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
                stmt = null;
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
