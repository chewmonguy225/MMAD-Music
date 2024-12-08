package MMAD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnectionManager extends DBConnectionManager {


    /**
     * Establishes a JDBC connection to a database. Initializes the connection information variables.
     *
     * @throws ClassNotFoundException if the JDBC driver class cannot be found.
     * @throws SQLException if an error occurs while establishing the connection.
     */
    @Override
    public void establishConnection() throws ClassNotFoundException, SQLException
    {
        driverClassName = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/music_share_db"; // AWS ENDPOINT: mmad-music.czm0mk46ipoz.us-east-2.rds.amazonaws.com
        username = "root";
        password = "password";

        Class.forName(driverClassName);
        databaseConnection = DriverManager.getConnection(url, username, password);
    }   
    
    /**
     * Closes the existing JDBC connection.
     *
     * @throws SQLException if an error occurs while closing the connection.
     */
    @Override
    public void closeConnection() throws SQLException
    {
    	databaseConnection.close();
    }

    /**
     * @return Connection object
     */
    @Override
    public Connection getConnectionObject() {
        return databaseConnection;
    }
}
