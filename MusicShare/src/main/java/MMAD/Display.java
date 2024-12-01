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


    public void home(){
        nextScreen();
        System.out.println("Please enter a number: 0-6");
        System.out.println("1: Playlist\n2: Friends\n3: Reviews\n4: Search Song\n5: Search Album\n6: Search Artist");
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
}

