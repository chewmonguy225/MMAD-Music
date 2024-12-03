package MMAD;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
  public static void main(String[] args) {
  //   SpotifyAPIQueryBuilder thQueryBuilder = new SpotifyAPIQueryBuilder();
  //   Scanner scan = new Scanner(System.in);
  //   System.out.print("Search: ");
  //   String songTitle = scan.nextLine();
  //   ArrayList<Song> songs = thQueryBuilder.searchSong(songTitle);
  //   // ArrayList<Artist> artists = thQueryBuilder.searchArtist(songTitle);

  //   listSongOptions(songs);
    
  //   scan.close();
  // }


  // private static void listSongOptions(ArrayList<Song> songs) {
  //   int num = 1;
  //   for (Song i : songs) {
  //     System.out.print("[" + num + "]");
  //     System.out.println(i.getName() + " by " + i.getArtist().getName() + " from " + i.getAlbum().getName());
  //     num++;
  //   }
  //   int nextOption = songs.size() + 1;
  //   System.out.print("[" + nextOption + "]");
  //   System.out.println("List more");
 
  ItemHandler ih = ItemHandler.access();
  UI ui = UI.access();
  Display d = Display.access();


  ih.searchSong("let it be", ui, d);
  }
}
