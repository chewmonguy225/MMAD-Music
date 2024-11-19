package MMAD;

import java.util.ArrayList;

public class User {
    private Login loginCredential;
    private User[] followingList;
    private Item[] reviewedItemList;
    private Playlist playlist;

    public User(Login loginCredentials){
        //call accountHandler
    }

    public Item[] getReviewedItems(){
        return reviewedItemList;
    }

    public Playlist getPlaylist(){
        return playlist;
    }

    public Playlist makePlaylist(Playlist playlist, Playlist friendPlaylist){
        Playlist mergedPlaylist =  new Playlist();
        //Call PlaylistHandler

        return mergedPlaylist;
    }
}
