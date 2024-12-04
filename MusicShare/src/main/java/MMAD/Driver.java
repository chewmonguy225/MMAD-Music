package MMAD;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
  private static ItemHandler ih = ItemHandler.access();
  private static UI ui = UI.access();
  private static Display d = Display.access();
  private static PlaylistHandler ph = PlaylistHandler.access();
  private static ReviewHandler rh = ReviewHandler.access();
  private static Driver dr = new Driver();

  public static void main(String[] args) {
    // SpotifyAPIQueryBuilder thQueryBuilder = new SpotifyAPIQueryBuilder();
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

    routeSongSearch();
  }

  public static int routeSongSearch() {
    d.searchPrompt();
    String songTitle = ui.getString();
    int option = ih.searchSong(songTitle, ui, d);
    dr.songOptionMenu(ih.getSelectedSong());
    return option;
  }

  public void songOptionMenu(Song song) {
    d.songOptionMenu();
    switch (ui.getInt()) {
      case 1:// add song to playlist
        ph.addSongToPlaylist(null, song);
        break;
      case 2:// remove song from playlist
        ph.removeSongFromPlaylist(null, song);
        break;
      case 3:// write review
        d.reviewPrompt();
        String description = ui.getString();
        d.ratingPrompt();
        int rating = ui.getInt();
        rh.createReview(null, song, description, rating);
        break;
      case 4:// delete review
        //rh.deleteReview();
        break;
      case 5:// previous page (routeSongMenu)
        routeSongSearch();
        break;
      default:// exit to main menu
    }
  }
}
