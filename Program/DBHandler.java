import java.sql.Connection;
import java.sql.SQLException;

public class DBHandler {
    
    private SQLConnectionManager connectionManager = null;
    private Connection connection = null;
    private QueryExecutor queryExecutor = null;


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
    * @param name The username of the user that is logged in.
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


    /**
    * Adds a song into the database song table.
    * First checks if the song is already in the database. If it is, just return true and do not execute any further queries.
    *
     * @param name The title/name of the song.
    * @param artist The name of the song's artist.
    * @param album The title/name of the album the song is in.
    * @return True if the song is added succesfully (or was already in the db), false otherwise.
    */
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


    /**
    * Adds an album into the database album table.
    * First checks if the album is already in the database. If it is, just return true and do not execute any further queries.
    *
    * @param name The title/name of the album.
    * @param artist The name of the album's artist.
    * @return True if the album is added succesfully (or was already in database), false otherwise.
    */
    public boolean addAlbumToDB(String name, String artist)
    {
        try {
            if(queryExecutor.checkAlbumInDB(name, artist)){
                return true; 
            }
            else {
                queryExecutor.addAlbumToDB(name, artist);
                return true;
            }
        } 
        catch (Exception e) {
            return false;
        }
    }


    /**
    * Adds an artist into the database album table.
    * First checks if the artist is already in the database. If it is, just return true and do not execute any further queries.
    *
    * @param name The name of the artist.
    * @return True if the artist is added succesfully (or was already in database), false otherwise.
    */
    public boolean addArtistToDB(String name)
    {
        try {
            if(queryExecutor.checkArtistInDB(name)){
                return true; 
            }
            else {
                queryExecutor.addArtistToDB(name);
                return true;
            }
        } 
        catch (Exception e) {
            return false;
        }
    }
}
