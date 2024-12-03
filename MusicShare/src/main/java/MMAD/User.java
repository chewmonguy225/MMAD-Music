package MMAD;
import java.util.*;
public class User {
    private Login loginCredentials;
    private ArrayList<User> followList;
    private ArrayList<Review> reviews;
    private Playlist playlist;

    public User (Login cred){
        loginCredentials = cred;
        //Account handler will call database to validate/create user
        playlist = new Playlist();
        followList = new ArrayList<>();
        reviews = new ArrayList<>();
    }

    public Login getLogin(){
        return loginCredentials;
    }

    public String getUsername(){
        if(loginCredentials != null)
            return loginCredentials.getUsername();
        return "";
    }

    public ArrayList<Item> getReviewedItems(){
        ArrayList<Item> reviewedItems = null;

        for(int i = 0; i < reviews.size(); i++){
            reviewedItems.add(reviews.get(i).getItem());
        }
        return reviewedItems;
    }
}
