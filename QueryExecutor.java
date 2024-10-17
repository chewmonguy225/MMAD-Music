import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class QueryExecutor {
    
    private Connection sqlConnection = null;

    public QueryExecutor(Connection sqlConnection) // Connection object passed as param in main method
    {
        this.sqlConnection = sqlConnection;
    }

    
    public boolean attemptLogin(String username, String password) throws SQLException
    {
        String query = String.format("select from users where username=%s and password=%s", username, password);
        Statement statement = sqlConnection.createStatement();
        int rowsReturned = statement.executeUpdate(query);
        return rowsReturned == 1;
    }


    public boolean createAccount(String username, String password) throws SQLException
    {
        String query = String.format("insert into users values %s, %s", username, password);//Query is incorrect/temporary
        Statement statement = sqlConnection.createStatement();
        int rowsInserted = statement.executeUpdate(query);
        return rowsInserted == 1;
    }

    
}
