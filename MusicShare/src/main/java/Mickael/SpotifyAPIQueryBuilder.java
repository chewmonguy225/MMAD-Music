package Mickael;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.search.simplified.*;


public class SpotifyAPIQueryBuilder extends AbstractAPIQueryBuilder {
  SpotifyAPIConnection theApiConnection = new SpotifyAPIConnection();

  public AlbumSimplified[] searchAlbum(String albumTitle) {
    AlbumSimplified[] albums = null;
    
    SearchAlbumsRequest searchAlbumsRequest = theApiConnection.getConnection().searchAlbums(albumTitle)
        .limit(5)
        .build();
    try {
      Paging<AlbumSimplified> albumSimplifiedPaging = searchAlbumsRequest.execute();

      albums = albumSimplifiedPaging.getItems();
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return albums;
  }

  public Track[] searchSong(String songTitle){
    Track[] tracks = null;
    
    SearchTracksRequest searchTracksRequest = theApiConnection.getConnection().searchTracks(songTitle)
        .limit(5)
        .build();
    try {
      Paging<Track> albumSimplifiedPaging = searchTracksRequest.execute();

      tracks = albumSimplifiedPaging.getItems();
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return tracks;
  }

  public Artist[] searchArtists(String artistName){
    Artist[] artists = null;
    
    SearchArtistsRequest searchArtistsRequest = theApiConnection.getConnection().searchArtists(artistName)
        .limit(5)
        .build();
    try {
      Paging<Artist> albumSimplifiedPaging = searchArtistsRequest.execute();

      artists = albumSimplifiedPaging.getItems();
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return artists;
  }
}
