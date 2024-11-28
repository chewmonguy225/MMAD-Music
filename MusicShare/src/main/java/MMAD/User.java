package MMAD;
import java.util.*;
public class User {
    private Login loginCredentials;
    private ArrayList<User> followList;
    private ArrayList<Review> reviews;

    public User (Login cred){
        loginCredentials = cred;
        //call database to validate/create user
    }

    public Login getLogin(){
        return loginCredentials;
    }

    public ArrayList<Item> getReviewedItems(){
        ArrayList<Item> reviewedItems = null;

        for(int i = 0; i < reviews.size(); i++){
            reviewedItems.add(reviews.get(i).getItem());
        }
        return reviewedItems;
    }
}
