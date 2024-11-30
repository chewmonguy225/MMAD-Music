package MMAD;

import java.util.ArrayList;

/**
 * This class should be called by the controller if the user chose one of the following:
 *      1 show playlist
 *      2 add song to playlist
 *      3 remove song from playlist
 *      4 clear playlist
 *      5 combine playlist
 */
public class PlaylistHandler {
    private static PlaylistHandler ph = null;

    //first thing, query if the user has an existing playlist
    //if the user has a playlist, query to create a playlist object then continue.
    ArrayList<Song> musicList = new ArrayList<>();
    Playlist playlist_EXISTS = new Playlist(musicList);
    
    //if the user does not have a playlist (at least one saved song), then create a new playlist object
    Playlist playlist_DNE = new Playlist();//empty playlist

    //both options will end up with a playlist object called playlist
    Playlist playlist = new Playlist();

    DBHandler dbh = new DBHandler();
    
    private PlaylistHandler (){

    }

    public static PlaylistHandler access(){
        if (ph == null){
            ph = new PlaylistHandler();
        }
        return ph;
    }


    public void removeSong(Song song){
        playlist.removeSong(song);//removes song from playlist object if it exists in the playlist.
        
        //queries the removal of the song in the DB
    }

    public void addSong(Login login, Song song){
        playlist.addSong(song);//adds song to playlist if it is not already in the playlist
        
        int n = dbh.getSongID(song);//will be replaced with getSongID in DBHandler
        int r = dbh.addSongToPlaylist(login.getUsername(), n);
        System.out.println(r);
        //queries the addition of the song to playlist
        //boolean addSongToPlaylist(String username, int songID)
    }

    public void printPlaylist(){
        ArrayList<Song> musicList = playlist.getPlaylist();
        //send every song to display
    }

    public void clearPlaylist(Login login){
        //query to remove all songs from playlist
        dbh.clearPlaylist(login.getUsername());
        playlist = new Playlist();//create empty playlist object
        
    }
    
}
