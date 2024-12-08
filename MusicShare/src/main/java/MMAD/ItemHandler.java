package MMAD;

import java.util.ArrayList;

public class ItemHandler {
    private static ItemHandler ih;
    private static SpotifyAPIQueryBuilder api = SpotifyAPIQueryBuilder.access();
    DBHandler dbh = DBHandler.access();
    UI ui = UI.access();
    Display display = Display.access();
    private final int itemsPerPage = 5;
    private final int intialLimit = 25;


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
        ArrayList<String> songInfo = dbh.getSong(id);
        Artist artist = createArtistFromID(Integer.parseInt(songInfo.get(3)));
        Album album = createAlbumFromID(Integer.parseInt(songInfo.get(4)));
        Song song = new Song(id, songInfo.get(1), songInfo.get(2), artist, album);
        return song;
        // use song id to get all other song information.
        // calls the dbhandler to retrieve each piece of song info in order to create
        // and return a song object
    }

    public Album createAlbumFromID(int id) {
        ArrayList<String> albumInfo = dbh.getAlbum(id);
        Artist artist = createArtistFromID(Integer.parseInt(albumInfo.get(3)));
        Album album = new Album(Integer.parseInt(albumInfo.get(0)), albumInfo.get(1), albumInfo.get(2), artist);
        return album;
    }

    public Artist createArtistFromID(int id) {
        ArrayList<String> artistInfo = dbh.getArtist(id);
        Artist artist = new Artist(Integer.parseInt(artistInfo.get(0)), artistInfo.get(1), artistInfo.get(2));
        return artist;
    }

    public Song searchSong(String songTitle) {
        ArrayList<Song> searchResults = api.searchSong(songTitle, intialLimit);

        int totalPages = (int) Math.ceil((double) searchResults.size() / itemsPerPage);
        int currentPage = 1;

        display.displaySearchResult(searchResults, currentPage, totalPages);
        int option = ui.getInt();
        Song selected = null;

        while (selected == null && !((option == 6 && (currentPage == 1 || searchResults.isEmpty())))) {
            if (option >= 1 && option <= itemsPerPage) {
                selected = searchResults.get(((currentPage - 1) * itemsPerPage) + (option - 1));
            } else if (option == (itemsPerPage + 1) && currentPage != 1) {
                currentPage--;
                display.displaySearchResult(searchResults, currentPage, totalPages);
                option = ui.getInt();
            } else if (option == (itemsPerPage + 2) && currentPage != totalPages) {
                currentPage++;
                display.displaySearchResult(searchResults, currentPage, totalPages);
                option = ui.getInt();
            }else if(option == 7 && currentPage == totalPages){
                searchResults = api.searchSong(songTitle, (searchResults.size() + 25));
                totalPages = (int) Math.ceil((double) searchResults.size() / itemsPerPage);
            }
            else{
                display.invalidOption();
            }
        }

        return selected;
    }

    public Album searchAlbum(String albumTitle) {
        ArrayList<Album> searchResults = api.searchAlbum(albumTitle);

        int totalPages = (int) Math.ceil((double) searchResults.size() / itemsPerPage);
        int currentPage = 1;

        display.displaySearchResult(searchResults, currentPage, totalPages);
        int option = ui.getInt();
        Album selected = null;

        while (selected == null && !((option == 6 && (currentPage == 1 || searchResults.isEmpty())))) {
            if (option >= 1 && option <= itemsPerPage) {
                selected = searchResults.get(((currentPage - 1) * itemsPerPage) + (option - 1));
            } else if (option == (itemsPerPage + 1) && currentPage != 1) {
                currentPage--;
                display.displaySearchResult(searchResults, currentPage, totalPages);
                option = ui.getInt();
            } else if (option == (itemsPerPage + 2) && currentPage != totalPages) {
                currentPage++;
                display.displaySearchResult(searchResults, currentPage, totalPages);
                option = ui.getInt();
            }else if(option == 7 && currentPage == totalPages){
                searchResults = api.searchAlbum(albumTitle, (searchResults.size() + 25));
                totalPages = (int) Math.ceil((double) searchResults.size() / itemsPerPage);
            }
            else{
                display.invalidOption();
            }
        }

        return selected;
    }

    public Artist searchArtist(String artistName) {
        ArrayList<Artist> searchResults = api.searchArtist(artistName);

        int totalPages = (int) Math.ceil((double) searchResults.size() / itemsPerPage);
        int currentPage = 1;

        display.displaySearchResult(searchResults, currentPage, totalPages);
        int option = ui.getInt();
        Artist selected = null;

        while (selected == null && !((option == 6 && (currentPage == 1 || searchResults.isEmpty())))) {
            if (option >= 1 && option <= itemsPerPage) {
                selected = searchResults.get(((currentPage - 1) * itemsPerPage) + (option - 1));
            } else if (option == (itemsPerPage + 1) && currentPage != 1) {
                currentPage--;
                display.displaySearchResult(searchResults, currentPage, totalPages);
                option = ui.getInt();
            } else if (option == (itemsPerPage + 2) && currentPage != totalPages) {
                currentPage++;
                display.displaySearchResult(searchResults, currentPage, totalPages);
                option = ui.getInt();
            }else if(option == 7 && currentPage == totalPages){
                searchResults = api.searchArtist(artistName, (searchResults.size() + 25));
                totalPages = (int) Math.ceil((double) searchResults.size() / itemsPerPage);
            }
            else{
                display.invalidOption();
            }
        }

        return selected;
    }

}
