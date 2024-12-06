package MMAD;

import java.util.ArrayList;

public class ItemHandler {
    private static ItemHandler ih;
    private static SpotifyAPIQueryBuilder api = SpotifyAPIQueryBuilder.access();
    DBHandler dbh = DBHandler.access();
    UI ui = UI.access();
    Display display = Display.access();
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

    public Song searchSong(String songTitle) {
        ArrayList<Song> results = api.searchSong(songTitle);

        int totalPages = (int) Math.ceil((double) results.size() / itemsPerPage);
        int currentPage = 1;

        Song selected = selectSong(results, currentPage, totalPages);
        return selected;
    }

    public Album searchAlbum(String albumTitle) {
        ArrayList<Album> results = api.searchAlbum(albumTitle);

        int totalPages = (int) Math.ceil((double) results.size() / itemsPerPage);
        int currentPage = 1;

        Album selected = selectAlbum(results, currentPage, totalPages);
        return selected;
    }

    public Artist searchArtist(String artistName) {
        ArrayList<Artist> results = api.searchArtist(artistName);

        int totalPages = (int) Math.ceil((double) results.size() / itemsPerPage);
        int currentPage = 1;

        Artist selected = (Artist) selectItem(results, currentPage, totalPages);
        return selected;
    }

    private Song selectSong(ArrayList<Song> searchResults, int currentPage, int totalPages) {
        display.displaySearchResult(searchResults, currentPage, totalPages);
        int option = ui.getInt();
        Song selected = null;
        while (selected == null && !((option == 6 && currentPage == 1) || (option == 7 && currentPage == totalPages))) {
            if (option >= 1 && option <= 5) {
                selected = searchResults.get(((currentPage - 1) * itemsPerPage) + (option - 1));
            } else if (option == (itemsPerPage + 1) && currentPage != 1) {
                currentPage--;
                display.displaySearchResult(searchResults, currentPage, totalPages);
                option = ui.getInt();
            } else if (option == (itemsPerPage + 2) && currentPage != totalPages) {
                currentPage++;
                display.displaySearchResult(searchResults, currentPage, totalPages);
                option = ui.getInt();
            }else{
                display.invalidOption();
            }
        }

        return selected;
    }

    private Album selectAlbum(ArrayList<Album> searchResults, int currentPage, int totalPages) {
        display.displaySearchResult(searchResults, currentPage, totalPages);
        int option = ui.getInt();
        Album selected = null;
        while (selected == null && !((option == 6 && currentPage == 1) || (option == 7 && currentPage == totalPages))) {
            if (option >= 1 && option <= 5) {
                selected = searchResults.get(((currentPage - 1) * itemsPerPage) + (option - 1));
            } else if (option == (itemsPerPage + 1) && currentPage != 1) {
                currentPage--;
                display.displaySearchResult(searchResults, currentPage, totalPages);
                option = ui.getInt();
            } else if (option == (itemsPerPage + 2) && currentPage != totalPages) {
                currentPage++;
                display.displaySearchResult(searchResults, currentPage, totalPages);
                option = ui.getInt();
            }else{
                display.invalidOption();
            }
        }

        return selected;
    }

    private Item selectItem(ArrayList<? extends Item> searchResults, int currentPage, int totalPages) {
        display.displaySearchResult(searchResults, currentPage, totalPages); 
        int option = ui.getInt();
        Item selected = null;
        
        while (selected == null && !((option == 6 && currentPage == 1) || (option == 7 && currentPage == totalPages))) {
            if (option >= 1 && option <= 5) { // Valid options for selection
                selected = searchResults.get(((currentPage - 1) * itemsPerPage) + (option - 1)); 
            } else if (option == (itemsPerPage + 1) && currentPage != 1) { // Option to go to the previous page
                currentPage--;
                display.displaySearchResult(searchResults, currentPage, totalPages);
                option = ui.getInt();
            } else if (option == (itemsPerPage + 2) && currentPage != totalPages) { // Option to go to the next page
                currentPage++;
                display.displaySearchResult(searchResults, currentPage, totalPages);
                option = ui.getInt();
            } else {
                display.invalidOption();
            }
        }
    
        return selected;
    }

}
