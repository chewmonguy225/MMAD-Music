package Mickael;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
  public static void main(String[] args) {
    SpotifyAPIQueryBuilder thQueryBuilder = new SpotifyAPIQueryBuilder();
    Scanner scan = new Scanner(System.in);
    ArrayList<ArrayList<String>> albums = thQueryBuilder.listAlbums("Let it Be");
    
    int num = 1;
    for(ArrayList<String> i: albums){
      System.out.print("[" + num + "]");
      System.out.println(" Album: " + i.get(0) + " By " + i.get(1));
      num++;
    }

    String input = scan.nextLine();
    int albumNum = Integer.valueOf(input) - 1;

    String IDsearch = albums.get(albumNum).get(2);

    Album choosenAlbum = thQueryBuilder.searchAlbum(IDsearch);
    System.out.println(" Album: " + choosenAlbum.getTitle() + " By " + choosenAlbum.getArtist().getName());
  }
}
