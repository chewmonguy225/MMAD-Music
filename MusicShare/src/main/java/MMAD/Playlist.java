package MMAD;
import java.util.*;

public class Playlist {
    private ArrayList<Song> musicList;

    public Playlist (){
        this.musicList = new ArrayList<Song>();
    }
    public Playlist (ArrayList<Song> musicList){
        this.musicList = musicList;
    }

    public void addSong(Song song){
        if(!musicList.contains(song))
            musicList.add(song);
    }
    
    public void removeSong (Song song){
        if(musicList.contains(song))
            musicList.remove(song);
    }

    public ArrayList<Song> getPlaylist(){
        return musicList;
    }
}
