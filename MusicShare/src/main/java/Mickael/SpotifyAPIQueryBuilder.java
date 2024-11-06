package Mickael;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.*;

public class SpotifyAPIQueryBuilder extends AbstractAPIQueryBuilder {
  SpotifyAPIConnection theApiConnection = new SpotifyAPIConnection();
  
  //Method to find the specific album that was chosen using the spotifyID
  public Album searchAlbum(String ID) {
    Album theAlbum = null;
    GetAlbumRequest getAlbumRequest = theApiConnection.getConnection().getAlbum(ID)
        .build();
    try {
      se.michaelthelin.spotify.model_objects.specification.Album album = getAlbumRequest.execute();
      theAlbum = convertAlbum(album);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return theAlbum;
  }

  //method to get a list of list of string that contain the albums Title (index: 0), Artist(index: 1), album's spotifyID (index:2)
  public ArrayList<ArrayList<String>> listAlbums(String albumTitle) {
    ArrayList<ArrayList<String>> Albums = new ArrayList<ArrayList<String>>();

    SearchAlbumsRequest searchAlbumsRequest = theApiConnection.getConnection().searchAlbums(albumTitle)
        .limit(5)
        .build();
    try {
      Paging<AlbumSimplified> albumSimplifiedPaging = searchAlbumsRequest.execute();
      AlbumSimplified[] spotifyAlbums = albumSimplifiedPaging.getItems();
      for (AlbumSimplified theAlbum : spotifyAlbums) {
        ArrayList<String> info = new ArrayList<String>();
        info.add(theAlbum.getName());
        info.add(theAlbum.getArtists()[0].getName());
        info.add(theAlbum.getId());

        Albums.add(info);
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return Albums;
  }

  //converts the Spotify's album object into local album version
  private Album convertAlbum(se.michaelthelin.spotify.model_objects.specification.Album album) {
    Artist artist = new Artist(album.getArtists()[0].getId(), album.getArtists()[0].getName(), null);
    Song[] trackList = null;
    Album localAlbum = new Album(album.getId(), album.getName(), artist, trackList);
    return localAlbum;
  }

  //converts the Spotify's SimplifiedAlbum object into local album version
  private Album convertAlbum(AlbumSimplified album) {
    Artist artist = new Artist(album.getArtists()[0].getId(), album.getArtists()[0].getName(), null);
    Song[] trackList = null;
    Album localAlbum = new Album(album.getId(), album.getName(), artist, trackList);
    return localAlbum;
  }

}