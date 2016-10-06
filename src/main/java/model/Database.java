package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by Bang on 10/6/16.
 */
public class Database {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/database_name";
    private static final String USER = "root";
    private static final String PASSWORD = "pass";



    public void connectToDatabase(){

        //declare connection, statement and resultSet objects
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        //load jdbc driver for mysql database
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e) {
            System.out.println("Unable to load Driver");
        }

        //Establish connection using DriverManager
        try {
            connection = DriverManager.getConnection(DRIVER, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Unable to connect to database");
        }

        //if connection is successfully established, create statement
        if(connection != null) {
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                System.out.println("Unable to create statement");
            }
        }

        //if statement is created successfully, execute query and get results
        if(statement != null) {
            try {
                resultSet = statement.executeQuery("SELECT * FROM orders");
            } catch (SQLException e) {
                System.out.println("Unable to create statement");
            }
        }

        //if resultset is received and is not empty,
        // iterate over resultset to get values
        if(resultSet != null) {
            try {
                while(resultSet.next()) {
                    System.out.println("Value in 1st column "+resultSet.getString(1));
                }
            } catch (SQLException e) {
                System.out.println("Unable to iterate over resultset");
            }
        }

        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
