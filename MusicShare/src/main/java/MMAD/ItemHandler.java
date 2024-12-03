package MMAD;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ItemHandler {
    private static ItemHandler ih;
    private static AbstractAPIQueryBuilder api = AbstractAPIQueryBuilder.access();
    DBHandler dbh = new DBHandler();
    private ItemHandler (){

    }

    public static ItemHandler access(){
        if (ih == null){
            ih = new ItemHandler();
        }
        return ih;
    }
    public void addSong(Song song){
        dbh.addSongToDB(song);
    }

    public void addAlbum(Album album){
        dbh.addAlbumToDB(album);
    }

    public void addArtist(Artist artist){
        dbh.addArtistToDB(artist);
    }

    public Song createSongFromID(int id){
        //use song id to get all other song information.
        //calls the dbhandler to retrieve each piece of song info in order to create and return a song object
    }

     public int searchSong(String songTitle, UI ui, Display d){
        ArrayList<Song> results = api.searchSong(songTitle);
        d.displaySongs();
        int option = ui.getInt();
        if(option == 0){
            return option;
        }else if(option > 7){
            return -1;
        }
        
        return option;

    }
    
    public int searchSong(String songTitle, UI ui, Display d){
        ArrayList<Song> results = api.searchSong(songTitle);
        d.displaySongs();
        int option = ui.getInt();
        if(option == 0){
            return option;
        }else if(option > 7){
            return -1;
        }
        
        return option;

    }
}
