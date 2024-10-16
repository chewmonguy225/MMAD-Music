package main;

import java.sql.*;

public class DBConnectionManager {

    final private String driverClassName = "com.mysql.cj.jdbc.Driver";
    final private String url = "jdbc:mysql://localhost:3306/music_share_db";
    final private String username = "root";
    final private String password = "dbrootpass123";
    private Connection databaseConnection = null;
    
    /**
     * Establishes a JDBC connection to a database.
     *
     * <p>This method loads the specified JDBC driver class, then creates a connection
     * to the database using the provided URL, username, and password.
     *
     * @throws ClassNotFoundException if the JDBC driver class cannot be found.
     * @throws SQLException if an error occurs while establishing the connection.
     */
    public void getConnection() 
    		throws ClassNotFoundException, SQLException
    {
    	// Load driver class
        Class.forName(driverClassName);
        
        // Obtain a connection
        databaseConnection = DriverManager.getConnection(url, username, password);
    }   
    
    /**
     * Closes the existing JDBC connection.
     *
     * <p>This method closes the database connection, releasing any associated resources.
     *
     * @throws SQLException if an error occurs while closing the connection.
     */
    public void closeConnection() 
    		throws SQLException
    {
    	databaseConnection.close();
    }
}
