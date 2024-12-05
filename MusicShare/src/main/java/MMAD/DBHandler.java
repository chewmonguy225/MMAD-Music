package MMAD;

import java.util.ArrayList;

public class DBHandler {
    private static DBHandler dbHandler;
    private final QueryExecutor queryExecutor;


    /**
     * Constructor
     * 
     * Initializes the QueryExecutor object
     */
    private DBHandler()
    {
        queryExecutor = new QueryExecutor(); 
    }

    public static DBHandler access() {
        if (dbHandler == null) {
            dbHandler = new DBHandler();
        }
        return dbHandler;
    }    


    /**
    * Adds a new friend relationship into the database after confirming that the user exists.
    *
    * @param login1 The user's login object.
    * @param login2 The friend's login object.
    * @return 1 if the friend is added succesfully. Return 2 if the friend has already been added. Return 3 if the 'friend' does not exist. Return -1 if there is an error.
    */
    public int addFriend(Login login1, Login login2)
    {
        try {
            if(! queryExecutor.checkUserExists(login2.getUsername())){
                return 3;
            }
            else if (queryExecutor.checkAlreadyAFriend(login1.getUsername(), login2.getUsername())) {
                return 2;
            }
            else {
                queryExecutor.addFriend(login1.getUsername(), login2.getUsername());
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

    public int getSongID(Song song){
        try{
            return queryExecutor.getSongID(song);
        }
        catch(Exception e){
            return -1;
        }
    }


    /**
     * Creates a new user in the database
     * 
     * @param login The login object to be entered into the database
     * @return True if the user was created succesfully, false if an error occured.
     */
    public boolean createUser(Login login){

        if(!queryExecutor.checkUserExists(login.getUsername())){
            return queryExecutor.createAccount(login);
        }
        
        return false;
    }


    /**
     * Queries the database to delete an account
     * 
     * @param login The user's login object
     * @return True if the account was deleted succesfully. False if an error occurred.
     */
    public boolean deleteUser(Login login){
        return queryExecutor.deleteUser(login);
    }


    /**
     * Attempts to log a user in by querying the database user table
     * 
     * @param login The login object to be logged in
     * @return True if log in was successful, false if an error occurred.
     */
    public boolean attemptLogin(Login login){

        if(queryExecutor.checkUserExists(login.getUsername())){
            return queryExecutor.attemptLogin(login);
        }
        return false;

    }


    /**
     * Returns an array list containing song information
     * 
     * @param id The song's id in the database
     * 
     * @return A string array list containing the following info in each index:
     *          0: songId 1: source_id 2: songName 3: artistName 4: albumName 5: artistId 6: artistSrcId 7: albumId 8: albumSrcId 
     *          Returns empty array list if error occurs
     */
    public ArrayList<String> getSong(int id){
        return queryExecutor.getSong(id);
    }

    /**
     * Returns a string array list containing artist information
     * 
     * @param id The artist's id in the database
     * 
     * @return A string array list containing the following info in each index:
     *          0: artistId 1: source_id 2: artistName 
     *          Returns empty array list if error occurs
     */
    public ArrayList<String> getArtist(int id){
        return queryExecutor.getArtist(id);
    }


    /**
     * Returns a string array list containing album information
     * 
     * @param id The album's id in the database
     * 
     * @return A string array list containing the following info in each index:
     *          0: albumId 1: source_id 2: albumName 3: artistName 4: artistId 5: artistSrcId
     *          Returns empty array list if error occurs
     */
    public ArrayList<String> getAlbum(int id){
        return queryExecutor.getAlbum(id);
    }


    /**
     * Removes a song from the user's playlist
     * 
     * @param login The user's login object
     * @param songID The song's database id
     * @return True if the song is removed succesfully, false otherwise.
     */
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
    

    /**
     * Returns an array list of all song ids in the user's playlist
     * 
     * @param login The login object for the user
     * 
     * @return An integer array list of all song ids in the user's playlist. An empty array list if there were no songs or an error occurred.
     */
    public ArrayList<Integer> getPlaylist(Login login)//this should return an integer arraylist of all the song IDs that are in a users playlist
    {
        return queryExecutor.getPlaylist(login);
    }


    /**
     * Creates a new review in the database
     * 
     * @param login The user's login object.
     * @param review The review object to be added.
     * @return True if successful, false otherwise.
     */
    public boolean createReview(Login login, Review review){
        return queryExecutor.createReview(login, review);
    }


    /**
     * Returns a string array list containing review information
     * 
     * @param id The review's db id
     * 
     * @return A string array list containing the following info in each index:
     *          0: reviewId 1: description 2: rating 3: 
     *          Returns empty array list if error occurs
     */
    public ArrayList<String> getReview(String id){
        return queryExecutor.getReview(login, review);
    }


    /**
     * Returns an array list containing all the review id's of all a user's reviews
     * 
     * @param login The user's login object.
     * @return an array list containing all the review id's of all a user's reviews
     */
    public ArrayList<String> getUserReviews(Login login){
        return queryExecutor.getUserReviews(login);
    }


    /**
     * Returns an array list of all song ids in the shared playlist
     * 
     * @param login1 The first login object
     * @param login2 The second login object
     * 
     * @return An integer array list of all song ids in the shared playlist. An empty array list if there were no songs or an error occurred.
     */
    public ArrayList<Integer> getSharedPlaylist(Login login1, Login login2){
        return queryExecutor.getSharedPlaylist(login1, login2);
    }


    /**
     * Returns the usernames of all a user's friends
     * 
     * @param login The user's login object.
     * @return a string array list containing all the usernames of friends
     */
    public ArrayList<String> getFriendsList(Login login){
        return queryExecutor.getFriendsList(login);
    }


    // DBHandler
    // public bool deleteReview(getReviewID)
    // public ArrayList<String> getReviewInfo(reviewID) - return an ArrayList. Index 0: Description, Index 1: Rating, Index 2: Item ID  

    // QueryExecutor only
    // public int getReviewID(username, Item)
}
