package Mickael;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.search.simplified.*;

public class SpotifyAPIQueryBuilder extends AbstractAPIQueryBuilder {
  SpotifyAPIConnection theApiConnection = new SpotifyAPIConnection();

  public ArrayList<String[]> searchAlbum(String albumTitle) {
    ArrayList<String[]> Albums = new ArrayList<>();
    SearchAlbumsRequest searchAlbumsRequest = theApiConnection.getConnection().searchAlbums(albumTitle)
        .limit(5)
        .build();
    try {
      Paging<AlbumSimplified> albumSimplifiedPaging = searchAlbumsRequest.execute();
      AlbumSimplified[] spotifyAlbums = albumSimplifiedPaging.getItems();
      for (AlbumSimplified theAlbum : spotifyAlbums) {
        String[] albumInfo = new String[4];
        albumInfo[0] = theAlbum.getName();
        albumInfo[1] = null; // CHECK THIS BEOFRE OUT
        albumInfo[2] = theAlbum.getArtists()[0].getName();
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return Albums;
  }

  public ArrayList<String[]> searchSong(String songTitle) {
    ArrayList<String[]> Songs = new ArrayList<>();
    SearchTracksRequest searchTracksRequest = theApiConnection.getConnection().searchTracks(songTitle)
        .limit(5)
        .build();
    try {
      final Paging<Track> trackPaging = searchTracksRequest.execute();
      Track[] spotifySongs = trackPaging.getItems();
      for (Track theSong : spotifySongs) {
        String title = theSong.getName();
        String inAlbumTitle = theSong.getAlbum().getName();
        String trackNumber = Integer.toString(theSong.getTrackNumber());
        String artistName = theSong.getArtists()[0]
        String[] songInfo = {};
        Songs.add(songInfo);
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return Songs;
  }

  public ArrayList<String[]> searchArtist(String artistName) {
    ArrayList<Artist> Artists = new ArrayList<>();
    SearchArtistsRequest searchArtistsRequest = theApiConnection.getConnection().searchArtists(artistName)
        .limit(5)
        .build();

    try {
      final Paging<se.michaelthelin.spotify.model_objects.specification.Artist> artistPaging = searchArtistsRequest
          .execute();
      se.michaelthelin.spotify.model_objects.specification.Artist[] spotifyArtists = artistPaging.getItems();
      for (se.michaelthelin.spotify.model_objects.specification.Artist artist : spotifyArtists) {
        Artist converted = convertArtist(artist);
        Artists.add(converted);
      }
      System.out.println("Total: " + artistPaging.getTotal());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return Artists;
  }

  public ArrayList<String[]> searchAlbum(String albumTitle, int limit) {
    ArrayList<Album> Albums = new ArrayList<>();
    SearchAlbumsRequest searchAlbumsRequest = theApiConnection.getConnection().searchAlbums(albumTitle)
        .limit(limit)
        .build();
    try {
      Paging<AlbumSimplified> albumSimplifiedPaging = searchAlbumsRequest.execute();
      AlbumSimplified[] spotifyAlbums = albumSimplifiedPaging.getItems();
      for (AlbumSimplified theAlbum : spotifyAlbums) {
        Album converted = convertAlbum(theAlbum);
        Albums.add(converted);
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return Albums;
  }

  public ArrayList<String[]> searchSong(String songTitle, int limit) {
    ArrayList<Song> Songs = new ArrayList<>();
    SearchTracksRequest searchTracksRequest = theApiConnection.getConnection().searchTracks(songTitle)
        .limit(limit)
        .build();
    try {
      final Paging<Track> trackPaging = searchTracksRequest.execute();
      Track[] spotifySongs = trackPaging.getItems();
      for (Track theSong : spotifySongs) {
        Song converted = convertSong(theSong);
        Songs.add(converted);
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return Songs;
  }

  public ArrayList<String[]> searchArtist(String artistName, int limit) {
    ArrayList<Artist> Artists = new ArrayList<>();
    SearchArtistsRequest searchArtistsRequest = theApiConnection.getConnection().searchArtists(artistName)
        .limit(limit)
        .build();

    try {
      final Paging<se.michaelthelin.spotify.model_objects.specification.Artist> artistPaging = searchArtistsRequest
          .execute();
      se.michaelthelin.spotify.model_objects.specification.Artist[] spotifyArtists = artistPaging.getItems();
      for (se.michaelthelin.spotify.model_objects.specification.Artist artist : spotifyArtists) {
        Artist converted = convertArtist(artist);
        Artists.add(converted);
      }
      System.out.println("Total: " + artistPaging.getTotal());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return Artists;
  }

  // converts the Spotify's SimplifiedAlbum object into local album version
  private Album convertAlbum(AlbumSimplified album) {
    Artist artist = new Artist(album.getArtists()[0].getId(), album.getArtists()[0].getName());
    Song[] trackList = null; /// WHAT ABOUT THISSSS???? // another querycall maybe just delete
    Album localAlbum = new Album(album.getId(), album.getName(), artist, trackList);
    return localAlbum;
  }

  private Song convertSong(Track song) {
    Artist artist = new Artist(song.getArtists()[0].getId(), song.getArtists()[0].getName());
    Album album = convertAlbum(song.getAlbum());
    Song localSong = new Song(song.getId(), song.getName(), artist, album, song.getTrackNumber());
    return localSong;
  }

  private Artist convertArtist(se.michaelthelin.spotify.model_objects.specification.Artist artist) {
    Artist localArtist = new Artist(artist.getId(), artist.getName());
    return localArtist;
  }
}