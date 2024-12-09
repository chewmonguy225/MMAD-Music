package MMAD;

import java.util.ArrayList;

public class AccountHandler {
    private static AccountHandler ah;
    private static DBHandler dbh = DBHandler.access();
    private static ReviewHandler rh = ReviewHandler.access();
    private static PlaylistHandler ph = PlaylistHandler.access();
    private final int itemsPerPage = 5;
    Login currentUser;
    User currentUserObject;

    private AccountHandler(){
    }

    public static AccountHandler access(){
        if(ah == null){
            ah = new AccountHandler();
        }
        return ah;
    }

    public Login getCurrentUser(){
        return currentUser;
    }

    public User getCurrentUserObject(){
        return currentUserObject;
    }

    public int login(UI ui, Display d){
        d.loginUsername();
        String username = ui.getString();
        if(username.equals("0"))
            return 0;
        d.loginPassword();
        String password = ui.getString();
        if(password.equals("0"))
            return 0;

        if(loginAttempt(username, password))
        {   
            d.successfulLogin(username);
            return 1;
        } else {
            d.invalidLogin();
            return -1; //simulates username or password invalid
        }   
    }

    public boolean loginAttempt(String username, String password){
        Login login = new Login(username, password);
        boolean result = login.attemptLogin(dbh);
        if(result){
            currentUser = new Login(username, password);
            currentUserObject = new User(currentUser);
            currentUserObject = getCompleteUser(currentUserObject);
        }
        return result;
    }

    public boolean createAccount(String username, String password){
        Login login = new Login(username, password);
        currentUser = new Login(username, password);
        currentUserObject = new User(currentUser);
        return dbh.createUser(login);
    }

    public int signup(UI ui, Display d){
        d.loginUsername();
        String username = ui.getString();
        if(username.equals("0"))
            return 0;
        d.loginPassword();
        String password = ui.getString();
        if(password.equals("0"))
            return 0;

        if(ah.createAccount(username, password)){
            d.successfulSignup();
            currentUser = new Login(username, password);
            currentUserObject = new User(currentUser);
            return 1;
        } else {
            d.unsuccessfulSignup();
            return -1;
        }
    }

    

    

    public int changePassword(UI ui, Display d){
        d.changePassword();
        String newPass = ui.getString();
        Login login = new Login(currentUser.getUsername(), newPass);
        if(dbh.changePassword(login)){
            currentUser = new Login(currentUser.getUsername(), newPass);
            d.passwordChanged();
            return 1;
        }
        //query to change password - return 
        return -1;
    }

    public int logout(){
        currentUser = null;
        return 1;
    }

    public boolean deleteAccount(){
        if(currentUser != null)
            return dbh.deleteUser(currentUser);
        return false;
    }

    // @return 1 if the friend is added succesfully. Return 2 if the friend has already been added. Return 3 if the 'friend' does not exist. Return -1 if there is an error.
    public int followUser(User friendUser){
        int response = dbh.addFriend(ah.getCurrentUserObject().getLogin(), friendUser.getLogin());
        if(response == 1){
            currentUserObject.addToFollowlist(friendUser);
        }
        return response;
    }

    public int unfollowUser(User friendUser){
        int response = dbh.unfollowFriend(ah.getCurrentUserObject().getLogin(), friendUser.getLogin());
        if(response == 2){
            currentUserObject.addToFollowlist(friendUser);
        }
        return response;
    }

    public User getUserPublicSharables(User user){
        Login usernameOnly = new Login(user.getLogin().getUsername(), "");
        ArrayList<User> followings = new ArrayList<User>();
        User publicUser = new User(usernameOnly, followings, user.getReviews(), user.getPlaylist());
        return publicUser;
    }

    public User searchUser(String username, UI ui, Display d) {
        ArrayList<String> results = dbh.searchUsers(username);
        ArrayList<User> users = new ArrayList<User>();
        for(String usernameInfo : results){
            Login login = new Login(usernameInfo, null);
            User user = new User(login);
            users.add(user);
        }

        int totalPages = (int) Math.ceil((double) results.size() / itemsPerPage);
        int currentPage = 1;

        
        User selected = selectUser(users, currentPage, totalPages, ui, d);
        return selected;
    }

    public User listUsers(ArrayList<User> users, UI ui, Display d){
        int totalPages = (int) Math.ceil((double) users.size() / itemsPerPage);
        int currentPage = 1;
        
        User selected = selectUser(users, currentPage, totalPages, ui, d);
        return selected;
    }

    private User selectUser(ArrayList<User> searchResults, int currentPage, int totalPages, UI ui, Display display) {
        display.displayUserSearchResult(searchResults, currentPage, totalPages);
        int option = ui.getInt();
        User selected = null;
        while (selected == null && !((option == (itemsPerPage + 1) && currentPage == 1) || (option == (itemsPerPage + 2) && currentPage == totalPages))) {
            if (option >= 1 && option <= itemsPerPage) {
                selected = searchResults.get(((currentPage - 1) * itemsPerPage) + (option - 1));
                selected = getCompleteUser(selected);
            } else if (option == (itemsPerPage + 1) && currentPage != 1) {
                currentPage--;
                display.displayUserSearchResult(searchResults, currentPage, totalPages);
                option = ui.getInt();
            } else if (option == (itemsPerPage + 2) && currentPage != totalPages) {
                currentPage++;
                display.displayUserSearchResult(searchResults, currentPage, totalPages);
                option = ui.getInt();
            }else{
                display.invalidOption();
            }
        }
        
        return selected;
        
    }

    public void setFollowing(User user) {
        ArrayList<User> followingList = new ArrayList<User>(); 

        ArrayList<String> followingInfo = dbh.getFriendsList(user.getLogin()); 
        if (!followingInfo.isEmpty()) {
            for (String username : followingInfo) {
                Login friendLogin = new Login(username, null);
                User friendUser = new User(friendLogin);
                followingList.add(friendUser);
            }
        }

        user.setFollowing(followingList);
    }

    public void setFollower(User user) {
        ArrayList<User> followingList = new ArrayList<User>(); 

        ArrayList<String> followerInfo = dbh.getFollowerList(user.getLogin()); 
        if (!followerInfo.isEmpty()) {
            for (String username : followerInfo) {
                Login friendLogin = new Login(username, null);
                User friendUser = new User(friendLogin);
                followingList.add(friendUser);
            }
        }

        user.setFollowersList(followingList);
    }

    private User getCompleteUser(User user){
        user.setPlaylist(ph.getPlaylist(user));
        rh.setUserReviews(user);
        setFollowing(user);
        setFollower(user);
        return user;
    }

}


