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

    public void addSong(Song song) {
        dbh.addSongToDB(song);
    }

    public void addAlbum(Album album) {
        dbh.addAlbumToDB(album);
    }

    public void addArtist(Artist artist) {
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

        boolean selected = false;
        while (!selected) {
            if (option >= 1 && option <= 5) {
                selected = true;
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

    public Item selectItem(ArrayList<Item> theItems, int option) {
        return theItems.get(option - 1);
    }

}
