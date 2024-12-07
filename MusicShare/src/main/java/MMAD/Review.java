package MMAD;
public class Review {

    private User author;
    //type + int::itemId
    private String id;
    private Item item;
    private String description;
    private int rating; 

    /**
     * This constructer is called to create a new review (before DB)
     * @param item
     * @param description
     * @param rating
     */
    public Review (User author, Item item, String description, int rating){
        this.author = author;
        this.description = description;
        this.id = null;
        this.item = item;
        this.rating = rating;
    }

    /**
     * This constructer is called to create an existing review (after DB)
     * @param id
     * @param item
     * @param description
     * @param rating
     */
    public Review (String id, Item item, String description, int rating){
        this.description = description;
        this.id = id;
        this.item = item;
        this.rating = rating;
    }

    /*
     * returns the reviewed item, does not matter if it was a song, abum, or artist
     */
    public Item getItem (){
        return item;
    }

    /**
     * returns the item description
     * @return
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * returns the item rating
     */
    public int getRating(){
        return this.rating;
    }

    public User getAuthor(){
        return this.author;
    }


}
