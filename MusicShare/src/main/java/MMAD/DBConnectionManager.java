package MMAD;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DBConnectionManager {

    protected Connection databaseConnection;
    protected String driverClassName;
    protected String url; 
    protected String username;
    protected  String password;
    
    public void establishConnection() throws ClassNotFoundException, SQLException {}

    public void closeConnection() throws SQLException {}

    // @return the Connection object
    public Connection getConnectionObject() 
    {
        return databaseConnection;
    }
}