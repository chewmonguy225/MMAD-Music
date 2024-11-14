package Mickael;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
  // //public static void main(String[] args) {
  //   SpotifyAPIQueryBuilder thQueryBuilder = new SpotifyAPIQueryBuilder();
  //   Scanner scan = new Scanner(System.in);
  //   System.out.print("Search: ");
  //   String songTitle = scan.nextLine();
  //   ArrayList<Song> songs = thQueryBuilder.searchSong(songTitle);
  //   // ArrayList<Artist> artists = thQueryBuilder.searchArtist(songTitle);

  //   listSongOptions(songs);


  //   String input = scan.nextLine();
  //   int songNum = Integer.valueOf(input);
  //   int nextOption = songs.size() + 1;
  //   while (songNum == nextOption) {
  //     songs = thQueryBuilder.searchSong(songTitle, songs.size() + 5);
  //     listSongOptions(songs);
  //     songNum = Integer.valueOf(input);
  //     input = scan.nextLine();
  //     songNum = Integer.valueOf(input);
  //     nextOption = songs.size() + 1;
  //   }

  //   Song choosenSong = songs.get(songNum - 1);
  //   System.out.println("You selected: " + choosenSong.getTitle());

  //   System.out.print("Review: ");
  //   String description = scan.nextLine();
  //   System.out.print("Rating: ");
  //   String stringRating = scan.nextLine();
  //   int rating = Integer.valueOf(stringRating);
  //   Review review = new Review(choosenSong.getID(), choosenSong, description, rating);
    
  //   System.out.println("Reviews");
  //   System.out.println("Item: " + review.getItem().getTitle() + " by " + review.getItem().getArtist().getName());
  //   System.out.println("Description: " + review.getDescription());
  //   System.out.println("Rating: " + review.getRating());
    
  //   scan.close();
  // }

  // private static void listArtistOptions(ArrayList<Artist> artists) {
  //   int num = 1;
  //   for (Artist i : artists) {
  //     System.out.print("[" + num + "]");
  //     System.out.println(i.getName());
  //     num++;
  //   }
  //   int nextOption = artists.size() + 1;
  //   System.out.print("[" + nextOption + "]");
  //   System.out.println("List more");
  // }

  // private static void listSongOptions(ArrayList<Song> songs) {
  //   int num = 1;
  //   for (Song i : songs) {
  //     System.out.print("[" + num + "]");
  //     System.out.println(i.getTitle() + " by " + i.getArtist().getName());
  //     num++;
  //   }
  //   int nextOption = songs.size() + 1;
  //   System.out.print("[" + nextOption + "]");
  //   System.out.println("List more");
  }

