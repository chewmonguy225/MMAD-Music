package MMAD;

public class ReviewHandler {
    private static ReviewHandler rh = null;
    private QueryExecutor qe = new QueryExecutor();
    private static AccountHandler ah = AccountHandler.access();

    private ReviewHandler(){

    }

    public static ReviewHandler access(){
        if(rh == null){
            rh = new ReviewHandler();
        }
        return rh;
    }

    public void createReview(Login login, Item item, String description, int rating) {
        Review r = new Review(item, description, rating); // Create review object
        qe.createReview(ah.getCurrentUser(), r); // Add the review to the database
    }

    public void deleteReview(Review review){
        //qe.deleteReview(review);
    }

}
