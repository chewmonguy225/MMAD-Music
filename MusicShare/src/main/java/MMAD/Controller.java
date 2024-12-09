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
                case "friends":
                    c.routeFriends();
                    option = -1;
                    currentMenu = "home";
                    // unrelated -- add a account menu that allows user to change password or delete
                    // account
                    break;
                case "review":
                    c.routeReview();
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
                    if (option == 2) {
                        currentMenu = "login or signup";
                        c.routeLogin();
                    }
                    if (option == 0) {
                        currentMenu = "exit";
                    } else {
                        currentMenu = "home";
                        RouteHome();
                    }
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
                if (result == -1) {
                    d.invalidPassword();
                }
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
        int option = ph.displayPlaylist(ah.getCurrentUserObject().getPlaylist(), ui, d);
        while (option == -2) {
            option = ph.displayPlaylist(ah.getCurrentUserObject().getPlaylist(), ui, d);
        }
        return option;

    }

    public static int routeSearch() {
        d.searchOptions();
        int option = ui.getInt();
        switch (option) {
            case 0:
                exit();
                break;
            case 1:
                routeSongSearch();
                routeSearch();
                option = -1;
                break;
            case 2:
                routeAlbumSearch();
                routeSearch();
                option = -1;
                break;
            case 3:
                routeArtistSearch();
                routeSearch();
                option = -1;
                break;
            case 4:
                routeUserSearch();
                routeSearch();
                option = -1;
                break;
            case 5:
                RouteHome();
                option = -1;
                break;
            default:
                d.invalidOption();
                routeSearch();
                break;
        }
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
            itemOptionMenu(selectedSong);
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
            itemOptionMenu(selected);
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
            itemOptionMenu(selected);
        }
        return option;
    }

    public static void routeUserSearch() {
        d.searchPrompt("User");
        String username = ui.getString();
        User selected = ah.searchUser(username, ui, d);
        if (selected != null) {
            userOptionMenu(selected);
        }
        RouteHome();
    }

    private static void itemOptionMenu(Item item) {
        if (item instanceof Song) {
            d.songOptionMenu(); // Display options specific to Song
        } else {
            d.itemOptionMenu(); // Display general options for other Item types
        }

        int option = ui.getInt(); // Get the user's choice

        switch (option) {
            case 0: // Exit the menu
                exit();
                break;

            case 1: // Write a review
                c.writeReview(item); // Unified handling for all Item types
                break;

            case 2: // Delete a review
                Review reviewToDelete = new Review(ah.getCurrentUserObject(), item, "", 0);
                rh.deleteReview(reviewToDelete);
                break;

            case 3: // View all user reviews for this item
                ArrayList<Review> reviews = rh.getItemReviews(item);
                rh.displayReviews(reviews, ui, d);
                break;

            case 4: // Song-specific: Add to playlist
                if (item instanceof Song) {
                    Song song = (Song) item;
                    ph.addSongToPlaylist(ah.getCurrentUser(), song);
                    RouteHome();
                } else {
                    d.invalidOption();
                }
                break;

            case 5: // Song-specific: Remove from playlist
                if (item instanceof Song) {
                    Song song = (Song) item;
                    ph.removeSongFromPlaylist(ah.getCurrentUser(), song);
                    RouteHome();
                } else {
                    d.invalidOption();
                }
                break;

            case 6:
                routeSearch();
                break;

            case 7: // Go to the home page
                RouteHome();
                break;

            default: // Handle invalid options
                d.invalidOption();
                itemOptionMenu(item);
                break;
        }
    }

    private static int userOptionMenu(User user) {
        d.otherUserOptionMenu();
        int option = ui.getInt();
        switch (option) {
            case 0:
                exit();
                break;
            case 1: // follow
                ah.followUser(user);
                routeSearch();
                break;
            case 2: // unfollow
                ah.unfollowUser(user);
                break;
            case 3: // view review
                ArrayList<Review> thReviews = user.getReviews();
                rh.displayReviews(thReviews, ui, d);
                break;
            case 4: // displayPlaylist
                Playlist playlist = ph.getPlaylist(user);
                ph.displayPlaylist(playlist, ui, d);
                break;
            case 5: // merge Playlists
                Playlist mergedPlaylist = ph.mergePlaylist(ah.getCurrentUserObject(), user);
                ph.mergePlaylist(ah.getCurrentUserObject(), user).getPlaylist();
                ph.displayOthersPlaylist(mergedPlaylist, ui, d);
                break;
            case 6:
                routeUserSearch();
                break;
            case 7:
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

    private Review writeReview(Item item) {
        d.reviewPrompt();
        String description = ui.getString();
        d.ratingPrompt();
        int rating = ui.getInt();
        return (rh.createReview(ah.getCurrentUserObject(), item, description, rating));

    }

    private void routeFriends() {
        d.friendOptionMenu();
        User selected;
        int option = ui.getInt();
        switch (option) {
            case 0:
                exit();
                break;
            case 1: // view following
                ArrayList<User> following = ah.currentUserObject.getFollowingList();
                selected = ah.listUsers(following, ui, d);
                if (selected != null) {
                    userOptionMenu(selected);
                }
                routeFriends();
                option = -1;
                break;
            case 2: // view followers
                // view followers
                ArrayList<User> followers = ah.currentUserObject.getFollowerList();
                selected = ah.listUsers(followers, ui, d);
                if (selected != null) {
                    userOptionMenu(selected);
                }
                routeFriends();
                option = -1;
                break;
            case 3: // find users
                routeUserSearch();
                option = -1;
                break;
            default:
                d.invalidOption();
                routeFriends();
                break;
        }
    }

    private void routeReview() {
        d.reviewOptionMenu();
        Review selectedReview = null;
        int option = ui.getInt();
        switch (option) {
            case 0:
                exit();
                break;
            case 1: // view Community Review
                selectedReview = rh.displayReviews(rh.getRecentReviews(), ui, d);
                if (selectedReview == null) {
                    RouteHome();
                } else {
                    othersReviewOptions(selectedReview, 1);
                }
                break;
            case 2: // view following Reviews
                selectedReview = rh.displayReviews(rh.getFollowingReviews(ah.getCurrentUserObject()), ui, d);
                if (selectedReview == null) {
                    RouteHome();
                } else {
                    othersReviewOptions(selectedReview, 2);
                }
                break;
            case 3: // view own reviews
                selectedReview = rh.displayReviews(ah.getCurrentUserObject().getReviews(), ui, d);
                if (selectedReview == null) {
                    RouteHome();
                } else {
                    ownReviewOptions(selectedReview);
                }
                break;
            case 4: // write review
                routeSearch();
                option = -1;
                break;
            case 5: // home
                RouteHome();
                option = -1;
                break;
            default:
                d.invalidOption();
                routeReview();
                break;
        }

    }

    private void ownReviewOptions(Review selectedReview) {
        d.displayOwnReviewOptions();
        int option = ui.getInt();
        switch (option) {
            case 1:// edit review
                Item item = selectedReview.getItem();
                rh.deleteReview(selectedReview);
                writeReview(item);
            case 2:// delete review
                if (rh.deleteReview(selectedReview)) {
                    d.successfulDeleteReview(selectedReview);
                }
                ;
            case 3: // go back
                routeReview();
            case 4:// go home
                RouteHome();
            default:
                d.invalidOption();
                routeReview();
                break;
        }
    }

    private void othersReviewOptions(Review selectedReview, int type) {
        d.displayOthersReviewOptions(type);
        int option = ui.getInt();
        if (type == 1) {
            option++;
        }
        int update;
        switch (option) {
            case 0:
                exit();
                break;
            case 1: // followUser
                if (type == 1) {
                    exit();
                }
                update = ah.followUser(selectedReview.getAuthor());
                d.followUpdate(selectedReview.getAuthor(), update);
            case 2:// unfollow
                update = ah.unfollowUser(selectedReview.getAuthor());
                d.followUpdate(selectedReview.getAuthor(), update);
            case 3: // go back
                routeReview();
            case 4:// go home
                RouteHome();
            default:
                d.invalidOption();
                othersReviewOptions(selectedReview, type);
                break;
        }
    }

}
