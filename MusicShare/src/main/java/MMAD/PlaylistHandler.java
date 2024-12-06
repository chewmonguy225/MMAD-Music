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
    private static AccountHandler ah = AccountHandler.access();
    private static DBHandler dbh = DBHandler.access();
    private static ItemHandler ih = ItemHandler.access();
    private static PlaylistHandler ph = null;
    private int page = 0;

    //first thing, query if the user has an existing playlist
    //if the user has a playlist, query to create a playlist object then continue.
    ArrayList<Song> musicList = new ArrayList<>();
    Playlist playlist = new Playlist();

    
    
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
        dbh.removeSongFromPlaylist(login, song);
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
        Login login = ah.getCurrentUser();
        ArrayList<Integer> songIDs = dbh.getPlaylist(login);
        musicList = new ArrayList<>();

        for(int songID:songIDs){
            musicList.add(ih.createSongFromID(songID));
        }
        if(musicList.size()>0)
            playlist = new Playlist(musicList);

        //d.displayPlaylist(playlist, 0);

        int option = -1;
        

        while(option <0 || option >7){//if 6 then prev, if 7 then next
            
            //displays the next (5) songs in playlist as long as there is at least one more song to print
            if(page >= 0 && playlist.getPlaylist().size() > page){
                System.out.println("\n\nThis is page: "+page);
                d.displayPlaylist(playlist, page);
                option = ui.getInt();
            } else {//if on the first page and user select previous, then will go home
                return -1;
            }

            if(option == 7)
                page = page + 5;
            if (option == 6)
                page = page - 5;

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
