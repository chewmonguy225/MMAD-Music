package MMAD;

import java.util.ArrayList;

public class Controller {
    private static Controller c;
    private static UI ui = UI.access();
    private static Display d = Display.access();
    private static AccountHandler ah = AccountHandler.access();
    private static ItemHandler ih = ItemHandler.access();
    private static PlaylistHandler ph = PlaylistHandler.access();
    private static ReviewHandler rh = ReviewHandler.access();
    private static ArrayList<String> menuList = populateMenus();
    private static String currentMenu = "login or signup";

    private static ArrayList<String> populateMenus() {
        ArrayList<String> ar = new ArrayList<String>();
        ar.add("home");
        ar.add("playlist");
        ar.add("friends");
        ar.add("review");
        ar.add("search");
        ar.add("account settings");
        ar.add("logout");
        return ar;
    }

    private Controller() {
    }

    public static Controller access() {
        if (c == null) {
            c = new Controller();
        }
        return c;
    }

    public void routeLogin() {
        int option = -1;

        while (option != 0) {// 0 input will exit system
            switch (currentMenu) {
                case "login or signup":
                    option = loginOrSignup();
                    if (option == 1) {
                        currentMenu = "login";
                    } else if (option == 2) {
                        currentMenu = "signup";
                    }
                    break;
                case "login":
                    option = ah.login(ui, d);
                    if (option == 1) {
                        currentMenu = "home";
                    }
                    break;
                case "signup":
                    option = ah.signup(ui, d);
                    if (option == 1)
                        currentMenu = "home";
                    break;
                case "home":
                    RouteHome();
                    currentMenu = "exit";
                    break;
                default:
                    option = 0;
            }
        }
        if (!currentMenu.equals("exit")) {
            exit();
        }
    }

    private static void exit() {
        d.exit();
        System.exit(0);
    }

    private static int loginOrSignup() {
        d.loginOrSignup();
        int userInputInt = ui.getInt();

        if (userInputInt == 1) {
            return 1;
        } else if (userInputInt == 2) {
            return 2;
        } else if (userInputInt == 0) {
            return 0;
        }
        return -1;
    }

    private static void RouteHome() {// routes all requests from home page
        int option = -1;
        while (option < 0 || option > 8) {
            d.home();
            option = ui.getInt();
        }
        currentMenu = menuList.get(option);
        while (option != 0) {// 0 input will exit system
            switch (currentMenu) {
                case "playlist":
                    option = routePlaylist();
                    if (option == -1) {
                        currentMenu = "home";
                        RouteHome();
                    }
                    break;
                case "friends":// new menu asks to view current friends or add new friend
                    option = -1;
                    currentMenu = "home";
                    // unrelated -- add a account menu that allows user to change password or delete
                    // account
                    break;
                case "review":
                    // option = routeReview();
                    option = -1;
                    currentMenu = "home";
                    break;
                case "search":
                    option = routeSearch();
                    if (option == -1) {
                        currentMenu = "home";
                    }
                    break;
                case "account settings":
                    option = accountSettings();
                    if (option == 2)
                        currentMenu = "login or signup";
                    c.routeLogin();
                    currentMenu = "exit";
                    break;
                case "logout":
                    option = logout();
                    currentMenu = "login or signup";
                    c.routeLogin();
                    break;
                default:
                    option = 0;
            }
        }
        if (option == 0)
            exit();
    }

    private static int logout() {
        return ah.logout();
    }

    private static int accountSettings() {
        d.accountSettings();
        int option = ui.getInt();
        if (option == 0)
            return 0;
        if (option == 1) {
            int result = -1;
            do {
                result = ah.changePassword(ui, d);// need to change logic to allow reprompting of new password.
            } while (result == -1);
            return result;
        }
        if (option == 2) {
            ah.deleteAccount();
            ah.logout();
            return 2;
        }
        return -1;
    }

    private static int routePlaylist() {// routes all playlist requests
        int option = ph.displayPlaylist(ui, d);

        return option;

    }

    public static int routeSearch() {
        d.searchOptions();
        int option = ui.getInt();
        switch (option) {
            case 1:
                routeSongSearch();
                break;
            case 2:
                routeAlbumSearch();
                break;
            case 3:
                routeArtistSearch();
                break;
            case 4:
                routeUserSearch();
                break;
            case 5:
                RouteHome();
                break;
            default:
                d.invalidOption();
                routeSearch();
                break;
        }
        option = -1;
        return option;
    }

    public static int routeSongSearch() {
        d.searchPrompt("Song");
        String songTitle = ui.getString();
        Song selectedSong = ih.searchSong(songTitle);
        int option = -1;
        if (selectedSong == null) {
            routeSearch();
        } else {
            ih.addSongToDB(selectedSong);
            c.songOptionMenu(selectedSong);
        }
        return option;
    }

    public static int routeAlbumSearch() {
        d.searchPrompt("Album");
        String albumTitle = ui.getString();
        Album selected = ih.searchAlbum(albumTitle);
        int option = -1;
        if (selected == null) {
            routeSearch();
        } else {
            ih.addAlbumToDB(selected);
            c.albumOptionMenu(selected);
        }
        return option;
    }

    public static int routeArtistSearch() {
        d.searchPrompt("Artist");
        String artistName = ui.getString();
        Artist selected = ih.searchArtist(artistName);
        int option = -1;
        if (selected == null) {
            routeSearch();
        } else {
            ih.addArtistToDB(selected);
            c.artistOptionMenu(selected);
        }
        return option;
    }

    public void songOptionMenu(Song song) {
        d.songOptionMenu();
        switch (ui.getInt()) {
            case 0:
                exit();
            case 1:// add song to playlist
                ph.addSongToPlaylist(ah.getCurrentUser(), song);
                RouteHome();
                break;
            case 2:// remove song from playlist
                ph.removeSongFromPlaylist(ah.getCurrentUser(), song);
                RouteHome();
                break;
            case 3:// write review
                d.reviewPrompt();
                String description = ui.getString();
                d.ratingPrompt();
                int rating = ui.getInt();
                rh.createReview(ah.getCurrentUser(), song, description, rating);
                break;
            case 4:// delete review
                   // rh.deleteReview();
                break;
            case 5:// previous page (routeSongMenu)
                routeSongSearch();
                break;
            case 6:// previous page (routeSongMenu)
                RouteHome();
                break;
            default:
                d.invalidOption();
                songOptionMenu(song);
                break;
        }
    }

    public void albumOptionMenu(Album album) {
        d.albumOptionMenu();
        switch (ui.getInt()) {
            case 0:
                exit();
            case 1:// write review
                d.reviewPrompt();
                String description = ui.getString();
                d.ratingPrompt();
                int rating = ui.getInt();
                rh.createReview(ah.getCurrentUser(), album, description, rating);
                break;
            case 2:// delete review
                   // rh.deleteReview();
                break;
            case 3:// previous page (routeSongMenu)
                routeAlbumSearch();
                break;
            case 4:
                RouteHome();
                break;
            default:
                d.invalidOption();
                albumOptionMenu(album);
                break;
        }
    }

    public void artistOptionMenu(Artist artist) {
        d.itemOptionMenu();
        switch (ui.getInt()) {
            case 0:
                exit();
            case 1:// write review
                d.reviewPrompt();
                String description = ui.getString();
                d.ratingPrompt();
                int rating = ui.getInt();
                rh.createReview(ah.getCurrentUser(), artist, description, rating);
                break;
            case 2:// delete review
                   // rh.deleteReview();
                break;
            case 3:// previous page (routeSongMenu)
                routeArtistSearch();
                break;
            case 4:
                RouteHome();
                break;
            default:
                d.invalidOption();
                artistOptionMenu(artist);
                break;
        }
    }


    public static void routeUserSearch(){
        d.searchPrompt("User");
        String username = ui.getString();
        User selected = ah.searchUser(username, ui, d);
        routeUserOption(selected);
    }

    public static int routeUserOption(User user){
        d.otherUserOptionMenu();
        int option = ui.getInt();
        switch (option) {
            case 1:
                //follow user
                break;
            case 2:
                //unfollow user
                break;
            case 3:
                //display user reviews
                break;
            case 4:
                //display playlist
                break;
            case 5:
                routeUserSearch();
                break;
            case 6:
                RouteHome();
                break;
            default:
                d.invalidOption();
                routeSearch();
                break;
        }
        option = -1;
        return option;
    }


    public void routeReview() {

    }

    public void routeReviewOptionsMenu() {
        d.reviewOptionMenu();
        switch (ui.getInt()) {
            case 0:
                viewReviews();
                break;
            case 1:
                //rh.viewReview(ah.getCurrentUser());
                exit();
                break;
            case 2:
                exit();
                break;
            case 3:
                exit();
                break;
            case 4:
                exit();
                break;
            case 5:
                exit();
                break;
            default:
               // d.invalidOption();
                songOptionMenu(song);
                break;
        }
    }

    public void viewReviews(){
        //rh.getReviews
    }
    

}