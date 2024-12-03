package MMAD;

import java.util.ArrayList;

//TRACK TO SONG REPLACE
public abstract class AbstractAPIQueryBuilder {
    public abstract ArrayList<Album> searchAlbum(String albumTitle);
    public abstract ArrayList<Song> searchSong(String songTitle);
    public abstract ArrayList<Artist> searchArtist(String artistName);
}