package MMAD;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ItemHandler {
    private static ItemHandler ih;
    private static SpotifyAPIQueryBuilder api = SpotifyAPIQueryBuilder.access();
    DBHandler dbh = new DBHandler();
    private final int itemsPerPage = 5;
    private Song selectedSong;


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
     * 
     * @param song
     */
    public void addSongToDB(Song song) {
        addAlbumToDB(song.getAlbum());
        dbh.addSongToDB(song);
    }

    /**
     * adds the album and the artist to the DB for the album object
     * 
     * @param album
     */
    public void addAlbumToDB(Album album) {
        addArtistToDB(album.getArtist());
        dbh.addAlbumToDB(album);
    }

    /**
     * adds the artist to the DB
     * 
     * @param artist
     */
    public void addArtistToDB(Artist artist) {
        dbh.addArtistToDB(artist);
    }

    public Song createSongFromID(int id) {
        // use song id to get all other song information.
        // calls the dbhandler to retrieve each piece of song info in order to create
        // and return a song object
        Song theSong = new Song(null, null, null, null);
        return theSong;
    }

    public int searchSong(String songTitle, UI ui, Display d) {
        ArrayList<Song> results = api.searchSong(songTitle);

        int totalPages = (int) Math.ceil((double) results.size() / itemsPerPage);
        int currentPage = 1;

        d.displaySongSearchResult(results, currentPage, totalPages);
        int option = ui.getInt();
 
        selectedSong = null;
        while (selectedSong == null && option != -1) {
            if (option >= 1 && option <= 5) {
                selectedSong = results.get((currentPage * itemsPerPage) + (option - 1));
            } else if (option == 6 && currentPage != totalPages) {
                currentPage++;
                d.displaySongSearchResult(results, currentPage, totalPages);
                option = ui.getInt();
            } else if (option == 7 && currentPage != 1) {
                currentPage--;
                d.displaySongSearchResult(results, currentPage, totalPages);
                option = ui.getInt();
            } else if (option == 7 && currentPage == 1) {
                option = -1;
            }
        }
        addSongToDB(selectedSong);
        return option;
    }

    public int searchAlbum(String albumTitle, UI ui, Display d) {
        ArrayList<Album> results = api.searchAlbum(albumTitle);

        int totalPages = (int) Math.ceil((double) results.size() / itemsPerPage);
        int currentPage = 1;

        d.displayAlbumSearchResult(results, currentPage, totalPages);
        int option = ui.getInt();
 
        Album selected = null;
        while (selected == null && option != -1) {
            if (option >= 1 && option <= 5) {
                selected = results.get((currentPage * itemsPerPage) + (option - 1));
            } else if (option == 6 && currentPage != totalPages) {
                currentPage++;
                d.displayAlbumSearchResult(results, currentPage, totalPages);
                option = ui.getInt();
            } else if (option == 7 && currentPage != 1) {
                currentPage--;
                d.displayAlbumSearchResult(results, currentPage, totalPages);
                option = ui.getInt();
            } else if (option == 7 && currentPage == 1) {
                option = -1;
            }
        }
        addAlbumToDB(selected);
        return option;
    }

    public int searchArtist(String artist, UI ui, Display d) {
        ArrayList<Artist> results = api.searchArtist(artist);

        int totalPages = (int) Math.ceil((double) results.size() / itemsPerPage);
        int currentPage = 1;

        d.displayArtistSearchResult(results, currentPage, totalPages);
        int option = ui.getInt();
 
        Artist selected = null;
        while (selected == null && option != -1) {
            if (option >= 1 && option <= 5) {
                selected = results.get((currentPage * itemsPerPage) + (option - 1));
            } else if (option == 6 && currentPage != totalPages) {
                currentPage++;
                d.displayArtistSearchResult(results, currentPage, totalPages);
                option = ui.getInt();
            } else if (option == 7 && currentPage != 1) {
                currentPage--;
                d.displayArtistSearchResult(results, currentPage, totalPages);
                option = ui.getInt();
            } else if (option == 7 && currentPage == 1) {
                option = -1;
            }
        }
        addArtistToDB(selected);
        return option;
    }

    public Song getSelectedSong(){
        return selectedSong;
    }
}
