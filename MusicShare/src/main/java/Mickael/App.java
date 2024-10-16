package Mickael;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;

import java.io.IOException;

public class App {
  String clientID = "39e68d74ea964fe8b12e4d03f28c817c";
  String clientSecret = "2e4bd1398d6e4e0aa43ed25e0116b4bf";

  SpotifyApi spotifyApi = new SpotifyApi.Builder()
      .setClientId(clientID)
      .setClientSecret(clientSecret)
      .build();

  ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
      .build();

  void accessToken() {
    try {
      // Execute the request synchronous
      final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
      spotifyApi.setAccessToken(clientCredentials.getAccessToken());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }

  void searchAlbum(SpotifyApi api) {
    SearchAlbumsRequest searchAlbumsRequest = api.searchAlbums("Let It Be")
        .limit(5)
        .build();

    try {
      Paging<AlbumSimplified> albumSimplifiedPaging = searchAlbumsRequest.execute();

      AlbumSimplified[] albums = albumSimplifiedPaging.getItems();

      for(int i = 0; i < 5; i++){
        System.out.println("Title: " + albums[i].getName() + " By " + albums[i].getArtists()[0].getName() + " Cover: " + albums[i].getImages()[1].getUrl());
      }

    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  void getAlbum(SpotifyApi api) {
    GetAlbumRequest getAlbumRequest = api.getAlbum("5zT1JLIj9E57p3e1rFm9Uq")
        .build();
    try {
      final Album album = getAlbumRequest.execute();

      System.out.println("Name: " + album.getName());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    App app = new App();
    System.out.println(app.spotifyApi.getAccessToken());
    app.accessToken();
    System.out.println(app.spotifyApi.getAccessToken());
    app.searchAlbum(app.spotifyApi);
    app.getAlbum(app.spotifyApi);
  }
}
