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


    /**
    * Adds a new friend relationship into the database after confirming that the user exists.
    *
    * @param username The username of the user that is logged in.
    * @param friend_username The username of the friend to be added.
    * @return True if the friend is added succesfully, false otherwise.
    */
    public boolean addFriend(String username, String friendUsername)
    {
        try {
            if(queryExecutor.checkUserExists(friendUsername)){
                return false;
            }
            else {
                queryExecutor.addFriend(username, friendUsername);
                return true;
            }
        } 
        catch (Exception e) {
            return false;
        }
    }


    public boolean addSongToDB(String name, String artist, String album)
    {
        try {
            if(queryExecutor.checkSongInDB(name, artist, album)){
                return true; 
            }
            else {
                queryExecutor.addSongToDB(name, artist, album);
                return true;
            }
        } 
        catch (Exception e) {
            return false;
        }
    }
}
