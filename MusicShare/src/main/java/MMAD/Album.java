package MMAD;
public class Album extends Item {
    private String[] trackList;
    private Artist artist;

    public Album(String id, String name, Artist artist, String[] trackList){
        super(id, name);
        this.trackList = trackList;
        this.artist = artist;
    }

    public String[] getTracklist(){
        return this.trackList;
    }

    public Artist getArtist(){
        return this.artist;
    }
}
