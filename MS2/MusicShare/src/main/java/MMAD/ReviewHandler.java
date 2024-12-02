package MMAD;

public class ReviewHandler {
    private static ReviewHandler rh = null;

    private ReviewHandler(){

    }

    public ReviewHandler access(){
        if(rh == null){
            rh = new ReviewHandler();
        }
        return rh;
    }

    public void createReview(Login login, Item item, String description, int rating){
        Review r = new Review(item, description, rating);//creates review object

        //Use the following condition to find the item in the DB
        if(item instanceof Song){
            //search for song with item.getID()
            //if song exists, save review object "r" in the DB
        }else if(item instanceof Album){
            //search if album then will record in the DB as an album object
        }else if (item instanceof Artist){

        } else {
            //error
        }
    }
}