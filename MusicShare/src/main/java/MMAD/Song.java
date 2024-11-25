package MMAD;
public class Song extends Item{
    private Album album;
    private Artist artist;

    public Song(String sourceID, String name, Artist artist, Album album){
        super(sourceID, name);
        this.artist = artist;
    }
    public Song(String id,String sourceID, String name, Artist artist, Album album){
        super(id, sourceID, name);
        this.artist = artist;
    }

    public String getID(){
        return this.id;
    }
    public String getSourceID(){
        return this.sourceID;
    }
    public String getname(){
        return this.name;
    }

    public Album getAlbum(){
        return this.album;
    }

    public Artist getArtist(){
        return this.artist;
    }
}