package Mickael;

import se.michaelthelin.spotify.model_objects.specification.*;

public class Driver {
    public static void main(String[] args) {
        SpotifyAPIQueryBuilder thQueryBuilder = new SpotifyAPIQueryBuilder();
        
        Artist[] albums = thQueryBuilder.searchArtists("Lizzy McAlpine");

        for(int i = 0; i < 5; i++){
        System.out.println("Title: " + albums[i].getName() + " ID: " + albums[i].getId());
      }
    }
}
