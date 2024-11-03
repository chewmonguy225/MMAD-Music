import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class QueryExecutor {
    
    private Connection sqlConnection = null;

    /**
    * Constructs a new QueryExecutor object using the provided SQL connection.
    * @param sqlConnection The SQL connection to be used for executing queries.
    */
    public QueryExecutor(Connection sqlConnection) // Connection object passed as param in main method
    {
        this.sqlConnection = sqlConnection;
    }

    /**
    * Attempts to log in a user with the specified username and password.
    *
    * @param username The username to attempt login with.
    * @param password The password to attempt login with.
    * @return True if the login is successful, false otherwise.
    */
    public boolean attemptLogin(String username, String password) 
    {
        try {
            String query = String.format("SELECT * from users WHERE username=\"%s\" AND password=\"%s\";", username, password);
            Statement statement = sqlConnection.createStatement();
            int rowsReturned = statement.executeUpdate(query); 
            return rowsReturned == 1;
        } catch (SQLException ex) {
            System.out.println("An error occurred trying to log you in. " + ex.getMessage());
            return false;
        }
    }

    /**
    * Creates a new user account with the specified username and password.
    *
    * @param username The desired username for the new account.
    * @param password The desired password for the new account.
    * @return True if the account is created successfully, false otherwise.
    */
    public boolean createAccount(String username, String password)
    {
        try {
            String query = String.format("INSERT INTO users (username, password) VALUES (\"%s\", \"%s\");", username, password);
            Statement statement = sqlConnection.createStatement();
            int rowsInserted = statement.executeUpdate(query); 
            return rowsInserted == 1;
        } 
        catch (SQLException ex) {
            System.out.println("An error occurred trying to create your account. " + ex.getMessage());
            return false;
        } 
    }

    
}
