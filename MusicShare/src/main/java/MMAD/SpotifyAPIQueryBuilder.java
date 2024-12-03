package MMAD;

import java.io.IOException;
import java.util.*;

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
        Album localAlbum = convertAlbum(theAlbum);
        Albums.add(localAlbum);
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      return new ArrayList<Album>();
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
        Song localSong = convertTrack(theSong);
        Songs.add(localSong);
      }
      return Songs;
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      return new ArrayList<Song>();
    }
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
        Artist localArtist = convertArtist(artist);
        Artists.add(localArtist);
      }
      System.out.println("Total: " + artistPaging.getTotal());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      return new ArrayList<Artist>();
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
        Album localAlbum = convertAlbum(theAlbum);
        Albums.add(localAlbum);
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      // System.out.println("Error: " + e.getMessage());
    }

    return Albums;
  }

  public ArrayList<Song> searchSong(String songTitle, int limit) {
    ArrayList<Song> Songs = new ArrayList<>();
    SearchTracksRequest searchTracksRequest = theApiConnection.getConnection().searchTracks(songTitle)
        .limit(5)
        .build();
    try {
      final Paging<Track> trackPaging = searchTracksRequest.execute();
      Track[] spotifySongs = trackPaging.getItems();
      for (Track theSong : spotifySongs) {
        Song localSong = convertTrack(theSong);
        Songs.add(localSong);
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      // System.out.println("Error: " + e.getMessage());
    }
    return Songs;
  }

  private Album convertAlbum(AlbumSimplified spotifyAlbum) {
    Artist theArtist = convertArtist(spotifyAlbum.getArtists()[0]);
    Album localAlbum = new Album(spotifyAlbum.getId(), spotifyAlbum.getName(), theArtist);
    return localAlbum;
  }

  private Song convertTrack(se.michaelthelin.spotify.model_objects.specification.Track spotifyTrack) {
    Artist theArtist = convertArtist(spotifyTrack.getArtists()[0]);
    Album theAlbum = convertAlbum(spotifyTrack.getAlbum());
    Song localSong = new Song(spotifyTrack.getId(), spotifyTrack.getName(), theArtist, theAlbum);
    return localSong;
  }

  private Artist convertArtist(se.michaelthelin.spotify.model_objects.specification.Artist spotifyArtist) {
    Artist localArtist = new Artist(spotifyArtist.getId(), spotifyArtist.getName());
    return localArtist;
  }

  private Artist convertArtist(se.michaelthelin.spotify.model_objects.specification.ArtistSimplified spotifyArtist) {
    Artist localArtist = new Artist(spotifyArtist.getId(), spotifyArtist.getName());
    return localArtist;
  }
}