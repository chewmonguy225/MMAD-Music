import java.util.*;

public class Favorite {
    private ArrayList<ReviewableItem> favSongs;
    private ArrayList<ReviewableItem> favAlbums;
    private ArrayList<Artist> favArtists;

    public Favorite (){
        this.favSongs = null;
        this.favAlbums = null;
        this.favArtists = null;
    }

    public void addFavoriteSong(ArrayList<ReviewableItem> favSongs){
        this.favSongs = favSongs;
    }
    
    public void addFavoriteAlbum (ArrayList<ReviewableItem> favAlbums){
        this.favAlbums = favAlbums;
    }

    public void addFavoriteArtist (ArrayList<Artist> favArtist){
        this.favArtists = favArtist;
    }
}
