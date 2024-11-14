public class Album extends Item {
    private String[] trackList;

    public Album(String id, String name, Artist artist, String[] trackList){
        super(id, name);
        this.trackList = trackList;
    }

    public String[] getTracklist(){
        return this.trackList;
    }
}
