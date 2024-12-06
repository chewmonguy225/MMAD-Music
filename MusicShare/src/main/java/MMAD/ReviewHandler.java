package MMAD;

import java.util.ArrayList;

public class ReviewHandler {
    private static ReviewHandler rh = null;
    private static DBHandler dbh = DBHandler.access();
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
        dbh.createReview(ah.getCurrentUser(), r); // Add the review to the database
    }

    public void deleteReview(Review review){
        //qe.deleteReview(review);
    }

    public ArrayList<Review> getUserReviews(String username){
        ArrayList<Review> userReviews = new ArrayList<Review>();
        Login login = new Login(username, null);
        ArrayList<String> reviewInfo = dbh.getUserReviews(login);
        for(String review: reviewInfo){

        }

        return userReviews;
    }

}
