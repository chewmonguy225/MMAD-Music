import java.util.*;
public class User {
    private Credential loginCredentials;
    private ArrayList<User> followList;
    private ArrayList<Review> reviews;

    public User (Credential cred){
        loginCredentials = cred;
        //call database to validate/create user
    }

    public ArrayList<ReviewableItem> getReviewedItems(){
        ArrayList<ReviewableItem> reviewedItems = null;

        for(int i = 0; i < reviews.size(); i++){
            reviewedItems.add(reviews.get(i).getItem());
        }
        return reviewedItems;
    }
}
