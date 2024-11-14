import java.sql.Connection;
import java.sql.SQLException;

public class DBHandler {
    
    SQLConnectionManager connectionManager = null;
    Connection connection = null;
    QueryExecutor queryExecutor = null;


    /**
     * Constructor
     * 
     * Establishes the database connection and initializes the instance variables.
     */
    public DBHandler()
    {
        try {
            connectionManager = new SQLConnectionManager();
            connectionManager.establishConnection();
            connection = connectionManager.getConnectionObject();
            queryExecutor = new QueryExecutor(connection);
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Closes the connection to the database.
     * 
     * @return True if connection was closed succesfully, false otherwise.
     */
    public boolean closeConnection()
    {
        try {
            connectionManager.closeConnection();
            return true;
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
