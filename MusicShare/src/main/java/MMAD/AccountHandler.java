package MMAD;

import java.util.ArrayList;

public class AccountHandler {
    private static AccountHandler ah;
    private static DBHandler dbh = DBHandler.access();
    private static ReviewHandler rh = ReviewHandler.access();
    private final int itemsPerPage = 5;
    Login currentUser;
    User user; 

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
            currentUser = new Login(username, password);
            return 1;
        } else {
            d.invalidLogin();
            return -1; //simulates username or password invalid
        }   
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
            return 1;
        } else {
            d.unsuccessfulSignup();
            return -1;
        }
    }

    public boolean createAccount(String username, String password){
        Login login = new Login(username, password);
        return dbh.createUser(login);
    }

    public static boolean loginAttempt(String username, String password){
        Login login = new Login(username, password);
        return login.attemptLogin(dbh);
    }

    public int changePassword(UI ui, Display d){
        d.changePassword();
        String newPass = ui.getString();
        currentUser = new Login(currentUser.getUsername(), newPass);
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

    public void followUser(User friendUser){
        user.addToFollowlist(friendUser);
    }

    public User getUserPublicSharables(String username){
        Login login = new Login(username, null);
        Playlist playlist = null;
        ArrayList<User> followList = null;
        //ph.getUserPlaylist(login);
        ArrayList<Review> thReviews = rh.getUserReviews(username);
        User publicUser = new User(login, followList, thReviews, playlist);

        return publicUser;
    }


    public User searchUser(String username, UI ui, Display d) {
        ArrayList<String> results = dbh.searchUsers(username);

        int totalPages = (int) Math.ceil((double) results.size() / itemsPerPage);
        int currentPage = 1;

        
        User selected = selectUser(results, currentPage, totalPages, ui, d);
        return selected;
    }

    private User selectUser(ArrayList<String> searchResults, int currentPage, int totalPages, UI ui, Display display) {
        display.displayUserSearchResult(searchResults, currentPage, totalPages);
        int option = ui.getInt();
        String selected = null;
        while (selected == null && !((option == 6 && currentPage == 1) || (option == 7 && currentPage == totalPages))) {
            if (option >= 1 && option <= 5) {
                selected = searchResults.get(((currentPage - 1) * itemsPerPage) + (option - 1));
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
        User selectedUser = getUserPublicSharables(selected);
        return selectedUser;
    }
}
