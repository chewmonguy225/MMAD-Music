package MMAD;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
  public static void main(String[] args) {
    SpotifyAPIQueryBuilder thQueryBuilder = new SpotifyAPIQueryBuilder();
    Scanner scan = new Scanner(System.in);
    System.out.print("Artist: ");
    String songTitle = scan.nextLine();
    // ArrayList<Song> songs = thQueryBuilder.searchSong(songTitle);
    ArrayList<Artist> artists = thQueryBuilder.searchArtist(songTitle);

    listOptions(artists);

    String input = scan.nextLine();
    int songNum = Integer.valueOf(input);
    int nextOption = artists.size() + 1;
    while (songNum == nextOption) {
      artists = thQueryBuilder.searchArtist(songTitle, artists.size() + 5);
      listOptions(artists);
      songNum = Integer.valueOf(input);
      input = scan.nextLine();
      songNum = Integer.valueOf(input);
      nextOption = artists.size() + 1;
    }

    Artist choosenSong = artists.get(songNum - 1);
    System.out.println("You selected: " + choosenSong.getName());
    scan.close();
  }

  private static void listOptions(ArrayList<Artist> artists) {
    int num = 1;
    for (Artist i : artists) {
      System.out.print("[" + num + "]");
      System.out.println(i.getName());
      num++;
    }
    int nextOption = artists.size() + 1;
    System.out.print("[" + nextOption + "]");
    System.out.println("List more");
  }
}
