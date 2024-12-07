package MMAD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryExecutor {

    private DBConnectionManager connectionManager;
    private Connection sqlConnection;

    /**
     * Initializes the connectionManager and sqlConnection objects that allow query
     * execution
     *
     * @param sqlConnection The SQL connection to be used for executing queries.
     */
    public QueryExecutor() // Connection object passed as param in main method
    {
        try {
            connectionManager = new SQLConnectionManager();
            connectionManager.establishConnection();
            sqlConnection = connectionManager.getConnectionObject();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Closes the connection to the database.
     * 
     * @return True if connection was closed succesfully, false otherwise.
     */
    public boolean closeConnection() {
        try {
            connectionManager.closeConnection();
            return true;
        } catch (SQLException ex) {
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
    public boolean attemptLogin(Login login) {
        try {
            PreparedStatement statement = sqlConnection
                    .prepareStatement("SELECT * from user WHERE username= ? AND password= ?;");
            statement.setString(1, login.getUsername());
            statement.setString(2, login.getPassword());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
    public boolean createAccount(Login login) {
        try {
            PreparedStatement statement = sqlConnection
                    .prepareStatement("INSERT INTO user (username, password) VALUES (?, ?);");
            statement.setString(1, login.getUsername());
            statement.setString(2, login.getPassword());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Searches the user table for an account with the given username.
     *
     * @param username The username of the account we are looking for.
     * @return True if the account exists, false otherwise.
     */
    public boolean checkUserExists(String username) {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("SELECT * from user WHERE username= ?;");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Queries the database to delete an account
     * 
     * @param login The user's login object
     * @return True if the account was deleted succesfully. False if an error
     *         occurred.
     */
    public boolean deleteUser(Login login) {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("DELETE FROM user WHERE username= ?;");
            statement.setString(1, login.getUsername());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Searches the user_friend table to see if the friend has already been added.
     * 
     * @param username       The user's username.
     * @param friendUsername The friend's username.
     * @return True if the friend has already been added, false otherwise.
     */
    public boolean checkAlreadyAFriend(String username, String friendUsername) {
        try {
            PreparedStatement statement = sqlConnection
                    .prepareStatement("SELECT * from user_friend WHERE username= ? AND friend_username= ?;");
            statement.setString(1, username);
            statement.setString(2, friendUsername);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Adds a new friend relationship into the database.
     *
     * @param username       The username of the user that is logged in.
     * @param friendUsername The username of the friend to be added.
     * @return True if the friend is added succesfully, false otherwise.
     */
    public boolean addFriend(String username, String friendUsername) {
        try {
            PreparedStatement statement = sqlConnection
                    .prepareStatement("INSERT INTO user_friend (username, friend_username) VALUES (?, ?);");
            statement.setString(1, username);
            statement.setString(2, friendUsername);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException ex) {
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
    public boolean checkSongInDB(Song song) {
        try {
            if (song.getID() >= 0) {
                PreparedStatement statement = sqlConnection.prepareStatement("SELECT * FROM song WHERE id= ?;");
                statement.setInt(1, song.getID());
                ResultSet resultSet = statement.executeQuery();
                return resultSet.next();
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Checks if an album already exists in the database.
     *
     * @param name   The title/name of the album.
     * @param artist The name of the album's artist.
     * @return True if the album was found in the database, false otherwise.
     */
    public boolean checkAlbumInDB(Album album) {
        try {
            PreparedStatement statement = sqlConnection
                    .prepareStatement("SELECT * FROM album WHERE name= ? AND artist= ?;");
            statement.setString(1, album.getName());
            statement.setString(2, album.getArtist().getName());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Checks if an artist already exists in the database.
     *
     * @param name The name of the artist.
     * @return True if the artist was found in the database, false otherwise.
     */
    public boolean checkArtistInDB(Artist artist) {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("SELECT * FROM artist WHERE name= ?;");
            statement.setString(1, artist.getName());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Inserts a new song in the song table of the database.
     *
     * @param song     The song object to be added into the database
     * @param sourceID The song's ID given from the API source (Spotify)
     * @return The song's database id if the song was inserted succesfully, -1 if
     *         there was an error.
     */
    public int addSongToDB(Song song) {
        try {
            PreparedStatement statement = sqlConnection
                    .prepareStatement("INSERT INTO song (source_id, name, artist, album) VALUES (?, ?, ?, ?);");
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
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    public int getSongID(Song song) {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("SELECT * FROM song WHERE source_id=?;");
            statement.setString(1, song.getSourceID());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                return -1;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }

    }

    /**
     * Inserts a new album in the album table of the database.
     *
     * @param album    The album object to be added into the database
     * @param sourceID The song's ID given from the API source (Spotify)
     * @return True if the album was inserted succesfully, false otherwise.
     */
    public boolean addAlbumToDB(Album album) {
        try {
            PreparedStatement statement = sqlConnection
                    .prepareStatement("INSERT INTO album (source_id, name, artist) VALUES (?, ?, ?);");
            statement.setString(1, album.getSourceID());
            statement.setString(2, album.getName());
            statement.setString(3, album.getArtist().getName());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Inserts a new artist in the artist table of the database.
     *
     * @param artist The artis to be added into the database.
     * @return True if the artist was inserted succesfully, false otherwise.
     */
    public boolean addArtistToDB(Artist artist) {
        try {
            PreparedStatement statement = sqlConnection
                    .prepareStatement("INSERT INTO artist (source_id, name) VALUES (?, ?);");
            statement.setString(1, artist.getSourceID());
            statement.setString(2, artist.getName());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Checks if a song is already in the user's playlist.
     *
     * @param username The user's username.
     * @param songID   The song's id in the database.
     * @return True if the song was found in the playlist, false otherwise.
     */
    public boolean checkSongInPlaylist(Login login, Song song) {
        try {
            PreparedStatement statement = sqlConnection
                    .prepareStatement("SELECT * FROM playlist_song WHERE username= ? AND songID= ?;");
            statement.setString(1, login.getUsername());
            statement.setInt(2, song.getID());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Queries the database to insert a now row in the playlist table.
     * 
     * @param username The user's username.
     * @param SongID   The database id of the song.
     * @return True if the song is added to the playlist succesfully, false
     *         otherwise.
     */
    public boolean addSongToPlaylist(Login login, Song song) {
        try {
            PreparedStatement statement = sqlConnection
                    .prepareStatement("INSERT INTO playlist_song (username, song_id) VALUES (?, ?);");
            statement.setString(1, login.getUsername());
            statement.setInt(2, song.getID());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Returns an array list of all song ids in the user's playlist
     * 
     * @param login The login object for the user
     * @return An integer array list of all song ids in the user's playlist. An
     *         empty array list if there were no songs or an error occurred.
     */
    public ArrayList<Integer> getPlaylist(Login login) {
        try {
            PreparedStatement statement = sqlConnection
                    .prepareStatement("SELECT * FROM playlist_song WHERE username= ?;");
            statement.setString(1, login.getUsername());
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Integer> songIDs = new ArrayList<>();

            while (resultSet.next()) {
                songIDs.add(resultSet.getInt("song_id"));
            }
            return songIDs;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<Integer>();
        }
    }

    /**
     * Returns an array list containing song information
     * 
     * @param id The song's id in the database
     * @return A string array list containing the following info in each index:
     *         0: songId 1: source_id 2: songName 3: artistName 4: albumName 5:
     *         artistId 6: artistSrcId 7: albumId 8: albumSrcId
     *         Returns empty array list if error occurs
     */
    public ArrayList<String> getSong(int id) {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("SELECT * FROM song WHERE id= ?;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<String> songInfo = new ArrayList<>();

            if (resultSet.next()) {
                songInfo.add(id + "");
                songInfo.add(resultSet.getString("source_id"));
                songInfo.add(resultSet.getString("name"));
                songInfo.add(resultSet.getString("artist"));
                songInfo.add(resultSet.getString("album"));
            }
            statement = sqlConnection.prepareStatement("SELECT * FROM artist WHERE name= ?;");
            statement.setString(1, songInfo.get(3));
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                songInfo.add(resultSet.getInt("id") + "");
                songInfo.add(resultSet.getString("source_id"));
            }
            statement = sqlConnection.prepareStatement("SELECT * FROM album WHERE name= ?;");
            statement.setString(1, songInfo.get(4));
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                songInfo.add(resultSet.getInt("id") + "");
                songInfo.add(resultSet.getString("source_id"));
            }

            return songInfo;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<String>();
        }
    }

    /**
     * Returns a string array list containing artist information
     * 
     * @param id The artist's id in the database
     * @return A string array list containing the following info in each index:
     *         0: artistId 1: source_id 2: artistName
     *         Returns empty array list if error occurs
     */
    public ArrayList<String> getArtist(int id) {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("SELECT * FROM artist WHERE id= ?;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<String> artistInfo = new ArrayList<>();

            if (resultSet.next()) {
                artistInfo.add(id + "");
                artistInfo.add(resultSet.getString("source_id"));
                artistInfo.add(resultSet.getString("name"));
            }
            return artistInfo;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<String>();
        }
    }

    /**
     * Returns a string array list containing album information
     * 
     * @param id The album's id in the database
     * @return A string array list containing the following info in each index:
     *         0: albumId 1: source_id 2: albumName 3: artistName 4: artistId 5:
     *         artistSrcId
     *         Returns empty array list if error occurs
     */
    public ArrayList<String> getAlbum(int id) {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("SELECT * FROM album WHERE id= ?;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<String> albumInfo = new ArrayList<>();

            if (resultSet.next()) {
                albumInfo.add(id + "");
                albumInfo.add(resultSet.getString("source_id"));
                albumInfo.add(resultSet.getString("name"));
            }
            statement = sqlConnection.prepareStatement("SELECT * FROM artist WHERE name= ?;");
            statement.setString(1, albumInfo.get(3));
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                albumInfo.add(resultSet.getString("id"));
                albumInfo.add(resultSet.getString("source_id"));
            }
            return albumInfo;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<String>();
        }
    }

    /**
     * Removes a song from the user's playlist
     * 
     * @param login  The user's login object
     * @param songID The song's database id
     * @return True if the song is removed succesfully, false otherwise.
     */
    public boolean removeSongFromPlaylist(Login login, Song song) {
        try {
            PreparedStatement statement = sqlConnection
                    .prepareStatement("DELETE FROM playlist_song WHERE username= ? AND song_id= ?;");
            statement.setString(1, login.getUsername());
            statement.setInt(2, song.getID());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Deletes all songs from the user's playlist.
     * 
     * @param login The user's login object.
     * @return True is the playlist was cleared succesfully, false if an error
     *         occurs.
     */
    public boolean clearPlaylist(Login login) {
        try {
            PreparedStatement statement = sqlConnection
                    .prepareStatement("DELETE FROM playlist_song WHERE username= ?;");
            statement.setString(1, login.getUsername());
            statement.executeUpdate();// something wrong with this statement
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Creates a new review in the database
     * 
     * @param login  The user's login object.
     * @param review The review object to be added.
     * @return True if successful, false otherwise.
     */
    public boolean createReview(Login login, Review review) {
        try {
            // get the item type
            String itemType;
            if (review.getItem() instanceof Song) {
                itemType = "s";
            } else if (review.getItem() instanceof Artist) {
                itemType = "ar";
            } else {
                itemType = "al";
            }

            System.out.println(review.getItem().getID());
            PreparedStatement statement = sqlConnection
                    .prepareStatement("INSERT INTO review (id, text, rating, username) VALUES (?,?,?,?);");
            String reviewID = itemType + review.getItem().getID();
            statement.setString(1, reviewID);
            statement.setString(2, review.getDescription());
            statement.setInt(3, review.getRating());
            statement.setString(4, login.getUsername());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Returns an array list containing all the review id's of all a user's reviews
     * 
     * @param login The user's login object.
     * @return an array list containing all the review id's of all a user's reviews
     */
    public ArrayList<String> getUserReviews(Login login) {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement("SELECT * FROM review WHERE username LIKE ?;");
            statement.setString(1, login.getUsername());
            ResultSet resultSet = statement.executeQuery();
            ArrayList<String> reviewIDs = new ArrayList<>();

            while (resultSet.next()) {
                reviewIDs.add(resultSet.getString("id"));
            }
            return reviewIDs;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<String>();
        }
    }

    /**
     * Returns a string array list containing review information
     * 
     * @param id    The id of the review.
     * @param login The user's login object.
     * @return A string array list containing the following info in each index:
     *         0: reviewId (type + id) 1: description 2: rating 3: username
     *         Returns empty array list if error occurs
     */
    public ArrayList<String> getReview(Login login, Item item) {
        try {
            String itemType;
            if (item instanceof Song) {
                itemType = "s";
            } else if (item instanceof Artist) {
                itemType = "ar";
            } else {
                itemType = "al";
            }
            System.out.println(itemType + item.getID());
            String reviewID = itemType + item.getID();
            PreparedStatement statement = sqlConnection.prepareStatement("SELECT * FROM review WHERE id = ? AND username = ?;");
            statement.setString(1, reviewID);
            statement.setString(2, login.getUsername());
            ResultSet resultSet = statement.executeQuery();
            ArrayList<String> reviewInfo = new ArrayList<>();

            if (resultSet.next()) {
                reviewInfo.add(reviewID);
                reviewInfo.add(resultSet.getString("text"));
                reviewInfo.add(resultSet.getInt("rating") + "");
                reviewInfo.add(resultSet.getString("username") + "");
                // reviewInfo.add(item type)
                // reviewInfo.add(item id)
            }
            return reviewInfo;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<String>();
        }
    }

    /**
     * Returns an array list of all song ids in the shared playlist
     * 
     * @param login1 The first login object
     * @param login2 The second login object
     * @return An integer array list of all song ids in the shared playlist. An
     *         empty array list if there were no songs or an error occurred.
     */
    public ArrayList<Integer> getSharedPlaylist(Login login1, Login login2) {
        try {
            PreparedStatement statement = sqlConnection.prepareStatement(
                    "SELECT ps1.song_id FROM playlist_song ps1 INNER JOIN playlist_song ps2 ON ps1.song_id = ps2.song_id WHERE ps1.username= ? AND ps2.username= ?;");
            statement.setString(1, login1.getUsername());
            statement.setString(1, login2.getPassword());
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Integer> songIDs = new ArrayList<>();

            while (resultSet.next()) {
                songIDs.add(resultSet.getInt("id"));
            }
            return songIDs;
        } catch (SQLException ex) {
            return new ArrayList<Integer>();
        }
    }

    /**
     * Returns the usernames of all a user's friends
     * 
     * @param login The user's login object.
     * @return a string array list containing all the usernames of friends
     */
    public ArrayList<String> getFriendsList(Login login) {
        try {
            PreparedStatement statement = sqlConnection
                    .prepareStatement("SELECT * FROM user_friend WHERE username= ?;");
            statement.setString(1, login.getUsername());
            ResultSet resultSet = statement.executeQuery();
            ArrayList<String> friendUsernames = new ArrayList<>();

            while (resultSet.next()) {
                friendUsernames.add(resultSet.getString("friend_username"));
            }
            return friendUsernames;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<String>();
        }
    }
    
    public ArrayList<String> searchUsers(String usernameToSearch) {
        try {
            // Use ? for dynamic parameter binding
            PreparedStatement statement = sqlConnection.prepareStatement(
                "SELECT username FROM user WHERE username LIKE ? LIMIT 25;"
            );
            // Add wildcards to the search term for partial matching
            statement.setString(1, usernameToSearch);
            
            ResultSet resultSet = statement.executeQuery();
            ArrayList<String> usernames = new ArrayList<>();
            for(String usern : usernames){
                System.out.println(usern);
            }
            // Retrieve "username" instead of "friend_username"
            while (resultSet.next()) {
                usernames.add(resultSet.getString("username"));
            }
            return usernames;
        } catch (SQLException ex) {
            System.out.println("Error while searching for users: " + ex.getMessage());
            return new ArrayList<>();
        }
    }
    

}
