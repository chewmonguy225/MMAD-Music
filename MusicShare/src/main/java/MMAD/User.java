package MMAD;
import java.util.*;
public class User {
    private Login loginCredentials;
    private ArrayList<User> followList;
    private ArrayList<User> followersList;
    private ArrayList<Review> reviews;
    private Playlist playlist;

    public User(Login cred){
        loginCredentials = cred;
        //Account handler will call database to validate/create user
        playlist = new Playlist();
        followList = new ArrayList<>();
        reviews = new ArrayList<>();
    }

    public User(Login cred, ArrayList<User> followList, ArrayList<Review> reviews, Playlist playlist) {
        this.loginCredentials = cred;
        this.playlist = playlist;
        this.followList = followList;
        this.reviews = reviews;
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
        ArrayList<Item> reviewedItems = new ArrayList<>();

        for(int i = 0; i < reviews.size(); i++){
            reviewedItems.add(reviews.get(i).getItem());
        }
        return reviewedItems;
    }

    public Playlist getPlaylist(){
        return playlist;
    }

    public void setPlaylist(Playlist playlist){
        this.playlist = playlist;
    }

    public void addToFollowlist(User userToAdd){
        followList.add(userToAdd);
    }
    
    public boolean removeFromFollowList(User userToRemove) {
        String username = userToRemove.getLogin().getUsername();
        for (User user : followList) {
            if (user.getUsername().equals(username)) {
                followList.remove(user);
                return true;
            }
        }
        return false;
    }
    
    public void setReviews(ArrayList<Review> reviews){
        this.reviews = reviews;
    }
    
    public ArrayList<Review> getReviews(){
        return reviews;
    }

    public void addReview(Review review){
        reviews.add(review);
    }

    public void setFollowing(ArrayList<User> followList){
        this.followList = followList;
    }

    public ArrayList<User> getFollowingList(){
        return followList;
    }

    public void setFollowersList(ArrayList<User> followersList){
        this.followersList = followersList;
    }

    public ArrayList<User> getFollowerList(){
        return followersList;
    }

    public boolean removeReview(Review review) {
        Item itemToRemove = review.getItem();
        
        String reviewID = "";
        if(itemToRemove instanceof Song){
            reviewID = "s" + itemToRemove.getID();
        }else if(itemToRemove instanceof Album){
            reviewID = "al" + itemToRemove.getID();
        }else if(itemToRemove instanceof Album){
            reviewID = "ar" + itemToRemove.getID();
        }
        for (Review reviewed : reviews) {
            String reviewedID = "";
            if(itemToRemove instanceof Song){
                reviewedID = "s" + itemToRemove.getID();
            }else if(itemToRemove instanceof Album){
                reviewedID = "al" + itemToRemove.getID();
            }else if(itemToRemove instanceof Album){
                reviewedID = "ar" + itemToRemove.getID();
            }

            if (reviewID.equals(reviewedID)) {
                reviews.remove(reviewed);
                return true;
            }
        }
        return false;
    }
}

