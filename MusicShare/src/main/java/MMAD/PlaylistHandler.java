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
    //Playlist playlist = new Playlist();

    
    
    private PlaylistHandler (){

    }

    public static PlaylistHandler access(){
        if (ph == null){
            ph = new PlaylistHandler();
        }
        return ph;
    }

    public void removeSongFromPlaylist(Login login, Song song){
        //playlist.removeSong(song);//removes song from playlist object if it exists in the playlist.
        
        //queries the removal of the song in the DB
        dbh.removeSongFromPlaylist(login, song);
    }

    public boolean addSongToPlaylist(Login login, Song song){
       // playlist.addSong(song);//adds song to playlist if it is not already in the playlist
        
        int n = dbh.getSongID(song);//gets the songs DB id

        if(n == -1)//song does not exist in DB yet
            ih.addSongToDB(song);//adds song to the DB
            
        song.setID(n);// makes sure that the song object has the correct DB id set

        int result = dbh.addSongToPlaylist(login, song);//adds the song to the playlist
        if(result == 1){
            ah.getCurrentUserObject().getPlaylist().addSong(song);
            //musicList.add(song);
            return true;
        } else if (result == 2){
            return false;
        }
        return false;
    }

    public Playlist getPlaylist(User user){
        Playlist playlist = new Playlist();
        ArrayList<Integer> songIDs = dbh.getPlaylist(user.getLogin());
        musicList = new ArrayList<>();

        for(int songID:songIDs){
            musicList.add(ih.createSongFromID(songID));
        }
        if(musicList.size()>0)
            playlist = new Playlist(musicList);

        return playlist;
    }

    

    public int displayPlaylist(Playlist playlist, UI ui, Display d){
        int option = -1;
        
        while(option <0 || option >7){//if 6 then prev, if 7 then next
            
            //displays the next (5) songs in playlist as long as there is at least one more song to print
            if(page >= 0 && playlist.getPlaylist().size() > page){
                d.displayPlaylist(playlist, page);
                option = ui.getInt();
            }
            
            if(option == 7 && playlist.getPlaylist().size() > page + 5)
                page = page + 5;
            if (option == 6)
                page = page - 5;

            //user chose to exit the system
            if (option == 0)
                return option;
            
        }

        if(option >= 7 && playlist.getPlaylist().size() < page){
            page = 0;
            return -2;
        }else if (option == 0 || option == -1){
            return option;
        }else if(option == 6 && page < 0){
            page = 0;
            return -1;
        }else if (option == 7 || option == 6){
            return option;
        }else {
            //this will call a display function asking if the user would like to remove the song from playlist.
            d.playlistOptionMenu();
            switch( ui.getInt()){
                case 0:
                    return 0;
                case 1:
                    ph.removeSongFromPlaylist(ah.getCurrentUserObject().getLogin(), playlist.getPlaylist().get(option+page-1));
                    d.songRemoved(playlist.getPlaylist().get(option+page-1).getName());
                    ah.getCurrentUserObject().setPlaylist(getPlaylist(ah.getCurrentUserObject()));
                    return -2;
                case 2:
                    return -2;
                case 3:
                    return option + page;
                default:
                    return option + page;
            }
            

            
        }
    }

    public void clearPlaylist(Login login){
        //query to remove all songs from playlist
        dbh.clearPlaylist(login);
        //playlist = new Playlist();//create empty playlist object
    }
    
}
