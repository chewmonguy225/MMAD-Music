import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class QueryExecutor {
    
    private Connection sqlConnection = null;

    public QueryExecutor(Connection sqlConnection) // Connection object passed as param in main method
    {
        this.sqlConnection = sqlConnection;
    }

    public boolean attemptLogin(String username, String password) 
                throws SQLException
    {
        String query = String.format("select from users where username=%s and password=%s", username, password);
        Statement statement = sqlConnection.createStatement();
        int count = statement.executeUpdate(query);
        return count == 1;
    }

}
