package MMAD;

import java.util.ArrayList;
import java.util.Scanner;

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
        ArrayList<String> ar = new ArrayList();
        ar.add("home");
        ar.add("playlist");
        ar.add("friends");
        ar.add("review");
        ar.add("song search");
        ar.add("album search");
        ar.add("artist search");
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
                    option = login();
                    if (option == 1) {
                        currentMenu = "home";
                    }
                    break;
                case "signup":
                    option = signup();
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

    private static int login() {
        d.loginUsername();
        String username = ui.getString();
        if (username.equals("0"))
            return 0;
        d.loginPassword();
        String password = ui.getString();
        if (password.equals("0"))
            return 0;

        if (ah.loginAttempt(username, password)) {
            d.successfulLogin(username);
            return 1;
        } else {
            d.invalidLogin();
            return -1; // simulates username or password invalid
        }

    }

    private static int signup() {
        d.loginUsername();
        String username = ui.getString();
        if (username.equals("0"))
            return 0;
        d.loginPassword();
        String password = ui.getString();
        if (password.equals("0"))
            return 0;

        if (ah.createAccount(username, password)) {
            d.successfulSignup();
            return 1;
        } else {
            d.unsuccessfulSignup();
            return -1;
        }
    }

    private static void RouteHome() {// routes all requests from home page
        int option = -1;
        while (option < 0 || option > 6) {
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
                    }

                    break;
                case "friends":
                    break;
                case "review":
                    break;
                case "song search":
                    option = routeSongSearch();
                    if (option == -1) {
                        currentMenu = "home";
                    }
                    break;
                case "album search":
                    option = routeAlbumSearch();
                    if (option == -1) {
                        currentMenu = "home";
                    }
                    break;
                case "artist search":
                    break;
                default:
                    if (option != 0) {
                        RouteHome();
                    }
            }
        }
        d.exit();
    }

    private static int routePlaylist() {// routes all playlist requests
        int option = ph.displayPlaylist(ui, d);

        return option;

    }

    public static int routeSongSearch() {
        d.searchPrompt();
        String songTitle = ui.getString();
        int option = ih.searchSong(songTitle, ui, d);
        c.songOptionMenu(ih.getSelectedSong());
        return option;
    }

    public static int routeAlbumSearch(){
        d.searchPrompt();
        String albumTitle = ui.getString();
        int option = ih.searchAlbum(albumTitle, ui, d);
        c.albumOptionMenu(ih.getSelectedAlbum());
        return option;
    }

    public void songOptionMenu(Song song) {
        d.songOptionMenu();
        switch (ui.getInt()) {
            case 1:// add song to playlist
                ph.addSongToPlaylist(null, song);
                break;
            case 2:// remove song from playlist
                ph.removeSongFromPlaylist(null, song);
                break;
            case 3:// write review
                d.reviewPrompt();
                String description = ui.getString();
                d.ratingPrompt();
                int rating = ui.getInt();
                rh.createReview(null, song, description, rating);
                break;
            case 4:// delete review
                //rh.deleteReview();
                break;
            case 5:// previous page (routeSongMenu)
                routeSongSearch();
                break;
            default:// exit to main menu
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
                rh.createReview(null, album, description, rating);
                break;
            case 2:// delete review
                //rh.deleteReview();
                break;
            case 3:// previous page (routeSongMenu)
                routeAlbumSearch();
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
        final DBHandler dbh = DBHandler.access();
        Login login = new Login("John", "123123");
        User user = new User(login);
        // dbh.createUser(user.getLogin().getUsername(), user.getLogin().getPassword());
        route1(user.getLogin());
    }
}
