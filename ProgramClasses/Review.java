public class Review {
    private String id;
    private Item item;
    private String description;
    private int rating;

    public Review (String id, Item item, String description, int rating){
        this.description = description;
        this.id = id;
        this.item = item;
        this.rating = rating;
    }

    public Item getItem (){
        return item;
    }    





    
}
