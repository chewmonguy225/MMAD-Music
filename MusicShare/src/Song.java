public class Song extends Item{
    private Album album;
    private Artist artist;
    private int trackNumber; //where the song appears in the album

    public Song(String id, String name, Artist artist, Album album, int trackNumber){
        super(id, name);
        this.artist = artist;
        this.trackNumber = trackNumber;
    }

    public Album getAlbum(){
        return this.album;
    }

    public int getTrackNumber(){
        return this.trackNumber;
    }

    public Artist getArtist(){
        return this.artist;
    }
}