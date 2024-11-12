import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
            String query = String.format("SELECT * from user WHERE username=\"%s\" AND password=\"%s\";", username, password);
            PreparedStatement statement = sqlConnection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } 
        catch (SQLException ex) {
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
            String query = String.format("INSERT INTO user (username, password) VALUES (\"%s\", \"%s\");", username, password);
            PreparedStatement statement = sqlConnection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery(); 
            return resultSet.next();
        } 
        catch (SQLException ex) {
            System.out.println("An error occurred trying to create your account. " + ex.getMessage());
            return false;
        } 
    }

    /**
    * Searches the user table for an account with the given username.
    *
    * @param username The username of the account we are looking for.
    * @return True if the account is found successfully, false otherwise.
    */
    public boolean checkUserExists(String username)
    {
        try {
            String query = String.format("SELECT * from user WHERE username=\"%s\";", username);
            PreparedStatement statement = sqlConnection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } 
        catch (SQLException ex) {
            System.out.println("An error occured trying to find the user. " + ex.getMessage());
            return false;
        }
    }
    
    public boolean addFriend(String username, String friend_username)
    {
        try {
            String query = String.format("INSERT INTO user_friend")
        } 
        catch (SQLException ex) {
            // TODO: handle exception
        }
    }

    
}
