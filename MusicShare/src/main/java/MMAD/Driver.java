package MMAD;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
  public static void main(String[] args) {
    SpotifyAPIQueryBuilder thQueryBuilder = new SpotifyAPIQueryBuilder();
    Scanner scan = new Scanner(System.in);
    System.out.print("Search: ");
    String songTitle = scan.nextLine();
    ArrayList<ArrayList<String>> songs = thQueryBuilder.searchSong(songTitle);
    // ArrayList<Artist> artists = thQueryBuilder.searchArtist(songTitle);

    listSongOptions(songs);


    String input = scan.nextLine();
    int songNum = Integer.valueOf(input);
    int nextOption = songs.size() + 1;
    while (songNum == nextOption) {
      songs = thQueryBuilder.searchSong(songTitle, songs.size() + 5);
      listSongOptions(songs);
      songNum = Integer.valueOf(input);
      input = scan.nextLine();
      songNum = Integer.valueOf(input);
      nextOption = songs.size() + 1;
    }

    ArrayList<String> choosenSong = songs.get(songNum - 1);
    System.out.println("You selected: " + choosenSong.get(0));
    
    scan.close();
  }


  private static void listSongOptions(ArrayList<ArrayList<String>> songs) {
    int num = 1;
    for (ArrayList<String> i : songs) {
      System.out.print("[" + num + "]");
      System.out.println(i.get(0) + " by " + i.get(1) + " from " + i.get(2));
      num++;
    }
    int nextOption = songs.size() + 1;
    System.out.print("[" + nextOption + "]");
    System.out.println("List more");
  }
}
