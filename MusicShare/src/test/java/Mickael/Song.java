package Mickael;
public class Song extends ReviewableItem{
    private Album album;
    private int trackNumber; //where the song appears in the album

    public Song(String id, String title, Artist artist, Album album, int trackNumber){
        super(id, title, artist);
        this.trackNumber = trackNumber;
    }

    public Album getAlbum(){
        return this.album;
    }

    public int getTrackNumber(){
        return this.trackNumber;
    }
}