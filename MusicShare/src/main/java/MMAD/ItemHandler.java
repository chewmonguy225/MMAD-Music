package MMAD;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ItemHandler {
    private static ItemHandler ih;
    private static AbstractAPIQueryBuilder api = AbstractAPIQueryBuilder.access();
    DBHandler dbh = new DBHandler();
    private final int itemsPerPage = 5;

    private ItemHandler() {

    }

    public static ItemHandler access() {
        if (ih == null) {
            ih = new ItemHandler();
        }
        return ih;
    }

    /**
     * adds song, album and artist to the DB for the song object
     * @param song
     */
    public void addSongToDB(Song song){
        addAlbumToDB(song.getAlbum());
        dbh.addSongToDB(song);
    }

    /**
     * adds the album and the artist to the DB for the album object
     * @param album
     */
    public void addAlbumToDB(Album album){
        addArtistToDB(album.getArtist());
        dbh.addAlbumToDB(album);
    }

    /**
     * adds the artist to the DB
     * @param artist
     */
    public void addArtistToDB(Artist artist){
        dbh.addArtistToDB(artist);
    }

    public Song createSongFromID(int id) {
        // use song id to get all other song information.
        // calls the dbhandler to retrieve each piece of song info in order to create
        // and return a song object
    }

    public int searchSong(String songTitle, UI ui, Display d) {
        ArrayList<Song> results = api.searchSong(songTitle);
        int totalPages = (int) Math.ceil((double) results.size() / itemsPerPage);
        int currentPage = 1;
        d.displaySongs(results, currentPage, totalPages);
        int option = ui.getInt();


        Song selectedSong = null;
        while (selectedSong == null) {
            if (option >= 1 && option <= 5) {
                selectedSong = results.get((currentPage * items);
            } else if (option == 6 && currentPage != 1) {
                currentPage--;
                d.displaySongs(results, currentPage, totalPages);
            } else if (option == 7 && currentPage != totalPages) {
                currentPage++;
                d.displaySongs(results, currentPage, totalPages);
            } else if (option == 7 && currentPage == 1) {
                option = -1;
                selected = true; 
            }
        }
        return option;
    }

}
