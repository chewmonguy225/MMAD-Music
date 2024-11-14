package Mickael;

public class Review {
    private String id;
    private ReviewableItem item;
    private String description;
    private int rating;

    public Review (String id, ReviewableItem item, String description, int rating){
        this.description = description;
        this.id = id;
        this.item = item;
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public ReviewableItem getItem (){
        return item;
    }    

    public int getRating() {
        return rating;
    }
}
