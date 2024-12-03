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
    ItemHandler ih = ItemHandler.access();
    
    private PlaylistHandler (){

    }

    public static PlaylistHandler access(){
        if (ph == null){
            ph = new PlaylistHandler();
        }
        return ph;
    }


    public void removeSongFromPlaylist(Login login, Song song){
        playlist.removeSong(song);//removes song from playlist object if it exists in the playlist.
        
        //queries the removal of the song in the DB
    }

    public boolean addSongToPlaylist(Login login, Song song){
        playlist.addSong(song);//adds song to playlist if it is not already in the playlist
        
        int n = dbh.getSongID(song);//gets the songs DB id

        if(n == -1)//song does not exist in DB yet
            ih.addSongToDB(song);//adds song to the DB
            
        song.setID(n);// makes sure that the song object has the correct DB id set

        return dbh.addSongToPlaylist(login, song);//adds the song to the playlist
    }

    public int displayPlaylist(UI ui, Display d){
        /** Do at top of handler
         * call database hander dbh.getPlaylist to retrieve an arraylist of song IDs
         * call item handler ih.createSongFromID for each song id in arraylist
         */


        //call display d.displauPlaylist(Playlist playlist, int page)

        int option = -1;
        int page = 0;
        while(option <0 || option >7){//if 6 then prev, if 7 then next
            if(option == 7)
                page +=5;
            if (option == 6)
                page -= 5;

            //displays the next (5) songs in playlist as long as there is at least one more song to print
            if(page >= 0 && playlist.getPlaylist().size() > page){
                d.displayPlaylist(playlist, page);
                option = ui.getInt();
            } else {//if on the first page and user select previous, then will go home
                return -1;
            }

            //user chose to exit the system
            if (option == 0)
                return option;
            
        }

        if (option == 0 || option == -1){
            return option;
        }
        else {
            //this will call a display function asking if the user would like to remove the song from playlist.
            return option + page;
        }
    }

    public void clearPlaylist(Login login){
        //query to remove all songs from playlist
        dbh.clearPlaylist(login);
        playlist = new Playlist();//create empty playlist object
        
    }
    
}
