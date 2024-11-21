import java.sql.Connection;
import java.sql.SQLException;

public abstract class DBConnectionManager {
    
    public void establishConnection() throws ClassNotFoundException, SQLException {}

    public void closeConnection() throws SQLException {}

    // @return the Connection object
    public Connection getConnectionObject() 
    {
        return null;
    }
}