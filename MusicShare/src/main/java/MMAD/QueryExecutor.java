package MMAD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class QueryExecutor {

    private DBConnectionManager connectionManager;
    private Connection sqlConnection;

    /**
    * Initializes the connectionManager and sqlConnection objects that allow query execution
    *
    * @param sqlConnection The SQL connection to be used for executing queries.
    */
    public QueryExecutor() // Connection object passed as param in main method
    {
        try {
            connectionManager = new SQLConnectionManager();
            connectionManager.establishConnection();
            sqlConnection = connectionManager.getConnectionObject();
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
    * Attempts to log in a user with the specified username and password.
    *
    * @param username The username to attempt login with.
    * @param password The password to attempt login with.
    * @return True if the login is successful, false otherwise.
    */
    public boolean attemptLogin(String username, String password) 
    {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("SELECT * from user WHERE username= ? AND password= ?;");
            statement.setString(1, username);
            statement.setString(2, password);
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
            PreparedStatement statement = sqlConnection.prepareStatement("INSERT INTO user (username, password) VALUES (?, ?);");
            statement.setString(1, username);
            statement.setString(2, password);
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
            PreparedStatement statement = sqlConnection.prepareStatement("SELECT * from user WHERE username= ?;");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } 
        catch (SQLException ex) {
            return false;
        }
    }

    
    /**
     * Searches the user_friend table to see if the friend has already been added.
     * 
     * @param username The user's username.
     * @param friendUsername The friend's username.
     * @return True if the friend has already been added, false otherwise.
     */
    public boolean checkAlreadyAFriend(String username, String friendUsername)
    {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("SELECT * from user_friend WHERE username= ? AND friend_username= ?;");
            statement.setString(1, username);
            statement.setString(2, friendUsername);
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
    * @param friendUsername The username of the friend to be added.
    * @return True if the friend is added succesfully, false otherwise.
    */
    public boolean addFriend(String username, String friendUsername)
    {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("INSERT INTO user_friend (username, friend_username) VALUES (?, ?);");
            statement.setString(1, username);
            statement.setString(2, friendUsername);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }


    /**
    * Checks if a song already exists in the database.
    *
    * @param songID The song's id in the database.
    * @return True if the song was found in the database, false otherwise.
    */
    public boolean checkSongInDB(Song song)
    {
        try {
            if(song.getID() >= 0){
            PreparedStatement statement = sqlConnection.prepareStatement("SELECT * FROM song WHERE id= ?;");
            statement.setInt(1, song.getID());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
            }
            else{
                return false;
            }
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
    public boolean checkAlbumInDB(Album album)
    {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("SELECT * FROM album WHERE name= ? AND artist= ?;");
            statement.setString(1, album.getName());
            statement.setString(2, album.getArtist().getName());
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
    public boolean checkArtistInDB(Artist artist)
    {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("SELECT * FROM artist WHERE name= ?;");
            statement.setString(1, artist.getName());
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
    * @param song The song object to be added into the database
    * @param sourceID The song's ID given from the API source (Spotify)
    * @return The song's database id if the song was inserted succesfully, -1 if there was an error.
    */
    public int addSongToDB(Song song)
    {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("INSERT INTO song (source_id, name, artist, album) VALUES (?, ?, ?, ?);");
            statement.setString(1, song.getSourceID());
            statement.setString(2, song.getName());
            statement.setString(3, song.getArtist().getName());
            statement.setString(4, song.getAlbum().getName());
            int rowsAffected = statement.executeUpdate();

            PreparedStatement statement2 = sqlConnection.prepareStatement("SELECT id FROM song WHERE source_id= ?;");
            statement2.setString(1, song.getSourceID());
            ResultSet resultSet = statement2.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("id");
            return id;
        } 
        catch (SQLException ex){
            return -1;
        }
    }

    public int getSongID (Song song){
        try{
            PreparedStatement statement = sqlConnection.prepareStatement("SELECT * FROM song WHERE source_id=?;");
            statement.setString(1, song.getSourceID());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("id");
            } else{
                return -1;
            }
            

        } catch (SQLException e){
            return -1;
        }
        
    }


    /**
    * Inserts a new album in the album table of the database.
    *
    * @param album The album object to be added into the database
    * @param sourceID The song's ID given from the API source (Spotify)
    * @return True if the album was inserted succesfully, false otherwise.
    */
    public boolean addAlbumToDB(Album album)
    {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("INSERT INTO album (source_id, name, artist) VALUES (?, ?, ?);");
            statement.setString(1, album.getSourceID());
            statement.setString(2, album.getName());
            statement.setString(3, album.getArtist().getName());
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
    * @param artist The artis to be added into the database.
    * @return True if the artist was inserted succesfully, false otherwise.
    */
    public boolean addArtistToDB(Artist artist)
    {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("INSERT INTO artist (source_id, name) VALUES (?, ?);");
            statement.setString(1, artist.getSourceID());
            statement.setString(2, artist.getName());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } 
        catch (SQLException ex){
            return false;
        }
    }


    /**
    * Checks if a song is already in the user's playlist.
    *
    * @param username The user's username.
    * @param songID The song's id in the database. 
    * @return True if the song was found in the playlist, false otherwise.
    */
    public boolean checkSongInPlaylist(String username, int songID)
    {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("SELECT * FROM playlist_song WHERE username= ? AND songID= ?;");
            statement.setString(1, username);
            statement.setInt(2, songID);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } 
        catch (SQLException ex) {
            return false;
        }
    }


    /**
     * Queries the database to insert a now row in the playlist table. 
     * 
     * @param username The user's username.
     * @param SongID The database id of the song.
     * @return True if the song is added to the playlist succesfully, false otherwise.
     */
    public boolean addSongToPlaylist(Login login, Song song)
    {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("INSERT INTO playlist_song (username, song_id) VALUES (?, ?);");
            statement.setString(1, login.getUsername());
            statement.setInt(2, song.getID());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } 
        catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<Integer> getPlaylist(Login login)//This should create an arraylist of all the song IDs in the user's playlist and return the list
    {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("SELECT * FROM playlist_song WHERE username= ?");
            statement.setString(1, login.getUsername());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){

                return resultSet.getInt("id");
            } else{
                return -1;
            }
        } 
        catch (SQLException ex) {
            return false;
        }
    }

    /**
     * Removes a song from the user's playlist
     * 
     * @param username The user's username
     * @param songID The song's database id
     * @return True if the song is removed succesfully, false otherwise.
     */
    public boolean removeSongFromPlaylist(Login login, Song song)
    {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("DELETE FROM playlist_song WHERE username= ? AND song_id= ?;");
            statement.setString(1, login.getUsername());
            statement.setInt(2, song.getID());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } 
        catch (SQLException ex) {
            return false;
        }
    }


    /**
     * Deletes all songs from the user's playlist.
     * 
     * @param username The user's username.
     * @return True is the playlist was cleared succesfully, false if an error occurs.
     */
    public boolean clearPlaylist(Login login)
    {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("DELETE FROM playlist_song WHERE username= ?;");
            statement.setString(1, login.getUsername());
            statement.executeUpdate();//something wrong with this statement
            return true;
        } 
        catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    
}
