package MMAD;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.search.simplified.*;

public class SpotifyAPIQueryBuilder extends AbstractAPIQueryBuilder {
  SpotifyAPIConnection theApiConnection = new SpotifyAPIConnection();

  public ArrayList<Album> searchAlbum(String albumTitle) {
    ArrayList<Album> Albums = new ArrayList<>();
    SearchAlbumsRequest searchAlbumsRequest = theApiConnection.getConnection().searchAlbums(albumTitle)
        .limit(5)
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

  public ArrayList<Song> searchSong(String songTitle) {
    ArrayList<Song> Songs = new ArrayList<>();
    SearchTracksRequest searchTracksRequest = theApiConnection.getConnection().searchTracks(songTitle)
        .limit(5)
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

  public ArrayList<Artist> searchArtist(String artistName) {
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

  public ArrayList<Album> searchAlbum(String albumTitle, int limit) {
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

  public ArrayList<Song> searchSong(String songTitle, int limit) {
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

  public ArrayList<Artist> searchArtist(String artistName, int limit) {
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
    Album album = null;/// WHAT ABOUT THISSSS????
    Song localSong = new Song(song.getId(), song.getName(), artist, album, song.getTrackNumber());
    return localSong;
  }

  private Artist convertArtist(se.michaelthelin.spotify.model_objects.specification.Artist artist) {
    Artist localArtist = new Artist(artist.getId(), artist.getName());
    return localArtist;
  }
}