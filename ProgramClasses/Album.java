public class Album extends ReviewableItem {
    private Song[] trackList;

    public Album(String id, String title, Artist artist, Song[] trackList){
        super(id, title, artist);
        this.trackList = trackList;
    }

    public Song[] getTracklist(){
        return this.trackList;
    }
}
