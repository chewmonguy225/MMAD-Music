package Mickael;
import java.util.*;
public class Artist {
    private String id;
    private String name;
    private ArrayList<Album> albums;

    public Artist(String id, String name, ArrayList<Album> albums){
        this.id = id;
        this.name = name;
        this.albums = albums;
    }

    public void addAlbum(Album album){
        this.albums.add(album);
    }

    public String getID(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Album> getAlbums(){
        return this.albums;
    }
}
