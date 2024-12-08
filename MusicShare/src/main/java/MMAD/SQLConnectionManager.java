package MMAD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnectionManager extends DBConnectionManager {
    SQLCredentials theCredentials = new SQLCredentials();
    /**
     * Establishes a JDBC connection to a database. Initializes the connection
     * information variables.
     *
     * @throws ClassNotFoundException if the JDBC driver class cannot be found.
     * @throws SQLException           if an error occurs while establishing the
     *                                connection.
     */
    @Override
    public void establishConnection() throws ClassNotFoundException, SQLException {
        driverClassName = SQLCredentials.getDriverClassName();
        url = SQLCredentials.getURL();
        username = SQLCredentials.getUsername();
        password = SQLCredentials.getPassword();
        Class.forName(driverClassName);
        databaseConnection = DriverManager.getConnection(url, username, password);
    }

    /**
     * Closes the existing JDBC connection.
     *
     * @throws SQLException if an error occurs while closing the connection.
     */
    @Override
    public void closeConnection() throws SQLException {
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
