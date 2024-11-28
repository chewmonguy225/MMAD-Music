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

    public void createReview(Login login, Item item){
        //Use the following condition to find the item in the DB
        if(item instanceof Song){
            //search for song with item.getID()
            //if song exists, record the review input and save it to DB
        }else if(item instanceof Album){

        }else if (item instanceof Artist){

        } else {
            //error
        }
    }
}
