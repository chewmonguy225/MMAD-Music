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
            int rowsAffected = statement.executeUpdate(); 
            return rowsAffected == 1;
        } 
        catch (SQLException ex) {
            return false;
        } 
    }


    /**
    * Searches the user table for an account with the given username.
    *
    * @param username The username of the account we are looking for.
    * @return True if the account exists, false otherwise.
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
            return false;
        }
    }

    
    /**
    * Adds a new friend relationship into the database.
    *
    * @param username The username of the user that is logged in.
    * @param friend_username The username of the friend to be added.
    * @return True if the friend is added succesfully, false otherwise.
    */
    public boolean addFriend(String username, String friend_username)
    {
        try {
            String query = String.format("INSERT INTO user_friend (username, friend_username) VALUES (\"%s\", \"%s\");", username, friend_username);
            PreparedStatement statement = sqlConnection.prepareStatement(query);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } 
        catch (SQLException ex) {
            return false;
        }
    }


    /**
    * Checks if a song already exists in the database.
    *
    * @param name The title/name of the song.
    * @param artist The name of the song's artist.
    * @param album The title/name of the album the song is in.
    * @return True if the song was found in the database, false otherwise.
    */
    public boolean checkSongInDB(String name, String artist, String album)
    {
        try {
            String query = String.format("SELECT * FROM song WHERE name=\"%s\" AND artist=\"%s\" AND album=\"%s\";", name, artist, album);
            PreparedStatement statement = sqlConnection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } 
        catch (SQLException ex) {
            return false;
        }
    }


    /**
    * Checks if an album already exists in the database.
    *
    * @param name The title/name of the album.
    * @param artist The name of the album's artist.
    * @return True if the album was found in the database, false otherwise.
    */
    public boolean checkAlbumInDB(String name, String artist)
    {
        try {
            String query = String.format("SELECT * FROM album WHERE name=\"%s\" AND artist=\"%s\";", name, artist);
            PreparedStatement statement = sqlConnection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } 
        catch (SQLException ex) {
            return false;
        }
    }


    /**
    * Checks if an artist already exists in the database.
    *
    * @param name The name of the artist.
    * @return True if the artist was found in the database, false otherwise.
    */
    public boolean checkArtistInDB(String name)
    {
        try {
            String query = String.format("SELECT * FROM artist WHERE name=\"%s\";", name);
            PreparedStatement statement = sqlConnection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } 
        catch (SQLException ex) {
            return false;
        }
    }


    /**
    * Inserts a new song in the song table of the database.
    *
    * @param name The title/name of the song.
    * @param artist The name of the song's artist.
    * @param album The title/name of the album the song is in.
    * @return True if the song was inserted succesfully, false otherwise.
    */
    public boolean addSongToDB(String name, String artist, String album)
    {
        try {
            String query = String.format("INSERT INTO song (name, artist, album) VALUES (\"%s\", \"%s\", \"%s\");", name, artist, album);
            PreparedStatement statement = sqlConnection.prepareStatement(query);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } 
        catch (SQLException ex){
            return false;
        }
    }


    /**
    * Inserts a new album in the album table of the database.
    *
    * @param name The title/name of the album.
    * @param artist The name of the album's artist.
    * @return True if the album was inserted succesfully, false otherwise.
    */
    public boolean addAlbumToDB(String name, String artist)
    {
        try {
            String query = String.format("INSERT INTO album (name, artist) VALUES (\"%s\", \"%s\");", name, artist);
            PreparedStatement statement = sqlConnection.prepareStatement(query);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } 
        catch (SQLException ex){
            return false;
        }
    }


    /**
    * Inserts a new artist in the artist table of the database.
    *
    * @param name The name of the artist.
    * @return True if the artist was inserted succesfully, false otherwise.
    */
    public boolean addArtistToDB(String name)
    {
        try {
            String query = String.format("INSERT INTO artist (name) VALUES (\"%s\");", name);
            PreparedStatement statement = sqlConnection.prepareStatement(query);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } 
        catch (SQLException ex){
            return false;
        }
    }

    
}
