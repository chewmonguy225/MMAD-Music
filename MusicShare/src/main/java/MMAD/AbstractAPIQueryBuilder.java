package MMAD;

import java.util.ArrayList;

//TRACK TO SONG REPLACE
public abstract class AbstractAPIQueryBuilder {
    //parameter: title of Album to be searched
    //return ArrayList of Array
    //array[0]: songTitle
    //array[1]: TrackList
    //array[2]: 
    public abstract ArrayList<ArrayList<String>> searchAlbum(String albumTitle);
    public abstract ArrayList<ArrayList<String>> searchSong(String songTitle);
    public abstract ArrayList<ArrayList<String>> searchArtist(String artistName);
}


////Playlist Hander