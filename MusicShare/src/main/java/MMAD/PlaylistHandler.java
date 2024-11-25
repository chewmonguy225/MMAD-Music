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
    //first thing, query if the user has an existing playlist
    //if the user has a playlist, query to create a playlist object then continue.
    ArrayList<Song> musicList = new ArrayList<>();
    Playlist playlist_EXISTS = new Playlist(musicList);
    
    //if the user does not have a playlist (at least one saved song), then create a new playlist object
    Playlist playlist_DNE = new Playlist();//empty playlist

    //both options will end up with a playlist object called playlist
    Playlist playlist = new Playlist();
    
    /**
     * create a playlistHandler to handle options 2 and 3
     * @param option
     * @param song
     */
    public PlaylistHandler(int option, Song song){
        switch (option){
            case 2:
                addSong(song);
                break;
            case 3:
                removeSong(song);
                break;
            default:
                //invalid request;
        }
    }
    /**
     * create a playlistHandler to handle option 3
     */
    public PlaylistHandler(int option){
        //query the removal of all playlist info
        switch (option){
            case 1:
                printPlaylist();
                break;
            case 3:
                clearPlaylist();
                break;
            default:
                //invalid request;
        }
    }

    public void removeSong(Song song){
        playlist.removeSong(song);//removes song from playlist object if it exists in the playlist.
        
        //queries the removal of the song in the DB
    }

    public void addSong(Song song){
        playlist.addSong(song);//adds song to playlist if it is not already in the playlist

        //queries the addition of the song to playlist
    }

    public void printPlaylist(){
        ArrayList<Song> musicList = playlist.getPlaylist();
        //send every song to display
    }

    public void clearPlaylist(){
        //query to remove all songs from playlist
        playlist = new Playlist();//create empty playlist object
    }
    
}
