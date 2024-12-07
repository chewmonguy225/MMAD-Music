package MMAD;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
  private static ItemHandler ih = ItemHandler.access();
  private static UI ui = UI.access();
  private static Display d = Display.access();
  private static PlaylistHandler ph = PlaylistHandler.access();
  private static ReviewHandler rh = ReviewHandler.access();
  private static DBHandler dbh = DBHandler.access();
  private static Driver dr = new Driver();
  private static QueryExecutor qe = new QueryExecutor();

  public static void main(String[] args) {
    SpotifyAPIQueryBuilder thQueryBuilder = SpotifyAPIQueryBuilder.access();
    // Scanner scan = new Scanner(System.in);
    // System.out.print("Search: ");
    // String songTitle = scan.nextLine();
    // ArrayList<Song> songs = thQueryBuilder.searchSong(songTitle);
    // // ArrayList<Artist> artists = thQueryBuilder.searchArtist(songTitle);

    // listSongOptions(songs);

    // scan.close();
    // }

    // private static void listSongOptions(ArrayList<Song> songs) {
    // int num = 1;
    // for (Song i : songs) {
    // System.out.print("[" + num + "]");
    // System.out.println(i.getName() + " by " + i.getArtist().getName() + " from "
    // + i.getAlbum().getName());
    // num++;
    // }
    // int nextOption = songs.size() + 1;
    // System.out.print("[" + nextOption + "]");
    // System.out.println("List more");

    // System.out.println("Search: ");
    // String search = ui.getString();
    // ih.searchArtist(search, ui, d);

    // d.searchPrompt();
    // String songTitle = ui.getString();
    // int option = ih.searchSong(songTitle, ui, d);
    // //songOptionMenu(ih.getSelectedSong());
    // System.out.println(option);

   Song song = new Song(26, null, null, null, null);
   Login login = new Login("m", null);
    ArrayList<String> b =  qe.getReview(login, song);
  
    for(String a: b ){
      System.out.println(a);
    }
  }


}
