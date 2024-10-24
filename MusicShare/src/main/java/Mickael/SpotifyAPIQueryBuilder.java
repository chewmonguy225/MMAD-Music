package Mickael;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.search.simplified.*;


public class SpotifyAPIQueryBuilder extends AbstractAPIQueryBuilder {
  SpotifyAPIConnection theApiConnection = new SpotifyAPIConnection();

  public ArrayList<Album> searchAlbum(String albumTitle) {
    ArrayList<Album> converted = new ArrayList<Album>();
    
    SearchAlbumsRequest searchAlbumsRequest = theApiConnection.getConnection().searchAlbums(albumTitle)
        .limit(5)
        .build();
    try {
      Paging<AlbumSimplified> albumSimplifiedPaging = searchAlbumsRequest.execute();
      AlbumSimplified[] spotifyAlbums = albumSimplifiedPaging.getItems();
      for(AlbumSimplified theAlbum : spotifyAlbums){
        converted.add(convertAlbum(theAlbum));
      }

    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return converted;
  }

  public ArrayList<Song> searchSong(String songTitle){
    ArrayList<Song> converted = new ArrayList<Song>();
    
    SearchTracksRequest searchTracksRequest = theApiConnection.getConnection().searchTracks(songTitle)
        .limit(5)
        .build();
    try {
      Paging<Track> albumSimplifiedPaging = searchTracksRequest.execute();
      Track[] tracks = albumSimplifiedPaging.getItems();
      for(Track song : tracks){
        converted.add(convertSong(song));
      }

    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return converted;
  }

  public ArrayList<Artist> searchArtists(String artistName){
    ArrayList<Artist> converted = new ArrayList<Artist>();
    
    SearchArtistsRequest searchArtistsRequest = theApiConnection.getConnection().searchArtists(artistName)
        .limit(5)
        .build();
    try {
      Paging<se.michaelthelin.spotify.model_objects.specification.Artist> albumSimplifiedPaging = searchArtistsRequest.execute();
      se.michaelthelin.spotify.model_objects.specification.Artist[] artists = albumSimplifiedPaging.getItems();

      for(se.michaelthelin.spotify.model_objects.specification.Artist theArtist : artists){
        converted.add(convertArtist(theArtist));
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return converted;
  }

  private Album convertAlbum(AlbumSimplified album){
    Album localAlbum = new Album(album.getId(), album.getName(), new Artist(album.getArtists()[0].getId(), album.getArtists()[0].getName(), null), null);
    return localAlbum;
  }

  private Song convertSong(Track track){
    Song localSong = new Song(track.getId(), track.getName(), new Artist(track.getArtists()[0].getId(), track.getArtists()[0].getName(), null), convertAlbum(track.getAlbum()), track.getTrackNumber());
    return localSong;
  }

  private Artist convertArtist(se.michaelthelin.spotify.model_objects.specification.Artist artist){
    Artist localArtist = new Artist(artist.getId(), artist.getName(), null);
    return localArtist;
  }
}
