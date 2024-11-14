package MMAD;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.search.simplified.*;

public class SpotifyAPIQueryBuilder extends AbstractAPIQueryBuilder {
  SpotifyAPIConnection theApiConnection = new SpotifyAPIConnection();

  public ArrayList<ArrayList<String>> searchAlbum(String albumTitle) {
    ArrayList<ArrayList<String>> Albums = new ArrayList<>();
    SearchAlbumsRequest searchAlbumsRequest = theApiConnection.getConnection().searchAlbums(albumTitle)
        .limit(5)
        .build();
    try {
      Paging<AlbumSimplified> albumSimplifiedPaging = searchAlbumsRequest.execute();
      AlbumSimplified[] spotifyAlbums = albumSimplifiedPaging.getItems();
      for (AlbumSimplified theAlbum : spotifyAlbums) {
        ArrayList<String> albumInfo = new ArrayList<>();
        albumInfo.add(theAlbum.getName());
        albumInfo.add(theAlbum.getArtists()[0].getName());
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return Albums;
  }

  public ArrayList<ArrayList<String>> searchSong(String songTitle) {
    ArrayList<ArrayList<String>> Songs = new ArrayList<>();
    SearchTracksRequest searchTracksRequest = theApiConnection.getConnection().searchTracks(songTitle)
        .limit(5)
        .build();
    try {
      final Paging<Track> trackPaging = searchTracksRequest.execute();
      Track[] spotifySongs = trackPaging.getItems();
      for (Track theSong : spotifySongs) {
        ArrayList<String> songInfo = new ArrayList<>();
        songInfo.add(theSong.getName());
        songInfo.add(theSong.getArtists()[0].getName());
        songInfo.add(theSong.getAlbum().getName());
        songInfo.add(Integer.toString(theSong.getTrackNumber()));
        Songs.add(songInfo);
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return Songs;
  }

  public ArrayList<ArrayList<String>> searchArtist(String artistName) {
    ArrayList<ArrayList<String>> Artists = new ArrayList<>();
    SearchArtistsRequest searchArtistsRequest = theApiConnection.getConnection().searchArtists(artistName)
        .limit(5)
        .build();

    try {
      final Paging<se.michaelthelin.spotify.model_objects.specification.Artist> artistPaging = searchArtistsRequest
          .execute();
      se.michaelthelin.spotify.model_objects.specification.Artist[] spotifyArtists = artistPaging.getItems();
      for (se.michaelthelin.spotify.model_objects.specification.Artist artist : spotifyArtists) {
        ArrayList<String> artistInfo = new ArrayList<>();
        artistInfo.add(artist.getName());
      }
      System.out.println("Total: " + artistPaging.getTotal());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return Artists;
  }

  public ArrayList<ArrayList<String>> searchAlbum(String albumTitle, int limit) {
    ArrayList<ArrayList<String>> Albums = new ArrayList<>();
    SearchAlbumsRequest searchAlbumsRequest = theApiConnection.getConnection().searchAlbums(albumTitle)
        .limit(limit)
        .build();
    try {
      Paging<AlbumSimplified> albumSimplifiedPaging = searchAlbumsRequest.execute();
      AlbumSimplified[] spotifyAlbums = albumSimplifiedPaging.getItems();
      for (AlbumSimplified theAlbum : spotifyAlbums) {
        ArrayList<String> albumInfo = new ArrayList<>();
        albumInfo.add(theAlbum.getName());
        albumInfo.add(theAlbum.getArtists()[0].getName());
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return Albums;
  }

  public ArrayList<ArrayList<String>> searchSong(String songTitle, int limit) {
    ArrayList<ArrayList<String>> Songs = new ArrayList<>();
    SearchTracksRequest searchTracksRequest = theApiConnection.getConnection().searchTracks(songTitle)
        .limit(limit)
        .build();
    try {
      final Paging<Track> trackPaging = searchTracksRequest.execute();
      Track[] spotifySongs = trackPaging.getItems();
      for (Track theSong : spotifySongs) {
        ArrayList<String> songInfo = new ArrayList<>();
        songInfo.add(theSong.getName());
        songInfo.add(theSong.getArtists()[0].getName());
        songInfo.add(theSong.getAlbum().getName());
        songInfo.add(Integer.toString(theSong.getTrackNumber()));
        Songs.add(songInfo);
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return Songs;
  }

  public ArrayList<ArrayList<String>> searchArtist(String artistName, int limit) {
    ArrayList<ArrayList<String>> Artists = new ArrayList<>();
    SearchArtistsRequest searchArtistsRequest = theApiConnection.getConnection().searchArtists(artistName)
        .limit(limit)
        .build();

    try {
      final Paging<se.michaelthelin.spotify.model_objects.specification.Artist> artistPaging = searchArtistsRequest
          .execute();
      se.michaelthelin.spotify.model_objects.specification.Artist[] spotifyArtists = artistPaging.getItems();
      for (se.michaelthelin.spotify.model_objects.specification.Artist artist : spotifyArtists) {
        ArrayList<String> artistInfo = new ArrayList<>();
        artistInfo.add(artist.getName());
      }
      System.out.println("Total: " + artistPaging.getTotal());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return Artists;
  }

}