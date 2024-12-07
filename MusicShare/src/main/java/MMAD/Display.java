package MMAD;

import java.util.*;

public class Display {
    private static Display d;
    private final int itemsPerPage = 5;

    private Display() {

    }

    public static Display access() {
        if (d == null) {
            d = new Display();
        }
        return d;
    }

    private static void nextScreen() {
        System.out.println(
                "_________________________________________________________________________________________\nEnter 0 to exit system.\n");
    }

    private static void split() {
        System.out
                .println("_________________________________________________________________________________________\n");
    }

    public void error() {
        System.out.println(
                "_________________________________________________________________________________________\nThere was an Unexpected Error. Please restart the system\n");
    }

    public void exit() {
        System.out.println(
                "_________________________________________________________________________________________\nExiting System\n\nThank you for using MMAD-Music");
    }

    public void loginOrSignup() {
        nextScreen();
        System.out.println("Please enter a number: 0-2\n\n1: Login\n2: Create Account\n");
    }

    public void loginUsername() {
        nextScreen();
        System.out.print("Please enter your Username: ");
    }

    public void loginPassword() {
        System.out.print("Please enter your Password: ");
    }

    public void invalidLogin() {
        split();
        System.out.println("Username or Password is incorrect\n\nPlease try again:");
    }

    public void successfulLogin(String username) {
        split();
        System.out.println("Welcome back " + username);
    }

    public void successfulSignup() {
        split();
        System.out.println("Welcome to MMAD-Music");
    }

    public void unsuccessfulSignup() {
        split();
        System.out.println("User with that username already exists, Please try again using a different Username");
    }

    public void home() {
        nextScreen();
        System.out.println("Please enter a number: 0-6");
        System.out.println("1: Playlist\n2: Friends\n3: Reviews\n4: Search\n5: Account Settings\n6: Logout");
    }

    public void displayPlaylist(Playlist playlist, int page) {
        nextScreen();
        ArrayList<Song> musicList = playlist.getPlaylist();
        if ((musicList.size() - page) >= 5)
            System.out.println("Please enter a number: 0-7");
        else {
            int num = musicList.size() - page;
            System.out.println("Please enter a number: 0-" + num + " or 6-7");
        }
        for (int i = page; i < musicList.size() && i < page + 5; i++) {
            // prints out the name and artist of the next 5 songs in the playlist
            System.out.println(i - (page - 1) + ": " + musicList.get(i).getName() + "  by  "
                    + musicList.get(i).getArtist().getName());
        }
        System.out.println("\n6: Previous\n7: Next");

    }

    public void accountSettings() {
        nextScreen();
        System.out.println("Please enter 0-2\n1: Change Password\n2: Delete Account");
    }

    public void searchPrompt(String type) {
        split();
        System.out.print("Search " + type + ": ");
    }

    public void songOptionMenu() {
        nextScreen();
        System.out.println("Please enter a number 0-5:\n");
        System.out.println("[1] Add song to playlist");
        System.out.println("[2] Remove song from playlist");
        System.out.println("[3] Write Review");
        System.out.println("[4] Delete Review");
        System.out.println("[5] Search different song");
        System.out.println("[6] Go Home");
    }

    public void albumOptionMenu() {
        nextScreen();
        System.out.println("Please enter a number 0-3:\n");
        System.out.println("[1] Write Review");
        System.out.println("[2] Delete Review");
        System.out.println("[3] Search different album");
        System.out.println("[4] Go Home");
    }

    public void reviewPrompt() {
        System.out.print("Description: ");
    }

    public void ratingPrompt() {
        System.out.print("Rating: ");
    }

    public void changePassword() {
        nextScreen();
        System.out.print("Please enter your new password: ");
    }

    public void searchOptions() {
        nextScreen();
        System.out.println("[1] Search Song");
        System.out.println("[2] Search Album");
        System.out.println("[3] Search Artist");
        System.out.println("[4] Search MMAD User");
        System.out.println("[5] Go Home");

    }

    public void invalidOption() {
        System.out.println("Invalid Option");
        System.out.println("Please try again");
    }

    public void itemOptionMenu() {
        nextScreen();
        System.out.println("Please enter a number 0-4:\n");
        System.out.println("[1] Write Review");
        System.out.println("[2] Delete Review");
        System.out.println("[3] Search different item");
        System.out.println("[4] Go Home");
    }

    public void displaySearchResult(ArrayList<? extends Item> Items, int currentPage, int totalPages) {
        nextScreen();
        int startIndex = ((currentPage - 1) * 5);

        if (Items.get(0) instanceof Song) {
            for (int i = 0; i < 5; i++) {
                Song song = (Song) Items.get(startIndex + i);
                System.out.println("[" + (i + 1) + "] " + song.getName() + " by "
                        + song.getArtist().getName());
            }
        } else if (Items.get(0) instanceof Album) {
            for (int i = 0; i < 5; i++) {
                Album album = (Album) Items.get(startIndex + i);
                System.out.println("[" + (i + 1) + "] " + album.getName() + " by "
                        + album.getArtist().getName());
            }
        } else if (Items.get(0) instanceof Artist) {
            for (int i = 0; i < 5; i++) {
                Artist artist = (Artist) Items.get(startIndex + i);
                System.out.println("[" + (i + 1) + "] " + artist.getName());
            }
        }

        if (currentPage == 1) {
            System.out.println("[" + 6 + "] Search different item");
            System.out.println("[" + 7 + "] Next page");
        } else if (currentPage == totalPages) {
            System.out.println("[" + 6 + "] Previous page");
            System.out.println("[" + 7 + "] Search different item");
        } else {
            System.out.println("[" + 6 + "] Previous page");
            System.out.println("[" + 7 + "] Next page");
        }

    }

    public void displayUserSearchResult(ArrayList<String> usernames, int currentPage, int totalPages) {
        nextScreen();
        int startIndex = ((currentPage - 1) * itemsPerPage);
        if (usernames.size() >= itemsPerPage) {
            for (int i = 0; i < itemsPerPage; i++) {
                System.out.println("[" + (i + 1) + "] " + usernames.get(startIndex));
            }
        } else if (usernames.isEmpty()) {
            System.out.println("[!] There are no users to display");
            split();
            System.out.println("[1] Search different item");
            System.out.println("[2] Go Home");
        } else {
            for (int i = 0; i < usernames.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + usernames.get(startIndex));
            }
        }

        split();
        if (currentPage == 1) {
            System.out.println("[" + 6 + "] Search different item");
            System.out.println("[" + 7 + "] Next page");
        } else if (currentPage == 1 && totalPages == 1 && !usernames.isEmpty()) {
            System.out.println("[" + 6 + "] Search different item");
        } else if (currentPage == totalPages) {
            System.out.println("[" + 6 + "] Previous page");
            System.out.println("[" + 7 + "] Search different item");
        } else {
            System.out.println("[" + 6 + "] Previous page");
            System.out.println("[" + 7 + "] Next page");
        }

    }

    public void displayReviewsResult(ArrayList<Review> theReviews, int currentPage, int totalPages) {
        nextScreen();
        int startIndex = ((currentPage - 1) * itemsPerPage);
        if (theReviews.size() >= itemsPerPage) {
            for (int i = 0; i < itemsPerPage; i++) {
                System.out.println(theReviews.get(startIndex + i).displayFormat());
            }
        } else if (theReviews.isEmpty()) {
            System.out.println("[!] There are reviews to display");
            split();
            System.out.println("[1] Search different item");
            System.out.println("[2] Go Home");
        } else {
            for (int i = 0; i < theReviews.size(); i++) {
                System.out.println(theReviews.get(startIndex + i).displayFormat());
            }
        }

        split();
        if (currentPage == 1) {
            System.out.println("[" + 6 + "] Search different item");
            System.out.println("[" + 7 + "] Next page");
        } else if (currentPage == 1 && totalPages == 1 && !theReviews.isEmpty()) {
            System.out.println("[" + 6 + "] Search different item");
        } else if (currentPage == totalPages) {
            System.out.println("[" + 6 + "] Previous page");
            System.out.println("[" + 7 + "] Search different item");
        } else {
            System.out.println("[" + 6 + "] Previous page");
            System.out.println("[" + 7 + "] Next page");
        }

    }

    public void reviewOptionMenu() {
        nextScreen();
        System.out.println("Please enter a number 0-3:\n");
        System.out.println("[1] View reviews");
        System.out.println("[2] View own reviews");
        // System.out.println("[2] Search reviews");
        System.out.println("[3] Write Review");
        System.out.println("[4] Delete Review");
        System.out.println("[5] Write Review");
    }

    public void friendOptionMenu() {
        nextScreen();
        System.out.println("Please enter a number 0-3:\n");
        System.out.println("[1] View following");
        System.out.println("[2] View followers");
        System.out.println("[3] Search Users");
    }

    public void otherUserOptionMenu() {
        nextScreen();
        System.out.println("Please enter a number 0-3:\n");
        System.out.println("[1] Follow User");
        System.out.println("[2] Unfollow User");
        System.out.println("[3] View User's Reviews");
        System.out.println("[4] View User's Playlist");
        System.out.println("[5] Search other users");
        System.out.println("[6] Go Home");
    }

    public void displaySongSearchResult(ArrayList<Song> theSongs, int currentPage, int totalPages) {
        nextScreen();
        System.out.println("Please enter a number 0-7:\n");
        int startIndex = ((currentPage - 1) * 5);
        for (int i = 0; i < 5; i++) {
            System.out.println("[" + (i + 1) + "] " + theSongs.get(startIndex + i).name + " by "
                    + theSongs.get(startIndex + i).getArtist().getName());
        }
        if (currentPage == 1) {
            System.out.println("[" + 6 + "] Go Home");
            System.out.println("[" + 7 + "] Next page");
        } else if (currentPage == totalPages) {
            System.out.println("[" + 6 + "] Previous page");
        } else {
            System.out.println("[" + 6 + "] Previous page");
            System.out.println("[" + 7 + "] Next page");
        }
    }

}
