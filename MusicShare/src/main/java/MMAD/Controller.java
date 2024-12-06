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

    private static ArrayList<String> populateMenus(){
        ArrayList<String> ar= new ArrayList();
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
                    if(option == 1){
                        currentMenu = "home";
                    }
                    break;
                case "signup":
                    option = ah.signup(ui, d);
                    if(option == 1)
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
        if (!currentMenu.equals("exit"))
            d.exit();
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
                case "home":
                    RouteHome();
                case "playlist":
                    option = routePlaylist();
                    if (option == -1) {
                        currentMenu = "home";
                    }

                    break;
                case "friends"://new menu asks to view current friends or add new friend
                    //unrelated -- add a account menu that allows user to change password or delete account
                    break;
                case "review":
                    break;
                case "search":
                    option = routeSearch();
                    if (option == -1) {
                        currentMenu = "home";
                    }
                    currentMenu = "home";
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
                    option =0;
            }
        }
        if(option == 0)
            d.exit();
    }
    private static int logout(){
        return ah.logout();
    }

    private static int accountSettings(){
        d.accountSettings();
        int option = ui.getInt();
        if(option == 0)
            return 0;
        if (option == 1){
            int result = -1;
            do{
                result =  ah.changePassword(ui, d);//need to change logic to allow reprompting of new password.
            }while(result == -1);
            return result;
        } 
        if(option == 2){
            ah.deleteAccount();
            ah.logout();
            return 2;
        }
        return -1;
    }
    private static int routePlaylist(){//routes all playlist requests
        int option = ph.displayPlaylist(ui, d);

        return option;

    }

    public static int routeSongSearch() {
        d.searchPrompt();
        String songTitle = ui.getString();
        Song selectedSong = ih.searchSong(songTitle);
        int option = -1;
        if(selectedSong == null){
            routeSearch();
        }else{
            c.songOptionMenu(selectedSong);
        }
        return option;
    }

    public static int routeAlbumSearch() {
        d.searchPrompt();
        String albumTitle = ui.getString();
        Album selected = ih.searchAlbum(albumTitle);
        int option = -1;
        if(selected == null){
            routeSearch();
        }else{
            c.albumOptionMenu(selected);
        }
        return option;
    }

    public static int routeArtistSearch() {
        d.searchPrompt();
        String artistName = ui.getString();
        Artist selected= ih.searchArtist(artistName);
        int option = -1;
        if(selected == null){
            routeSearch();
        }else{
            c.artistOptionMenu(selected);
        }
        return option;
    }

    public void songOptionMenu(Song song) {
        d.songOptionMenu();
        switch (ui.getInt()) {
            case 0:
                return 0;
            case 1:// add song to playlist
                ph.addSongToPlaylist(ah.getCurrentUser(), song);
                break;
            case 2:// remove song from playlist
                ph.removeSongFromPlaylist(ah.getCurrentUser(), song);
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
            default:// exit to main menu
                return -1;
        }
    }

    public void albumOptionMenu(Album album) {
        d.albumOptionMenu();
        switch (ui.getInt()) {
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
            default:// exit to main menu
        }
    }

    public void artistOptionMenu(Artist artist) {
        d.itemOptionMenu();
        switch (ui.getInt()) {
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
            default:// exit to main menu
        }
    }
    // --------------------------------------------------------------------------------------

    public void routeSongMenu() {
        switch (ui.getInt()) {
            case 0:// previous page, if no previous page then page 1
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                // create Song object "song" from option 1-5 and call songOptionMenu(song)
                break;
            case 6:// next page, if no next page then page 1
                break;
            default:// exit to main menu
        }
    }

    

    public static void route1(Login login) {
        Artist artist = new Artist("234234", "BLUR");
        Album album = new Album("123123", "album1", artist);
        Song song = new Song("12", "Song", artist, album);
        ih.addSongToDB(song);
        ih.addSongToDB(song);

        ph.addSongToPlaylist(login, song);
        ph.clearPlaylist(login);
    }

    public static void main(String[] args) {
        DBHandler dbh = DBHandler.access();
        Login login = new Login("John", "123123");
        User user = new User(login);
        // dbh.createUser(user.getLogin().getUsername(), user.getLogin().getPassword());
        route1(user.getLogin());
    }

    public static int routeSearch(){
        d.searchOptions();
        int option = ui.getInt();
        switch (option) {
            case 1:
                RouteHome();
                break;
            case 2:
                routeSongSearch();
                break;
            case 3:
                routeAlbumSearch();
                break;
            case 4:
                routeArtistSearch();
                break;
            default:
                d.invalidOption();
                routeSearch();
                break;
        }
        option = -1;
        return option;
    }

}

