package MMAD;

import java.util.ArrayList;

public class DBHandler {
    
    private final QueryExecutor queryExecutor;


    /**
     * Constructor
     * 
     * Initializes the QueryExecutor object
     */
    public DBHandler()
    {
        queryExecutor = new QueryExecutor(); 
    }


    /**
    * Adds a new friend relationship into the database after confirming that the user exists.
    *
    * @param name The username of the user that is logged in.
    * @param friend_username The username of the friend to be added.
    * @return 1 if the friend is added succesfully. Return 2 if the friend has already been added. Return 3 if the 'friend' does not exist. Return -1 if there is an error.
    */
    public int addFriend(String username, String friendUsername)
    {
        try {
            if(! queryExecutor.checkUserExists(friendUsername)){
                return 3;
            }
            else if (queryExecutor.checkAlreadyAFriend(username, friendUsername)) {
                return 2;
            }
            else {
                queryExecutor.addFriend(username, friendUsername);
                return 1;
            }
        } 
        catch (Exception e) {
            return -1;
        }
    }


    /**
    * Adds a song into the database song table.
    * First checks if the song is already in the database to avoid duplicate entries.
    *
    * @param song The song object to be added into the database
    * @param sourceID The song's ID given from the API source (Spotify)
    * @return The song id if the song was already in the database or was added succesfully. Return -1 if there is an error.
    */

    public int addSongToDB(Song song)
    {
        try {
            if(! queryExecutor.checkSongInDB(song)) {
                return queryExecutor.addSongToDB(song);
            }
            return queryExecutor.getSongID(song);//returns Song id if already in DB
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public boolean createUser(Login login){

        if(!queryExecutor.checkUserExists(login.getUsername())){
            return queryExecutor.createAccount(login);
        }
        
        return false;

    }

    public boolean attemptLogin(Login login){

        if(queryExecutor.checkUserExists(login.getUsername())){
            return queryExecutor.attemptLogin(login);
        }
        return false;

    }

    public ArrayList<String> getSong(int id){
        return queryExecutor.getSong(id);
    }

    public boolean deleteUser(Login login){
        return queryExecutor.deleteUser(login);
    }

    public boolean removeSongFromPlaylist(Login login, Song song){
        try {
            if(queryExecutor.checkSongInPlaylist(login, song)) {
                return queryExecutor.removeSongFromPlaylist(login, song);//returns true if successful, false if not
            }
            return true;//returns true if the song is already removed
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        
    }


    /**
    * Adds an album into the database album table.
    * First checks if the album is already in the database to avoid duplicate entries.
    *
    * @param name The title/name of the album.
    * @param artist The name of the album's artist.
    * @param sourceID The album's ID given from the API source (Spotify)
    * @return 1 if the album is added succesfully. Return 2 if the album was already in database. Return -1 if there is an error.
    */
    public int addAlbumToDB(Album album)
    {
        try {
            if(queryExecutor.checkAlbumInDB(album)){
                return 2; 
            }
            else {
                queryExecutor.addAlbumToDB(album);
                return 1;
            }
        } 
        catch (Exception e) {
            return -1;
        }
    }


    /**
    * Adds an artist into the database album table.
    * First checks if the artist is already in the database to avoid duplicate entries.
    *
    * @param name The name of the artist.
    * @param sourceID The artist's ID given from the API source (Spotify)
    * @return 1 if the artist is added succesfully. Return 2 if the artist was already in database. Return -1 if there is an error.
    */
    public int addArtistToDB(Artist artist)
    {
        try {
            if(queryExecutor.checkArtistInDB(artist)){
                return 2; 
            }
            else {
                queryExecutor.addArtistToDB(artist);
                return 1;
            }
        } 
        catch (Exception e) {
            return -1;
        }
    }

    
    /**
     * Adds a given song to the user's playlist.
     * 
     * @param username The user's username.
     * @param songID The id of the song in the database
     * @return 1 if the song was added to the playlist succesfully. Return -1 if an error occured.
     */
    public boolean addSongToPlaylist(Login login, Song song)
    {
        try {
            boolean f = queryExecutor.addSongToPlaylist(login, song);
            if (f)
                return true;
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    


    /**
     * Calls the query executor clearPlaylist method to clear the user's playlist.
     * 
     * @param username The user's username. 
     * @return True if the playlist was cleared. Return false if an error occured. 
     */
    public boolean clearPlaylist(Login login)
    {
        try {
            return queryExecutor.clearPlaylist(login);
        } 
        catch (Exception e) {
            return false;
        }
    }
    
    public ArrayList<Integer> getPlaylist(Login login)//this should return an integer arraylist of all the song IDs that are in a users playlist
    {
        try {
            return queryExecutor.getPlaylist(login);
        } 
        catch (Exception e) {
            return new ArrayList<Integer>();
        }
    }

    // DBHandler
    //
    // public bool createSongReview(username, review, song)
    // public bool createAlbumReview(username, review, album)
    // public bool createArtistReview(username, review, artist)
    // public bool deleteReview(getReviewID)
    // public String[] getReviews(username) - return String array of review ID's
    // public ArrayList<String> getReviewInfo(reviewID) - return an ArrayList. Index 0: Description, Index 1: Rating, Index 2: Item ID  

    // QueryExecutor only
    //
    // public int getReviewID(username, Item)
    // public ArrayList<String> getAlbumInfo()
    // public ArrayList<String> getArtistInfo()

    // public ArrayList<String> getFriendsList()


}
