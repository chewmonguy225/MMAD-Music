import java.sql.Connection;
import java.sql.SQLException;

public abstract class DBConnectionManager {
    
    public void establishConnection() throws ClassNotFoundException, SQLException {}

    public void closeConnection() throws SQLException {}

    public Connection getDatabaseConnection() 
    {
        return null;
    }
}