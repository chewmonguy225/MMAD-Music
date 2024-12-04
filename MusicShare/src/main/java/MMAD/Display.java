package MMAD;

import java.util.*;

public class Display {
    private static Display d;

    private Display(){

    }

    public static Display access(){
        if (d == null){
            d = new Display();
        }
        return d;
    }

    private static void nextScreen(){
        System.out.println("_________________________________________________________________________________________\nEnter 0 to exit system.\n");
    }
    private static void split(){
        System.out.println("_________________________________________________________________________________________\n");
    }
    public void error(){
        System.out.println("_________________________________________________________________________________________\nThere was an Unexpected Error. Please restart the system\n");
    }

    public void exit(){
        System.out.println("_________________________________________________________________________________________\nExiting System\n\nThank you for using MMAD-Music");
    }
    public void loginOrSignup(){
        nextScreen();
        System.out.println("Please enter a number: 0-2\n\n1: Login\n2: Create Account\n");
    }

    public void loginUsername(){
        nextScreen();
        System.out.print("Please enter your Username: ");
    }
    public void loginPassword(){
        System.out.print("Please enter your Password: ");
    }

    public void invalidLogin(){
        split();
        System.out.println("Username or Password is incorrect\n\nPlease try again:");
    }

    public void successfulLogin(String username){
        split();
        System.out.println("Welcome back "+ username);
    }

    public void successfulSignup(){
        split();
        System.out.println("Welcome to MMAD-Music");
    }

    public void unsuccessfulSignup(){
        split();
        System.out.println("User with that username already exists, Please try again using a different Username");
    }


    public void home(){
        nextScreen();
        System.out.println("Please enter a number: 0-6");
        System.out.println("1: Playlist\n2: Friends\n3: Reviews\n4: Search Song\n5: Search Album\n6: Search Artist\n");
    }

    public void displayPlaylist(Playlist playlist, int page){
        nextScreen();
        ArrayList<Song> musicList = playlist.getPlaylist();
        if((musicList.size() - page) >= 5)
            System.out.println("Please enter a number: 0-7");
        else{
            int num = musicList.size()-page;
            System.out.println("Please enter a number: 0-"+num+" or 6-7");
        }
        for(int i = page; i<musicList.size() && i < page+5; i++){
            //prints out the name and artist of the next 5 songs in the playlist
            System.out.println(i-(page-1)+": "+ musicList.get(i).getName()+"  by  "+ musicList.get(i).getArtist().getName());
        }

    }

    public void displaySongSearchResult(ArrayList<Song> theSongs, int currentPage, int totalPages){
        nextScreen();
        int startIndex = ((currentPage - 1) * 5);
        for(int i = 0; i < 5; i++){
            System.out.println("[" + (i+1) + "] " + theSongs.get(startIndex + i).name + " by " + theSongs.get(startIndex + i).getArtist().getName());
        }
        if(currentPage == 1){
            System.out.println("[" + 6 + "] Next page" );
            System.out.println("[" + 7 + "] Go Home" );
        }else if(currentPage == totalPages){
            System.out.println("[" + 7 + "] Previous page" );
        }else{
            System.out.println("[" + 6 + "] Next page" );
            System.out.println("[" + 7 + "] Previous page" );
        }
    }

    public void displayAlbumSearchResult(ArrayList<Album> theAlbums, int currentPage, int totalPages){
        nextScreen();
        int startIndex = ((currentPage - 1) * 5);
        for(int i = 0; i < 5; i++){
            System.out.println("[" + (i+1) + "] " + theAlbums.get(startIndex + i).name + " by " + theAlbums.get(startIndex + i).getArtist().getName());
        }
        if(currentPage == 1){
            System.out.println("[" + 6 + "] Next page" );
            System.out.println("[" + 7 + "] Go Home" );
        }else if(currentPage == totalPages){
            System.out.println("[" + 7 + "] Previous page" );
        }else{
            System.out.println("[" + 6 + "] Next page" );
            System.out.println("[" + 7 + "] Previous page" );
        }
    }

    public void displayArtistSearchResult(ArrayList<Artist> theArtists, int currentPage, int totalPages){
        nextScreen();
        int startIndex = ((currentPage - 1) * 5);
        for(int i = 0; i < 5; i++){
            System.out.println("[" + (i+1) + "] " + theArtists.get(startIndex + i).name);
        }
        if(currentPage == 1){
            System.out.println("[" + 6 + "] Next page" );
            System.out.println("[" + 7 + "] Go Home" );
        }else if(currentPage == totalPages){
            System.out.println("[" + 7 + "] Previous page" );
        }else{
            System.out.println("[" + 6 + "] Next page" );
            System.out.println("[" + 7 + "] Previous page" );
        }
    }

    public void accountSettings(){
        nextScreen();
        System.out.println("Please enter 0-2\n1: Change Password\n2: Delete Account");
    }
}

