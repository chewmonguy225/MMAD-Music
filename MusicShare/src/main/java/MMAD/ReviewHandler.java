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
        if(item instanceof Song){
            
        }else if(item instanceof Album){

        }else if (item instanceof Artist){

        } else {
            //error
        }
    }
}
