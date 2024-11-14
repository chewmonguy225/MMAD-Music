import java.util.*;

public class Playlist {
    private ArrayList<Song> musicList;

    public Playlist (){
        this.musicList = new ArrayList<Song>();
    }

    public void addFavoriteSong(Song song){
        musicList.add(song);
    }
    
    public void removeSong (Song song){
        musicList.remove(song);
    }
}
