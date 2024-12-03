package MMAD;

import java.util.ArrayList;

//TRACK TO SONG REPLACE
public abstract class AbstractAPIQueryBuilder {
    //parameter: title of Album to be searched
    //return ArrayList of Array
    //array[0]: songTitle
    //array[1]: TrackList
    //array[2]: 
    public abstract ArrayList<Album> searchAlbum(String albumTitle);
    public abstract ArrayList<Song> searchSong(String songTitle);
    public abstract ArrayList<Artist> searchArtist(String artistName);
}


////Playlist Hander