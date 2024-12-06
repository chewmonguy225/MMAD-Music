package MMAD;

import java.util.*;

public class Display {
    private static Display d;

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
        System.out.println(
                "1: Playlist\n2: Friends\n3: Reviews\n4: Search\n5: Account Settings\n6: Logout");
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
        }else if (Items.get(0) instanceof Artist) {
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

    public void accountSettings() {
        nextScreen();
        System.out.println("Please enter 0-2\n1: Change Password\n2: Delete Account");
    }

    public void searchPrompt() {
        split();
        System.out.print("Search: ");
    }

    public void songOptionMenu() {
        nextScreen();
        System.out.println("Please enter a number 0-5:\n");
        System.out.println("[1] Add song to playlist");
        System.out.println("[2] Remove song from playlist");
        System.out.println("[3] Write Review");
        System.out.println("[4] Delete Review");
        System.out.println("[5] Search different item");
    }

    public void ItemOptionMenu() {
        nextScreen();
        System.out.println("Please enter a number 0-3:\n");
        System.out.println("[1] Write Review");
        System.out.println("[2] Delete Review");
        System.out.println("[3] Search different item");
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

    public void homeOrSearchPrompt() {
        nextScreen();
        System.out.println("[1] Go Home");
        System.out.println("[2] Different Search");
    }

    public void searchOptions() {
        nextScreen();
        System.out.println("[1] Go Home");
        System.out.println("[2] Search Song");
        System.out.println("[3] Search Album");
        System.out.println("[4] Different Artist");

    }

    public void invalidOption() {
        System.out.println("Invalid Option");
        System.out.print("Please try again: ");
    }
}
