package MMAD;
public abstract class ReviewableItem {
    private String id;
    private String title;
    private Artist artist;

    public ReviewableItem(String id, String title, Artist artist){
        this.id = id;
        this.title = title;
        this.artist = artist;
    }

    public String getID(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    /**
     * 
     * @return artist Artist object 
     */
    public Artist getArtist(){
        return this.artist;
    }
    
}


