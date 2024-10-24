package Mickael;

import java.util.ArrayList;

public class Driver {
  public static void main(String[] args) {
    SpotifyAPIQueryBuilder thQueryBuilder = new SpotifyAPIQueryBuilder();

    ArrayList<Album> albums = thQueryBuilder.searchAlbum("Lizzy McAlpine");
    for (int i = 0; i < 5; i++) {
      System.out.println("Title: " + albums.get(i).getTitle() + " Artist: " + albums.get(i).getArtist().getName());
    }

    System.out.println();
    System.out.println();

    ArrayList<Song> songs = thQueryBuilder.searchSong("Method Acting");
    for (int i = 0; i < 5; i++) {
      System.out.println("Title: " + songs.get(i).getTitle() + " Artist: " + songs.get(i).getArtist().getName());
    }

    System.out.println();
    System.out.println();
    
    ArrayList<Artist> artists = thQueryBuilder.searchArtists("Lizzy McAlpine");
    for (int i = 0; i < 5; i++) {
      System.out.println("Name: " + artists.get(i).getName());
    }
  }
}
